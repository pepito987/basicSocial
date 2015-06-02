package basicSocial.DAOImp;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

import basicSocial.model.Message;
import basicSocial.model.User;
import basicSocial.utils.MongoUtils;

public class BasicSocialDAOImp implements BasicSocialDAO {
	
	private static MongoDatabase db = MongoUtils.getMongoDB();

	@Override
	public void createUser(User user) {
		
		MongoCollection<Document> userColl = db.getCollection(User.COLLECTION);
		Document doc = userColl.find(eq("name",user.getName())).first();
		if(doc == null ){
			doc = new Document("name", user.getName()).append("followers", user.getFollowers());
			userColl.insertOne(doc);
		}
	}

	@Override
	public void savePost(Message post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getAllPosts(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
