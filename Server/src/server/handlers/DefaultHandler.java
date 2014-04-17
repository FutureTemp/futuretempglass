package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import server.Server;

public class DefaultHandler extends PageHandler{

	public DefaultHandler(Server server)
	{
		super(server);
	}

	@Override
	public void handle(HttpExchange ex) throws IOException
	{
		sendHeader(ex);
		sendPage("pages/login.html", ex);
		finish(ex);
	}
}
