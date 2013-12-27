package storage.xml;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import server.objects.Account;
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

	@Override
	public boolean addAcount(Account account)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccount(String username)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAccount(String username)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
