package DAOImp;

import com.mongodb.client.MongoDatabase;
import model.Wall;
import org.bson.Document;
import org.junit.Test;
import utils.MongoUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by peppe on 29/05/15.
 */
public class WallDAOImpTest {

    @Test
    public void saveWallShouldSaveAWallIntanceInTheDatabase(){
        WallDAOImp wallImp = new WallDAOImp();
        wallImp.saveWall(new Wall("Alice"));

        MongoDatabase db = MongoUtils.getMongoDB();
        Document doc = db.getCollection(WallDAOImp.COLLECTION).find(new Document("userName","Alice")).first();
        assertNotNull(doc);
        assertEquals("Alice",doc.getString("userName"));
        MongoUtils.closeMongo();

    }


}
