package utils;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.RESTURL;

public class LoginUtils{

	public static void login(String username, String password) throws Exception
	{
		HashMap<String, String> urlParameters = new HashMap<String, String>();
		urlParameters.put("username", username);
		String response = HTTPUtils.doGetRequest(RESTURL.TOKEN.getUrl(),
				urlParameters, null);
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> token = null;
		try
		{
			token = mapper.readValue(response,
					new TypeReference<HashMap<String, String>>(){});
		}
		catch(JsonMappingException | JsonParseException e)
		{
			throw new Exception("Did not receive properly formatted token. "
					+ response);
		}
		HashMap<String, String> headerInfo = new HashMap<String, String>();
		headerInfo.put("token", token.get("token"));
		headerInfo.put("password", password);
		response = HTTPUtils.doGetRequest(RESTURL.LOGIN.getUrl(), null, headerInfo);
		if(!response.equalsIgnoreCase("login successful"))
		{
			throw new Exception("Login Failed!");
		}
	}

}
