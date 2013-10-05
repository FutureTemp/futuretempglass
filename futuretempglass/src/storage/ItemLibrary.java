package storage;

import items.Item;

import java.util.HashMap;
import java.util.List;

import xml.InventoryXml;

public class ItemLibrary{

	private static HashMap<String, Item> items;
	
	public static void init()
	{
		items = new HashMap<String, Item>();
		List<Item> itemList = InventoryXml.getItems();
		for(Item item: itemList)
		{
			items.put(item.getItemName(), item);
		}
	}
	
	public static Item getItem(String itemName)
	{
		return items.get(itemName);
	}
	
}
