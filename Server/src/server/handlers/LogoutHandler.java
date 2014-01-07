package server.handlers;

import java.io.IOException;
import java.util.List;

import server.Server;
import server.Session;

import com.sun.net.httpserver.HttpExchange;

public class LogoutHandler extends ServerHandler{

	private static String context = "/logout";
	
	public static String getContext()
	{
		return context;
	}
	
	@Override
	public void onGet(HttpExchange ex) throws IOException
	{
		List<Session> activeSessions = Server.getActiveSessions();
		sendHeader(ex);
		for(int i = 0; i < activeSessions.size(); i++)
		{
			if(activeSessions.get(i).isMatchingAddress(ex))
			{
				activeSessions.remove(i);
				sendResponse("Logout Successful", ex);
				finish(ex);
				return;
			}
		}
		sendResponse("Error trying to logout", ex);
		finish(ex);
	}
}
