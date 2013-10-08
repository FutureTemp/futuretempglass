import storage.client.ClientItemLibrary;
import ui.views.NewOrderWindow;
import core.Application;

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
