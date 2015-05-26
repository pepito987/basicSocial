package social;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by peppe on 26/05/15.
 */
public class SocialTest {

    static Social sc;

    @BeforeClass
    public static void setEnvironment(){
        sc = new Social();
    }

    @Test
    public void execRequestShouldAppendTheMessageToTheMessageListIfTheCommandIsPosting(){
        String cmd = "alice -> Hello World!";
        sc.execRequest(cmd);
        assertTrue("actual: "+Social.messages,Social.messages.contains("Hello World!"));
    }



}
