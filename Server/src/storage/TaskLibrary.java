package storage;

import java.util.List;

import workflow.Task;

public abstract class TaskLibrary{

	public abstract Task getTask(String taskId);
	
	public abstract List<Task> getAllTasks();
	
	public abstract List<Task> getTasksForAssignee(String assignee);
	
	public abstract boolean addTask(Task task);
	
	public abstract boolean updateTask(Task task);
	
	public abstract boolean removeTask(String taskId);
	
}
