package items;

import java.util.ArrayList;
import java.util.List;

import workflow.ProductionStep;

public class ItemFilter{

	private ItemFilterType filterType;

	private String itemName;
	
	private String orderNumber;

	private List<String> attributeNames;

	private List<String> attributeValues;

	private List<ProductionStep> productionSteps;

	public ItemFilter(ItemFilterType filterType)
	{
		setFilterType(filterType);
	}

	public List<Item> filter(List<Item> items)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(!passesFilter(items.get(i)))
			{
				items.remove(i--);
			}
		}
		return items;
	}

	private boolean passesFilter(Item item)
	{
		switch (getFilterType())
		{
		case ITEM_NAME:
			return getItemName().equals(item.getItemName());
		case ATTRIBUTE:
			for(int i = 0; i < attributeNames.size(); i++)
			{
				if(!attributeValues.get(i).equals(
						item.getAttribute(attributeNames.get(i))))
				{
					return false;
				}
			}
			return true;
		case PRODUCTION_STEP:
			for(int i = 0; i < productionSteps.size(); i++)
			{
				if(item.getProductionSteps() == null
						|| !item.getProductionSteps().contains(
								getProductionStep()))
				{
					return false;
				}
			}
			return true;
		case ORDER_NUMBER:
			//TODO
			break;
		}
			
		return false;
	}

	/**
	 * @return the filterType
	 */
	public ItemFilterType getFilterType()
	{
		return filterType;
	}

	/**
	 * @param filterType
	 *            the filterType to set
	 */
	public void setFilterType(ItemFilterType filterType)
	{
		this.filterType = filterType;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName()
	{
		return attributeNames == null ? null : attributeNames.get(0);
	}

	/**
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName)
	{
		this.attributeNames = new ArrayList<String>();
		this.attributeNames.add(attributeName);
	}

	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue()
	{
		return attributeValues == null ? null : attributeValues.get(0);
	}

	/**
	 * @param attributeValue
	 *            the attributeValue to set
	 */
	public void setAttributeValue(String attributeValue)
	{
		this.attributeValues = new ArrayList<String>();
		this.attributeValues.add(attributeValue);
	}

	/**
	 * @return the productionStep
	 */
	public ProductionStep getProductionStep()
	{
		return productionSteps == null ? null : productionSteps.get(0);
	}

	/**
	 * @param productionStep
	 *            the productionStep to set
	 */
	public void setProductionStep(ProductionStep productionStep)
	{
		this.productionSteps = new ArrayList<ProductionStep>();
		this.productionSteps.add(productionStep);
	}

	/**
	 * @return the productionSteps
	 */
	public List<ProductionStep> getProductionSteps()
	{
		return productionSteps;
	}

	/**
	 * @param productionSteps
	 *            the productionSteps to set
	 */
	public void setProductionSteps(List<ProductionStep> productionSteps)
	{
		this.productionSteps = productionSteps;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber()
	{
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public static enum ItemFilterType{
		ITEM_NAME,
		ATTRIBUTE,
		PRODUCTION_STEP,
		ORDER_NUMBER

	}

}
