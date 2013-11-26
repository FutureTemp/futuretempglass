package orders;

import java.util.List;

public class OrderFilter{

	private OrderFilterEnum filterType;
	
	private String orderNumber;
	
	private String customer;
	
	public OrderFilter(OrderFilterEnum filterType)
	{
		setFilterType(filterType);
	}
	
	
	/**
	 * @return the filterType
	 */
	public OrderFilterEnum getFilterType()
	{
		return filterType;
	}


	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(OrderFilterEnum filterType)
	{
		this.filterType = filterType;
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


	/**
	 * @return the customer
	 */
	public String getCustomer()
	{
		return customer;
	}


	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer)
	{
		this.customer = customer;
	}

	public List<Order> filter(List<Order> orders)
	{
		for(int i = 0; i < orders.size(); i++)
		{
			if(!orderPassesFilter(orders.get(i)))
			{
				orders.remove(i--);
			}
		}
		return orders;
	}

	private boolean orderPassesFilter(Order order)
	{
		switch(getFilterType())
		{
		case CUSTOMER:
			return customer.equals(order.getCustomer());
		case ORDER_NUMBER:
			return orderNumber.equals(order.getOrderNumber());
		default:
			return false;
		}
	}
	
	public static enum OrderFilterEnum{

		PRODUCTION_STEP,
		PRODUCTION_STEPS,
		ATTRIBUTE,
		ORDER_NUMBER,
		ATTRIBUTES,
		CUSTOMER

	}

}
