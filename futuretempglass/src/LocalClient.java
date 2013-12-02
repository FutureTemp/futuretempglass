import storage.database.DBInventoryLibrary;
import storage.database.DBItemLibrary;
import storage.database.DBOrderLibrary;
import storage.database.DBProductionStepsLibrary;
import ui.views.OrderSearchWindow;
import ui.views.WorkFlowWindow;
import core.Application;

public class LocalClient{

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Application.orderLibrary = new DBOrderLibrary();
		Application.itemLibrary = new DBItemLibrary();
		Application.productionStepsLibrary = new DBProductionStepsLibrary();
		Application.inventoryLibrary = new DBInventoryLibrary();

		new WorkFlowWindow(null);
		new OrderSearchWindow();
	}

}
