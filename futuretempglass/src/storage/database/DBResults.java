package storage.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBResults{

	private List<String> columnNames = new ArrayList<String>();
	
	private HashMap<String, Integer> columnIndex = new HashMap<String, Integer>();
	
	private String[][] results;
	
	private int currentRow = 0;
	
	public DBResults(ResultSet rs) throws SQLException
	{
		ResultSetMetaData metaData = rs.getMetaData();
		for(int i = 0; i < metaData.getColumnCount(); i++)
		{
			String columnName = metaData.getColumnName(i + 1);
			columnNames.add(columnName);
			columnIndex.put(columnName, i);
		}
		results = new String[columnNames.size()][rs.getFetchSize()];
		while(rs.next())
		{
			for(int i = 0; i < columnNames.size(); i++)
			{
				results[i][currentRow++] = rs.getString(i);
			}
		}
		currentRow = -1;
	}

	public String[] getRow()
	{
		return results[currentRow];
	}
	
	public boolean hasNext()
	{
		return results.length < currentRow - 1;
	}
	
	public boolean next() throws Exception
	{
		currentRow++;
		return hasNext();
	}
	
	public boolean goToRow(int index)
	{
		if(index < results.length)
		{
			currentRow = index;
			return true;
		}
		return false;
	}
}
