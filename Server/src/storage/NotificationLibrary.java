package storage;

import java.util.List;

import notifications.Notification;

public abstract class NotificationLibrary{

	/**
	 * Gets a list of the notifications for the specified user
	 * @param username
	 * @return
	 */
	public abstract List<Notification> getAllNotifications(String username);
	
	/**
	 * Gets a list of the unread notifications for the specified user
	 * @param username
	 * @return
	 */
	public abstract List<Notification> getUnreadNotifications(String username);
	
	/**
	 * Adds a notification
	 * @param notification
	 * @return
	 */
	public abstract boolean addNotification(Notification notification);
	
	/**
	 * Marks the notification with the specified ID as read for the username given
	 * @param notificationId
	 * @param username
	 * @return
	 */
	public abstract boolean readNotification(String notificationId, String username);
}
