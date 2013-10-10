package storage.server;

import java.util.List;

import storage.ProductionStepsLibrary;

import workflow.ProductionStep;
import xml.WorkFlowXml;

public class ServerProductionStepsLibrary extends ProductionStepsLibrary{

	private List<ProductionStep> steps;
	
	public ServerProductionStepsLibrary()
	{
		steps = WorkFlowXml.getProductionSteps();
	}
	
	public List<ProductionStep> getProductionSteps()
	{
		return steps;
	}
}
