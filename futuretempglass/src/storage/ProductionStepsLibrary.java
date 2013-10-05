package storage;

import java.util.List;

import workflow.ProductionStep;
import xml.WorkFlowXml;

public class ProductionStepsLibrary{

	private static List<ProductionStep> steps;
	
	public static void init()
	{
		steps = WorkFlowXml.getProductionSteps();
	}
	
	public static List<ProductionStep> getProductionSteps()
	{
		return steps;
	}
}
