package eu.ase.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientSocket {

	public static void main(String[] args) {
		try {
			Socket s = new Socket(InetAddress.getLocalHost(), 8883);
			
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			PrintWriter outputStream = new PrintWriter(s.getOutputStream(), true);
			
			String req = "GET /index.html HTTP/1.1\n"
					+ "Host: localhost:8883\n"
					+ "Connection: keep-alive\n"
					+ "Cache-Control: max-age=0\n"
					+ "sec-ch-ua: \"Chromium\";v=\"124\", \"Google Chrome\";v=\"124\", \"Not-A.Brand\";v=\"99\"\n"
					+ "sec-ch-ua-mobile: ?0\n"
					+ "sec-ch-ua-platform: \"macOS\"\n"
					+ "Upgrade-Insecure-Requests: 1\n"
					+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36\n"
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n"
					+ "Sec-Fetch-Site: none\n"
					+ "Sec-Fetch-Mode: navigate\n"
					+ "Sec-Fetch-User: ?1\n"
					+ "Sec-Fetch-Dest: document\n"
					+ "Accept-Encoding: gzip, deflate, br, zstd\n"
					+ "Accept-Language: ro,en-US;q=0.9,en;q=0.8,en-GB;q=0.7,es;q=0.6";
			
			outputStream.println(req);
			outputStream.println("");
						
			String inputLine = "";
			String processLine = "";
			//Serverul dupa ce imi raspunde da close si de asta stiu ca am sa opresc din citit la un moment dat
			while( ( (inputLine = inputStream.readLine()) != null ) ){
				processLine += inputLine + "\n";
			}
			
			
			System.out.println(processLine);

			inputStream.close();
			outputStream.close();
			s.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
