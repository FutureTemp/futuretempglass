package storage.database;

import java.util.ArrayList;
import java.util.List;

import orders.Order;
import orders.OrderFilter;
import storage.OrderLibrary;
import utils.StringUtils;

public class DBOrderLibrary extends OrderLibrary{

	private final static String database = "futuretemp";
	private final static String table = "order";
	private final static String orderNumberCol = "order_number";
	private final static String customerCol = "customer";
	private final static String itemIdsCol = "item_ids";

	@Override
	public Order getOrder(String orderId)
	{
		String query = "SELECT * FROM '" + database + "'.'" + table
				+ "' WHERE '" + orderNumberCol + "' = '" + orderId + "'";
		DBResults results = DBHelper.queryDb(query);
		if(results == null)
		{
			return null;
		}
		return dbResultsToOrders(results).get(0);
	}

	@Override
	public boolean addOrder(Order order)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrder(Order order)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(Order order)
	{
		return deleteOrder(order.getOrderNumber());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getOrderNumbers()
	{
		// TODO Auto-generated method stub
		return null;
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
	{
		List<Order> orders = new ArrayList<Order>();

		while (results.hasNext())
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
