package server.handlers;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import utils.StringUtils;
import utils.TaskUtils;
import workflow.Task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public class TaskHandler extends ServerHandler{

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
		if(task != null)
		{
			if(StringUtils.isEmpty(task.getTaskId()))
			{
				// Add new task
				TaskUtils.addTask(task);
			}
			else
			{
				// Update task
				TaskUtils.updateTask(task);
			}
			sendResponse(task, ex);
		}
		else
		{
			// Add/update list of tasks list of tasks
			throw new Exception("Adding multiple tasks at once is not yet implemented");
		}
	}
	
	@Override
	protected void onDelete(HttpExchange ex) throws Exception
	{
		super.onPut(ex);
		sendHeader(ex);
		String taskId = getRequestData(ex);
		TaskUtils.removeTask(taskId);
		sendResponse("Deleted", ex);
	}
	
}
