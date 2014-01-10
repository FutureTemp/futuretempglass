package storage.database;

import items.Item;
import items.ItemFilter;

import java.util.ArrayList;
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
	public Item getItem(String id) throws Exception
	{
		DBResults results = DBHelper.queryDb("SELECT * FROM " + database + "."
				+ table + " WHERE " + idCol + "='" + id + "'");
		return dbResultsToItems(results).get(0);
	}

	@Override
	public List<Item> getItems() throws Exception
	{
		DBResults results = DBHelper.queryDb("SELECT * FROM " + database + "."
				+ table);

		return dbResultsToItems(results);
	}

	@Override
	public List<Item> getItemsInOrder(String orderNum) throws Exception
	{
		DBResults results = DBHelper.queryDb("SELECT * FROM " + database + "."
				+ table + " WHERE " + orderNumCol + " = " + orderNum);
		return dbResultsToItems(results);
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

	private List<Item> dbResultsToItems(DBResults results) throws Exception
	{
		List<Item> items = new ArrayList<Item>();

		while (results.next())
		{
			Item item = new Item();
			item.setItemId(results.getString(idCol));
			item.setOrderNumber(results.getString(orderNumCol));
			item.setItemName(results.getString(nameCol));
			item = stringToAttributes(results.getString(attributesCol), item);

			List<ProductionStep> productionSteps = Application
					.getProductionStepsLibrary().getProductionSteps();
			List<String> stepsStrings = StringUtils.stringToList(results
					.getString(productionStepsCol));
			String currentStep = results.getString(currentStepCol);
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
			first = false;
		}
		return builder.toString();
	}

	private Item stringToAttributes(String attributes, Item item)
	{
		if(StringUtils.isEmpty(attributes))
		{
			item.setAttributeNames(new ArrayList<String>());
			return item;
		}
		for(String attributeString: attributes.split(", "))
		{
			String[] attributePair = attributeString.split(":");
			item.getAttributeNames().add(attributePair[0]);
			if(attributePair.length != 1)
			{
				item.setAttribute(attributePair[0], attributePair[1]);
			}
		}
		return item;
	}

	@Override
	public List<Item> getItems(List<String> itemIds) throws Exception
	{
		StringBuilder builder = new StringBuilder("SELECT * FROM " + database
				+ "." + table + " WHERE ");
		boolean first = true;
		for(String itemId: itemIds)
		{
			if(!first)
			{
				builder.append(" OR ");
			}
			first = false;
			builder.append(idCol + " = '" + itemId + "'");
		}

		DBResults results = DBHelper.queryDb(builder.toString());
		if(results == null)
		{
			return null;
		}

		return dbResultsToItems(results);
	}

	@Override
	public boolean addItems(List<Item> items) throws Exception
	{
		for(Item item: items)
		{
			addItem(item);
		}
		return true;// TODO optimize
	}

	@Override
	public boolean deleteItems(List<String> itemIds)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
