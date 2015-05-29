package utils;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by peppe on 29/05/15.
 */
public class MongoUtilsTest {

        @Test
    public void getMongoShouldReturnAMongoDatabase(){
        assertNotNull(MongoUtils.getMongoDB(MongoUtils.DB_NAME,MongoUtils.DB_PORT));
    }

}
