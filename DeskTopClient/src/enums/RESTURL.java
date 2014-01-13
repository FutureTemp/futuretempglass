package enums;

public enum RESTURL{

	TASKS("/tasks");
	
	private static String baseUrl = "";
	
	private String relativeUrl = "";
	
	private RESTURL(String relativeUrl)
	{
		this.relativeUrl = relativeUrl;
	}
	
	public String getUrl()
	{
		return baseUrl + relativeUrl;
	}
}
