package basicSocial.social;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SocialTest {
	
	@Test
	public void execRequestShouldAdd1MessageToTheMessageListIfTheUserPostOneMessage(){
		String post = "alice -> Hello World!";
		Social test = new Social();
		List<String> tmp = test.execRequest(post);
		assertTrue("Actual: "+tmp.size(),tmp.size() == 1);
	}
	
	@Test
	public void execRequestShouldAdd2MessagesToTheMessageListIfTheUserPostTwoMessage(){
		String post = "alice -> Hello World!";
		String post2 = "alice -> second message";
		Social test = new Social();
		test.execRequest(post);
		List<String> tmp = test.execRequest(post2);
		assertTrue("Actual: "+tmp.size(),tmp.size() == 2);
	}
	
	@Test
	public void execRequestShouldReturnTheUserMessageListIfTheCommandIsRead(){
		String post = "alice -> Hello World!";
		String read = "alice";
		Social test = new Social();
		test.execRequest(post);
		List<String> tmp = test.execRequest(read);
		assertEquals("Actual: "+tmp.size(),1,tmp.size());
	}
	
	@Test
	public void execRequestShouldReturnAllTheMessageForTheSpecificUserIfTheCommandIsRead(){
		String alice_post = "alice -> Hello World!";
		String bob_post = "bob -> first message from bob";
		String read_alice = "alice";
		String read_bob = "bob";
		Social test = new Social();
		test.execRequest(alice_post);
		test.execRequest(bob_post);
		List<String> tmp = test.execRequest(read_alice);
		assertEquals("Actual: "+tmp.size(),1,tmp.size());
		assertEquals("Hello World!", tmp.get(0));
		tmp = test.execRequest(read_bob);
		assertEquals("Actual: "+tmp.size(),1,tmp.size());
		assertEquals("first message from bob", tmp.get(0));
	}

}
