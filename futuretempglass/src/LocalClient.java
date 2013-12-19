import storage.server.XmlInventoryLibrary;
import storage.server.XmlItemLibrary;
import storage.server.XmlOrderLibrary;
import storage.server.XmlProductionStepsLibrary;
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
		Application.orderLibrary = new XmlOrderLibrary();
		Application.itemLibrary = new XmlItemLibrary();
		Application.productionStepsLibrary = new XmlProductionStepsLibrary();
		Application.inventoryLibrary = new XmlInventoryLibrary();

		new WorkFlowWindow(null);
		new OrderSearchWindow();
	}

}
