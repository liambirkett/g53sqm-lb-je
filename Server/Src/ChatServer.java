import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {
	
	static final int DEFAULTPORT = 1344;
	
	static ArrayList<ClientInfo> userList = new ArrayList<ClientInfo>();
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
			ClientInfo aClientInfo = new ClientInfo(clientSoc);
			userList.add(aClientInfo);
			aHandleConnection.start();
			System.out.println("Client Connected");




		}
	}

}
