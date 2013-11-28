package storage.database;

import java.util.ArrayList;
import java.util.List;

import storage.ProductionStepsLibrary;
import utils.StringUtils;
import workflow.ProductionStep;

public class DBProductionStepsLibrary extends ProductionStepsLibrary{

	private final static String database = "futuretemp";
	private final static String table = "production_steps";
	private final static String nameRow = "name";
	private final static String dependenciesRow = "dependencies";

	@Override
	public List<ProductionStep> getProductionSteps() throws Exception
	{
		DBResults results = DBHelper
				.queryDb("SELECT * FROM " + database + "." + table);
		return dbResultsToProductionSteps(results);
	}

	private List<ProductionStep> dbResultsToProductionSteps(DBResults results) throws Exception
	{
		List<ProductionStep> steps = new ArrayList<ProductionStep>();

		while(results.next())
		{
			ProductionStep step = new ProductionStep();
			step.setName(results.getString(nameRow));
			step.setDependencies(StringUtils.stringToList(results.getString(dependenciesRow)));
			steps.add(step);
		}

		return steps;
	}

}
