package storage.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import storage.TaskLibrary;
import utils.FileUtils;
import utils.StringUtils;
import workflow.Task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTaskLibrary extends TaskLibrary{

	private static final String TASKS_PATH = "jsonfiles/tasks.txt";

	private List<Task> tasks;

	private void save() throws Exception
	{
		FileUtils.createDirectoryAndFile(TASKS_PATH);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(TASKS_PATH), tasks);
	}

	@Override
	public Task getTask(String taskId)
	{
		for(Task task: getAllTasks())
		{
			if(task.getTaskId().equals(taskId))
			{
				return task;
			}
		}
		return null;
	}

	@Override
	public List<Task> getAllTasks()
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			tasks = mapper.readValue(new File(TASKS_PATH), new TypeReference<List<Task>>(){});
		}
		catch(IOException e)
		{
			tasks = new ArrayList<Task>();
		}
		if(tasks == null)
		{
			tasks = new ArrayList<Task>();
		}
		return tasks;
	}

	@Override
	public List<Task> getTasksForAssignee(String assignee)
	{
		List<Task> tasks = new ArrayList<Task>();
		for(Task task: getAllTasks())
		{
			if(task.getAssignee().equals(assignee))
			{
				tasks.add(task);
			}
		}
		return tasks;
	}

	@Override
	public boolean addTask(Task task)
	{
		if(StringUtils.isEmpty(task.getTaskId()))
		{
			task.setTaskId(getNewTaskID());
		}
		getAllTasks().add(task);
		try
		{
			save();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeTask(String taskId)
	{
		Task taskToRemove = null;
		for(Task task: getAllTasks())
		{
			if(task.getTaskId().equals(taskId))
			{
				taskToRemove = task;
				break;
			}
		}
		if(taskToRemove == null)
		{
			return false;
		}
		tasks.remove(taskToRemove);
		try
		{
			save();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateTask(Task task)
	{
		List<Task> allTasks = getAllTasks();
		for(Task existing: allTasks)
		{
			if(existing.getTaskId().equals(task.getTaskId()))
			{
				allTasks.remove(existing);
				allTasks.add(task);
				try
				{
					save();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public String getNewTaskID()
	{
		return StringUtils.getRandomStringOfLettersAndNumbers(6);
	}

}
