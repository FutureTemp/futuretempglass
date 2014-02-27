package utils;

import java.util.List;

import storage.TaskLibrary;
import workflow.Task;
import core.Application;

public class TaskUtils{

	/**
	 * Gets a list of all the tasks from storage
	 * @return List of Tasks
	 */
	public static List<Task> getAllTasks()
	{
		return Application.getTaskLibrary().getAllTasks();
	}

	/**
	 * Gets a task from storage with the provided task ID
	 * @param taskId
	 * @return Task
	 * @throws Exception
	 */
	public static Task getTask(String taskId) throws Exception
	{
		Task task = Application.getTaskLibrary().getTask(taskId);
		if(task == null)
		{
			throw new Exception("Task with ID [" + taskId + "] was not found");
		}
		return task;
	}

	/**
	 * Adds the task provided into storage and populates it with a valid ID
	 * @param task
	 * @throws Exception
	 */
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

	/**
	 * Updates a task in storage to match the task provided. The
	 * task provided must contain the ID of the task to update
	 * @param task
	 * @throws Exception
	 */
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

	/**
	 * Gets all the tasks with the assignee provided
	 * @param assignee
	 * @return List of Tasks
	 * @throws Exception
	 */
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
	
	/**
	 * Deletes the task with the given task ID
	 * @param taskId
	 * @throws Exception
	 */
	public static void removeTask(String taskId) throws Exception
	{
		Application.getTaskLibrary().removeTask(taskId);
	}
}
