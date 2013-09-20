package items;

import java.util.Hashtable;
import java.util.List;

import orders.Order;

import enums.ProductionStep;

public class Item{

	private Order order;

	private String itemName;

	private int quantity;

	private List<ProductionStep> productionSteps;

	private Hashtable<String, String> attributes;

	public Item(Order order)
	{
		this.setOrder(order);
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

	public Hashtable<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Hashtable<String, String> attributes)
	{
		this.attributes = attributes;
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
