package storage.client;

import java.util.List;

import storage.ProductionStepsLibrary;
import workflow.ProductionStep;
import core.Client;

public class ClientProductionStepsLibrary extends ProductionStepsLibrary{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductionStep> getProductionSteps()
	{
		Object response = Client.sendMessageToServer("get production steps");
		return (List<ProductionStep>)response;
	}

}
