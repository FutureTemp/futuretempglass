package notifications;

import workflow.Task;

public class TaskNotification extends Notification{

	public TaskNotification(Task task)
	{
		setUser(task.getAssignee());
		setType("task");
		setMessage("\"" + task.getTitle() + "\" was assigned to you.");
	}

}
