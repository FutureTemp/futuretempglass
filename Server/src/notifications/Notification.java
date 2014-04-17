package notifications;

public class Notification{

	private String notificationId;

	private String user;

	private String message;

	private String type;

	private boolean read = false;

	public Notification()
	{
	}

	/**
	 * @return the notificationId
	 */
	public String getNotificationId()
	{
		return notificationId;
	}

	/**
	 * @param notificationId
	 *            the notificationId to set
	 */
	public void setNotificationId(String notificationId)
	{
		this.notificationId = notificationId;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getUser()
	{
		return this.user;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return this.type;
	}

	/**
	 * @return the read
	 */
	public boolean isRead()
	{
		return read;
	}

	/**
	 * @param read
	 *            the read to set
	 */
	public void setRead(boolean read)
	{
		this.read = read;
	}

}
