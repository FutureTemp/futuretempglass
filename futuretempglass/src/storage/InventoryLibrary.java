package storage;

import items.Item;

import java.util.List;

public abstract class InventoryLibrary{

	public abstract List<Item> getItems();

	public abstract Item getItem(String itemName);

	public abstract List<String> getItemNames();

}
