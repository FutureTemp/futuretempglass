package storage.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import storage.ProductionStepsLibrary;
import workflow.ProductionStep;

public class DBProductionStepsLibrary extends ProductionStepsLibrary{

	private final static String database = "futuretemp";
	private final static String table = "production_steps";
	private final static String nameRow = "name";
	private final static String dependenciesRow = "dependencies";

	@Override
	public List<ProductionStep> getProductionSteps()
	{
		HashMap<String, List<String>> results = DBHelper
				.queryDb("SELECT * FROM " + database + "." + table);
		return hashmapToProductionSteps(results);
	}

	private List<ProductionStep> hashmapToProductionSteps(
			HashMap<String, List<String>> hashmap)
	{
		List<ProductionStep> steps = new ArrayList<ProductionStep>();

		for(int i = 0; i < hashmap.get(nameRow).size(); i++)
		{
			ProductionStep step = new ProductionStep();
			step.setName(hashmap.get(nameRow).get(i));
			String dependencies = hashmap.get(dependenciesRow).get(i);
			if(dependencies != null)
			{
				step.setDependencies(Arrays.asList(dependencies.split(",")));
			}
			steps.add(step);
		}

		return steps;
	}

}
