package xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "attribute")
public class ItemAttributeXml{

	@XmlAttribute(name = "name")
	public String name;

	@XmlAttribute(name = "value")
	public String value;

}
