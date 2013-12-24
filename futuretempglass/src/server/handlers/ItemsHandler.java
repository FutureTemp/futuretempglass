package server.handlers;

import items.Item;

import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;

import core.Application;

public class ItemsHandler extends ServerHandler{

	private static String context = "/items";
	
	public static String getContext()
	{
		return context;
	}
	
	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		HashMap<String, String> parameters = getParameters(ex);
		sendHeader(ex);
		String itemId = parameters.get("item");
		if(itemId != null)
		{
			Item item = Application.getItemLibrary().getItem(itemId);
			sendResponse(item, ex);
		}
	}
	
}
