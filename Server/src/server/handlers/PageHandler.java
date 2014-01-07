package server.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public abstract class PageHandler extends ServerHandler{

	protected void sendPage(File file, HttpExchange ex)
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
			OutputStream out = ex.getResponseBody();
			int next = fis.read();
			while(next != -1)
			{
				out.write(next);
				next = fis.read();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void sendPage(String filePath, HttpExchange ex)
	{
		sendPage(new File(filePath), ex);
	}
	
}
