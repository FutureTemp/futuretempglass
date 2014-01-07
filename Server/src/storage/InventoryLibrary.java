package storage;

import items.Item;

import java.util.List;

public abstract class InventoryLibrary{

	public abstract List<Item> getItems();

	public abstract Item getItem(String itemName) throws Exception;

	public abstract List<String> getItemNames() throws Exception;

}
