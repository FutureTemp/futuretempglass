package server.handlers;

import java.util.HashMap;
import java.util.List;

import notifications.Notification;
import server.Server;
import utils.NotificationUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public class NotificationHandler extends ServerHandler{

	private static final String context = "/notifications";

	public NotificationHandler(Server server)
	{
		super(server);
	}

	public static String getContext()
	{
		return context;
	}

	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		String username = getSession(ex).getAccount().getUsername();
		boolean read = !"false".equals(getParameters(ex).get("read"));
		List<Notification> notifications = null;
		if(read)
		{
			notifications = NotificationUtils
					.getAllUsersNotifications(username);
		}
		else
		{
			notifications = NotificationUtils.getAllUsersUnread(username);
		}
		sendHeader(ex);
		sendResponse(notifications, ex);
		finish(ex);
	}

	@Override
	protected void onPost(HttpExchange ex) throws Exception
	{
		super.onPost(ex);
		HashMap<String, String> parameters = new ObjectMapper().readValue(
				getRequestData(ex),
				new TypeReference<HashMap<String, String>>(){});
		String action = parameters.get("action");
		if("read".equals(action))
		{
			String notificationId = parameters.get("notificationId");
			NotificationUtils.readNotification(notificationId, getSession(ex)
					.getAccount().getUsername());
		}
		sendHeader(ex);
		sendResponse(action, ex);
		finish(ex);
	}
}
