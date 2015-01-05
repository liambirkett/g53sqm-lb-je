import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TestListener {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
	String hostName = "localhost";
		
		int portNumber = 1344;
		

		Socket clientSocket = new Socket(hostName, portNumber);
		PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),
				true);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		
		System.out.println(input.readLine());
		output.println("IDEN Bob");
		System.out.println("Input: IDEN = " + input.readLine());
		while(true){
			System.out.println("From the Server: " + input.readLine());
	
		}
}
}
