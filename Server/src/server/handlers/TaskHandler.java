package server.handlers;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import server.Server;
import server.objects.Account;
import utils.StringUtils;
import utils.TaskUtils;
import workflow.Task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public class TaskHandler extends ServerHandler{

	public TaskHandler(Server server)
	{
		super(server);
	}

	private static String context = "/tasks";

	public static String getContext()
	{
		return context;
	}

	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		sendHeader(ex);
		String taskId = getParameters(ex).get("taskId");
		String assignee = getParameters(ex).get("assignee");
		if(!StringUtils.isEmpty(taskId))
		{
			Task task = TaskUtils.getTask(taskId);
			sendResponse(task, ex);
		}
		else if(!StringUtils.isEmpty(assignee))
		{
			List<Task> assignedTasks = TaskUtils.getTasksAssignedTo(assignee);
			sendResponse(assignedTasks, ex);
		}
		else
		{
			List<Task> allTasks = TaskUtils.getAllTasks();
			sendResponse(allTasks, ex);
		}
	}

	@Override
	protected void onPost(HttpExchange ex) throws Exception
	{
		super.onPost(ex);
		sendHeader(ex);
		Task task = null;
		List<Task> tasks = null;
		try
		{
			task = getObjectFromRequestBody(Task.class, ex);
		}
		catch(IOException e)
		{
			ObjectMapper mapper = new ObjectMapper();
			String requestData = URLDecoder.decode(getRequestData(ex), "UTF-8");
			tasks = mapper.readValue(requestData,
					new TypeReference<List<Task>>(){});
		}
		Action action = Action.getAction(getUrlParameters(ex).get("action"));
		if(task != null)
		{
			if(action == null)
			{
				throw new Exception("Incorrect action");
			}

			switch (action)
			{
			case ADD:
				task.setCreator(getSession(ex).getAccount().getUsername());
				TaskUtils.addTask(task);
				break;

			case EDIT:
				onEditTask(task, ex);
				break;

			case COMPLETE:
				onCompleteTask(task.getTaskId(), ex);
				break;
			}

			sendResponse(TaskUtils.getTask(task.getTaskId()), ex);
		}
		else
		{
			// Add/update list of tasks list of tasks
			throw new Exception(
					"Adding multiple tasks at once is not yet implemented");
		}
	}

	@Override
	protected void onDelete(HttpExchange ex) throws Exception
	{
		super.onPut(ex);
		sendHeader(ex);
		String taskId = getRequestData(ex);
		Task existing = TaskUtils.getTask(taskId);
		Account account = getSession(ex).getAccount();
		if(account.isAdmin()
				|| account.getUsername().equals(existing.getCreator()))
		{
			TaskUtils.removeTask(taskId);
			sendResponse("Deleted", ex);
		}
		else
		{
			throw new Exception("You do not have permission to delete task ["
					+ taskId + "]");
		}
	}

	private void onEditTask(Task task, HttpExchange ex) throws Exception
	{
		Task existing = TaskUtils.getTask(task.getTaskId());
		if(getSession(ex).getAccount().isAdmin()
				|| getSession(ex).getAccount().getUsername()
						.equals(existing.getCreator()))
		{
			TaskUtils.updateTask(task);
		}
		else
		{
			throw new Exception("You do not have permission to edit task ["
					+ task.getTaskId() + "]");
		}
	}

	private void onCompleteTask(String taskId, HttpExchange ex)
			throws Exception
	{
		Task existing = TaskUtils.getTask(taskId);
		if(!getSession(ex).getAccount().getUsername()
				.equals(existing.getAssignee()))
		{
			throw new Exception("Only the assignee of task [" + taskId
					+ "] can complete it");
		}
		TaskUtils.completeTask(taskId);
	}

	private enum Action{
		ADD("add"), EDIT("edit"), COMPLETE("complete");

		private String description;

		private Action(String description)
		{
			this.description = description;
		}

		public String getDescription()
		{
			return this.description;
		}

		public static Action getAction(String description)
		{
			for(Action action: values())
			{
				if(description.equals(action.getDescription()))
				{
					return action;
				}
			}
			return null;
		}
	}
}
