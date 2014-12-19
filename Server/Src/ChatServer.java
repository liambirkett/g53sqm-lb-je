import java.io.*;
import java.net.*;

public class ChatServer {
	
	static final int DEFAULTPORT = 110;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		int portNumber;
		
				
		
			
			try{portNumber = Integer.parseInt(args[0]);}
			catch(Exception e){portNumber = DEFAULTPORT;}
			
		

		Socket clientSoc = null;

		ServerSocket ss = new ServerSocket(portNumber);
		System.out.println("ServerStarted");
		while (true) { // Loops forever accepted client connections and passing
						// them to a separate thread
			
			clientSoc = ss.accept();
		
			Thread aHandleConnection = new Thread(new ClientHandlerThread(
					clientSoc));
			aHandleConnection.start();
			System.out.println("Client Connected");




		}
	}

}
