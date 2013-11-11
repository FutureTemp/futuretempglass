package xml;

import items.Item;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import core.Application;

@XmlType(name = "item")
public class ItemXml{

	@XmlAttribute
	public String id;

	@XmlAttribute
	public String name;

	@XmlAttribute(name = "quantity")
	public int quantity = 1;

	@XmlElement
	public String orderId;

	@XmlElement(name = "attribute")
	public List<ItemAttributeXml> attributes = new ArrayList<ItemAttributeXml>();

	public Item getItem()
	{
		Item item = new Item(null);
		item.setItemId(id);
		item.setName(name);
		item.setQuantity(quantity);
		item.setOrder(Application.getOrderLibrary().getOrder(orderId));
		for(ItemAttributeXml itemAttribute: attributes)
		{
			item.setAttribute(itemAttribute.name, itemAttribute.value);
		}
		return item;
	}
}
