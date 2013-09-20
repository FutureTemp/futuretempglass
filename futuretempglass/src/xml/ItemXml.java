package xml;

import items.Item;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "item")
public class ItemXml{

	@XmlAttribute
	public String name;

	@XmlAttribute(name = "quantity")
	public int quantity = 1;

	@XmlElement(name = "attribute")
	public List<ItemAttributeXml> attributes = new ArrayList<ItemAttributeXml>();

	public Item getItem()
	{
		Item item = new Item(null);
		item.setName(name);
		item.setQuantity(quantity);
		Hashtable<String, String> attributes = new Hashtable<String, String>();
		if(this.attributes != null)
		{
			for(ItemAttributeXml attribute: this.attributes)
			{
				attributes.put(attribute.name, attribute.value);
			}
		}
		return item;
	}
}
