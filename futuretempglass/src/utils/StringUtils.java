package utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils{

	public static char getRandomLetterOrNumber()
	{
		int randomNum = (int)(Math.random() * 62)+1;
		if(randomNum > 36)
		{
			randomNum += 60;
		}
		else if(randomNum > 10)
		{
			randomNum += 54;
		}
		else
		{
			randomNum += 47;
		}
		return (char)randomNum;// 48-57, 65-90, 97-122
		// 10+26+26
	}

	public static String getRandomeStringOfLettersAndNumbers(int length)
	{
		String random = "";
		for(int i = 0; i < length; i++)
		{
			random += getRandomLetterOrNumber();
		}
		return random;
	}
	
	public static void main(String args[])
	{
		List<String> strings = new ArrayList<String>();
		while(true)
		{
			String string = getRandomeStringOfLettersAndNumbers(8);
			if(strings.contains(string))
			{
				break;
			}
			//System.out.println(string);
			strings.add(string);
		}
		System.out.println(strings.size());
	}

}
