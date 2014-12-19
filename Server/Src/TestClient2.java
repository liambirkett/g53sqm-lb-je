import java.io.*;
import java.net.*;

public class TestClient2 {
	public static void main(String[] args) throws IOException {

		String hostName = "localhost";
		
		int portNumber = 110;
		

		Socket clientSocket = new Socket(hostName, portNumber);
		PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),
				true);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream())); // Connects to the server on
													// the port I used for
													// testing 1344 and tests
													// all commands with all
													// multiple argument
													// variations where
													// applicable, in both AUTH
													// and TRANS states
		System.out.println(input.readLine());
		output.println("IDEN jake");
		System.out.println("Input: IDEN = " + input.readLine());
		output.println("LIST");
		System.out.println("Input: LIST = " + input.readLine());
		output.println("QUIT");
		System.out.println("Input: QUIT = " + input.readLine());
		clientSocket.close();

	}
}
