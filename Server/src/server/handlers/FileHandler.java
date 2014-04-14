package server.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import server.Server;

import com.sun.net.httpserver.HttpExchange;

public class FileHandler extends ServerHandler{

	public FileHandler(Server server)
	{
		super(server);
	}

	private static final String context = "/files";

	public static String getContext()
	{
		return context;
	}

	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		sendHeader(ex);
		if("/files".equals(ex.getRequestURI().getPath()))
		{
			File directory = new File("files/");
			File[] files = directory.listFiles();
			for(File file: files)
			{
				String output = "<!DOCTYPE html><html><head><title>Files</title></head><body>";
				output += "<a href='/files/" + file.getName() + "'>";
				output += file.getName();
				output += "</a><br>";
				sendResponse(output, ex);
			}
		}
		else
		{
			sendFile(ex.getRequestURI().getPath().substring(1), ex);
		}
	}

	private void sendFile(String path, HttpExchange ex)
	{
		File file = new File(path);
		sendFile(file, ex);
	}

	private void sendFile(File file, HttpExchange ex)
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
			OutputStream out = ex.getResponseBody();
			int next = fis.read();
			while (next != -1)
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
}
