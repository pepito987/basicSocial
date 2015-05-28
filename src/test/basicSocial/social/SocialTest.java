package social;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void execRequestShouldAddTheUserToTheFollowListIfTheCommandIsFollow(){
		String cmd = "charlie follow bob";
		Social test = new Social();
		List<String> followers = test.execRequest(cmd);

		assertEquals("Actual: "+followers, Arrays.asList("bob"),followers);
	}
	
	@Test
	public void execRequestShouldReturnTheUserWallWithIfTheCommandIsWallAndThereIsOnlyOneSubscriber(){
		String bob_post = "bob -> first message from bob";
		String charlie_post = "charlie -> Hi I am charlie";
		String follow = "charlie follow bob";
		String wall = "charlie wall";
		Social test = new Social();
		test.execRequest(bob_post);
		test.execRequest(charlie_post);
		test.execRequest(follow);
		List<String> tmp = test.execRequest(wall);
		assertEquals("Actual: "+tmp.size(), 2, tmp.size() );
		
	}
	
	@Test
	public void execRequestShouldReturnTheUserWallIfTheCommandIsWallEndThereAreMoreThenOneSubscriber(){
		String bob_post = "bob -> first message from bob";
		String alice_post = "alice -> today is Friday :D";
		String charlie_post = "charlie -> Hi I am charlie";
		String charlie_follow = "charlie follow bob";
		String alice_follow = "alice follow bob";
		String charlie_wall = "charlie wall";
		String alice_wall = "alice wall";
		Social test = new Social();
		test.execRequest(bob_post);
		test.execRequest(alice_post);
		test.execRequest(charlie_post);
		test.execRequest(charlie_follow);
		test.execRequest(alice_follow);
		List<String> charlie_wall_list = test.execRequest(charlie_wall);
		List<String> alice_wall_list = test.execRequest(alice_wall);
		
		assertEquals("Actual: "+charlie_wall_list.size(), 2, charlie_wall_list.size() );
		assertTrue("Actual: "+charlie_wall_list, charlie_wall_list.contains("first message from bob"));
		assertEquals("Actual: "+alice_wall_list.size(), 2, alice_wall_list.size() );
		assertTrue("Actual: "+alice_wall_list,alice_wall_list.contains("first message from bob"));
	}

}
