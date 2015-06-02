package basicSocial.DAOImp;

import java.util.List;

import org.bson.Document;
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

}
