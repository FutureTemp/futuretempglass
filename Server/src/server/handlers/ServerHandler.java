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

	/**
	 * Gets the active Session object associated with the IP given
	 * in the HttpExchange object
	 * @param ex
	 * @return Session
	 */
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

	/**
	 * Gets the acive Session object associated with the given username
	 * @param username
	 * @return Session
	 */
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

	/**
	 * finishes the handling of a request by closing all the streams
	 * and removing certain HttpExchange attributes.
	 * @param ex
	 * @throws IOException
	 */
	protected void finish(HttpExchange ex) throws IOException
	{
		ex.setAttribute("requestData", null);
		ex.getRequestBody().close();
		ex.getResponseBody().flush();
		ex.getResponseBody().close();
		ex.close();
	}

	/**
	 * Is called when handling a GET request
	 * @param ex
	 * @throws Exception
	 */
	protected void onGet(HttpExchange ex) throws Exception
	{

	}

	/**
	 * Is called when handling a POST request
	 * @param ex
	 * @throws Exception
	 */
	protected void onPost(HttpExchange ex) throws Exception
	{

	}

	/**
	 * Is called when handling a PUT request
	 * @param ex
	 * @throws Exception
	 */
	protected void onPut(HttpExchange ex) throws Exception
	{

	}

	/**
	 * Authenticates the HttpExchange object by checking for
	 * an active session associated with its IP. If found
	 * it is put into the HttpExchange attribute "session".
	 * @param ex
	 * @return true if authenticated correctly, and false otherwise
	 */
	protected boolean authenticate(HttpExchange ex)
	{
		ex.setAttribute("session", getActiveSession(ex));
		return ex.getAttribute("session") != null;
	}

	/**
	 * Gets the session associated with the HttpExchange by 
	 * grabbing it from its attributes, under "session"
	 * @param ex
	 * @return Session
	 */
	protected Session getSession(HttpExchange ex)
	{
		return (Session)ex.getAttribute("session");
	}
	
	/**
	 * Sends the header with a 200 code and sets an attribute
	 * to indicate that it has been sent
	 * @param ex
	 * @throws IOException
	 */
	protected void sendHeader(HttpExchange ex) throws IOException
	{
		ex.sendResponseHeaders(200, 0);
		ex.setAttribute("headerSent", "true");
	}

	/**
	 * Sends the string provided as the response
	 * @param response
	 * @param ex
	 * @throws IOException
	 */
	protected void sendResponse(String response, HttpExchange ex)
			throws IOException
	{
		ex.getResponseBody().write(response.getBytes());
	}

	/**
	 * Sends the object provided in JSON format as the response
	 * @param object
	 * @param ex
	 * @throws IOException
	 */
	protected void sendResponse(Object object, HttpExchange ex)
			throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		sendResponse(mapper.writeValueAsString(object), ex);
	}

	/**
	 * Returns the String of data in the request body
	 * @param ex
	 * @return requestData
	 * @throws IOException
	 */
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

	/**
	 * Gets the request parameters from the HttpExchange attributes
	 * if it already exists, otherwise it generates the HashMap containing
	 * the parameters and stores it in the attributes.
	 * @param ex
	 * @return HashMap containing the request parameters
	 * @throws IOException
	 */
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

	/**
	 * Checks whether the user sending the HttpExchange is an
	 * admin or not.
	 * @param ex
	 * @return whether the user is an admin or not
	 */
	protected boolean isAdmin(HttpExchange ex)
	{
		return getSession(ex).getAccount().isAdmin();
	}
	
	/**
	 * Assumes the request data is in JSON form and returns that object, 
	 * given the class and the HttpExchange object containing the request.
	 * @param clazz
	 * @param ex
	 * @return
	 * @throws IOException
	 */
	protected <T> T getObjectFromRequestBody(Class<T> clazz, HttpExchange ex)
			throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(getRequestData(ex), clazz);
	}

}
