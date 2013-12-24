package server.handlers;

import items.Item;

import java.util.HashMap;
import java.util.List;

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
		String orderId = parameters.get("order");
		// one item
		if(itemId != null)
		{
			Item item = Application.getItemLibrary().getItem(itemId);
			sendResponse(item, ex);
		}
		// all items in an order
		else if(orderId != null)
		{
			List<Item> items = Application.getItemLibrary().getItemsInOrder(orderId);
			sendResponse(items, ex);
		}
		// all items
		else
		{
			List<Item> items = Application.getItemLibrary().getItems();
			sendResponse(items, ex);
		}
	}

}
