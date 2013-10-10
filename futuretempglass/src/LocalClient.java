import storage.server.ServerItemLibrary;
import storage.server.ServerProductionStepsLibrary;
import ui.views.NewOrderWindow;
import core.Application;

public class LocalClient{

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Application.itemLibrary = new ServerItemLibrary();
		Application.productionStepsLibrary = new ServerProductionStepsLibrary();

		new NewOrderWindow();
	}

}
