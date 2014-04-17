package utils;

import java.util.List;

import notifications.Notification;
import core.Application;

public class NotificationUtils{

	/**
	 * Adds a notification to the notification library. throws exception if not
	 * able to add the notification
	 * 
	 * @param notification
	 * @throws Exception
	 */
	public static void addNotification(Notification notification)
			throws Exception
	{
		notification.setNotificationId(StringUtils
				.getRandomStringOfLettersAndNumbers(8));
		if(!Application.getNotificationLibrary().addNotification(notification))
		{
			throw new Exception("Failed to add notification to library");
		}
	}

	/**
	 * Gets the list of all the notifications associated with the given username
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static List<Notification> getAllUsersNotifications(String username)
			throws Exception
	{
		List<Notification> notifications = Application.getNotificationLibrary()
				.getAllNotifications(username);
		if(notifications == null)
		{
			throw new Exception("Could not retrieve notifications for ["
					+ username + "]");
		}
		return notifications;
	}

	/**
	 * Gets the list of all the unread notifications associated with the given
	 * username
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static List<Notification> getAllUsersUnread(String username)
			throws Exception
	{
		List<Notification> notifications = Application.getNotificationLibrary()
				.getUnreadNotifications(username);
		if(notifications == null)
		{
			throw new Exception("Could not retrieve unread notifications for ["
					+ username + "]");
		}
		return notifications;
	}

	/**
	 * Sets the notification attribute 'read' to true, of the notification
	 * with the given ID
	 * @param notificationId
	 * @param username
	 * @throws Exception
	 */
	public static void readNotification(String notificationId, String username)
			throws Exception
	{
		if(StringUtils.isEmpty(notificationId))
		{
			throw new Exception("Invalid Notification ID");
		}
		if(StringUtils.isEmpty(username))
		{
			throw new Exception("Invalid Username");
		}
		if(!Application.getNotificationLibrary().readNotification(
				notificationId, username))
		{
			throw new Exception("Could not set notification [" + notificationId
					+ "] for [" + username + "] as read");
		}
	}
	
}
