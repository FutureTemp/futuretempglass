package storage.server;

import java.util.ArrayList;
import java.util.List;

import orders.Order;
import storage.OrderLibrary;

public class ServerOrderLibrary extends OrderLibrary{

	private List<Order> orders;

	private String lastOrderNumber = "0427";

	public ServerOrderLibrary()
	{
		orders = new ArrayList<Order>();
	}

	@Override
	public Order getOrder(String orderId)
	{
		for(Order order: orders)
		{
			if(order.getOrderNumber().equals(orderId))
			{
				return order;
			}
		}
		return null;
	}

	@Override
	public boolean addOrder(Order order)
	{
		if(order.getOrderNumber() == null)
		{
			order.setOrderNumber(getNextOrderNumber());
		}
		orders.add(order);
		return true;
	}

	@Override
	public String getNextOrderNumber()
	{
		int length = lastOrderNumber.length();
		lastOrderNumber = Integer
				.toString(Integer.parseInt(lastOrderNumber) + 1);
		while(lastOrderNumber.length() < length)
		{
			lastOrderNumber = "0" + lastOrderNumber;
		}
		return lastOrderNumber; // TODO implement
	}

	@Override
	public boolean updateOrder(Order order)
	{
		for(int i = 0; i < orders.size(); i++)
		{
			if(orders.get(i).getOrderNumber().equals(order.getOrderNumber()))
			{
				orders.remove(i);
				orders.add(i, order);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteOrder(Order order)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(String orderId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> getOrders()
	{
		return orders;
	}

	@Override
	public List<String> getOrderNumbers()
	{
		List<String> orderNumbers = new ArrayList<String>();

		for(Order order: orders)
		{
			orderNumbers.add(order.getOrderNumber());
		}

		return orderNumbers;
	}

}
