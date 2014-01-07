package storage;

import java.util.List;

import workflow.ProductionStep;

public abstract class ProductionStepsLibrary{

	public abstract List<ProductionStep> getProductionSteps() throws Exception;
	
}
