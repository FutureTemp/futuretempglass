package storage.database;

import java.util.ArrayList;
import java.util.List;

import core.Application;

import orders.Order;
import orders.OrderFilter;
import storage.OrderLibrary;
import utils.StringUtils;

public class DBOrderLibrary extends OrderLibrary{

	private final static String database = "futuretemp";
	private final static String table = "orders";
	private final static String orderNumberCol = "order_number";
	private final static String customerCol = "customer";
	private final static String itemIdsCol = "item_ids";

	@Override
	public Order getOrder(String orderId) throws Exception
	{
		String query = "SELECT * FROM " + database + "." + table + " WHERE "
				+ orderNumberCol + " = '" + orderId + "'";
		DBResults results = DBHelper.queryDb(query);
		if(results == null)
		{
			return null;
		}
		List<Order> orders = dbResultsToOrders(results);
		if(orders.size() == 0)
		{
			return null;
		}
		return orders.get(0);
	}

	@Override
	public boolean addOrder(Order order)
	{
		String query = "INSERT INTO " + database + "." + table + " ("
				+ orderNumberCol + ", " + customerCol + ", " + itemIdsCol
				+ ") VALUES (" + order.getOrderNumber() + ", '"
				+ order.getCustomer() + "', '"
				+ StringUtils.listToString(order.getItemIds()) + "');";
		return DBHelper.writeToDb(query);
	}

	@Override
	public boolean updateOrder(Order order)
	{
		String query = "UPDATE " + database + "." + table + " SET "
				+ orderNumberCol + "=" + order.getOrderNumber() + ", "
				+ itemIdsCol + "='"
				+ StringUtils.listToString(order.getItemIds()) + "', "
				+ customerCol + "='" + order.getCustomer() + "' WHERE "
				+ orderNumberCol + "='" + order.getOrderNumber() + "';";
		return DBHelper.writeToDb(query);
	}

	@Override
	public boolean deleteOrder(Order order) throws Exception
	{
		return deleteOrder(order.getOrderNumber());
	}

	@Override
	public boolean deleteOrder(String orderId) throws Exception
	{
		String query = "DELETE FROM " + database + "." + table + " WHERE "
				+ orderNumberCol + " = '" + orderId + "'";
		Order order = getOrder(orderId);
		if(DBHelper.writeToDb(query))
		{
			for(String itemId: order.getItemIds())
			{
				if(!Application.getItemLibrary().deleteItem(itemId))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Order> getOrders() throws Exception
	{
		String query = "SELECT * FROM " + database + "." + table;
		DBResults results = DBHelper.queryDb(query);

		return dbResultsToOrders(results);
	}

	@Override
	public List<String> getOrderNumbers() throws Exception
	{
		String query = "SELECT " + orderNumberCol + " FROM " + database + "."
				+ table;
		DBResults results = DBHelper.queryDb(query);
		if(results == null)
		{
			return null;
		}
		return results.getColumn(orderNumberCol);
	}

	@Override
	public String getNextOrderNumber()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSequentialOrderNumbersUsed()
	{
		// TODO Auto-generated method stub
		return false;
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

	private static List<Order> dbResultsToOrders(DBResults results)
			throws Exception
	{
		List<Order> orders = new ArrayList<Order>();
		if(results == null)
		{
			return null;
		}
		while (results.next())
		{
			Order order = new Order();
			order.setOrderNumber(results.getString(orderNumberCol));
			order.setCustomer(results.getString(customerCol));
			order.setItemIds(StringUtils.stringToList(results
					.getString(itemIdsCol)));
			orders.add(order);
		}

		return orders;
	}

}
