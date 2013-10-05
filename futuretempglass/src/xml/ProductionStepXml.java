package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "productionStep")
public class ProductionStepXml{

	@XmlAttribute(name = "name")
	public String name;

	@XmlElement(name = "dependency")
	public List<String> dependcies = new ArrayList<String>();

}
