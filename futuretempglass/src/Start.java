import storage.ItemLibrary;
import ui.views.NewOrderWindow;

public class Start{

	
	private static void initLibraries()
	{
		ItemLibrary.init();
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		initLibraries();
		
		//Application application = new FutureTempApplication();
		new NewOrderWindow();
	}

}
