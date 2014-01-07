package storage.database;

import items.Item;

import java.util.ArrayList;
import java.util.List;

import storage.InventoryLibrary;
import utils.StringUtils;

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
	public Item getItem(String itemName) throws Exception
	{
		DBResults results = DBHelper.queryDb("SELECT * FROM " + database + "."
				+ table + " WHERE name='" + itemName + "'");
		Item item = new Item();
		item.setName(itemName);
		results.next();
		String attributes = results.getString(attributesRow);
		if(attributes == null)
		{
			item.setAttributeNames(new ArrayList<String>());
		}
		else
		{
			item.setAttributeNames(StringUtils.stringToList(attributes));
		}
		return item;
	}

	@Override
	public List<String> getItemNames() throws Exception
	{
		DBResults results = DBHelper
				.queryDb("SELECT name FROM futuretemp.inventory");
		List<String> names = new ArrayList<String>();
		while(results.next())
		{
			names.add(results.getString(nameRow));
		}
		return names;
	}

}
