package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import storage.JAXBHelper;
import enums.ProductionStep;

@XmlRootElement
public class ProductionStepsXml{

	private static final String PRODUCTION_STEPS_PATH = "factory/production-steps.xml";

	@XmlElement(name = "productionStep")
	public List<ProductionStep> productionSteps = new ArrayList<ProductionStep>();

	public static List<ProductionStep> getProductionSteps()
	{
		ProductionStepsXml stepsXml = (ProductionStepsXml)JAXBHelper
				.readFromXmlFile(PRODUCTION_STEPS_PATH,
						ProductionStepsXml.class);
		ArrayList<ProductionStep> steps = new ArrayList<ProductionStep>();
		if(stepsXml != null)
		{
			for(ProductionStep step: stepsXml.productionSteps)
			{
				steps.add(step);
			}
		}
		return steps;
	}
	
	public static void setProductionSteps(List<ProductionStep> productionSteps)
	{
		ProductionStepsXml stepsXml = new ProductionStepsXml();
		stepsXml.productionSteps = productionSteps;
		
	}
}
