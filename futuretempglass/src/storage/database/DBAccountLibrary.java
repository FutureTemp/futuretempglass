package storage.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import storage.AccountLibrary;

public class DBAccountLibrary extends AccountLibrary{

	@Override
	public String getHashedPassword(String username)
	{
		if("francesco".equals(username))
		{
			try
			{
				byte[] bytes = MessageDigest.getInstance("SHA-256").digest("francesco12345".getBytes());
				String hash = HexBin.encode(bytes).toUpperCase();
				return hash;
			}
			catch(NoSuchAlgorithmException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	
}
