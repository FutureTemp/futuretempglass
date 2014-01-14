package rest;

import orders.Order;

import org.junit.Test;

import utils.HTTPUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddOrder{

	@Test
	public void addOrder() throws Exception
	{
		Order order = new Order();
		order.setOrderNumber("12345");
		order.setCustomer("Test Customer");
		ObjectMapper mapper = new ObjectMapper();
		String response = HTTPUtils.doPostRequest("http://localhost:8080/orders", mapper.writeValueAsString(order), null);
		System.out.println(response);
	}
	
}
