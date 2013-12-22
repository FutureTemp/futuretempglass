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
		HashMap<String, String> parameters = getParameters(ex);
		if(StringUtils.isEmpty(parameters.get("username")))
		{
			return;
		}
		String token = AccountUtils.getToken(parameters.get("username"));
		sendHeader(ex);
		sendResponse("{\"token\":\"" + token + "\"}", ex);
		finish(ex);
	}
	
}
