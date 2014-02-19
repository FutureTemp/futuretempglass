package server.handlers;

import java.io.IOException;
import java.util.HashMap;

import utils.AccountUtils;
import utils.StringUtils;

import com.sun.net.httpserver.HttpExchange;

public class TokenHandler extends ServerHandler{

	private static String context = "/token";
	
	public static String getContext()
	{
		return context;
	}
	
	@Override
	public void handle(HttpExchange ex) throws IOException
	{
		ex.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
		HashMap<String, String> parameters = getParameters(ex);
		sendHeader(ex);
		if(StringUtils.isEmpty(parameters.get("username")))
		{
			sendResponse("Must include username", ex);
			finish(ex);
			return;
		}
		String token = AccountUtils.getToken(parameters.get("username"));
		if(token == null)
		{
			sendResponse("Invalid Username", ex);
			finish(ex);
			return;
		}
		sendResponse("{\"token\":\"" + token + "\"}", ex);
		finish(ex);
	}
	
}
