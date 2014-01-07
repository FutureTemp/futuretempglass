package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class HTTPUtils{
	
	public static HashMap<String, String> parameterStringToHashMap(String parametersString)
	{
		HashMap<String, String> parameters = new HashMap<String, String>();
		String[] assignmentStrings = parametersString.split("&");
		for(String assignmentString: assignmentStrings)
		{
			String[] pair = assignmentString.split("=");
			if(pair.length == 2)
			{
				parameters.put(pair[0], pair[1]);
			}
			else if(pair.length == 1)
			{
				parameters.put(pair[0], "");
			}
		}
		return parameters;
	}

	public static String buildParametersString(
			HashMap<String, String> parameters) throws Exception
	{
		if(parameters == null)
		{
			return "";
		}
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for(String key: parameters.keySet())
		{
			if(!first)
			{
				builder.append("&");
			}
			builder.append(key);
			builder.append("=");
			builder.append(URLEncoder.encode(parameters.get(key), "UTF-8"));
		}
		return builder.toString();
	}

	public static String doGetRequest(String targetURL,
			HashMap<String, String> urlParameters,
			HashMap<String, String> headerInfo)
	{
		URL url;
		HttpURLConnection connection = null;
		try
		{
			String parameters = buildParametersString(urlParameters);

			// Create connection
			url = new URL(targetURL
					+ (StringUtils.isEmpty(parameters) ? "" : "?" + parameters));
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");

			if(headerInfo != null)
			{
				for(String key: headerInfo.keySet())
				{
					connection.setRequestProperty(key, headerInfo.get(key));
				}
			}

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(parameters.getBytes().length));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			connection.getOutputStream().flush();
			connection.getOutputStream().close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;

		}
		finally
		{

			if(connection != null)
			{
				connection.disconnect();
			}
		}
	}

	public static String doPostRequest(String targetURL,
			String content, HashMap<String, String> headerInfo)
	{
		URL url;
		HttpURLConnection connection = null;
		try
		{
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(content.getBytes().length));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Add header information
			if(headerInfo != null)
			{
				for(String key: headerInfo.keySet())
				{
					connection.setRequestProperty(key, headerInfo.get(key));
				}
			}
			
			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(content);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;

		}
		finally
		{

			if(connection != null)
			{
				connection.disconnect();
			}
		}
	}

	public static String doPutRequest(String targetURL,
			String content, HashMap<String, String> headerInfo)
	{
		URL url;
		HttpURLConnection connection = null;
		try
		{
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("PUT");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(content.getBytes().length));

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Add header information
			if(headerInfo != null)
			{
				for(String key: headerInfo.keySet())
				{
					connection.setRequestProperty(key, headerInfo.get(key));
				}
			}
			
			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(content);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;

		}
		finally
		{

			if(connection != null)
			{
				connection.disconnect();
			}
		}
	}
}
