package basicSocial.DAOImp;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

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
		User result = null;
		Document find = db.getCollection(User.COLLECTION).find(eq("name",name)).first();
		if(find != null){
			result = new User();
			result.setName(find.getString("name"));
			result.setFollowers(find.get("followers", List.class));
		}
		return result;
	}

	@Override
	public List<Message> getAllPosts(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
