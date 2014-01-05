package storage.json;

import items.Item;
import items.ItemFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import orders.Order;
import storage.ItemLibrary;
import utils.FileUtils;
import utils.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import core.Application;

public class JSONItemLibrary extends ItemLibrary{

	private static final String ITEMS_PATH = "jsonfiles/items.txt";

	private HashMap<String, Item> itemsMap = new HashMap<String, Item>();

	private List<Item> items = new ArrayList<Item>();

	public JSONItemLibrary()
	{
		init();
	}

	private void init()
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			items = mapper.readValue(new File(ITEMS_PATH),
					new TypeReference<List<Item>>(){});
		}
		catch(Exception e)
		{
		}
		for(Item item: items)
		{
			itemsMap.put(item.getItemId(), item);
		}
	}

	private void save()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try
		{
			FileUtils.createDirectoryAndFile(ITEMS_PATH);
			mapper.writeValue(new File(ITEMS_PATH), items);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Item getItem(String id) throws Exception
	{
		return itemsMap.get(id);
	}

	@Override
	public List<Item> getItems() throws Exception
	{
		return items;
	}

	@Override
	public List<Item> getItems(List<String> itemIds) throws Exception
	{
		List<Item> items = new ArrayList<Item>();
		for(String id: itemIds)
		{
			items.add(itemsMap.get(id));
		}
		return items;
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
			item.setItemId(getAvailableId());
		}
		items.add(item);
		itemsMap.put(item.getItemId(), item);
		save();
		return true;
	}
	
	@Override
	public boolean addItems(List<Item> items) throws Exception
	{
		for(Item item: items)
		{
			if(item == null)
			{
				continue;
			}
			if(StringUtils.isEmpty(item.getItemId()))
			{
				item.setItemId(getAvailableId());
			}
			this.items.add(item);
			itemsMap.put(item.getItemId(), item);
		}
		save();
		return true;
	}

	@Override
	public boolean updateItem(Item item)
	{
		if(item == null)
		{
			return false;
		}
		if(itemsMap.get(item.getItemId()) == null)
		{
			return false;
		}
		return deleteItem(item.getItemId()) && addItem(item);
	}

	@Override
	public boolean deleteItem(String itemId)
	{
		if(StringUtils.isEmpty(itemId))
		{
			return false;
		}
		items.remove(itemsMap.remove(itemId));
		return true;
	}

	@Override
	public List<Item> getItemsInOrder(String orderNum) throws Exception
	{
		if(StringUtils.isEmpty(orderNum))
		{
			return null;
		}
		Order order = Application.getOrderLibrary().getOrder(orderNum);
		if(order == null)
		{
			return null;
		}
		List<Item> items = new ArrayList<Item>();
		for(String itemId: order.getItemIds())
		{
			items.add(itemsMap.get(itemId));
		}
		return items;
	}

	@Override
	public List<Item> getItemsWithFilter(ItemFilter filter)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
