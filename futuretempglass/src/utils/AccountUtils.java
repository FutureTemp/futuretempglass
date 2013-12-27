package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import server.objects.Account;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import core.Application;

public class AccountUtils{

	/**
	 * Maps expected authentication hashes to the username associated to it
	 */
	private static HashMap<String, String> hashes = new HashMap<String, String>();

	public static Account authenticate(String hash)
	{
		return Application.getAccountLibrary().getAccount(hashes.remove(hash));
	}

	public static String getToken(String username)
	{
		String token = StringUtils.getRandomStringOfLettersAndNumbers(10)
				.toUpperCase();
		try
		{
			MessageDigest m = MessageDigest.getInstance("SHA-256");
			m.update(token.getBytes());
			String hashedPassword = Application.getAccountLibrary()
					.getHashedPassword(username);
			if(hashedPassword == null)
			{
				return null;
			}
			m.update(hashedPassword.getBytes());
			byte[] bytes = m.digest();
			String hash = HexBin.encode(bytes);
			hashes.put(hash.toUpperCase(), username);
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