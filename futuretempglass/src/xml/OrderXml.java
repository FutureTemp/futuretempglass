package xml;

import items.Item;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import orders.Order;
import storage.JAXBHelper;

@XmlRootElement(name = "order")
public class OrderXml{

	final public static String ORDERS_PATH = "xml-orders/";

	@XmlAttribute(name = "orderNumber")
	public String orderNumber;

	@XmlElement(name = "item")
	public List<ItemXml> items = new ArrayList<ItemXml>();

	public OrderXml()
	{
		
	}
	
	public OrderXml(Order order)
	{
		this.orderNumber = order.getOrderNumber();
		for(Item item: order.getItems())
		{
			items.add(new ItemXml(item));
		}
	}
	
	public boolean saveOrder()
	{
		return JAXBHelper.writeToXmlFile(this, ORDERS_PATH + orderNumber
				+ ".xml");
	}

	public static OrderXml loadOrder(String orderNumber)
	{
		return (OrderXml) JAXBHelper.readFromXmlFile(ORDERS_PATH + orderNumber
				+ ".xml", OrderXml.class);
	}
	
	public Order getOrder()
	{
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		List<Item> items = new ArrayList<Item>();
		for(ItemXml itemXml: this.items)
		{
			Item item = itemXml.getItem();
			item.setOrder(order);
			items.add(item);
		}
		order.setItems(items);
		return order;
	}
}
