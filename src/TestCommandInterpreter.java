import static org.junit.Assert.*;

import org.junit.Test;


public class TestCommandInterpreter extends CommandInterpreter {

	@Test
	public void testHandleInputSTAT(){
		
		//stat test
		ISLOGGEDIN=false;
		String s = handleInput("STAT");
		assertEquals(s, "OK. 0 users logged in. You are logged out.");
	}
	
	@Test
	public void testHandleInputUnknown(){
		
		//unknown command test
		String s = handleInput("blah");
		assertEquals(s, "-ERR unknown command");	
	}
	
	@Test
	public void testHandleInputIDEN(){
		
		//iden test
		String s = handleInput("IDEN exampleUsername");
		assertEquals(s, "OK. Welcome exampleUsername. Have a lot of fun...");
	}

}
