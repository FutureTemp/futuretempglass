package storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper{

	private static String urlPrefix = "jdbc:mysql://";
	private static String connectionURL = "127.0.0.1:3306/";
	private static String userName = "root";
	private static String password = "4201994Fr";

	public static boolean writeToDb(String query)
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(urlPrefix + connectionURL,
					userName, password);

			Statement statement = con.createStatement();

			statement.execute(query);
			con.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static DBResults queryDb(String query)
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(urlPrefix + connectionURL,
					userName, password);

			Statement statement = con.createStatement();

			statement.execute(query);
			ResultSet rs = statement.getResultSet();

			DBResults results = new DBResults(rs);

			con.close();
			return results;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * @return the connectionURL
	 */
	public static String getConnectionURL()
	{
		return connectionURL;
	}

	/**
	 * @param connectionURL
	 *            the connectionURL to set
	 */
	public static void setConnectionURL(String connectionURL)
	{
		DBHelper.connectionURL = connectionURL;
	}

	/**
	 * @return the userName
	 */
	public static String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public static void setUserName(String userName)
	{
		DBHelper.userName = userName;
	}

	/**
	 * @return the password
	 */
	public static String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public static void setPassword(String password)
	{
		DBHelper.password = password;
	}
}
