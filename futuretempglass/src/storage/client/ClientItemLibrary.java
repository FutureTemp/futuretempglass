package storage.client;

import items.Item;
import items.ItemFilter;

import java.util.List;

import storage.ItemLibrary;
import core.Client;

public class ClientItemLibrary extends ItemLibrary{

	private static String className = ClientItemLibrary.class.getPackage()
			.toString() + ClientItemLibrary.class.getName();

	@Override
	public Item getItem(String itemId)
	{
		Object response = Client.sendMessageToServer(className + ",getItem,"
				+ itemId);
		return (Item)response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItems()
	{
		Object response = Client.sendMessageToServer("get items");
		return (List<Item>)response;
	}

	@Override
	public boolean addItem(Item item)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateItem(Item item)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteItem(Item item)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteItem(String itemId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAvailableId()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItemsWithFilter(ItemFilter filter)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
