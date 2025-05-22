package eu.ase.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] args) {
		byte[] bufRecv1 = null; byte[] bufRecv2 = null;
		byte[] bufResp = null;
		
		//try with resources, implements closeable interface
		try (DatagramSocket socketServer = new DatagramSocket(8333)) {
			System.out.println("UDP Server bind on 8333 port.");
			while(true) {
				bufRecv1 = new byte[256];
				
				DatagramPacket packet1 = new DatagramPacket(bufRecv1, bufRecv1.length);
				socketServer.receive(packet1);//e blocant acest receive
				//System.out.println("Server received from client the packet 1 = " + new String(packet1.getData()));
				System.out.println("Server received from client the packet 1 = " + new String(bufRecv1));
				
				
				bufRecv2 = new byte[256];
				DatagramPacket packet2 = new DatagramPacket(bufRecv2, bufRecv2.length);
				socketServer.receive(packet2);
				System.out.println("Server received from client the packet 2 = " + new String(bufRecv2));
				
				
				bufResp = new String("OK").getBytes();
				DatagramPacket packResp = new DatagramPacket(bufResp, 0, bufResp.length, packet2.getAddress(), packet2.getPort());
				socketServer.send(packResp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
