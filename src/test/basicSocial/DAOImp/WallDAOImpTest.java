package DAOImp;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import model.Wall;
import org.bson.Document;
import org.junit.Test;
import utils.MongoUtils;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by peppe on 29/05/15.
 */
public class WallDAOImpTest {

    @Test
    public void saveWallShouldSaveAWallIntanceInTheDatabase(){
        WallDAOImp wallImp = new WallDAOImp();
        wallImp.saveWall(new Wall("Alice"));

        MongoDatabase db = MongoUtils.getMongoDB().getDatabase(WallDAOImp.COLLECTION);
        FindIterable<Document> iter = db.getCollection(WallDAOImp.COLLECTION).find(new Document("userName","Alice"));
        assertNotNull(iter.first());
        MongoUtils.closeMongo();

    }

}
