import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Test;


public class TestClient{
	
	Socket s = new Socket();
	Client c = new Client(s);

	@Test
	public void testClientSocketIsAssigned(){ 
		//checks client socket is assigned
		assertNotNull(c.clientSocket);
	}

}
