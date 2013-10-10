package items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import orders.Order;
import workflow.ProductionStep;
import xml.ProductionStepXml;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Order order;

	private String itemName;

	private int quantity;

	private List<ProductionStep> productionSteps;

	private List<ProductionStep> doneSteps;

	private ProductionStep currentStep;

	private List<String> attributeNames;

	private Hashtable<String, Object> attributes;

	public Item()
	{
		this(null);
	}

	public Item(Order order)
	{
		this.setOrder(order);
		attributeNames = new ArrayList<String>();
		attributes = new Hashtable<String, Object>();
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setName(String itemName)
	{
		this.itemName = itemName;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public void addProductionStep(ProductionStep productionStep)
	{
		productionSteps.add(productionStep);
	}

	public List<ProductionStep> getProductionSteps()
	{
		return productionSteps;
	}

	public void setProductionSteps(List<ProductionStep> productionSteps)
	{
		this.productionSteps = productionSteps;
	}

	public List<String> getAttributeNames()
	{
		return attributeNames;
	}

	public void setAttributeNames(List<String> attributeNames)
	{
		this.attributeNames = attributeNames;
	}

	public Object getAttribute(String attributeName)
	{
		return attributes.get(attributeName);
	}

	public void setAttribute(String attributeName, Object value)
	{
		attributes.put(attributeName, value);
		if(!attributeNames.contains(attributeName))
		{
			attributeNames.add(attributeName);
		}
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public void startStep(ProductionStep step) throws Exception
	{
		if(!productionSteps.contains(step))
		{
			throw new Exception("This item does not call for " + step.getName());
		}
		if(doneSteps.contains(step))
		{
			throw new Exception(step.getName() + " has already been completed");
		}
		if(currentStep != null)
		{
			throw new Exception(currentStep + " is already in progress");
		}
		List<ProductionStep> notDoneSteps = new ArrayList<ProductionStep>();
		for(ProductionStep dependency: step.getDependency())
		{
			if(!doneSteps.contains(dependency))
			{
				notDoneSteps.add(dependency);
			}
		}
		if(notDoneSteps.size() != 0)
		{
			throw new Exception("Cannot start " + step + ", before completing " + doneSteps);
		}
		currentStep = step;
	}

	public void finishCurrentStep()
	{
		doneSteps.add(currentStep);
		currentStep = null;
	}

	public boolean isDone(ProductionStep step)
	{
		return doneSteps.contains(step);
	}

	public ProductionStep getCurrentStep()
	{
		return currentStep;
	}

}
