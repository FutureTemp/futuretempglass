package core;

import utils.LoginUtils;

public class Client{

	public static void main(String[] args) throws Exception
	{
		Application.init();
		LoginUtils.login("francesco", "12345");
	}
	
}
