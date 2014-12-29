import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Test;


public class TestCommandInterpreter extends CommandInterpreter {

	public TestCommandInterpreter(Socket clientSoc) {
		super(clientSoc);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testSTAT(){
		
		//stat test (not logged in)
		ISLOGGEDIN=false;
		String s = handleInput("STAT");
		assertEquals(s, "OK. 0 users logged in. You are logged out.");
		
		//stat test (logged in)
		ISLOGGEDIN=true;
		s = handleInput("STAT");
		assertEquals(s, "OK. 0 users logged in. You are logged in. No of msgs sent = 0.");
	}
	
	@Test
	public void testUnknownCommand(){
		
		//unknown command test
		String s = handleInput("blah");
		assertEquals(s, "-ERR unknown command");	
	}
	
	@Test
	public void testIDEN(){
		
		//iden test (correct input)
		String s = handleInput("IDEN exampleUsername");
		assertEquals(s, "OK. Welcome exampleUsername. Have a lot of fun...");
		
		//iden test (illegal characters)
		s = handleInput("IDEN exa**mlpeuname");
		assertEquals(s, "BAD. Username has illegal character. Try a different one.");
		
		
	}
	
	@Test
	public void testLIST(){
		//list test (not logged in)
		ISLOGGEDIN=false;
		String s = handleInput("LIST");
		assertEquals(s, "BAD. You are not logged in.");
	}
	
	@Test
	public void testMESG(){
		//mesg test (not logged in)
		ISLOGGEDIN=false;
		String s = handleInput("MESG recipient1 hello");
		assertEquals(s, "BAD. You are not logged in.");
		
		//mesg test (logged in)
		ISLOGGEDIN = true;
		s = handleInput("MESG recipient1 hello");
		assertEquals(s, "OK. Message sent.");
		
		//mesg test (illegal characters)
		s = handleInput("MESG rec* hi");
		assertEquals(s, "BAD. Message has illegal characters.");
	}

	@Test
	public void testHAIL(){
		//hail test (not logged in)
		ISLOGGEDIN = false;
		String s = handleInput("HAIL hello everyone!");
		assertEquals(s, "BAD. You are not logged in.");
		
		//hail test (logged in)
		ISLOGGEDIN = true;
		s = handleInput("HAIL hello everyone!");
		assertEquals(s, "OK. Message sent");
		
		//hail test (illegal characters)
		s = handleInput("HAIL ** hi");
		assertEquals(s, "BAD. Message has illegal characters.");
	}
	
	@Test
	public void testQUIT(){
		//quit test (not logged in)
		ISLOGGEDIN = false;
		String s = handleInput("QUIT");
		assertEquals(s, "BAD. You are not logged in.");
		
		//quit test (logged in)
		ISLOGGEDIN = true;
		s = handleInput("QUIT");
		assertEquals(s, "OK. You have logged out. Thankyou, come again...");
	}
	
	@Test
	public void testContainsIllegal(){
		
		//no illegal characters
		boolean b = containsIllegal("abc123");
		assertFalse(b);
		
		//all illegal characters
		b = containsIllegal("+%{}|\"");
		assertTrue(b);
		
		//some illegal characters
		b = containsIllegal("aj*1{d!0z(02");
		assertTrue(b);
		
		//no illegal characters
		String[] sa = {"hello", "this", "is", "a", "test"};
		b = containsIllegal(arrayToString(sa));
		assertFalse(b);
	}
	
	
}
