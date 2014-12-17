
public interface CommandInterpreterInterface {
	
	public String handleInput(String input);
	
	public String stat();
	
	public String iden(String uname);
	
	public String list();
	
	public String mesg(String[] recipientAndMessage);
	
	public String hail(String[] message);
	
	public String quit();
	
}
