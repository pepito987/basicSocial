package DAOImp;

import com.mongodb.client.MongoDatabase;
import model.Wall;
import org.bson.Document;
import utils.MongoUtils;

/**
 * Created by peppe on 28/05/15.
 */
public class WallDAOImp implements WallDAO {

    public static String COLLECTION = "SocialWall";

    @Override
    public void saveWall(Wall wall) {
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
