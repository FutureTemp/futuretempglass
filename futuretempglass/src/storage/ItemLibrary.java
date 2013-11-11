package storage;

import items.Item;

import java.util.List;

import utils.StringUtils;

public abstract class ItemLibrary{

	public abstract Item getItem(String id);

	public abstract List<Item> getItems();

	public abstract boolean addItem(Item item);

	public abstract boolean updateItem(Item item);

	public abstract boolean deleteItem(Item item);

	public abstract boolean deleteItem(String itemId);
	
	public String getAvailableId()
	{
		return StringUtils.getRandomeStringOfLettersAndNumbers(8);
	}
}
