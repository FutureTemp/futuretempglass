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
		if(rs == null)
		{
			return;
		}
		ResultSetMetaData metaData = rs.getMetaData();
		for(int i = 0; i < metaData.getColumnCount(); i++)
		{
			String columnName = metaData.getColumnName(i + 1);
			columnNames.add(columnName);
			columnIndex.put(columnName, i);
		}
		rs.last();
		int lastRow = rs.getRow();
		if(lastRow <= 0)
		{
			results = null;
		}
		else
		{
			results = new String[rs.getRow()][columnNames.size()];
		}
		rs.beforeFirst();
		while (rs.next())
		{
			for(int i = 0; i < columnNames.size(); i++)
			{
				String string = rs.getString(i + 1);
				results[currentRow][i] = string;
			}
			currentRow++;
		}
		currentRow = -1;
	}

	public List<String> getColumnNames()
	{
		return columnNames;
	}

	public String[] getRow()
	{
		return results[currentRow];
	}

	public String getString(int colIndex)
	{
		if(results == null)
		{
			return null;
		}
		return results[currentRow][colIndex];
	}

	public String getString(String colName)
	{
		return getString(columnIndex.get(colName));
	}

	public boolean hasNext()
	{
		if(results == null)
		{
			return false;
		}
		return results.length > currentRow - 1;
	}

	public boolean next() throws Exception
	{
		boolean more = hasNext();
		currentRow++;
		return more;
	}

	public boolean goToRow(int index)
	{
		if(results == null)
		{
			return false;
		}
		if(index < results.length)
		{
			currentRow = index;
			return true;
		}
		return false;
	}

	public String[][] getAllResults()
	{
		return results;
	}
}
