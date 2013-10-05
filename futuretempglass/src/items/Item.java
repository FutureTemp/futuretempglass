package items;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import orders.Order;
import xml.ProductionStepXml;

public class Item{

	private Order order;

	private String itemName;

	private int quantity;

	private List<ProductionStepXml> productionSteps;

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

	public void addProductionStep(ProductionStepXml productionStep)
	{
		productionSteps.add(productionStep);
	}

	public List<ProductionStepXml> getProductionSteps()
	{
		return productionSteps;
	}

	public void setProductionSteps(List<ProductionStepXml> productionSteps)
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

}
