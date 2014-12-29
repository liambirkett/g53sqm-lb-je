import java.net.Socket;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Liam
 * 
 * todo
 * -implement the placeholders
 *
 */
public class CommandInterpreter implements CommandInterpreterInterface{

	
	protected String output;
	private String command;
	public static boolean ISLOGGEDIN = false;
	private Socket clientSoc; 
	

	//constructor
	public CommandInterpreter(Socket clientSoc){
		this.clientSoc = clientSoc;
	}
	
	//handle the user's commands
	public String handleInput(String input){
		try{
			//split up command into its constituent words
			//first word is command
			String[] result = input.split("\\s");
		    command = result[0];
		    
		    	switch(command){
				case "STAT":
					output = stat();
					break;
				case "IDEN":
					output = iden(result[1]);
					break;
				case "LIST":
					output = list();
					break;
				case "MESG":
					output = mesg(result);
					break;
				case "HAIL":
					output = hail(result);
					break;
				case "QUIT":
					output = quit();
					break;
		    	default:
		    		output = "-ERR unknown command";
		    		break;
		    			
		    	}
		    
			return output;
		}catch(Exception e){
			return "-ERR invalid argument";
		}
	}
	
	//handles the stat command
	public String stat(){
		
		int noOfUsersLoggedIn = 0; //placeholder until functionality is there
		String status = "OK. " + noOfUsersLoggedIn + " users logged in. You are logged ";
		
		
		int noOfMsgsSent=0; //placeholder until functionality is there
		
		//checks if user is logged in
		if(ISLOGGEDIN){
			status += "in. No of msgs sent = " + noOfMsgsSent + ".";
		}else{
			status += "out.";
		}
		
		return status;
	}
	
	public String iden(String uname){
		int i;
		boolean unameTaken = false; //placeholder until functionality is there
		boolean unameHasIllegalCharacters = containsIllegal(uname);
		for(i = 0; i < ChatServer.userList.size();i++){
			
			if (uname == ChatServer.userList.get(i).getUname()){
				unameTaken = true;
			}
			
		}
		
		
		if(unameTaken){
			return "BAD. Username already taken. Try a different one.";
			
		}else if(unameHasIllegalCharacters){
			return "BAD. Username has illegal character. Try a different one.";
		}else{
			for(i = 0; i < ChatServer.userList.size();i++){
				if(clientSoc.equals(ChatServer.userList.get(i).getClientSoc())){
					ChatServer.userList.get(i).setUname(uname);
				}
				
				
			}
			ISLOGGEDIN = true;
			return "OK. Welcome " + uname + ". Have a lot of fun...";
		}
	}
	
	public String list(){
		if(ISLOGGEDIN){
			String userListToReturn = "Ok, user connected are";
			for(int i = 0; i < ChatServer.userList.size();i++){
				
				if (ChatServer.userList.get(i).getUname() != null){
					userListToReturn = "," + userListToReturn + ChatServer.userList.get(i).getUname();
					
				}
				
			}
			return "OK. Users logged in: ...";
		}else{
			return "BAD. You are not logged in.";
		}
	}
	
	public String mesg(String[] recipientAndMessage){
		boolean hasIllegalCharacters = containsIllegal(arrayToString(recipientAndMessage));
		boolean recipientUnameDoesntExist = false; 
		
		if(!ISLOGGEDIN){
			return "BAD. You are not logged in.";
		}else if (hasIllegalCharacters){
			return "BAD. Message has illegal characters.";
		}else if (recipientUnameDoesntExist){
			return "BAD. Recipient username does not exist.";
		}else{
			//send msg to user here
			return "OK. Message sent.";
		}	
	}
	
	public String hail(String[] message){
		
		
		boolean msgHasIllegalCharacters = containsIllegal(arrayToString(message));
		if(!ISLOGGEDIN){
			return "BAD. You are not logged in.";
		}else if(msgHasIllegalCharacters){
			return "BAD. Message has illegal characters.";
		}else{
		
			//send msg to all clients here
			return "OK. Message sent";
		}
	}
	
	public String quit(){
		if(!ISLOGGEDIN){
			return "BAD. You are not logged in.";
		}else{
			//end session, remove user from active users list.
			return "OK. You have logged out. Thankyou, come again...";
		}
	}
	
	//returns true if the string contains any specified illegal characters
	protected boolean containsIllegal(String toExamine) {
	    Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]"); //add or remove illegal characters as needed
	    Matcher matcher = pattern.matcher(toExamine);
	    return matcher.find();
	}
	
	//utility func, String array -> String. Concatenates all of array to one string.
	protected String arrayToString(String[] arr){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
		   result.append( arr[i] );
		}
		return result.toString();
	}
}
