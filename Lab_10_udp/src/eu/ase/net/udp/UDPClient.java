package eu.ase.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	public static void main(String[] args) {
		try {
			DatagramSocket client = new DatagramSocket();
			
			byte[] buf1 = new byte[256]; //max capacity of 1 packet
			buf1[0] = 'S'; buf1[1] = 'a'; buf1[2] = 'l'; buf1[3] = 'u'; buf1[4] = 't';
			
//			InetAddress addr = InetAddress.getByName("localhost");
//			InetAddress addr = InetAddress.getLocalHost();
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			
			DatagramPacket packet1 = new DatagramPacket(buf1, 0, buf1.length,addr, 8333);
			
			client.send(packet1);
			
			byte[] buf2 = (new String("Hello")).getBytes();
			
			DatagramPacket packet2 = new DatagramPacket(buf2, 0, buf2.length,addr, 8333);
			
			client.send(packet2);
			
			byte[] bufResp = new byte[256];
			DatagramPacket packetResp = new DatagramPacket(bufResp, bufResp.length);
			
			client.receive(packetResp);
			
			System.out.println("Client received = " + new String(packetResp.getData()));
			
			client.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
