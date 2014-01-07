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
		Application.setJSONLibraries();

		new WorkFlowWindow(null);
		new OrderSearchWindow();
	}

}
