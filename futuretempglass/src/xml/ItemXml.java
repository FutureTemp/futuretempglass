package xml;

import java.util.ArrayList;
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
}
