package orders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Order{

	private String orderNumber;

	private String customer;

	private Calendar dueDate;

	private List<String> itemIds = new ArrayList<String>();

	private boolean rush;

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public List<String> getItemIds()
	{
		return itemIds;
	}

	public void setItemIds(List<String> itemIds)
	{
		this.itemIds = itemIds;
	}

	public boolean isRush()
	{
		return rush;
	}

	public void setRush(boolean rush)
	{
		this.rush = rush;
	}

	public Calendar getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Calendar dueDate)
	{
		this.dueDate = dueDate;
	}

	public String getCustomer()
	{
		return customer;
	}

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
