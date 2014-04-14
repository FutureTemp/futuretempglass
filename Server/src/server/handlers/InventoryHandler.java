package server.handlers;

import items.Item;
import server.Server;
import utils.InventoryUtils;
import utils.StringUtils;

import com.sun.net.httpserver.HttpExchange;

public class InventoryHandler extends ServerHandler{

	public InventoryHandler(Server server)
	{
		super(server);
	}

	private static String context = "/inventory";
	
	public static String getContext()
	{
		return context;
	}
	
	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		sendHeader(ex);
		String context = ex.getRequestURI().getPath();
		switch(InventoryContext.getInventoryContext(context))
		{
		case ALL_INVENTORY:
			String itemName = getParameters(ex).get("itemName");
			if(StringUtils.isEmpty(itemName))
			{
			sendResponse(InventoryUtils.getAllInventory(), ex);
			}
			else
			{
				Item item = InventoryUtils.getItem(itemName);
				sendResponse(item, ex);
			}
			break;
		case INVENTORY_NAMES:
			sendResponse(InventoryUtils.getInventoryNames(), ex);
			break;
		}
	}
	
	private enum InventoryContext
	{
		
		ALL_INVENTORY("/inventory"),
		INVENTORY_NAMES("/inventoryNames");
		
		private String context = "";
		
		private InventoryContext(String context)
		{
			this.context = context;
		}
		
		public String getContext()
		{
			return context;
		}
		
		public static InventoryContext getInventoryContext(String context)
		{
			for(InventoryContext inventoryContext: values())
			{
				if(inventoryContext.getContext().equals(context))
				{
					return inventoryContext;
				}
			}
			return null;
		}
	}
}
