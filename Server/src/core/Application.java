package core;

import storage.AccountLibrary;
import storage.InventoryLibrary;
import storage.ItemLibrary;
import storage.OrderLibrary;
import storage.ProductionStepsLibrary;
import storage.TaskLibrary;
import storage.database.DBAccountLibrary;
import storage.database.DBInventoryLibrary;
import storage.database.DBItemLibrary;
import storage.database.DBOrderLibrary;
import storage.database.DBProductionStepsLibrary;
import storage.json.JSONAccountLibrary;
import storage.json.JSONItemLibrary;
import storage.json.JSONOrderLibrary;
import storage.json.JSONTaskLibrary;
import storage.xml.XmlAccountLibrary;
import storage.xml.XmlInventoryLibrary;
import storage.xml.XmlItemLibrary;
import storage.xml.XmlOrderLibrary;
import storage.xml.XmlProductionStepsLibrary;

public class Application{

	private static ItemLibrary itemLibrary;

	private static ProductionStepsLibrary productionStepsLibrary;

	private static InventoryLibrary inventoryLibrary;

	private static OrderLibrary orderLibrary;

	private static AccountLibrary accountLibrary;
	
	private static TaskLibrary taskLibrary;

	/**
	 * Returns the Item Library used for the application
	 * @return
	 */
	public static ItemLibrary getItemLibrary()
	{
		return itemLibrary;
	}

	/**
	 * Returns the Production Steps Library used for the application
	 * @return
	 */
	public static ProductionStepsLibrary getProductionStepsLibrary()
	{
		return productionStepsLibrary;
	}

	/**
	 * Returns the Inventory Library used for the application
	 * @return
	 */
	public static InventoryLibrary getInventoryLibrary()
	{
		return inventoryLibrary;
	}

	/**
	 * Returns the Order Library used for the application
	 * @return
	 */
	public static OrderLibrary getOrderLibrary()
	{
		return orderLibrary;
	}

	/**
	 * Returns the Account Library used for the application
	 * @return
	 */
	public static AccountLibrary getAccountLibrary()
	{
		return accountLibrary;
	}

	/**
	 * Returns the Task Library used for the application
	 * @return
	 */
	public static TaskLibrary getTaskLibrary()
	{
		return taskLibrary;
	}
	
	/**
	 * Populates the libraries with DB library implementations
	 */
	public static void setDBLibraries()
	{
		itemLibrary = new DBItemLibrary();
		orderLibrary = new DBOrderLibrary();
		inventoryLibrary = new DBInventoryLibrary();
		accountLibrary = new DBAccountLibrary();
		productionStepsLibrary = new DBProductionStepsLibrary();
	}

	/**
	 * Populates the libraries with XML library implementations
	 */
	public static void setXMLLibraries()
	{
		itemLibrary = new XmlItemLibrary();
		orderLibrary = new XmlOrderLibrary();
		inventoryLibrary = new XmlInventoryLibrary();
		accountLibrary = new XmlAccountLibrary();
		productionStepsLibrary = new XmlProductionStepsLibrary();
	}
	
	/**
	 * Populates the libraries with JSON library implementations
	 */
	public static void setJSONLibraries()
	{
		inventoryLibrary = new XmlInventoryLibrary();
		accountLibrary = new JSONAccountLibrary();
		productionStepsLibrary = new XmlProductionStepsLibrary();
		orderLibrary = new JSONOrderLibrary();
		itemLibrary = new JSONItemLibrary();
		taskLibrary = new JSONTaskLibrary();
	}
}