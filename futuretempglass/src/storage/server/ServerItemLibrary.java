package storage.server;

import items.Item;
import items.ItemFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import orders.Order;
import storage.ItemLibrary;
import utils.StringUtils;

import com.sun.istack.internal.NotNull;

import core.Application;

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
		items = new HashMap<String, Item>();
		itemList = new ArrayList<Item>();
		for(Order order: Application.getOrderLibrary().getOrders())
		{
			for(Item item: order.getItems())
			{
				itemList.add(item);
				items.put(item.getItemId(), item);
			}
		}
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
		if(StringUtils.isEmpty(item.getItemId()))
		{
			item.setItemId(StringUtils.getRandomeStringOfLettersAndNumbers(8));
		}
		if(items.get(item.getItemId()) == null)
		{
			items.put(item.getItemId(), item);
			itemList.add(item);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateItem(Item item)
	{
		if(item == null || item.getItemId() == null)
		{
			return false;
		}

		if(items.get(item.getItemId()) == null)
		{
			return false;
		}
		
		items.put(item.getItemId(), item);
		itemList.add(item);
		
		return true;
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
		return false;
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
		return true;
	}

	@Override
	public List<Item> getItemsWithFilter(ItemFilter filter)
	{
		return filter.filter(new ArrayList<Item>(itemList));
	}
}
