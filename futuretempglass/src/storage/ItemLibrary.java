package storage;

import items.Item;

import java.util.List;

public abstract class ItemLibrary{

	public abstract Item getItem(String itemName);
	
	public abstract List<Item> getItems();
	
	public abstract List<String> getItemNames();
}
