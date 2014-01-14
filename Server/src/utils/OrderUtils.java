package utils;

import java.util.List;

import orders.Order;
import core.Application;

public class OrderUtils{

	/**
	 * Adds the order provided into storage. The order must have a valid order number
	 * @param order
	 * @throws Exception
	 */
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
	
	/**
	 * Adds the orders in the list provided to storage. Each order must have
	 * a valid and unique order number
	 * @param orders
	 * @throws Exception
	 */
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

	/**
	 * Gets the order from storage with the provided order number
	 * @param orderNumber
	 * @return
	 * @throws Exception
	 */
	public static Order getOrder(String orderNumber) throws Exception
	{
		if(StringUtils.isEmpty(orderNumber))
		{
			throw new Exception("Invalid order number");
		}
		return Application.getOrderLibrary().getOrder(orderNumber);
	}

	/**
	 * Updates an order in storage to match the order provided. 
	 * The order provided must contain the order number of the order to update
	 * @param order
	 * @throws Exception
	 */
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

	/**
	 * Removes the order with the provided order number from storage
	 * @param orderNumber
	 * @throws Exception
	 */
	public static void deleteOrder(String orderNumber) throws Exception
	{
		if(StringUtils.isEmpty(orderNumber))
		{
			throw new Exception("Invalid order number");
		}
		Order order = Application.getOrderLibrary().getOrder(orderNumber);
		Application.getOrderLibrary().deleteOrder(orderNumber);
		Application.getItemLibrary().deleteItems(order.getItemIds());
	}

	/**
	 * Gets a list of all the orders in storage
	 * @return List of Orders
	 * @throws Exception
	 */
	public static List<Order> getOrders() throws Exception
	{
		return Application.getOrderLibrary().getOrders();
	}

	/**
	 * Gets the orders with the order numbers provided
	 * @param orderNumbers
	 * @return List of Orders
	 * @throws Exception
	 */
	public static List<Order> getOrders(List<String> orderNumbers)
			throws Exception
	{
		return Application.getOrderLibrary().getOrders(orderNumbers);
	}

	/**
	 * Gets a list of all the order numbers from the orders in storage
	 * @return List of Strings
	 * @throws Exception
	 */
	public static List<String> getOrderNumbers() throws Exception
	{
		return Application.getOrderLibrary().getOrderNumbers();
	}

	/**
	 * Gets the next unused order number
	 * @return order number as a String
	 * @throws Exception
	 */
	public static String getNextOrderNumber() throws Exception
	{
		return Application.getOrderLibrary().getNextOrderNumber();
	}
}
