package storage;

import java.util.List;

import orders.Order;
import orders.OrderFilter;

public abstract class OrderLibrary{

	public abstract Order getOrder(String orderId);

	public abstract boolean addOrder(Order order) throws Exception;

	public abstract boolean updateOrder(Order order) throws Exception;
	
	public abstract boolean deleteOrder(Order order) throws Exception;
	
	public abstract boolean deleteOrder(String orderId) throws Exception;

	public abstract List<Order> getOrders();
	
	public abstract List<String> getOrderNumbers();

	public abstract String getNextOrderNumber();
	
	public abstract boolean isSequentialOrderNumbersUsed();
	
	public abstract void setSequentialOrderNumbersUsed(boolean used);
	
	public abstract List<Order> getOrdersWithFilter(OrderFilter filter);

}
