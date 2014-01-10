package server.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public abstract class PageHandler extends ServerHandler{

	/**
	 * Sends a response through the HttpExchange object provided
	 * which contains the bytes for a page passed in as File.
	 * @param file
	 * @param ex
	 */
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
	
	/**
	 * Sends a response through the HttpExchange object provided
	 * which contains the bytes for a page at the specified filePath.
	 * @param filePath
	 * @param ex
	 */
	protected void sendPage(String filePath, HttpExchange ex)
	{
		sendPage(new File(filePath), ex);
	}
	
}
