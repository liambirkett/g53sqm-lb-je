import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Liam
 * 
 * todo
 * -implement the placeholders
 * -make function to check if a string has dodgy characters
 *
 */
public class CommandInterpreter {

	
	protected String output;
	private String command;
	public static boolean ISLOGGEDIN = false;
	
	//constructor
	public CommandInterpreter(){
		
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
	private String stat(){
		
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
	
	private String iden(String uname){
		
		//check that username is not taken
		boolean unameTaken = false, unameHasIllegalCharacters = false; //placeholder until functionality is there
		
		if(unameTaken){
			return "BAD. Username already taken. Try a different one.";
			
		}else if(unameHasIllegalCharacters){
			char x = '*'; //placeholder until functionality is there
			return "BAD. Username has illegal character " + x + ". Try a different one.";
		}else{
			//log the user in
			ISLOGGEDIN = true;
			return "OK. Welcome " + uname + ". Have a lot of fun...";
		}
	}
	
	private String list(){
		if(ISLOGGEDIN){
			//get list of users here
			return "OK. Users logged in: ...";
		}else{
			return "BAD. You are not logged in.";
		}
	}
	
	private String mesg(String[] recipientAndMessage){
		boolean msgHasIllegalCharacters = false; //placeholder
		boolean recipientUnameDoesntExist = false; //placeholder
		
		if(!ISLOGGEDIN){
			return "BAD. You are not logged in.";
		}else if (msgHasIllegalCharacters){
			return "BAD. Message has illegal characters.";
		}else if (recipientUnameDoesntExist){
			return "BAD. Recipient username does not exist.";
		}else{
			//send msg to user here
			return "OK. Message sent.";
		}	
	}
	
	private String hail(String[] message){
		boolean msgHasIllegalCharacters = false; //placeholder
		if(!ISLOGGEDIN){
			return "BAD. You are not logged in.";
		}else if(msgHasIllegalCharacters){
			return "BAD. Message has illegal characters.";
		}else{
		
			//send msg to all clients here
			return "OK. Message sent";
		}
	}
	
	private String quit(){
		if(!ISLOGGEDIN){
			return "BAD. You are not logged in.";
		}else{
			//end session, remove user from active users list.
			return "OK. You have logged out. Thankyou, come again...";
		}
	}
	
	//returns true if the string contains any specified illegal characters
	public boolean containsIllegal(String toExamine) {
	    Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]"); //add or remove illegal characters as needed
	    Matcher matcher = pattern.matcher(toExamine);
	    return matcher.find();
	}
}
