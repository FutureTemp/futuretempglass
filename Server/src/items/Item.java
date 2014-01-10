package items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import workflow.ProductionStep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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

	private List<String> attributeNames = new ArrayList<String>();

	private Hashtable<String, Object> attributes = new Hashtable<String, Object>();

	public Item()
	{
		this(null);
	}

	public Item(String orderId)
	{
		this.setOrderNumber(orderId);
	}
	
	/**
	 * @return itemId
	 */
	public String getItemId()
	{
		return id;
	}
	
	/**
	 * Sets the Item's ID
	 * @param id
	 */
	public void setItemId(String id)
	{
		this.id = id;
	}

	/**
	 * @return itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * Sets itemName
	 * @param itemName
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * @return quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Sets quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Adds a production step to the list of production steps
	 * @param productionStep
	 */
	public void addProductionStep(ProductionStep productionStep)
	{
		productionSteps.add(productionStep);
	}

	/**
	 * @return productionSteps
	 */
	public List<ProductionStep> getProductionSteps()
	{
		return productionSteps;
	}

	/**
	 * Sets the list of production steps
	 * @param productionSteps
	 */
	public void setProductionSteps(List<ProductionStep> productionSteps)
	{
		this.productionSteps = productionSteps;
	}

	/**
	 * Sets the current production step
	 * @param step
	 */
	public void setCurrentStep(ProductionStep step)
	{
		this.currentStep = step;
	}
	
	/**
	 * Returns the list of attribute names associated with this item
	 * @return attributeNames
	 */
	public List<String> getAttributeNames()
	{
		return attributeNames;
	}
	
	/**
	 * Returns the hashmap of the attribute name and value pairs
	 * for this item
	 * @return attributes
	 */
	public Hashtable<String, Object> getAttributes()
	{
		return attributes;
	}

	/**
	 * Sets the list of attribute names
	 * @param attributeNames
	 */
	public void setAttributeNames(List<String> attributeNames)
	{
		this.attributeNames = attributeNames;
	}

	/**
	 * Returns the value of the attribute with the given attribute name
	 * @param attributeName
	 * @return
	 */
	public Object getAttribute(String attributeName)
	{
		return attributes.get(attributeName);
	}

	/**
	 * Sets an attribute
	 * @param attributeName
	 * @param value
	 */
	public void setAttribute(String attributeName, Object value)
	{
		attributes.put(attributeName, value);
		if(!attributeNames.contains(attributeName))
		{
			attributeNames.add(attributeName);
		}
	}

	/**
	 * Returns the order number that this item is associated with
	 * @return orderNumber
	 */
	public String getOrderNumber()
	{
		return orderNumber;
	}

	/**
	 * Sets the order number
	 * @param orderId
	 */
	public void setOrderNumber(String orderId)
	{
		this.orderNumber = orderId;
	}

	/**
	 * Moves the current step to the list
	 * of finished steps
	 */
	public void finishCurrentStep()
	{
		doneSteps.add(currentStep);
	}

	/**
	 * Checks if a certain step has been done
	 * @param step
	 * @return
	 */
	public boolean isDone(ProductionStep step)
	{
		return doneSteps.contains(step);
	}

	/**
	 * Returns the currentStep,
	 * if currentStep is null, it sets it as the first
	 * given step in the productionSteps list
	 * @return currentStep
	 */
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
