package basicSocial.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

/**
 * Created by peppe on 29/05/15.
 */
public class MongoUtils {

    public static MongoClient mongoClient = null;
    public static MongoDatabase db ;

    public static String HOST = "localhost";
    public static String DB_NAME = "basicSocialDB";
    public static int DB_PORT = 27017;

    public static MongoDatabase getMongoDB() {
        if (mongoClient == null) {
            try {
                mongoClient = new MongoClient(HOST, DB_PORT);
            } catch (MongoException e) {
                e.printStackTrace();
            }
            db = mongoClient.getDatabase(DB_NAME);
            
        }
        return db;
    }
    
    public static MongoDatabase getMongoDB(String dbName) {
        if (mongoClient == null) {
            try {
                mongoClient = new MongoClient(HOST, DB_PORT);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        db = mongoClient.getDatabase(dbName);
        return db;
    }

    public static void closeMongo(){
        if(mongoClient !=  null ) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
