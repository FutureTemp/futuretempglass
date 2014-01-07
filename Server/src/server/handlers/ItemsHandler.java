package server.handlers;

import items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.ItemUtils;
import utils.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			List<Item> items = ItemUtils.getItemsInOrder(orderId);
			sendResponse(items, ex);
		}
		// all items
		else
		{
			List<Item> items = ItemUtils.getItems();
			sendResponse(items, ex);
		}
	}

	@Override
	protected void onPost(HttpExchange ex) throws Exception
	{
		super.onPost(ex);
		sendHeader(ex);
		ObjectMapper mapper = new ObjectMapper();
		String requestData = getRequestData(ex);
		List<Item> items = null;
		try
		{
			items = mapper.readValue(requestData,
					new TypeReference<List<Item>>(){});
		}
		catch(Exception e)
		{

		}
		if(items == null)
		{
			items = new ArrayList<Item>();
			try
			{
				items.add(mapper.readValue(requestData, Item.class));
			}
			catch(Exception e)
			{

			}
		}
		if(items.size() == 1)
		{
			if(StringUtils.isEmpty(items.get(0).getItemId()))
			{
				ItemUtils.addItem(items.get(0));
				sendResponse("Added", ex);
			}
			else
			{
				ItemUtils.updateItem(items.get(0));
				sendResponse("Updated", ex);
			}
		}
		else if(items.size() > 1)
		{
			ItemUtils.addItems(items);
		}
	}
}
