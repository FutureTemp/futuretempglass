package storage.server;

import items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import storage.ItemLibrary;

import xml.InventoryXml;

public class ServerItemLibrary extends ItemLibrary{

	private HashMap<String, Item> items;
	
	private List<Item> itemList;
	
	public ServerItemLibrary()
	{
		init();
	}
	
	public void init()
	{
		items = new HashMap<String, Item>();
		itemList = InventoryXml.getItems();
		for(Item item: itemList)
		{
			items.put(item.getItemName(), item);
		}
	}
	
	@Override
	public Item getItem(String itemName)
	{
		return items.get(itemName);
	}
	
	@Override
	public List<Item> getItems()
	{
		return itemList;
	}

	@Override
	public List<String> getItemNames()
	{
		List<String> itemNames = new ArrayList<String>();
		for(Item item: itemList)
		{
			itemNames.add(item.getItemName());
		}
		return itemNames;
	}
}
