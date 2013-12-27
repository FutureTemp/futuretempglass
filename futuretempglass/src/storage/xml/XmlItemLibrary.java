package storage.xml;

import items.Item;
import items.ItemFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import storage.ItemLibrary;
import utils.StringUtils;

import com.sun.istack.internal.NotNull;

public class XmlItemLibrary extends ItemLibrary{

	private static final String NUMBERS_LOCATION = "xml-items/items.txt";

	/**
	 * Maps item ID's to their respective items
	 */
	private HashMap<String, Item> items = new HashMap<String, Item>();

	private List<Item> itemList = new ArrayList<Item>();

	private List<String> itemNumbers = new ArrayList<String>();

	public XmlItemLibrary()
	{
		init();
	}

	public void init()
	{
		initItemNumbers();
		initItems();
	}

	private void initItemNumbers()
	{
		BufferedReader reader = null;
		FileReader fr = null;
		File file = new File(NUMBERS_LOCATION);
		try
		{
			fr = new FileReader(file);
			reader = new BufferedReader(fr);

			String line = reader.readLine();
			while(line != null)
			{
				itemNumbers.add(line.trim());
				line = reader.readLine();
			}
		}
		catch(Exception e)
		{
		}
		finally
		{
			try
			{
				if(fr != null)
				{
					fr.close();
				}
				if(reader != null)
				{
					reader.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void initItems()
	{
		// TODO
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
	public List<Item> getItemsInOrder(String orderNum) throws Exception
	{
		List<Item> items = new ArrayList<Item>();
		for(Item item: itemList)
		{
			if(item.getOrderNumber().equals(orderNum))
			{
				items.add(item);
			}
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
			item.setItemId(StringUtils.getRandomStringOfLettersAndNumbers(8));
		}
		if(items.get(item.getItemId()) == null)
		{
			items.put(item.getItemId(), item);
			itemList.add(item);
			itemNumbers.add(item.getItemId());
			return true;
		}
		return false;
	}

	@Override
	public boolean addItems(List<Item> items) throws Exception
	{
		throw new Exception("MUST IMPLEMENT");
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
		deleteItem(item.getItemId());
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
		itemNumbers.remove(itemId);
		return true;
	}

	@Override
	public List<Item> getItemsWithFilter(ItemFilter filter)
	{
		return filter.filter(new ArrayList<Item>(itemList));
	}

	@Override
	public List<Item> getItems(List<String> itemIds)
	{
		List<Item> items = new ArrayList<Item>();
		for(String id: itemIds)
		{
			items.add(this.items.get(id));
		}
		return items;
	}

}
