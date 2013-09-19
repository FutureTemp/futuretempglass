package framework;

import java.util.Hashtable;

public class Data{

	private Hashtable<String, Object> data;
	
	public Data(Hashtable<String, Object> data)
	{
		this.data = data;
	}
	
	public Data()
	{
		this(new Hashtable<String, Object>());
	}
	
	public Object getData(String name)
	{
		return data.get(name);
	}
	
	public void addData(String name, Object value)
	{
		data.put(name, value);
	}
	
	public Object removeData(String name)
	{
		return data.remove(name);
	}
	
}
