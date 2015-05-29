package utils;

import com.mongodb.MongoClient;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by peppe on 29/05/15.
 */
public class MongoUtilsTest {

    @Test
    public void getMongoShouldReturnAMongoClient(){
        MongoClient db = MongoUtils.getMongoDB();
        assertNotNull(db);
        db.close();
    }

}
