package communication;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Client{
	public static DatagramSocket ds;
	public static byte buffer[] = new byte[1024];

	public static void Myclient() throws Exception
	{
		while (true)
		{
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ds.receive(p);
			System.out.println(new String(p.getData(), 0, p.getLength()));
		}
	}

	public static void main(String args[]) throws Exception
	{
		System.out.println("for quitting client press ctrl+c");
		ds = new DatagramSocket(777);
		Myclient();
	}
}