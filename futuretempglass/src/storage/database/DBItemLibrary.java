package storage.database;

import items.Item;
import items.ItemFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import storage.ItemLibrary;
import utils.StringUtils;
import workflow.ProductionStep;
import core.Application;

public class DBItemLibrary extends ItemLibrary{

	private static String database = "futuretemp";
	private static String table = "items";
	private static String idCol = "id";
	private static String nameCol = "name";
	private static String orderNumCol = "order_number";
	private static String attributesCol = "attributes";
	private static String productionStepsCol = "production_steps";
	private static String currentStepCol = "current_step";

	@Override
	public Item getItem(String id)
	{
		HashMap<String, List<String>> results = DBHelper
				.queryDb("SELECT * FROM " + database + "." + table + " WHERE "
						+ idCol + "='" + id + "'");
		return hashmapToItems(results).get(0);
	}

	@Override
	public List<Item> getItems()
	{
		HashMap<String, List<String>> results = DBHelper
				.queryDb("SELECT * FROM " + database + "." + table);

		return hashmapToItems(results);
	}

	@Override
	public boolean addItem(Item item)
	{
		if(item.getItemId() == null)
		{
			item.setItemId(getAvailableId());
		}
		String query = "INSERT INTO `" + database + "`.`" + table + "` (`"
				+ idCol + "`, `" + nameCol + "`, `" + orderNumCol + "`, `"
				+ attributesCol + "`, `" + productionStepsCol + "`, `"
				+ currentStepCol + "`) VALUES ('" + item.getItemId() + "', '"
				+ item.getItemName() + "', '" + item.getOrderNumber() + "', '"
				+ attributesToString(item) + "', '"
				+ StringUtils.listToString(item.getProductionSteps()) + "', '"
				+ item.getCurrentStep() + "');";
		return DBHelper.writeToDb(query);
	}

	@Override
	public boolean updateItem(Item item)
	{
		String query = "UPDATE `" + database + "`.`" + table + "` SET `"
				+ nameCol + "`='" + item.getItemName() + "', `" + orderNumCol
				+ "`='" + item.getOrderNumber() + "', `" + attributesCol
				+ "`='" + attributesToString(item) + "', `"
				+ productionStepsCol + "`='"
				+ StringUtils.listToString(item.getProductionSteps()) + "', `"
				+ currentStepCol + "`='" + item.getCurrentStep() + "' WHERE `"
				+ idCol + "`='" + item.getItemId() + "';";
		return DBHelper.writeToDb(query);
	}

	@Override
	public boolean deleteItem(Item item)
	{
		return deleteItem(item.getItemId());
	}

	@Override
	public boolean deleteItem(String itemId)
	{
		String query = "DELETE FROM `" + database + "`.`" + table + "` WHERE `"
				+ idCol + "`='" + itemId + "';";
		return DBHelper.writeToDb(query);
	}

	@Override
	public List<Item> getItemsWithFilter(ItemFilter filter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private List<Item> hashmapToItems(HashMap<String, List<String>> hashmap)
	{
		List<Item> items = new ArrayList<Item>();

		for(int i = 0; i < hashmap.get(idCol).size(); i++)
		{
			Item item = new Item();
			item.setItemId(hashmap.get(idCol).get(i));
			item.setName(hashmap.get(nameCol).get(i));
			item.setOrderNumber(hashmap.get(orderNumCol).get(i));

			List<String> stepsStrings = StringUtils.stringToList(hashmap.get(
					productionStepsCol).get(i));

			String currentStep = hashmap.get(currentStepCol).get(i);

			List<ProductionStep> productionSteps = Application
					.getProductionStepsLibrary().getProductionSteps();
			for(ProductionStep step: productionSteps)
			{
				if(stepsStrings.contains(step.getName()))
				{
					item.addProductionStep(step);
					if(step.getName().equals(currentStep))
					{
						item.setCurrentStep(step);
					}
				}
			}

			String attributesString = hashmap.get(attributesCol).get(i);
			if(StringUtils.isEmpty(attributesString))
			{
				item.setAttributeNames(new ArrayList<String>());
			}
			else
			{
				item = stringToAttributes(attributesString, item);
			}
			items.add(item);
		}

		return items;
	}

	private String attributesToString(Item item)
	{
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for(String attribute: item.getAttributeNames())
		{
			if(!first)
			{
				builder.append(", ");
			}
			builder.append(attribute + ":" + item.getAttribute(attribute));
		}
		return builder.toString();
	}

	private Item stringToAttributes(String attributes, Item item)
	{
		if(attributes == null)
		{
			item.setAttributeNames(new ArrayList<String>());
			return item;
		}
		for(String attributeString: attributes.split(", "))
		{
			String[] attributePair = attributeString.split(":");
			item.getAttributeNames().add(attributePair[0]);
			item.setAttribute(attributePair[0], attributePair[1]);
		}
		return item;
	}

	@Override
	public List<Item> getItems(List<String> itemIds)
	{
		StringBuilder builder = new StringBuilder("SELECT * FROM '" + database
				+ "'.'" + table + "' WHERE ");
		boolean first = true;
		for(String itemId: itemIds)
		{
			if(!first)
			{
				builder.append(" OR ");
			}
			first = false;
			builder.append("'" + idCol + "' = '" + itemId + "'");
		}
		
		HashMap<String, List<String>> hashmap = DBHelper.queryDb(builder.toString());
		if(hashmap == null)
		{
			return null;
		}
		
		return hashmapToItems(hashmap);
	}
}
