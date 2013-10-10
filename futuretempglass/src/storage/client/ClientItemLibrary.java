package storage.client;

import items.Item;

import java.util.List;

import storage.ItemLibrary;
import core.Client;

public class ClientItemLibrary extends ItemLibrary{

	@Override
	public Item getItem(String itemName)
	{
		Object response = Client.sendMessageToServer("get item " + itemName);
		return (Item)response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItems()
	{
		Object response = Client.sendMessageToServer("get items");
		return (List<Item>)response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getItemNames()
	{
		Object response = Client.sendMessageToServer("get item names");
		return (List<String>)response;
	}

}
