package storage;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBHelper{

	/**
	 * Instantiates a new instance of a JAXBContext object 
	 * @param xmlClass
	 * @return JAXBContext
	 */
	private static JAXBContext getJAXBContext(Class<?> xmlClass)
	{
		// Passing in package name where XML objects are held
		try
		{
			return JAXBContext.newInstance(xmlClass);
		}
		catch(JAXBException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the Unmarshaller object for a given class type
	 * @param xmlClass
	 * @return Unmarshaller
	 */
	private static Unmarshaller getUnmarshaller(Class<?> xmlClass)
	{
		try
		{
			return getJAXBContext(xmlClass).createUnmarshaller();
		}
		catch(JAXBException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the Marshaller object for a given class type
	 * @param xmlClass
	 * @return Marshaller
	 */
	private static Marshaller getMarshaller(Class<?> xmlClass)
	{
		try
		{
			Marshaller m = getJAXBContext(xmlClass).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			return m;
		}
		catch(JAXBException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads an XML file at the given path and returns object of the
	 * class specified
	 * @param filePath
	 * @param xmlClass
	 * @return the object read from the file
	 */
	public static Object readFromXmlFile(String filePath, Class<?> xmlClass)
	{
		File file = new File(filePath);
		try
		{
			if(file.exists())
			{
				return getUnmarshaller(xmlClass).unmarshal(file);
			}
		}
		catch(JAXBException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes an XML file at the specified file path using the given
	 * XML object
	 * @param xmlObject
	 * @param filePath
	 * @return
	 */
	public static boolean writeToXmlFile(Object xmlObject, String filePath)
	{
		File file = new File(filePath);
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		try
		{
			getMarshaller(xmlObject.getClass()).marshal(xmlObject, file);
			return true;
		}
		catch(JAXBException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
