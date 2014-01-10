package storage;

import java.util.List;

import workflow.ProductionStep;

public abstract class ProductionStepsLibrary{

	/**
	 * Gets a list of all the production steps in the the workflow
	 * @return List of ProductionSteps
	 * @throws Exception
	 */
	public abstract List<ProductionStep> getProductionSteps() throws Exception;
	
}
