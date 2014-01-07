package storage;

import java.util.List;

import orders.Order;
import orders.OrderFilter;

public abstract class OrderLibrary{

	public abstract Order getOrder(String orderId) throws Exception;

	public abstract boolean addOrder(Order order) throws Exception;

	public abstract boolean updateOrder(Order order) throws Exception;

	@Deprecated
	public abstract boolean deleteOrder(Order order) throws Exception;

	public abstract boolean deleteOrder(String orderId) throws Exception;

	public abstract List<Order> getOrders() throws Exception;

	public abstract List<Order> getOrders(List<String> orderNumbers)
			throws Exception;

	public abstract List<String> getOrderNumbers() throws Exception;

	public abstract String getNextOrderNumber();

	public abstract boolean isSequentialOrderNumbersUsed();

	public abstract void setSequentialOrderNumbersUsed(boolean used);

	public abstract List<Order> getOrdersWithFilter(OrderFilter filter);

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

	public boolean removeItemsFromOrder(String itemId, String orderNumber)
			throws Exception
	{
		Order order = getOrder(orderNumber);
		order.getItemIds().remove(itemId);
		updateOrder(order);
		return true;
	}
}
