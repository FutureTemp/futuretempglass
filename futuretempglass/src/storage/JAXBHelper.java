package storage;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import xml.OrderXml;

public class JAXBHelper{

	private static JAXBContext getJAXBContext(Class xmlClass)
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

	private static Unmarshaller getUnmarshaller(Class xmlClass)
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

	private static Marshaller getMarshaller(Class xmlClass)
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

	public static Object readFromXmlFile(String filePath, Class xmlClass)
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
