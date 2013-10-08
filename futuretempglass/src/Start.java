import core.Application;
import storage.client.ClientItemLibrary;
import storage.server.ServerItemLibrary;
import ui.views.NewOrderWindow;

public class Start{

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Application.itemLibrary = new ClientItemLibrary();
		
		new NewOrderWindow();
	}

}
