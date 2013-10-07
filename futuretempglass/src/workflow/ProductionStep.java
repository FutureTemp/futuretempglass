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

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof ProductionStep))
		{
			return false;
		}
		return ((ProductionStep)obj).getName().equals(getName());
	}

	@Override
	public String toString()
	{
		return getName();
	}

	public List<ProductionStep> getDependency()
	{
		return dependencies;
	}
}
