package storage.server;

import items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import storage.InventoryLibrary;
import xml.InventoryXml;

public class ServerInventoryLibrary extends InventoryLibrary{

	private HashMap<String, Item> itemsMap;

	private List<Item> itemList;

	private List<String> itemNames;

	public ServerInventoryLibrary()
	{
		init();
	}

	public void init()// TODO change
	{
		itemsMap = new HashMap<String, Item>();
		itemNames = new ArrayList<String>();
		itemList = InventoryXml.getItems();
		for(Item item: itemList)
		{
			itemsMap.put(item.getItemName(), item);
			itemNames.add(item.getItemName());
		}
	}

	@Override
	public List<Item> getItems()
	{
		return itemList;
	}

	@Override
	public Item getItem(String itemName)
	{
		Item item = new Item();
		Item masterItem = itemsMap.get(itemName);
		item.setName(masterItem.getItemName());
		item.setAttributeNames(masterItem.getAttributeNames());
		return item;
	}

	@Override
	public List<String> getItemNames()
	{
		return itemNames;
	}

}