package core;
import storage.ItemLibrary;
import storage.ProductionStepsLibrary;


public class Application
{
	
	public static ItemLibrary itemLibrary;
	
	public static ProductionStepsLibrary productionStepsLibrary;
	
	public static ItemLibrary getItemLibrary()
	{
		return itemLibrary;
	}
	
	public static ProductionStepsLibrary getProductionStepsLibrary()
	{
		return productionStepsLibrary;
	}
	
}