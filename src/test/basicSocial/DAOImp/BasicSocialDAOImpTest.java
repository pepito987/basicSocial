package basicSocial.DAOImp;

import java.util.List;

import org.bson.Document;
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
	
	@Test
	public void createUserShouldCreateAnUserInTheDatabase(){
		User user = new User();
		user.setName("Charlie");
		user.addFollower("Bob");
		user.addFollower("Alice");
		BasicSocialDAOImp daoImp = new BasicSocialDAOImp();
		daoImp.createUser(user);
		
		MongoDatabase db = MongoUtils.getMongoDB();
		MongoCollection<Document> collection = db.getCollection(User.COLLECTION);
		Document myDoc = collection.find(eq("name","Charlie")).first();
		assertEquals(user.getName(),myDoc.getString("name"));

	}

}
