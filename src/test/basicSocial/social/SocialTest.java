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

}
