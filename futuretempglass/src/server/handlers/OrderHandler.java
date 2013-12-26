package server.handlers;

import java.util.List;

import orders.Order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import core.Application;

public class OrderHandler extends ServerHandler{

	private static String context = "/orders";

	public static String getContext()
	{
		return context;
	}

	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		try
		{
			String orderNum = getParameters(ex).get("order");
			// Send all orders
			if(orderNum == null)
			{
				List<Order> orders = Application.getOrderLibrary().getOrders();
				sendHeader(ex);
				sendResponse(orders, ex);
			}
			// Send one order
			else
			{
				Order order = Application.getOrderLibrary().getOrder(orderNum);
				if(order == null)
				{
					ex.sendResponseHeaders(404, 0);
					return;
				}
				sendHeader(ex);
				sendResponse(order, ex);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void onPost(HttpExchange ex) throws Exception
	{
		super.onPost(ex);
		sendHeader(ex);
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.readValue(getRequestData(ex), Order.class);
		if(Application.getOrderLibrary().addOrder(order))
		{
			sendResponse("Added", ex);
		}
		else if(Application.getOrderLibrary().updateOrder(order))
		{
			sendResponse("Updated", ex);
		}
	}

}
