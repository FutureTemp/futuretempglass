package workflow;

import java.util.ArrayList;
import java.util.List;

public class ProductionStep{

	private String name;
	
	private List<ProductionStep> dependencies = new ArrayList<ProductionStep>();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addDependency(ProductionStep dependency)
	{
		dependencies.add(dependency);
	}
	
}
