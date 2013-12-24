package server.handlers;

import java.util.List;

import orders.Order;
import storage.database.DBOrderLibrary;

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

	public static void main(String[] args) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		Order order = new DBOrderLibrary().getOrder("123");
		System.out.println(order);
		System.out.println(mapper.writeValueAsString(order));
	}
}
