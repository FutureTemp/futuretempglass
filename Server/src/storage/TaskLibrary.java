package storage;

import java.util.List;

import workflow.Task;

public abstract class TaskLibrary{

	/**
	 * Gets a test with the task ID given
	 * @param taskId
	 * @return Task, null if not found
	 */
	public abstract Task getTask(String taskId);
	
	/**
	 * Gets all the tasks in storage
	 * @return List of Tasks
	 */
	public abstract List<Task> getAllTasks();
	
	/**
	 * Gets all the tasks with the assignee specified
	 * @param assignee
	 * @return List of Tasks
	 */
	public abstract List<Task> getTasksForAssignee(String assignee);
	
	/**
	 * Adds the task given into storage, and populates the
	 * object with a task ID
	 * @param task
	 * @return true if successful, false otherwise
	 */
	public abstract boolean addTask(Task task);
	
	/**
	 * Updates a task in storage to mimic the task given. The
	 * task given must contain the task ID of the task to update
	 * @param task
	 * @return true if successful, false otherwise
	 */
	public abstract boolean updateTask(Task task);
	
	/**
	 * Removes a task from storage with the specified task ID
	 * @param taskId
	 * @return true if successful, false otherwise
	 */
	public abstract boolean removeTask(String taskId);
	
}
