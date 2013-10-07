package communication;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Server{
	public static DatagramSocket ds;
	public static byte buffer[] = new byte[1024];

	public static void MyServer() throws Exception
	{
		int pos = 0;
		while (true)
		{
			int c = System.in.read();
			switch (c)
			{
			case '~':
				System.out.println("\n Quits");
				return;
			case '\r':
				break;
			case '\n':
				ds.send(new DatagramPacket(buffer, pos, InetAddress
						.getByName("127.0.0.1"), 777));
				pos = 0;
				break;
			default:
				buffer[pos++] = (byte)c;
			}
		}
	}

	public static void main(String args[]) throws Exception
	{
		System.out.println("server ready....\n please type here");
		ds = new DatagramSocket(888);
		MyServer();
	}
}