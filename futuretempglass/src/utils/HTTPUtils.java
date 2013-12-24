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
			connection.setRequestProperty("Content-Language", "en-US");

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
			HashMap<String, String> parameters)
	{
		URL url;
		HttpURLConnection connection = null;
		try
		{
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			String parametersString = buildParametersString(parameters);

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(parametersString.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(parametersString);
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

	public static void main(String[] args) throws Exception
	{
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "francesco");
		System.out.println(doGetRequest("http://localhost:8080/token",
				parameters, null));
	}

}
