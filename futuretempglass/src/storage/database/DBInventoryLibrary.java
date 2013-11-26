package storage.database;

import items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import storage.InventoryLibrary;

public class DBInventoryLibrary extends InventoryLibrary{

	private static String database = "futuretemp";
	private static String table = "inventory";
	private static String nameRow = "name";
	private static String attributesRow = "attributes";

	@Override
	public List<Item> getItems()
	{
		return null; // TODO
	}

	@Override
	public Item getItem(String itemName)
	{
		HashMap<String, List<String>> results = DBHelper
				.queryDb("SELECT * FROM " + database + "." + table
						+ " WHERE name='" + itemName + "'");
		Item item = new Item();
		item.setName(itemName);
		String attributes = results.get(attributesRow).get(0);
		if(attributes == null)
		{
			item.setAttributeNames(new ArrayList<String>());
		}
		else
		{
			item.setAttributeNames(Arrays.asList(attributes.split(",")));
		}
		return item;
	}

	@Override
	public List<String> getItemNames()
	{
		HashMap<String, List<String>> rs = DBHelper
				.queryDb("SELECT name FROM futuretemp.inventory");
		return rs.get("name");
	}

}
