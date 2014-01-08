package utils;

import java.util.List;

import workflow.Task;
import core.Application;

public class TaskUtils{

	public static List<Task> getAllTasks()
	{
		return Application.getTaskLibrary().getAllTasks();
	}

	public static Task getTask(String taskId) throws Exception
	{
		Task task = Application.getTaskLibrary().getTask(taskId);
		if(task == null)
		{
			throw new Exception("Task with ID [" + taskId + "] was not found");
		}
		return task;
	}

	public static void addTask(Task task) throws Exception
	{
		if(!StringUtils.isEmpty(task.getTaskId())
				&& Application.getTaskLibrary().getTask(task.getTaskId()) == null)
		{
			throw new Exception("Task with ID [" + task.getTaskId()
					+ "] already exists");
		}
		Application.getTaskLibrary().addTask(task);
	}

	public static void updateTask(Task task) throws Exception
	{
		if(StringUtils.isEmpty(task.getTaskId()))
		{
			throw new Exception("Not a valid task ID");
		}
		if(!Application.getTaskLibrary().updateTask(task))
		{
			throw new Exception("Task could not be updated");
		}
	}

	public static List<Task> getTasksAssignedTo(String assignee)
			throws Exception
	{
		List<Task> assignedTasks = Application.getTaskLibrary()
				.getTasksForAssignee(assignee);
		if(assignedTasks == null)
		{
			throw new Exception(
					"Error occured while gettings tasks for assignee ["
							+ assignee + "]");
		}
		return assignedTasks;
	}
}
