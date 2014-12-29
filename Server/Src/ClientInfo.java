import java.net.Socket;

import javax.swing.Spring;


public class ClientInfo {
private String uname;
private Socket clientSoc;
public ClientInfo(Socket clientSocket){
	clientSoc = clientSocket;
	uname = null;
	
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public Socket getClientSoc() {
	return clientSoc;
}

}
