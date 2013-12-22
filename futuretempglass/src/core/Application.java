package core;
import storage.AccountLibrary;
import storage.InventoryLibrary;
import storage.ItemLibrary;
import storage.OrderLibrary;
import storage.ProductionStepsLibrary;


public class Application
{
	
	public static ItemLibrary itemLibrary;
	
	public static ProductionStepsLibrary productionStepsLibrary;
	
	public static InventoryLibrary inventoryLibrary;
	
	public static OrderLibrary orderLibrary;
	
	public static AccountLibrary accountLibrary;
	
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
	
}