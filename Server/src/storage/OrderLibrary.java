package storage;

import java.util.List;

import orders.Order;
import orders.OrderFilter;

public abstract class OrderLibrary{

	/**
	 * Gets the order from storage that matches the specified Order number
	 * @param orderNumber
	 * @return Order, null if not found
	 * @throws Exception
	 */
	public abstract Order getOrder(String orderNumber) throws Exception;

	/**
	 * Adds the order given into storage, must have a unused order number
	 * @param order
	 * @return true if successful, false otherwise
	 * @throws Exception
	 */
	public abstract boolean addOrder(Order order) throws Exception;

	/**
	 * Updates an order in storage to match the order object given.
	 * the order specified must contain the order number of the order 
	 * to update.
	 * @param order
	 * @return true if successful, false otherwise.
	 * @throws Exception
	 */
	public abstract boolean updateOrder(Order order) throws Exception;

	@Deprecated
	public abstract boolean deleteOrder(Order order) throws Exception;

	/**
	 * 
	 * @param orderNumber
	 * @return
	 * @throws Exception
	 */
	public abstract boolean deleteOrder(String orderNumber) throws Exception;

	/**
	 * Gets the list of all the orders in storage
	 * @return List of Orders
	 * @throws Exception
	 */
	public abstract List<Order> getOrders() throws Exception;

	/**
	 * Gets the list of orders with the specified order numbers
	 * @param orderNumbers
	 * @return List of Orders
	 * @throws Exception
	 */
	public abstract List<Order> getOrders(List<String> orderNumbers)
			throws Exception;

	/**
	 * Gets the list of all the order numbers of the orders in storage.
	 * @return List of Strings representing the order numbers
	 * @throws Exception
	 */
	public abstract List<String> getOrderNumbers() throws Exception;

	/**
	 * Gets the next order number that is not in use
	 * @return String representing the order number
	 */
	public abstract String getNextOrderNumber();

	/**
	 * Gets the configuration property to see if sequential order numbers is enabled
	 * @return true if enabled, false otherwise
	 */
	public abstract boolean isSequentialOrderNumbersUsed();

	/**
	 * Sets whether sequention order numbers should be used
	 * @param used
	 */
	public abstract void setSequentialOrderNumbersUsed(boolean used);

	@Deprecated
	public abstract List<Order> getOrdersWithFilter(OrderFilter filter);

	/**
	 * Adds an item ID to the order in storage
	 * @param itemId
	 * @param orderNumber
	 * @return true if successful, false otherwise
	 * @throws Exception
	 */
	public boolean addItemToOrder(String itemId, String orderNumber)
			throws Exception
	{
		Order order = getOrder(orderNumber);
		if(order.getItemIds().contains(itemId))
		{
			throw new Exception("Order [" + orderNumber
					+ "] already contains item [" + itemId + "]");
		}
		order.getItemIds().add(itemId);
		updateOrder(order);
		return true;
	}

	/**
	 * Adds all the item ID's in the list specified to the order with
	 * the order number specified
	 * @param itemIds
	 * @param orderNumber
	 * @return true if successful, false otherwise
	 * @throws Exception
	 */
	public boolean addItemsToOrder(List<String> itemIds, String orderNumber)
			throws Exception
	{
		Order order = getOrder(orderNumber);
		for(String itemId: itemIds)
		{
			if(!order.getItemIds().contains(itemId))
			{
				order.getItemIds().add(itemId);
			}
		}
		updateOrder(order);
		return true;
	}

	/**
	 * Removes the item ID specified from the order with the
	 * order number that is specified.
	 * @param itemId
	 * @param orderNumber
	 * @return true if successful, false otherwise
	 * @throws Exception
	 */
	public boolean removeItemsFromOrder(String itemId, String orderNumber)
			throws Exception
	{
		Order order = getOrder(orderNumber);
		order.getItemIds().remove(itemId);
		updateOrder(order);
		return true;
	}
}
