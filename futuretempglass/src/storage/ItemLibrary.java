package storage;

import items.Item;
import items.ItemFilter;

import java.util.List;

import utils.StringUtils;

public abstract class ItemLibrary{

	public abstract Item getItem(String id) throws Exception;

	public abstract List<Item> getItems() throws Exception;
	
	public abstract List<Item> getItems(List<String> itemIds) throws Exception;

	public abstract boolean addItem(Item item);

	public abstract boolean addItems(List<Item> items) throws Exception;
	
	public abstract boolean updateItem(Item item);

	public abstract boolean deleteItem(Item item);

	public abstract boolean deleteItem(String itemId);
	
	public abstract List<Item> getItemsInOrder(String orderNum) throws Exception;
	
	public abstract List<Item> getItemsWithFilter(ItemFilter filter);
	
	public String getAvailableId()
	{
		return StringUtils.getRandomStringOfLettersAndNumbers(8);
	}
}
