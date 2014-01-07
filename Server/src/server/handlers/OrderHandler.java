package server.handlers;

import java.util.HashMap;
import java.util.List;

import orders.Order;
import utils.OrderUtils;
import utils.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

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
			HashMap<String, String> parameters = getParameters(ex);
			String orderNum = parameters.get("order");
			List<String> orderNums = StringUtils.stringToList(parameters
					.get("orders"));
			// Send one order
			if(!StringUtils.isEmpty(orderNum))
			{
				Order order = OrderUtils.getOrder(orderNum);
				if(order == null)
				{
					ex.sendResponseHeaders(404, 0);
					return;
				}
				sendHeader(ex);
				sendResponse(order, ex);
			}
			// Send list of orders
			else if(orderNums != null && orderNums.size() > 0)
			{
				List<Order> orders = OrderUtils.getOrders(orderNums);
				sendHeader(ex);
				sendResponse(orders, ex);
			}
			// Send all orders
			else
			{
				List<Order> orders = OrderUtils.getOrders();
				sendHeader(ex);
				sendResponse(orders, ex);
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
		String requestData = getRequestData(ex);
		try
		{
			Order order = mapper.readValue(requestData, Order.class);
			OrderUtils.addOrder(order);
		}
		catch(JsonMappingException e)
		{
			List<Order> orders = mapper.readValue(requestData, new TypeReference<List<Order>>(){});
			OrderUtils.addOrders(orders);
		}
		sendResponse("Added", ex);
	}

}
