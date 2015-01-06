import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
	
public class Client implements ClientInterface{
	
	static boolean running = true;
	static String hostName;
	static final int portNumber = 1344;
	static BufferedReader input;
	static Socket clientSocket;
	
	public Client(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		Client c = new Client();


		System.out.println("Welcome to the chat server...");

		boolean hostnameIsFine = false;
		while(!hostnameIsFine){
			System.out.print("Enter Server IP: ");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			hostName = sc.nextLine();
		}

		try{
			clientSocket = new Socket(hostName, portNumber);
			hostnameIsFine = true;
		}catch(Exception e){
			System.out.println("IP address not valid, please try again.");
		}
		//connect to server
		PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),
				true);
		input = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream())); 
		
	
		System.out.println(input.readLine());
		
		//log user in
		login(output, input);
		
		//creates a thread to listen for server msgs
        c.new GetServerMsg().start();
        
        System.out.print("Enter command: ");

		//program loop
		while(running){
						
			@SuppressWarnings("resource")
			Scanner sca = new Scanner(System.in);
			String command = sca.nextLine(); 
			output.println(command);
			output.flush();
						
			
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
	
	
	
	
	
//thread that waits for server input and prints it
    class GetServerMsg extends Thread {
    	boolean msgSent;
        public void run() {

            while(true) {
            	
                try {
                   	
                	while(input.ready()){
                		System.out.println(input.readLine());
                		msgSent = true;
                	}
                	
                	if(msgSent){
                		System.out.print("Enter Command: ");
                		msgSent = false;
                	}
                }
                catch(IOException e) {
                    e.printStackTrace();
                } 
            }
        }
    }
}




