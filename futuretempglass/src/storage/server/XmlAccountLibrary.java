package storage.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import storage.AccountLibrary;

public class XmlAccountLibrary extends AccountLibrary{

	@Override
	public String getHashedPassword(String username)
	{
		if("francesco".equals(username))
		{
			try
			{
				byte[] bytes = MessageDigest.getInstance("SHA-256").digest("francesco12345".getBytes());
				return HexBin.encode(bytes).toUpperCase();
			}
			catch(NoSuchAlgorithmException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		// TODO Auto-generated method stub
		return "";
	}

}
