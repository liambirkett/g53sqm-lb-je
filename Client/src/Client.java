import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
	
public class Client implements ClientInterface, Runnable{
	
		Socket clientSocket;
		CommandInterpreter c;
		
		/**
		 * This method is the constructor for the
		 * Client thread.
		 * 
		 * @param the socket that a thread is being made for.
		 */
		//constructor
		public Client(Socket clientSocket){
			this.clientSocket = clientSocket;
			c = new CommandInterpreter();	
		}
		
		/**
		 * This thread is required to make a thread.
		 * It is executed when Thread.start() is called.
		 * It creates new PrintWriters and BufferedReaders to deal
		 * with the input and output from and to the client.
		 * 
		 */
		public void run(){
			try (
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){

				String input, output;

				output = "Connected to server!";
				out.println(output);

				while ((input = in.readLine()) != null) {
					output = c.handleInput(input);
					out.println(output);
					if (input.equals("QUIT")){
						break;
					}
				}
			} catch (SocketTimeoutException ste) {
				//logout
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

