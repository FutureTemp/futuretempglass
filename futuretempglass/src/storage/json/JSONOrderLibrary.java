package storage.json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import orders.Order;
import orders.OrderFilter;
import storage.OrderLibrary;
import utils.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONOrderLibrary extends OrderLibrary{

	private static final String ORDERS_PATH = "jsonfiles/orders.txt";
	
	private List<Order> orders = new ArrayList<Order>();
	
	private HashMap<String, Order> ordersMap = new HashMap<String, Order>();
	
	private List<String> orderNumbers = new ArrayList<String>();
	
	public JSONOrderLibrary()
	{
		init();
	}
	
	private void init()
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			orders = mapper.readValue(new File(ORDERS_PATH), new TypeReference<List<Order>>(){});
		}
		catch(Exception e)
		{
			orders = new ArrayList<Order>();
		}
		for(Order order: orders)
		{
			orderNumbers.add(order.getOrderNumber());
			ordersMap.put(order.getOrderNumber(), order);
		}
	}
	
	private void save()
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File(ORDERS_PATH), orders);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Order getOrder(String orderId) throws Exception
	{
		return ordersMap.get(orderId);
	}

	@Override
	public boolean addOrder(Order order) throws Exception
	{
		if(StringUtils.isEmpty(order.getOrderNumber()))
		{
			return false;
		}
		if(ordersMap.get(order.getOrderNumber()) != null)
		{
			return false;
		}
		orders.add(order);
		orderNumbers.add(order.getOrderNumber());
		ordersMap.put(order.getOrderNumber(), order);
		save();
		return true;
	}

	@Override
	public boolean updateOrder(Order order) throws Exception
	{
		boolean result = deleteOrder(order.getOrderNumber()) && addOrder(order);
		if(result)
		{
			save();
		}
		return result;
	}

	@Override
	public boolean deleteOrder(Order order) throws Exception
	{
		if(order == null)
		{
			return false;
		}
		return deleteOrder(order.getOrderNumber());
	}

	@Override
	public boolean deleteOrder(String orderId) throws Exception
	{
		Order order = ordersMap.get(orderId);
		if(order == null)
		{
			return false;
		}
		orders.remove(order);
		orderNumbers.remove(orderId);
		ordersMap.put(orderId, null);
		save();
		return true;
	}

	@Override
	public List<Order> getOrders() throws Exception
	{
		return orders;
	}

	@Override
	public List<String> getOrderNumbers() throws Exception
	{
		return orderNumbers;
	}

	@Override
	public String getNextOrderNumber()
	{
		return "1"; // TODO
	}

	@Override
	public boolean isSequentialOrderNumbersUsed()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setSequentialOrderNumbersUsed(boolean used)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getOrdersWithFilter(OrderFilter filter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrders(List<String> orderNumbers) throws Exception
	{
		List<Order> orders = new ArrayList<Order>();
		for(String orderNumber: orderNumbers)
		{
			orders.add(ordersMap.get(orderNumber));
		}
		return orders;
	}

}
