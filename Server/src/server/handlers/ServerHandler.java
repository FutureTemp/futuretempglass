package server.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import server.Server;
import server.Session;
import utils.HTTPUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class ServerHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange ex) throws IOException
	{
		try
		{
			if(!authenticate(ex))
			{
				sendHeader(ex);
				sendResponse("MUST LOGIN", ex);
				finish(ex);
				return;
			}
			if("GET".equals(ex.getRequestMethod()))
			{
				onGet(ex);
			}
			else if("POST".equals(ex.getRequestMethod()))
			{
				onPost(ex);
			}
			else if("PUT".equals(ex.getRequestMethod()))
			{
				onPut(ex);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(!"true".equalsIgnoreCase(getParameters(ex).get("headerSent")))
			{
				sendHeader(ex);
			}
			sendResponse(e.toString(), ex);
		}
		finally
		{
			finish(ex);
		}
	}

	private static Session getActiveSession(HttpExchange ex)
	{
		List<Session> activeSessions = Server.getActiveSessions();
		for(Session session: activeSessions)
		{
			if(session.isMatchingAddress(ex))
			{
				return session;
			}
		}
		return null;
	}

	protected static Session getActiveSession(String username)
	{
		List<Session> activeSessions = Server.getActiveSessions();
		for(Session session: activeSessions)
		{
			if(session.getAccount().getUsername().equals(username))
			{
				return session;
			}
		}
		return null;
	}

	protected void finish(HttpExchange ex) throws IOException
	{
		ex.setAttribute("requestData", null);
		ex.getRequestBody().close();
		ex.getResponseBody().flush();
		ex.getResponseBody().close();
		ex.close();
	}

	protected void onGet(HttpExchange ex) throws Exception
	{

	}

	protected void onPost(HttpExchange ex) throws Exception
	{

	}

	protected void onPut(HttpExchange ex) throws Exception
	{

	}

	protected boolean authenticate(HttpExchange ex)
	{
		ex.setAttribute("session", getActiveSession(ex));
		return ex.getAttribute("session") != null;
	}

	protected Session getSession(HttpExchange ex)
	{
		return (Session)ex.getAttribute("session");
	}
	
	protected void sendHeader(HttpExchange ex) throws IOException
	{
		ex.sendResponseHeaders(200, 0);
		ex.setAttribute("headerSent", "true");
	}

	protected void sendResponse(String response, HttpExchange ex)
			throws IOException
	{
		ex.getResponseBody().write(response.getBytes());
	}

	protected void sendResponse(Object object, HttpExchange ex)
			throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		sendResponse(mapper.writeValueAsString(object), ex);
	}

	protected String getRequestData(HttpExchange ex) throws IOException
	{
		String requestData = (String)ex.getAttribute("requestData");
		if(requestData != null)
		{
			return requestData;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				ex.getRequestBody()));
		StringBuilder builder = new StringBuilder();
		String line = reader.readLine();
		while (line != null)
		{
			builder.append(line);
			line = reader.readLine();
		}
		requestData = builder.toString();
		ex.setAttribute("requestData", requestData);
		return requestData;
	}

	@SuppressWarnings("unchecked")
	protected HashMap<String, String> getParameters(HttpExchange ex) throws IOException
	{
		String parametersString = "";
		if("GET".equals(ex.getRequestMethod()) && ex.getRequestURI().getQuery() != null)
		{
			parametersString = ex.getRequestURI().getQuery();
		}
		else if("POST".equals(ex.getRequestMethod()))
		{
			parametersString = getRequestData(ex);
		}
		ex.setAttribute("parameters", HTTPUtils.parameterStringToHashMap(parametersString));
		return (HashMap<String, String>)ex.getAttribute("parameters");
	}

	protected boolean isAdmin(HttpExchange ex)
	{
		return getSession(ex).getAccount().isAdmin();
	}
	
	protected <T> T getObjectFromRequestBody(Class<T> clazz, HttpExchange ex)
			throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(getRequestData(ex), clazz);
	}

}
