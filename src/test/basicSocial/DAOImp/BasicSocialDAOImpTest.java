package basicSocial.DAOImp;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import basicSocial.model.Message;
import basicSocial.model.User;
import basicSocial.social.Social;
import basicSocial.utils.MongoUtils;
import static com.mongodb.client.model.Filters.*;
import static org.junit.Assert.*;

public class BasicSocialDAOImpTest {

	private static final String dbName = "mongoTestBasicSocialDAOImpTest";
	private static MongoDatabase db = MongoUtils.getMongoDB(dbName);
	private static StringWriter jsonString = new StringWriter();
	private static ObjectMapper mapper = new ObjectMapper();

	@BeforeClass
	public static void initialize(){
		// to prevent exception when encountering unknown property:
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// to write java.util.Date, Calendar as number (timestamp):
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	@AfterClass
	public static void closeDB() {
		db.drop();
		MongoUtils.closeMongo();
	}

	@Before
	public void dropDB() {
		db.drop();
		db = MongoUtils.getMongoDB(dbName);
		try {
			jsonString.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonString =  new StringWriter();
	}

	@Test
	public void createUserShouldCreateAnUserInTheDatabase() {
		try {
			User user = new User();
			user.setName("Charlie");
			user.addFollower("Bob");
			user.addFollower("Alice");

			BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
			daoImp.createUser(user);

			MongoCollection<Document> collection = db.getCollection(User.COLLECTION);
			Document myDoc = collection.find(eq("name", "Charlie")).first();
			User result = mapper.readValue(myDoc.toJson(), User.class);

			assertEquals(user, result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void createUserShouldNotCreateTheUserIfItAlreadyExist() {
		User user = new User();
		user.setName("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		daoImp.createUser(user);
		daoImp.createUser(user);

		MongoCollection<Document> collection = db.getCollection(User.COLLECTION);
		assertEquals(1, collection.count(eq("name", "Charlie")));
	}

//	@Test
	public void getUserShoudlReturnTheUserIfItExistOnTheDB() {
		try {
			User user = new User();
			user.setName("Charlie");
			user.addFollower("Bob");
			user.addFollower("Alice");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(jsonString, user);
			Document doc = new Document(mapper.readValue(jsonString.toString(),	HashMap.class));
			System.out.println(doc.toJson());

			db.getCollection(User.COLLECTION).insertOne(doc);

			BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
			assertEquals(user, daoImp.getUser(user.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getUserShouldReturnNullIfTheUserDoesNotExist() {
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		assertNull(daoImp.getUser("Alice"));
	}

	@Test
	public void savePostShouldSaveAPostInTheDB() {
		try{
			Message post = new Message("Alice", "Hello World");
			BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
			daoImp.savePost(post);
			Document pst = db.getCollection(Message.COLLECTION).find(new Document(mapper.readValue(mapper.writeValueAsString(post), HashMap.class))).first();
			System.out.println(pst.toJson());
			assertEquals(post, pst);
		}catch(IOException e){
			
		}
		
	}

	@Test
	public void getAllPostShouldReturnAllTheUserssPosts() {
		User alice = new User();
		alice.setName("Alice");
		List<Message> msgList = new ArrayList<Message>();
		msgList.add(new Message(alice.getName(), "Hello World!"));
		msgList.add(new Message(alice.getName(), "today is Friday!!"));

		for (Message message : msgList) {
			db.getCollection(Message.COLLECTION).insertOne(
					new Document("sender", message.getSender()).append("text",
							message.getText()).append("time",
							message.getTime().toDate()));
		}

		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();

		assertEquals(msgList, daoImp.getAllPosts(alice, true));
	}

//	@Test
	public void getAllPostShouldReturnAllTheUserPostAlongWithAllTheFollowersPost() {
		try{
			User user = new User("Charlie");
			user.addFollower("Bob");
			user.addFollower("Alice");
			List<Message> msgList = new ArrayList<Message>();
			msgList.add(new Message("Charlie", "Hello World!"));
			msgList.add(new Message("Bob", "today is Friday!!"));
			msgList.add(new Message("Alice", "Hi, this is Alice!"));
			ObjectMapper mapper = new ObjectMapper();
			for (Message message : msgList) {
				mapper.writeValue(jsonString, message);
				db.getCollection(Message.COLLECTION).insertOne(
						new Document(mapper.readValue(jsonString.toString(), HashMap.class)));
			}
			BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
			List<Message> result = daoImp.getAllPosts(user, false);
			assertEquals(msgList, result);
		}catch (IOException e) {
			// TODO: handle exception
		}
	}

	@Test
	public void updateUserShouldUpdateTheUserInformation() {
		User user = new User();
		user.setName("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		daoImp.createUser(user);

		user.addFollower("NewFollower");
		assertEquals(user, daoImp.updateFollowerList(user));

	}

}
