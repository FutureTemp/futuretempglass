package utils;

import items.Item;

import java.util.List;

import core.Application;

public class InventoryUtils{

	/**
	 * Gets a list of all the items in inventory
	 * @return List of Items
	 */
	public static List<Item> getAllInventory()
	{
		return Application.getInventoryLibrary().getItems();
	}

	/**
	 * Gets the names of all the items in inventory
	 * @return List of Strings representing the item names
	 * @throws Exception
	 */
	public static List<String> getInventoryNames() throws Exception
	{
		return Application.getInventoryLibrary().getItemNames();
	}

	/**
	 * Gets the item in inventory with the specified item name
	 * @param itemName
	 * @return the Item with the specified item name
	 * @throws Exception
	 */
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
