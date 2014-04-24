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

	/**
	 * Writes a file at the given path with the contents provided. The contents
	 * is a List of Strings, each String representing a line in the file.
	 * @param path
	 * @param contents
	 * @return true if successful, false otherwise
	 */
	public static boolean writeFile(String path, List<String> contents)
	{
		File file = new File(path);
		return writeFile(file, contents);
	}

	/**
	 * Writes the contents to a file at the path specified
	 * @param path
	 * @param contents
	 * @return true if successful, false otherwise
	 */
	public static boolean writeFile(String path, String contents)
	{
		List<String> fileContents = new ArrayList<String>();
		fileContents.add(contents);
		return writeFile(path, fileContents);
	}

	/**
	 * Writes to the file provided with the contents provided. The contents
	 * is a List of String where each String is a line in the file
	 * @param file
	 * @param contents
	 * @return true if successful, false otherwise
	 */
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

	/**
	 * Gets the file contents at the path specified, and returns
	 * it as a list of Strings where each String is a line in the file
	 * @param path
	 * @return List of Strings
	 */
	public static List<String> getFileContents(String path)
	{
		File file = new File(path);
		return getFileContents(file);
	}

	/**
	 * Gets the file contents of the file provided and returns
	 * it as a list of Strings where each String is a line in the file
	 * @param file
	 * @return List of Strings
	 */
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

	/**
	 * Deletes the file at the specified path
	 * @param path
	 * @return true if successful, false otherwise
	 */
	public static boolean deleteFile(String path)
	{
		File file = new File(path);
		return deleteFile(file);
	}

	/**
	 * Deletes the file provided
	 * @param file
	 * @return true if successful, false otherwise
	 */
	public static boolean deleteFile(File file)
	{
		if(!file.exists())
		{
			return false;
		}
		return file.delete();
	}

	/**
	 * Creates the file provided and the directory as will if needed
	 * @param file
	 * @return true if successful, false otherwise
	 */
	public static boolean createDirectoryAndFile(File file)
	{
		boolean result = false;
		if(!file.exists())
		{
			File dir = file.getParentFile();
			result = dir.mkdirs();
			if(result)
			{
				try
				{
					result = file.createNewFile();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * Creates a file at the filePath provided. Also creates
	 * the directory if necessary
	 * @param filePath
	 * @return true if successful, false otherwise
	 */
	public static boolean createDirectoryAndFile(String filePath)
	{
		return createDirectoryAndFile(new File(filePath));
	}

}
