package server.objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import utils.StringUtils;

public class Account{

	private String username;
	
	private String hashedPassword;
	
	private String firstName;
	
	private String lastName;
	
	private boolean admin = false;

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * @return the hashedPassword
	 */
	public String getHashedPassword()
	{
		return hashedPassword;
	}

	/**
	 * Sets the password by taking the given password
	 * and hashing it.
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		if(StringUtils.isEmpty(password))
		{
			return;
		}
		MessageDigest messageDigest;
		try
		{
			messageDigest = MessageDigest.getInstance("SHA-256");
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return;
		}
		messageDigest.update(getUsername().getBytes());
		messageDigest.update(password.getBytes());
		byte[] bytes = messageDigest.digest();
		hashedPassword = HexBin.encode(bytes);
	}
	
	/**
	 * Authenticates by checking if the password is correct. The password is correct
	 * if hashing it results in a matching hashed password.
	 * @param password
	 * @return true if the password was correct and false otherwise
	 */
	public boolean authenticate(String password)
	{
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(getUsername().getBytes());
			messageDigest.update(password.getBytes());
			byte[] bytes = messageDigest.digest();
			String hash = HexBin.encode(bytes);
			return hash.equals(hashedPassword);
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin()
	{
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	
}
