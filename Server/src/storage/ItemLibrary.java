package storage;

import items.Item;
import items.ItemFilter;

import java.util.List;

import utils.StringUtils;

public abstract class ItemLibrary{

	/**
	 * Gets the item with the specified ID, returns null
	 * if the ID is not in use.
	 * @param id
	 * @return Item, null if not found
	 * @throws Exception
	 */
	public abstract Item getItem(String id) throws Exception;

	/**
	 * Gets the list of all the items in storage
	 * @return List of Items
	 * @throws Exception
	 */
	public abstract List<Item> getItems() throws Exception;
	
	/**
	 * Gets the Items with the specified ID's.
	 * If any of the ID's are not in use, there will be null
	 * value in the list returned
	 * @param itemIds
	 * @return List of Items with matching ID's
	 * @throws Exception
	 */
	public abstract List<Item> getItems(List<String> itemIds) throws Exception;

	/**
	 * Adds an item into storage and also populates the ID field
	 * @param item
	 * @return true if successfully added, false otherwise
	 */
	public abstract boolean addItem(Item item);

	/**
	 * Adds the items given in the list into storage
	 * @param items
	 * @return true if successful, false otherwise
	 * @throws Exception
	 */
	public abstract boolean addItems(List<Item> items) throws Exception;
	
	/**
	 * Updates an item in storage to match the item specified.
	 * The item specified must have a valid ID matching
	 * the item to update.
	 * @param item
	 * @return true if successful, false otherwise
	 */
	public abstract boolean updateItem(Item item);

	/**
	 * Removes an item from storage with the given item ID
	 * @param itemId
	 * @return true if successful, false otherwise
	 */
	public abstract boolean deleteItem(String itemId);
	
	/**
	 * Deletes the items with the specified ID's provided
	 * @param itemIds
	 * @return true if successful, false otherwise
	 */
	public abstract boolean deleteItems(List<String> itemIds);
	
	/**
	 * Gets all the items with the specified order number provided
	 * @param orderNum
	 * @return List of Items with that order number, null if unsuccessful
	 * @throws Exception
	 */
	public abstract List<Item> getItemsInOrder(String orderNum) throws Exception;
	
	@Deprecated
	public abstract List<Item> getItemsWithFilter(ItemFilter filter);
	
	/**
	 * Generates a new ID for new items to use
	 * @return an item ID
	 */
	public String getAvailableId()
	{
		return StringUtils.getRandomStringOfLettersAndNumbers(8);
	}
}
