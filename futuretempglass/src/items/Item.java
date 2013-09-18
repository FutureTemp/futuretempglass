package items;

import java.util.Hashtable;
import java.util.List;

import orders.Order;

import enums.ProductionSteps;

public class Item{

	private Order order;
	
	private String itemName;

	private int quantity;

	private List<ProductionSteps> productionSteps;
	
	private int testIntField;

	private Hashtable<String, String> attributes;

	public Item(Order order)
	{
		this.order = order;
	}
	
	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
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

	public void addProductionStep(ProductionSteps productionStep)
	{
		productionSteps.add(productionStep);
	}
	
	public List<ProductionSteps> getProductionSteps()
	{
		return productionSteps;
	}

	public void setProductionSteps(List<ProductionSteps> productionSteps)
	{
		this.productionSteps = productionSteps;
	}

	public Hashtable<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Hashtable<String, String> attributes)
	{
		this.attributes = attributes;
	}

}