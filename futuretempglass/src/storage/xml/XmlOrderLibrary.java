package storage.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import orders.Order;
import orders.OrderFilter;
import storage.OrderLibrary;
import utils.FileUtils;
import xml.OrderXml;
import core.Application;

public class XmlOrderLibrary extends OrderLibrary{

	private final static String orderNumbersFilePath = "xml-orders/Orders.txt";
	private final static String orderPropertiesFilePath = "xml-orders/properties.txt";
	private final static String ordersPath = "xml-orders/";

	private final List<String> orderPropertyNames = Arrays.asList(
			"last_order_number", "use_sequential_order_numbers");

	private final List<String> defaultPropertyValues = Arrays.asList("0",
			"false");

	private List<Order> orders;

	private List<String> orderNumbers;

	private HashMap<String, String> orderProperties = new HashMap<String, String>();

	public XmlOrderLibrary()
	{
		init();
	}

	private void init()
	{
		initOrderNumbers();
		initOrdersList();
		initOrderProperties();
	}

	private void initOrderNumbers()
	{
		orderNumbers = new ArrayList<String>();
		orders = new ArrayList<Order>();
		File orderNumberFile = new File(orderNumbersFilePath);
		List<String> orderNumberFileContents = FileUtils
				.getFileContents(orderNumberFile);
		if(orderNumberFileContents == null)
		{
			try
			{
				orderNumberFile.createNewFile();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			for(String line: orderNumberFileContents)
			{
				orderNumbers.add(line.trim());
			}
		}
	}

	private void initOrdersList()
	{
		if(orderNumbers == null)
		{
			return;
		}
		for(String orderNumber: orderNumbers)
		{
			OrderXml orderXml = OrderXml.loadOrder(orderNumber);
			Order order = orderXml.getOrder();
			orders.add(order);
		}
	}

	private void initOrderProperties()
	{
		List<String> orderPropertyContents;
		File orderPropertiesFile = new File(orderPropertiesFilePath);
		orderPropertyContents = FileUtils.getFileContents(orderPropertiesFile);
		if(orderPropertyContents != null)
		{
			for(String line: orderPropertyContents)
			{
				String propertyName = line.substring(0, line.indexOf("="))
						.trim();
				orderProperties.put(propertyName,
						line.substring(line.indexOf("=") + 1).trim());
			}
		}
		orderPropertyContents = new ArrayList<String>();
		for(String propertyName: orderPropertyNames)
		{
			String propertyValue = orderProperties.get(propertyName);
			if(propertyValue == null)
			{
				String defaultValue = defaultPropertyValues
						.get(orderPropertyNames.indexOf(propertyName));
				propertyValue = defaultValue;
				orderProperties.put(propertyName, propertyValue);
			}
			orderPropertyContents.add(propertyName + "=" + propertyValue);
		}
		FileUtils.writeFile(orderPropertiesFile, orderPropertyContents);
	}

	private void save() throws Exception
	{
		saveOrderNumbers();
		saveOrders();
	}

	private void saveOrderNumbers()
	{
		List<String> fileContents = new ArrayList<String>();
		for(String orderNumber: orderNumbers)
		{
			fileContents.add(orderNumber);
		}
		FileUtils.writeFile(orderNumbersFilePath, fileContents);
	}

	private void saveOrders() throws Exception
	{
		for(Order order: orders)
		{
			OrderXml orderXml = new OrderXml(order);
			orderXml.saveOrder();
		}
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
	public boolean addOrder(Order order) throws Exception
	{
		if(order.getOrderNumber() == null)
		{
			order.setOrderNumber(getNextOrderNumber());
		}
		orders.add(order);
		orderNumbers.add(order.getOrderNumber());
		save();
		return true;
	}

	@Override
	public String getNextOrderNumber()
	{
		String lastOrderNumber = orderProperties.get(orderPropertyNames.get(0));
		int length = lastOrderNumber.length();
		try
		{
			lastOrderNumber = Integer.toString(Integer
					.parseInt(lastOrderNumber) + 1);
		}
		catch(Exception e)
		{
			return "";
		}
		while (lastOrderNumber.length() < length)
		{
			lastOrderNumber = "0" + lastOrderNumber;
		}
		orderProperties.put(orderPropertyNames.get(0), lastOrderNumber);
		return lastOrderNumber; // TODO implement
	}

	@Override
	public boolean updateOrder(Order order) throws Exception
	{
		for(int i = 0; i < orders.size(); i++)
		{
			if(orders.get(i).getOrderNumber().equals(order.getOrderNumber()))
			{
				orders.remove(i);
				orders.add(i, order);
				save();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteOrder(Order order) throws Exception
	{

		if(order.getOrderNumber() == null
				|| !orderNumbers.contains(order.getOrderNumber()))
		{
			return false;
		}
		if(order.getItemIds() != null)
		{
			for(String itemId: order.getItemIds())
			{
				Application.getItemLibrary().deleteItem(itemId);
			}
		}
		orders.remove(order);
		orderNumbers.remove(order.getOrderNumber());
		save();
		FileUtils.deleteFile(ordersPath + order.getOrderNumber() + ".xml");
		return true;
	}

	@Override
	public boolean deleteOrder(String orderId) throws Exception
	{
		if(orderId == null)
		{
			return false;
		}
		Order order = new Order();
		order.setOrderNumber(orderId);
		return deleteOrder(order);
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

	@Override
	public boolean isSequentialOrderNumbersUsed()
	{
		return orderProperties.get(orderPropertyNames.get(1)).equals("true");
	}

	@Override
	public void setSequentialOrderNumbersUsed(boolean used)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Order> getOrdersWithFilter(OrderFilter filter)
	{
		return filter.filter(new ArrayList<Order>(orders));
	}

	@Override
	public List<Order> getOrders(List<String> orderNumbers) throws Exception
	{
		List<Order> orders = new ArrayList<Order>();
		for(String orderNum: orderNumbers)
		{
			orders.add(getOrder(orderNum));
		}
		return orders;
	}

}
