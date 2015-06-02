package basicSocial.DAOImp;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.print.Doc;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.bson.Document;
import org.joda.time.LocalDateTime;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
		Document pst = new Document("sender",post.getSender()).append("text", post.getText()).append("time", post.getTime().toDate());
		db.getCollection(Message.COLLECTION).insertOne(pst);
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
	public List<Message> getAllPosts(User user) {
		List<Message> postsList = new ArrayList<Message>();
		System.out.println(user.getFollowers());
		List<String> constrain = new ArrayList<String>();
		constrain.add(user.getName());
		constrain.addAll(user.getFollowers());
		FindIterable<Document> results = db.getCollection(Message.COLLECTION).find(new Document("sender", new Document("$in", constrain)));
		MongoCursor<Document> cursor = results.iterator();
		while(cursor.hasNext()){
			Document tempDoc = cursor.next();
			Message post = new Message(tempDoc.getString("sender"), tempDoc.getString("text"));
			post.setTime(new LocalDateTime(tempDoc.get("time",Date.class).getTime())); 
			postsList.add(post);
		}
		return postsList;
	}

}
