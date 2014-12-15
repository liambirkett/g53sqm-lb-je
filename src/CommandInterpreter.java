
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
}
