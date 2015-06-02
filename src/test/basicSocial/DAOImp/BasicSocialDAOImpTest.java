package basicSocial.DAOImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import basicSocial.model.Message;
import basicSocial.model.User;
import basicSocial.social.Social;
import basicSocial.utils.MongoUtils;
import static com.mongodb.client.model.Filters.*;
import static org.junit.Assert.*;

public class BasicSocialDAOImpTest{
	
	private static final String dbName = "mongoTestBasicSocialDAOImpTest";
	private static MongoDatabase db = MongoUtils.getMongoDB(dbName);
	
	
	@AfterClass
	public static void closeDB(){
		db.drop();
		MongoUtils.closeMongo();
	}
		
	@Before
	public void dropDB(){
		db.drop();
		db = MongoUtils.getMongoDB(dbName);
	}
	
	
	@Test
	public void createUserShouldCreateAnUserInTheDatabase(){
		User user = new User();
		user.setName("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		daoImp.createUser(user);
		
		MongoCollection<Document> collection = db.getCollection(User.COLLECTION);
		Document myDoc = collection.find(eq("name","Charlie")).first();
		assertEquals(user.getName(),myDoc.getString("name"));
		assertEquals(user.getFollowers(), myDoc.get("followers", List.class));

	}
	
	@Test
	public void createUserShouldNotCreateTheUserIfItAlreadyExist(){
		User user = new User();
		user.setName("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		daoImp.createUser(user);
		daoImp.createUser(user);
		
		MongoCollection<Document> collection = db.getCollection(User.COLLECTION);
		assertEquals(1, collection.count(eq("name","Charlie")));
	}
	
	@Test
	public void getUserShoudlReturnTheUserIfItExistOnTheDB(){
		User user = new User();
		user.setName("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		Document doc = new Document("name", user.getName()).append("followers", user.getFollowers());
		db.getCollection(User.COLLECTION).insertOne(doc);
		
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		assertEquals(user,daoImp.getUser(user.getName()));
	}
	
	@Test
	public void getUserShouldReturnNullIfTheUserDoesNotExist(){
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		assertNull(daoImp.getUser("Alice"));
	}
	
	@Test
	public void savePostShouldSaveAPostInTheDB(){
		Message post = new Message("Alice", "Hello World");
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		daoImp.savePost(post);
		Document pst = db.getCollection(Message.COLLECTION).find(new Document("sender",post.getSender()).append("time",post.getTime().toDate())).first();
		assertEquals(post.getSender(), pst.getString("sender"));
		assertEquals(post.getText(), pst.getString("text"));
		assertEquals(post.getTime().toDate(), pst.get("time",Date.class));
	}
	
	@Test
	public void getAllPostShouldReturnAllTheUserssPosts(){
		User alice = new User();
		alice.setName("Alice");
		List<Message> msgList = new ArrayList<Message>();
		msgList.add(new Message(alice.getName(), "Hello World!"));
		msgList.add(new Message(alice.getName(), "today is Friday!!"));
		
		for (Message message : msgList) {
			db.getCollection(Message.COLLECTION).insertOne(new Document("sender",message.getSender()).append("text", message.getText()).append("time",message.getTime().toDate()));
		}
				
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		
		assertEquals(msgList, daoImp.getAllPosts(alice));	
	}
	
	
	@Test
	public void getAllPostShouldReturnAllTheUserPostAlongWithAllTheFollowersPost(){
		User user = new User("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		List<Message> msgList = new ArrayList<Message>();
		msgList.add(new Message("Charlie", "Hello World!"));
		msgList.add(new Message("Bob", "today is Friday!!"));
		msgList.add(new Message("Alice", "Hi, this is Alice!"));
		
		for (Message message : msgList) {
			db.getCollection(Message.COLLECTION).insertOne(new Document("sender",message.getSender()).append("text", message.getText()).append("time",message.getTime().toDate()));
		}
		
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		List<Message> result = daoImp.getAllPosts(user);
		System.out.println(result.size());
		assertEquals(msgList, result);	
	}
	


}
