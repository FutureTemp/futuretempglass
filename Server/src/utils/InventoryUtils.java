package utils;

import items.Item;

import java.util.List;

import core.Application;

public class InventoryUtils{

	public static List<Item> getAllInventory()
	{
		return Application.getInventoryLibrary().getItems();
	}

	public static List<String> getInventoryNames() throws Exception
	{
		return Application.getInventoryLibrary().getItemNames();
	}

	public static Item getItem(String itemName) throws Exception
	{
		if(StringUtils.isEmpty(itemName))
		{
			throw new Exception("Not a valid item name");
		}
		Item item = Application.getInventoryLibrary().getItem(itemName);
		if(item == null)
		{
			throw new Exception("Item ["+itemName+"] was not found in inventory");
		}
		return item;
	}
	
}
