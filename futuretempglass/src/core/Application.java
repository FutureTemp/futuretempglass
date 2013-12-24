package core;

import storage.AccountLibrary;
import storage.InventoryLibrary;
import storage.ItemLibrary;
import storage.OrderLibrary;
import storage.ProductionStepsLibrary;
import storage.database.DBAccountLibrary;
import storage.database.DBInventoryLibrary;
import storage.database.DBItemLibrary;
import storage.database.DBOrderLibrary;
import storage.database.DBProductionStepsLibrary;
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

	public static ItemLibrary getItemLibrary()
	{
		return itemLibrary;
	}

	public static ProductionStepsLibrary getProductionStepsLibrary()
	{
		return productionStepsLibrary;
	}

	public static InventoryLibrary getInventoryLibrary()
	{
		return inventoryLibrary;
	}

	public static OrderLibrary getOrderLibrary()
	{
		return orderLibrary;
	}

	public static AccountLibrary getAccountLibrary()
	{
		return accountLibrary;
	}

	public static void setDBLibraries()
	{
		itemLibrary = new DBItemLibrary();
		orderLibrary = new DBOrderLibrary();
		inventoryLibrary = new DBInventoryLibrary();
		accountLibrary = new DBAccountLibrary();
		productionStepsLibrary = new DBProductionStepsLibrary();
	}

	public static void setXMLLibraries()
	{
		itemLibrary = new XmlItemLibrary();
		orderLibrary = new XmlOrderLibrary();
		inventoryLibrary = new XmlInventoryLibrary();
		accountLibrary = new XmlAccountLibrary();
		productionStepsLibrary = new XmlProductionStepsLibrary();
	}
}