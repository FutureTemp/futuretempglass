package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{

	public static void writeFile(String path, List<String> contents)
	{
		File file = new File(path);
		writeFile(file, contents);
	}

	public static boolean writeFile(File file, List<String> contents)
	{
		BufferedWriter writer = null;
		FileWriter fw = null;
		try
		{
			if(!file.exists())
			{
				file.createNewFile();
			}
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			for(String line: contents)
			{
				writer.write(line);
				writer.newLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(writer != null)
				{
					writer.close();
				}
				if(fw != null)
				{
					fw.close();
				}
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public static List<String> getFileContents(String path)
	{
		File file = new File(path);
		return getFileContents(file);
	}

	public static List<String> getFileContents(File file)
	{
		if(!file.exists())
		{
			return null;
		}
		BufferedReader reader = null;
		FileReader fr = null;
		List<String> contents = new ArrayList<String>();
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
			e.printStackTrace();
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
	
	public static boolean deleteFile(String path)
	{
		File file = new File(path);
		return deleteFile(file);
	}
	
	public static boolean deleteFile(File file)
	{
		if(!file.exists())
		{
			return false;
		}
		return file.delete();
	}

}
