package social;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by peppe on 26/05/15.
 */
public class SocialTest {

    @Test
    public void execRequestShouldAppendTheMessageToTheMessageListIfTheCommandIsPosting(){
        String cmd = "alice -> Hello World!";
        Social test = new Social();
        test.execRequest(cmd);
        assertTrue("actual: "+Social.messages,Social.messages.contains("Hello World!"));
    }



}
