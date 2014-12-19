
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
		c = new CommandInterpreter();	
	}
	
	public void run(){
		
		try (
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){

			String input, output;

			output = "Connected to server!";
			out.println(output);

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

}
