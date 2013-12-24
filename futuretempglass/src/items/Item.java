package items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import workflow.ProductionStep;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String itemName;

	private String orderNumber;

	private int quantity;

	private List<ProductionStep> productionSteps = new ArrayList<ProductionStep>();

	private List<ProductionStep> doneSteps;

	private ProductionStep currentStep;

	private List<String> attributeNames;

	private Hashtable<String, Object> attributes;

	public Item()
	{
		this(null);
	}

	public Item(String orderId)
	{
		this.setOrderNumber(orderId);
		attributeNames = new ArrayList<String>();
		attributes = new Hashtable<String, Object>();
	}
	
	public String getItemId()
	{
		return id;
	}
	
	public void setItemId(String id)
	{
		this.id = id;
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

	public void setCurrentStep(ProductionStep step)
	{
		this.currentStep = step;
	}
	
	public List<String> getAttributeNames()
	{
		return attributeNames;
	}
	
	public Hashtable<String, Object> getAttributes()
	{
		return attributes;
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

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderId)
	{
		this.orderNumber = orderId;
	}

	public void startStep(ProductionStep step) throws Exception
	{
		//TODO
	}

	public void finishCurrentStep()
	{
		doneSteps.add(currentStep);
	}

	public boolean isDone(ProductionStep step)
	{
		return doneSteps.contains(step);
	}

	public ProductionStep getCurrentStep()
	{
		if(currentStep == null)
		{
			if(getProductionSteps() == null || getProductionSteps().size() == 0)
			{
				return null;
			}
			currentStep = getProductionSteps().get(0);
		}
		return currentStep;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Item))
		{
			return false;
		}
		Item item = (Item)obj;
		if(item.getItemId() == null || getItemId() == null)
		{
			return false;
		}
		return item.getItemId().equals(getItemId());
	}
}
