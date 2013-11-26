package storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper{

	private static String connectionURL = "jdbc:mysql://127.0.0.1:3306/";
	private static String userName = "root";
	private static String password = "4201994Fr";

	public static boolean writeToDb(String query)
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager
					.getConnection(connectionURL, userName, password);

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
	
	public static HashMap<String, List<String>> queryDb(String query)
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager
					.getConnection(connectionURL, userName, password);

			Statement statement = con.createStatement();

			statement.execute(query);
			ResultSet rs = statement.getResultSet();

			HashMap<String, List<String>> results = new HashMap<String, List<String>>();

			ResultSetMetaData data = rs.getMetaData();

			List<List<String>> columnLists = new ArrayList<List<String>>();
			int colCount = data.getColumnCount();
			for(int i = 0; i < colCount; i++)
			{
				List<String> list = new ArrayList<String>();
				columnLists.add(list);
				results.put(data.getColumnName(i + 1), list);
			}
			while (rs.next())
			{
				for(int i = 0; i < colCount; i++)
				{
					columnLists.get(i).add(rs.getString(i + 1));
				}
			}

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
}
