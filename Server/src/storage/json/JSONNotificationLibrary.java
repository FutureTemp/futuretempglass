package storage.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import notifications.Notification;
import storage.NotificationLibrary;
import utils.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONNotificationLibrary extends NotificationLibrary{
	
	private static final String NOTIFICATIONS_PATH = "jsonfiles/notifications.txt";

	private HashMap<String, List<Notification>> notifications = null;
	
	public JSONNotificationLibrary()
	{
		init();
	}
	
	private void init()
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			notifications = mapper.readValue(new File(NOTIFICATIONS_PATH),
					new TypeReference<HashMap<String, List<Notification>>>(){});
		}
		catch(FileNotFoundException fnfe)
		{
			File file = new File(NOTIFICATIONS_PATH);
			try
			{
				file.createNewFile();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(notifications == null)
		{
			notifications = new HashMap<String, List<Notification>>();
		}
	}

	private void save()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try
		{
			mapper.writeValue(new File(NOTIFICATIONS_PATH), notifications);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Notification> getAllNotifications(String username)
	{
		if(StringUtils.isEmpty(username))
		{
			return null;
		}
		List<Notification> usersNotifications = notifications.get(username);
		if(usersNotifications == null)
		{
			usersNotifications = new ArrayList<Notification>();
			notifications.put(username, usersNotifications);
		}
		return usersNotifications;
	}

	@Override
	public List<Notification> getUnreadNotifications(String username)
	{
		List<Notification> allNotifications = getAllNotifications(username);
		if(allNotifications == null)
		{
			return null;
		}
		List<Notification> unread = new ArrayList<Notification>();
		for(Notification notification: allNotifications)
		{
			if(!notification.isRead())
			{
				unread.add(notification);
			}
		}
		return unread;
	}
	
	public boolean addNotification(Notification notification)
	{
		if(StringUtils.isEmpty(notification.getUser()))
		{
			return false;
		}
		List<Notification> usersNotifications = notifications.get(notification.getUser());
		if(usersNotifications == null)
		{
			usersNotifications = new ArrayList<Notification>();
			notifications.put(notification.getUser(), usersNotifications);
		}
		usersNotifications.add(0, notification);
		save();
		return true;
	}

	@Override
	public boolean readNotification(String notificationId, String username)
	{
		if(StringUtils.isEmpty(notificationId) || StringUtils.isEmpty(username))
		{
			return false;
		}
		List<Notification> notifications = this.notifications.get(username);
		for(Notification notification: notifications)
		{
			if(notificationId.equals(notification.getNotificationId()))
			{
				notification.setRead(true);
				save();
				return true;
			}
		}
		return false;
	}

}
