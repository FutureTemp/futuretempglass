package utils;

import items.Item;

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
	}

	public static void addItems(List<Item> items) throws Exception
	{
		if(items == null)
		{
			throw new Exception("Items cannot be null");
		}
		Application.getItemLibrary().addItems(items);
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
		Application.getItemLibrary().updateItem(item);
	}

	public static void deleteItem(String itemId) throws Exception
	{
		if(StringUtils.isEmpty(itemId)){
			throw new Exception("Invalid item ID");
		}
		Application.getItemLibrary().deleteItem(itemId);
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
