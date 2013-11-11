package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{

	public static List<String> getFileContents(String path)
			throws FileNotFoundException
	{
		List<String> contents = new ArrayList<String>();
		File file = new File(path);
		if(!file.exists())
		{
			throw new FileNotFoundException(path);
		}
		BufferedReader reader = null;
		FileReader fr = null;
		try
		{
			fr = new FileReader(file);
			reader = new BufferedReader(fr);
			String line = reader.readLine();
			while (line != null)
			{
				contents.add(line);
				line = reader.readLine();
			}
		}
		catch(Exception e)
		{
			contents = null;
		}
		finally
		{
			try
			{
				if(reader != null)
				{
					reader.close();
				}
				if(fr != null)
				{
					fr.close();
				}
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contents;
	}

}
