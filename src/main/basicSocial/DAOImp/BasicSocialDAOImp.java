package basicSocial.DAOImp;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

import basicSocial.model.Message;
import basicSocial.model.User;
import basicSocial.utils.MongoUtils;

public class BasicSocialDAOImp implements BasicSocialDAO {

	@Override
	public void createUser(User user) {
		Document doc = new Document("name", user.getName())
		.append("followers", user.getFollowers());
		MongoDatabase db = MongoUtils.getMongoDB();
		db.getCollection(User.COLLECTION).insertOne(doc);
	}

	@Override
	public void savePost(User user, Message post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getPosts(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
