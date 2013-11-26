package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import workflow.ProductionStep;

@XmlType(name = "productionStep")
public class ProductionStepXml{

	@XmlAttribute(name = "name")
	public String name;

	@XmlElement(name = "dependency")
	public List<String> dependcies = new ArrayList<String>();
	
	public ProductionStepXml()
	{
		
	}
	
	public ProductionStepXml(ProductionStep step)
	{
		this.name = step.getName();
		this.dependcies = step.getDependencies();
	}
	
	public ProductionStep getProductionStep()
	{
		ProductionStep step = new ProductionStep();
		step.setName(this.name);
		step.setDependencies(this.dependcies);
		return step;
	}

}
