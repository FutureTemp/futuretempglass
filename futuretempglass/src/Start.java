import framework.Application;
import implementation.FutureTempApplication;

import java.util.ArrayList;

import ui.views.NewOrderScreen;

import xml.ItemAttributeXml;
import xml.ItemXml;
import xml.OrderXml;

public class Start{

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		OrderXml order = new OrderXml();
		order.orderNumber = "12345";

		ItemXml item = new ItemXml();
		item.attributes = new ArrayList<ItemAttributeXml>();
		ItemAttributeXml attribute = new ItemAttributeXml();

		item.name = "glass";
		item.quantity = 5;

		attribute.name = "width";
		attribute.value = "5";
		item.attributes.add(attribute);

		attribute = new ItemAttributeXml();
		attribute.name = "height";
		attribute.value = "10";
		item.attributes.add(attribute);

		order.items.add(item);

		item = new ItemXml();
		item.name = "crate";

		attribute = new ItemAttributeXml();
		attribute.name = "material";
		attribute.value = "wood";

		item.attributes.add(attribute);

		order.items.add(item);

		order.saveOrder();

		order = OrderXml.loadOrder("12345");

		System.out.println("Items: " + order.items.size());
		for(ItemXml i: order.items)
		{
			System.out.println("Item name: " + i.name);
			System.out.println("\tItem quantity: " + i.quantity);
			for(ItemAttributeXml att: i.attributes)
			{
				System.out.println("\tItem " + att.name + ": " + att.value);
			}
		}
		Application application = new FutureTempApplication();
		application.startApplication(new NewOrderScreen(application));
	}

}
