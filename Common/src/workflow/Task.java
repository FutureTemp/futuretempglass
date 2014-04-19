package workflow;

import java.util.Date;

public class Task{

	private String taskId = null;

	private String title = "";
	
	private String creator = null;
	
	private String assignee = "";

	private String description = "";

	private Date dueDate = null;
	
	private boolean complete = false;

	/**
	 * @return the taskId
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee()
	{
		return assignee;
	}

	/**
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(String assignee)
	{
		this.assignee = assignee;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate()
	{
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	/**
	 * @return the complete
	 */
	public boolean isComplete()
	{
		return complete;
	}

	/**
	 * @param complete the complete to set
	 */
	public void setComplete(boolean complete)
	{
		this.complete = complete;
	}

	/**
	 * @return the creator
	 */
	public String getCreator()
	{
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}

}
