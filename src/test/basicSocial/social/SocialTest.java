package basicSocial.social;

import static org.junit.Assert.*;

import org.junit.Test;

public class SocialTest {
	
	@Test
	public void execRequestShouldAdd1MessageToTheMessageListIfTheUserPostOneMessage(){
		String post = "alice -> Hello World!";
		Social test = new Social();
		test.execRequest(post);
		assertTrue("Actual: "+test.messagesList.size(),test.messagesList.size() == 1);
	}
	
	@Test
	public void execRequestShouldAdd2MessagesToTheMessageListIfTheUserPostTwoMessage(){
		String post = "alice -> Hello World!";
		String post2 = "alice -> second message";
		Social test = new Social();
		test.execRequest(post);
		test.execRequest(post2);
		assertTrue("Actual: "+test.messagesList.size(),test.messagesList.size() == 2);
	}

}
