
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class ClientHandlerThread implements Runnable{
			
	Socket clientSocket;
	CommandInterpreter c;
	

	public ClientHandlerThread(Socket clientSocket){
		this.clientSocket = clientSocket;
		
	}
	
	public void run(){
		c = new CommandInterpreter(clientSocket);
		try (
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){

			String input, output;

			output = "Connected to server!";
			out.println(output);
			out.flush();

			while ((input = in.readLine()) != null) {
			System.out.println(input);
				output = c.handleInput(input);
				out.println(output);
				if (input.equals("QUIT")){
					break;
				}
			}
		
		} catch (IOException e){
			e.printStackTrace();
		}
		
		try {
			//closes socket
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void outputString(String output){
		PrintWriter out;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(output);
		} catch (IOException e) {
			System.out.println("Error no socket assigned");
			e.printStackTrace();
		}
		
	}
	public String getClientIden(){
		return null;
		
	}
	

}
