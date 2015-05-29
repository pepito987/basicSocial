package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Created by peppe on 29/05/15.
 */
public class MongoUtils {

    public static MongoClient mongoClient = null;

    public static String HOST = "localhost";
    public static int DB_PORT = 27017;

    public static MongoClient getMongoDB() {
        if(mongoClient == null){
            try {
                mongoClient = new MongoClient(HOST,DB_PORT) ;
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        return mongoClient;
    }

    public static void closeMongo(){
        if(mongoClient !=  null ) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
