package storage;

import items.Item;

import java.util.List;

public abstract class InventoryLibrary{

	/**
	 * Gets the list of all the items in inventory
	 * @return the list of items
	 */
	public abstract List<Item> getItems();

	/**
	 * Gets the item in inventory with the given itemName
	 * @param itemName
	 * @return Item, null if item is not found
	 * @throws Exception
	 */
	public abstract Item getItem(String itemName) throws Exception;

	/**
	 * Gets the list of all the item names in inventory
	 * @return List of strings containing all the item names
	 * @throws Exception
	 */
	public abstract List<String> getItemNames() throws Exception;

}
