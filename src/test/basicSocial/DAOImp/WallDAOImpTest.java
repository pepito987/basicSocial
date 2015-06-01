package DAOImp;

import com.mongodb.client.MongoDatabase;
import model.Wall;
import org.bson.Document;
import org.junit.Test;
import utils.MongoUtils;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        System.out.println(doc.toJson());
        MongoUtils.closeMongo();

    }

//    @Test
    public void getWallShouldReturnTheWallForTheSpecificUser(){
        Wall wall = new Wall("Alice");

        MongoDatabase db = MongoUtils.getMongoDB();
        Document doc = new Document();
        doc.append("userName",wall.getUserName());
        db.getCollection(WallDAOImp.COLLECTION).insertOne(doc);

        WallDAOImp wallImp = new WallDAOImp();
        assertEquals(wall,wallImp.getWall("Alice"));
    }

}
