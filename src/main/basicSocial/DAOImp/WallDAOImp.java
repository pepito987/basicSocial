package basicSocial.DAOImp;

import org.bson.Document;

import basicSocial.model.Wall;
import basicSocial.model.WallTest;
import basicSocial.utils.MongoUtils;

import com.mongodb.client.MongoDatabase;


/**
 * Created by peppe on 28/05/15.
 */
public class WallDAOImp implements BasicSocialDAO {

    public static String COLLECTION = "SocialWall";

    @Override
    public void saveMessage(Wall wall) {
        Document doc = new Document();
        doc.append("userName",wall.getUserName());
        doc.append("posts",wall.getPosts());
        doc.append("followers",wall.getFollowers());
        MongoDatabase db = MongoUtils.getMongoDB();
        db.getCollection(COLLECTION).insertOne(doc);

    }

    @Override
    public Wall getWall(String userName) {
        return null;
    }
}
