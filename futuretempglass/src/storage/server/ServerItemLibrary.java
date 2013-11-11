package storage.server;

import items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import storage.ItemLibrary;

import com.sun.istack.internal.NotNull;

public class ServerItemLibrary extends ItemLibrary{

	/**
	 * Maps item ID's to their respective items
	 */
	private HashMap<String, Item> items;

	private List<Item> itemList;

	public ServerItemLibrary()
	{
		init();
	}

	public void init()
	{
		// TODO implement
		items = new HashMap<String, Item>();
		itemList = new ArrayList<Item>();
	}

	private boolean save()
	{
		// TODO implement
		return false;
	}

	@Override
	public Item getItem(String id)
	{
		return items.get(id);
	}

	@Override
	public List<Item> getItems()
	{
		return itemList;
	}

	@Override
	public boolean addItem(Item item)
	{
		if(item == null)
		{
			return false;
		}
		if(item.getItemId() == null)
		{
			item.setItemId(getAvailableId());
		}
		return updateItem(item);
	}

	@Override
	public boolean updateItem(Item item)
	{
		if(item == null || item.getItemId() == null)
		{
			return false;
		}
		items.put(item.getItemId(), item);
		itemList.add(item);
		return save();
	}

	@Override
	@NotNull
	public boolean deleteItem(Item item)
	{
		if(item == null || item.getItemId() == null)
		{
			return false;
		}
		items.remove(item.getItemId());
		itemList.remove(item);
		return save();
	}

	@Override
	@NotNull
	public boolean deleteItem(String itemId)
	{
		if(itemId == null)
		{
			return false;
		}
		itemList.remove(items.remove(itemId));
		return save();
	}
}
