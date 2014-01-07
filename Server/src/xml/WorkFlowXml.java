package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import storage.JAXBHelper;
import workflow.ProductionStep;

@XmlRootElement(name = "workFlow")
public class WorkFlowXml{

	private static final String PRODUCTION_STEPS_PATH = "factory/production-steps.xml";

	@XmlElement(name = "productionStep")
	public List<ProductionStepXml> productionSteps = new ArrayList<ProductionStepXml>();

	public static List<ProductionStep> getProductionSteps()
	{
		WorkFlowXml workFlow = (WorkFlowXml)JAXBHelper.readFromXmlFile(
				PRODUCTION_STEPS_PATH, WorkFlowXml.class);
		
		List<ProductionStep> steps = new ArrayList<ProductionStep>();
		for(ProductionStepXml stepXml: workFlow.productionSteps)
		{
			ProductionStep step = new ProductionStep();
			step.setName(stepXml.name);
			step.setDependencies(stepXml.dependcies);
			steps.add(step);
		}
		
		return steps;
	}

	public static void setProductionSteps(
			List<ProductionStepXml> productionSteps)
	{
		WorkFlowXml stepsXml = new WorkFlowXml();
		stepsXml.productionSteps = productionSteps;

	}
}
