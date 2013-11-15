import storage.server.ServerInventoryLibrary;
import storage.server.ServerItemLibrary;
import storage.server.ServerOrderLibrary;
import storage.server.ServerProductionStepsLibrary;
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
		Application.orderLibrary = new ServerOrderLibrary();
		Application.itemLibrary = new ServerItemLibrary();
		Application.productionStepsLibrary = new ServerProductionStepsLibrary();
		Application.inventoryLibrary = new ServerInventoryLibrary();

		new OrderSearchWindow();
		new WorkFlowWindow(null);
	}

}
