package xml;

import items.Item;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import storage.JAXBHelper;

@XmlRootElement(name = "inventory")
public class InventoryXml{
	private static final String INVENTORY_PATH = "factory/inventory.xml";
	@XmlElement(name = "item")
	public List<ItemXml> items;

	public static ArrayList<Item> getItems()
	{
		ArrayList<Item> items = new ArrayList<Item>();
		InventoryXml inventory = (InventoryXml)JAXBHelper.readFromXmlFile(
				INVENTORY_PATH, InventoryXml.class);
		for(ItemXml item: inventory.items)
		{
			items.add(item.getItem());
		}
		return items;
	}
}
