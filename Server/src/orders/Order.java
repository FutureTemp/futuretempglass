package orders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order{

	private String orderNumber;

	private String customer;

	private Calendar dueDate;

	private List<String> itemIds = new ArrayList<String>();

	/**
	 * @return orderNumber
	 */
	public String getOrderNumber()
	{
		return orderNumber;
	}

	/**
	 * Sets the order number
	 * @param orderNumber
	 */
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	/**
	 * Returns the list of itemId's associated with this order
	 * @return itemIds
	 */
	public List<String> getItemIds()
	{
		return itemIds;
	}

	/**
	 * Sets the list of associated itemId's
	 * @param itemIds
	 */
	public void setItemIds(List<String> itemIds)
	{
		this.itemIds = itemIds;
	}

	/**
	 * @return dueDate
	 */
	public Calendar getDueDate()
	{
		return dueDate;
	}

	/**
	 * Sets the due date
	 * @param dueDate
	 */
	public void setDueDate(Calendar dueDate)
	{
		this.dueDate = dueDate;
	}

	/**
	 * Returns the customer associated with this order
	 * @return customer
	 */
	public String getCustomer()
	{
		return customer;
	}

	/**
	 * Sets the customer associated with this order
	 * @param customer
	 */
	public void setCustomer(String customer)
	{
		this.customer = customer;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Order))
		{
			return false;
		}
		Order order = (Order)obj;
		if(order.getOrderNumber() == null || getOrderNumber() == null)
		{
			return super.equals(obj);
		}
		return order.getOrderNumber().equals(getOrderNumber());
	}
}
