package rest;

import static junit.framework.Assert.*;

import java.security.MessageDigest;
import java.util.HashMap;

import org.junit.Test;

import server.objects.Token;
import utils.HTTPUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class Login{

	@Test
	public void login() throws Exception
	{
		String username = "francesco";
		String password = "12345";
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", username);
		String response = HTTPUtils.doGetRequest("http://localhost:8080/token", parameters, null);
		ObjectMapper mapper = new ObjectMapper();
		Token token = mapper.readValue(response, Token.class);
		
		MessageDigest m = MessageDigest.getInstance("SHA-256");
		m.update(username.getBytes());
		m.update(password.getBytes());
		byte[] bytes = m.digest();
		String hash = HexBin.encode(bytes);
		
		m = MessageDigest.getInstance("SHA-256");
		m.update(token.getToken().getBytes());
		m.update(hash.getBytes());
		bytes = m.digest();
		String auth = HexBin.encode(bytes);
		
		parameters.remove("username");
		parameters.put("Authentication", auth);
		response = HTTPUtils.doGetRequest("http://localhost:8080/login", null, parameters);
		assertEquals("Login Successful", response.trim());
	}
	
}
