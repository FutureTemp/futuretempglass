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
	public List<String> productionSteps = new ArrayList<String>();

	public static List<ProductionStep> getProductionSteps()
	{
		ProductionStepsXml stepsXml = (ProductionStepsXml)JAXBHelper
				.readFromXmlFile(PRODUCTION_STEPS_PATH,
						ProductionStepsXml.class);
		ArrayList<ProductionStep> steps = new ArrayList<ProductionStep>();
		if(stepsXml != null)
		{
			for(String step: stepsXml.productionSteps)
			{
				steps.add(ProductionStep.getProductionStep(step));
			}
		}
		return steps;
	}
}
