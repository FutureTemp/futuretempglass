package storage.database;

import items.Item;

import java.util.HashMap;
import java.util.List;

import storage.InventoryLibrary;

public class DBInventoryLibrary extends InventoryLibrary{

	@Override
	public List<Item> getItems()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(String itemName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getItemNames()
	{
		HashMap<String, List<String>> rs = DBHelper
				.queryDb("SELECT name FROM futuretemp.inventory");
		return rs.get("name");
	}

}
