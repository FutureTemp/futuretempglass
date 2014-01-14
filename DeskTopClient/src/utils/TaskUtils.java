package utils;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.RESTURL;
import utils.HTTPUtils;
import workflow.Task;

public class TaskUtils{

	public List<Task> getAllTasks() throws Exception
	{
		String response = HTTPUtils.doGetRequest(RESTURL.TASKS.getUrl(), null,
				null);
		List<Task> tasks = null;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			tasks = mapper.readValue(response, new TypeReference<List<Task>>(){});
		}
		catch(Exception e)
		{
			throw new Exception(
					"Exception thrown while mapping to a List of Tasks with String ["
							+ response + "]");
		}
		return tasks;
	}

}
