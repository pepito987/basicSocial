package basicSocial.utils;

import com.mongodb.client.MongoDatabase;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by peppe on 29/05/15.
 */
public class MongoUtilsTest {

    @Test
    public void getMongoShouldReturnAMongoClient(){
        MongoDatabase db = MongoUtils.getMongoDB();
        assertNotNull(db);
        MongoUtils.closeMongo();
    }

}
