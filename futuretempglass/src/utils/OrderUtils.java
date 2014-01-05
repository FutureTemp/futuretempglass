package utils;

import java.util.List;

import core.Application;
import orders.Order;

public class OrderUtils{

	public static void addOrder(Order order) throws Exception
	{
		if(order == null)
		{
			throw new Exception("Order cannot be null");
		}
		if(StringUtils.isEmpty(order.getOrderNumber()))
		{
			throw new Exception("Order must have a valid order number");
		}
		Application.getOrderLibrary().addOrder(order);
	}
	
	public static void addOrders(List<Order> orders) throws Exception
	{
		if(orders == null)
		{
			throw new Exception("Orders list cannot be null");
		}
		for(Order order: orders)
		{
			addOrder(order);
		}
	}

	public static Order getOrder(String orderId) throws Exception
	{
		if(StringUtils.isEmpty(orderId))
		{
			throw new Exception("Invalid order number");
		}
		return Application.getOrderLibrary().getOrder(orderId);
	}

	public static void updateOrder(Order order) throws Exception
	{
		if(order == null)
		{
			throw new Exception("Order cannot be null");
		}
		if(StringUtils.isEmpty(order.getOrderNumber()))
		{
			throw new Exception("Order must have a valid order number");
		}
		Application.getOrderLibrary().updateOrder(order);
	}

	public static void deleteOrder(String orderId) throws Exception
	{
		if(StringUtils.isEmpty(orderId))
		{
			throw new Exception("Invalid order number");
		}
		Order order = Application.getOrderLibrary().getOrder(orderId);
		Application.getOrderLibrary().deleteOrder(orderId);
		Application.getItemLibrary().deleteItems(order.getItemIds());
	}

	public static List<Order> getOrders() throws Exception
	{
		return Application.getOrderLibrary().getOrders();
	}

	public static List<Order> getOrders(List<String> orderNumbers)
			throws Exception
	{
		return Application.getOrderLibrary().getOrders(orderNumbers);
	}

	public static List<String> getOrderNumbers() throws Exception
	{
		return Application.getOrderLibrary().getOrderNumbers();
	}

	public static String getNextOrderNumber() throws Exception
	{
		return Application.getOrderLibrary().getNextOrderNumber();
	}
}
