package storage.xml;

import java.util.List;

import storage.ProductionStepsLibrary;
import workflow.ProductionStep;
import xml.WorkFlowXml;

public class XmlProductionStepsLibrary extends ProductionStepsLibrary{

	private List<ProductionStep> steps;
	
	public XmlProductionStepsLibrary()
	{
		steps = WorkFlowXml.getProductionSteps();
	}
	
	public List<ProductionStep> getProductionSteps()
	{
		return steps;
	}
}
