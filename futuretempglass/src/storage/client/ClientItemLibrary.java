package storage.client;

import items.Item;

import java.util.Arrays;
import java.util.List;

import storage.ItemLibrary;
import core.Client;

public class ClientItemLibrary extends ItemLibrary{

	@Override
	public Item getItem(String itemName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItems()
	{
		//TODO IMPLEMENT
		return null;
	}
	
	@Override
	public List<String> getItemNames()
	{
		String response = Client.sendMessageToServer("get item names");
		if(response == null)
		{
			return null;
		}
		String[] itemNames = response.split("|");
		return Arrays.asList(itemNames);
	}

}
