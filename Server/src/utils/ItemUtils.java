package utils;

import items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Application;

public class ItemUtils{

	/**
	 * Gets the item from storage with the specified item ID
	 * @param id
	 * @return Item
	 * @throws Exception
	 */
	public static Item getItem(String id) throws Exception
	{
		Item item = Application.getItemLibrary().getItem(id);
		if(item == null)
		{
			throw new Exception("Item with ID [" + id + "] could not be found");
		}
		return item;
	}

	/**
	 * Gets a list of all the items in storage
	 * @return List of Items
	 * @throws Exception
	 */
	public static List<Item> getItems() throws Exception
	{
		return Application.getItemLibrary().getItems();
	}

	/**
	 * Gets a List of Items with the the corresponding item ID's that are provided
	 * @param itemIds
	 * @return List of Items
	 * @throws Exception
	 */
	public static List<Item> getItems(List<String> itemIds) throws Exception
	{
		List<Item> items = Application.getItemLibrary().getItems(itemIds);
		if(items == null)
		{
			throw new Exception("Could not retrieve items [" + itemIds + "]");
		}
		return items;
	}

	/**
	 * Adds an item into storage. If the item contains an order number it will
	 * also update that order to contain this item
	 * @param item
	 * @throws Exception
	 */
	public static void addItem(Item item) throws Exception
	{
		if(item == null)
		{
			throw new Exception("Item cannot be null");
		}
		Application.getItemLibrary().addItem(item);
		if(!StringUtils.isEmpty(item.getOrderNumber()))
		{
			Application.getOrderLibrary().addItemToOrder(item.getItemId(),
					item.getOrderNumber());
		}
	}

	/**
	 * Adds the items provided into storage. If an item contains an order number
	 * that order will be updated to contain the item
	 * @param items
	 * @throws Exception
	 */
	public static void addItems(List<Item> items) throws Exception
	{
		if(items == null)
		{
			throw new Exception("Items cannot be null");
		}
		Application.getItemLibrary().addItems(items);
		HashMap<String, List<String>> itemsMap = new HashMap<String, List<String>>();
		for(Item item: items)
		{
			if(!StringUtils.isEmpty(item.getOrderNumber()))
			{
				List<String> itemsInOrder = itemsMap.get(item.getOrderNumber());
				if(itemsInOrder == null)
				{
					itemsInOrder = new ArrayList<String>();
					itemsMap.put(item.getOrderNumber(), itemsInOrder);
				}
				itemsInOrder.add(item.getItemId());
			}
		}
		for(String orderNumber: itemsMap.keySet())
		{
			Application.getOrderLibrary().addItemsToOrder(
					itemsMap.get(orderNumber), orderNumber);
		}
	}

	/**
	 * Updates an item in storage to match the item provided.
	 * The item provided must have the ID of the item to update
	 * @param item
	 * @throws Exception
	 */
	public static void updateItem(Item item) throws Exception
	{
		if(item == null)
		{
			throw new Exception("Item cannot be null");
		}
		if(StringUtils.isEmpty(item.getItemId()))
		{
			throw new Exception("Item must have a valid ID");
		}
		if(!Application.getItemLibrary().updateItem(item))
		{
			throw new Exception("Failed to update item");
		}
	}

	/**
	 * Removes the item from storage with the provided item ID
	 * @param itemId
	 * @throws Exception
	 */
	public static void deleteItem(String itemId) throws Exception
	{
		if(StringUtils.isEmpty(itemId))
		{
			throw new Exception("Invalid item ID");
		}
		Item item = Application.getItemLibrary().getItem(itemId);
		Application.getItemLibrary().deleteItem(itemId);
		Application.getOrderLibrary().removeItemsFromOrder(itemId,
				item.getOrderNumber());
	}

	/**
	 * Gets all the items with the specified order number
	 * @param orderNum
	 * @return List of Items
	 * @throws Exception
	 */
	public static List<Item> getItemsInOrder(String orderNum) throws Exception
	{
		if(StringUtils.isEmpty(orderNum))
		{
			throw new Exception("Invalid order number");
		}
		return Application.getItemLibrary().getItemsInOrder(orderNum);
	}

	/**
	 * Gets a new Item ID to use with a new Item
	 * @return Item ID as String
	 */
	public static String getAvailableId()
	{
		return StringUtils.getRandomStringOfLettersAndNumbers(8);
	}

}
