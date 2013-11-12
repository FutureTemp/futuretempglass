package storage;

import java.util.List;

import orders.Order;

public abstract class OrderLibrary{

	public abstract Order getOrder(String orderId);

	public abstract boolean addOrder(Order order);

	public abstract boolean updateOrder(Order order);
	
	public abstract boolean deleteOrder(Order order);
	
	public abstract boolean deleteOrder(String orderId);

	public abstract List<Order> getOrders();
	
	public abstract List<String> getOrderNumbers();

	public abstract String getNextOrderNumber();
	
	public abstract boolean isSequentialOrderNumbersUsed();
	
	public abstract void setSequentialOrderNumbersUsed(boolean used);

}
