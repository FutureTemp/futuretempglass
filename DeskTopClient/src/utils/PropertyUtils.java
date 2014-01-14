package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.Application.Property;

public class PropertyUtils{

	private static String propertiesPath = "/properties/properties.txt";
	
	public static HashMap<String, String> loadProperties()
	{
		HashMap<String, String> properties = new HashMap<String, String>();
		List<String> propertiesContents;
		File propertiesFile = new File(propertiesPath);
		if(!propertiesFile.exists())
		{
			FileUtils.createDirectoryAndFile(propertiesFile);
		}
		propertiesContents = FileUtils.getFileContents(propertiesFile);
		if(propertiesContents != null)
		{
			for(String line: propertiesContents)
			{
				String propertyName = line.substring(0, line.indexOf("="))
						.trim();
				properties.put(propertyName,
						line.substring(line.indexOf("=") + 1).trim());
			}
		}
		propertiesContents = new ArrayList<String>();
		for(Property property: Property.values())
		{
			String propertyValue = properties.get(property.getPropertyName());
			if(propertyValue == null)
			{
				String defaultValue = property.getDefaultValue();
				propertyValue = defaultValue;
				properties.put(property.getPropertyName(), propertyValue);
			}
			propertiesContents.add(property.getPropertyName() + "=" + propertyValue);
		}
		FileUtils.writeFile(propertiesFile, propertiesContents);
		return properties;
	}
	
}
