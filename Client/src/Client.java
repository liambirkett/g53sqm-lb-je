import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
	
public class Client implements ClientInterface{
	
	static boolean running = true;
	static final String hostName = "localhost";
	static final int portNumber = 1344;
	
	public static void main(String[] args) throws IOException {
		
		//connect to server
		Socket clientSocket = new Socket(hostName, portNumber);
		PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),
				true);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream())); 
		
	
		System.out.println(input.readLine());
		
		//log user in
		login(output, input);
		
		//program loop
		while(running){
			System.out.print("Enter command: ");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String command = sc.next(); 
			output.println(command);
			System.out.println(input.readLine());
			
			//if command entered was QUIT
			String s = "QUIT";
			if(command.toLowerCase().equals(s.toLowerCase())){
				clientSocket.close();
				running = false;
			}

		}
	}
	
	//handles the logging in of the client
	private static void login(PrintWriter output, BufferedReader input){
		System.out.print("Username: ");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String uname = sc.next();
		output.println("IDEN " + uname);
		try {
			System.out.println(input.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

