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
		for(ProductionStep dependency: step.getDependencies())
		{
			dependcies.add(dependency.getName());
		}
	}
	
	public ProductionStep getProductionStep()
	{
		ProductionStep step = new ProductionStep();
		step.setName(this.name);
		for(String dependencyName: this.dependcies)
		{
			ProductionStep dependency = new ProductionStep();
			dependency.setName(dependencyName);
			step.addDependency(dependency);
		}
		return step;
	}

}
