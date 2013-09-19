package orders;

import items.Item;

import java.util.Calendar;
import java.util.List;

public class Order{

	private String orderNumber;

	private String customer;

	private Calendar dueDate;

	private List<Item> items;

	private boolean rush;

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public List<Item> getItems()
	{
		return items;
	}

	public void setItems(List<Item> items)
	{
		this.items = items;
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

}
