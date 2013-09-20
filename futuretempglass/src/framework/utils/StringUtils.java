package framework.utils;

public class StringUtils{

	public static boolean isEmpty(String string)
	{
		return (string == null || string.trim().length() == 0);
	}
	
	public static boolean equalsIgnoreCase(String string1, String string2)
	{
		if(string1 == null && string2 == null)
		{
			return true;
		}
		if(string1 == null || string2 == null)
		{
			return false;
		}
		return string1.toLowerCase().equals(string2.toLowerCase());
	}
	
}
