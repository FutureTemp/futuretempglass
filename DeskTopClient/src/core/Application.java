package core;

import java.util.HashMap;

import utils.PropertyUtils;

public class Application{

	private static HashMap<String, String> properties = new HashMap<String, String>();

	public static void init()
	{
		setProperties(PropertyUtils.loadProperties());
	}
	
	/**
	 * @return the properties
	 */
	public static HashMap<String, String> getProperties()
	{
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public static void setProperties(HashMap<String, String> properties)
	{
		Application.properties = properties;
	}
	
	public static enum Property
	{
		
		BASE_URL("baseUrl", "http://localhost:8080");
		
		private String name;
		
		private String defaultValue;
		
		private Property(String name, String defaultValue)
		{
			this.name = name;
			this.defaultValue = defaultValue;
		}
		
		public String getPropertyName()
		{
			return this.name;
		}
		
		public String getDefaultValue()
		{
			return this.defaultValue;
		}
	}
}
