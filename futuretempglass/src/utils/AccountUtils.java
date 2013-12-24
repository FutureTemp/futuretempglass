package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import core.Application;

public class AccountUtils{

	private static List<String> hashes = new ArrayList<String>();

	public static boolean authenticate(String hash)
	{
		return hashes.remove(hash);
	}

	public static String getToken(String username)
	{
		String token = StringUtils.getRandomStringOfLettersAndNumbers(10).toUpperCase();
		try
		{
			MessageDigest m = MessageDigest.getInstance("SHA-256");
			m.update(token.getBytes());
			String hashedPassword = Application.getAccountLibrary().getHashedPassword(username);
			if(hashedPassword == null)
			{
				return null;
			}
			m.update(hashedPassword.getBytes());
			byte[] bytes = m.digest();
			String hash = HexBin.encode(bytes);
			hashes.add(hash.toUpperCase());
			return token;
		}
		catch(NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}