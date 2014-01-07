package utils;

import items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Application;

public class ItemUtils{

	public static Item getItem(String id) throws Exception
	{
		Item item = Application.getItemLibrary().getItem(id);
		if(item == null)
		{
			throw new Exception("Item with ID [" + id + "] could not be found");
		}
		return item;
	}

	public static List<Item> getItems() throws Exception
	{
		return Application.getItemLibrary().getItems();
	}

	public static List<Item> getItems(List<String> itemIds) throws Exception
	{
		List<Item> items = Application.getItemLibrary().getItems(itemIds);
		if(items == null)
		{
			throw new Exception("Could not retrieve items [" + itemIds + "]");
		}
		return items;
	}

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

	public static List<Item> getItemsInOrder(String orderNum) throws Exception
	{
		if(StringUtils.isEmpty(orderNum))
		{
			throw new Exception("Invalid order number");
		}
		return Application.getItemLibrary().getItemsInOrder(orderNum);
	}

	public static String getAvailableId()
	{
		return StringUtils.getRandomStringOfLettersAndNumbers(8);
	}

}
