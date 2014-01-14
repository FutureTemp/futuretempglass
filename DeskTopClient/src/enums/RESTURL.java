package enums;

import core.Application;
import core.Application.Property;

public enum RESTURL{

	TASKS("/tasks"),
	TOKEN("/token"),
	LOGIN("/login");
	
	private String relativeUrl = "";
	
	private RESTURL(String relativeUrl)
	{
		this.relativeUrl = relativeUrl;
	}
	
	private String getBaseUrl()
	{
		return Application.getProperties().get(Property.BASE_URL.getPropertyName());
	}
	
	public String getUrl()
	{
		return getBaseUrl() + relativeUrl;
	}
}
