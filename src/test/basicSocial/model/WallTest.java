package model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by peppe on 28/05/15.
 */
public class WallTest {

    @Test
    public void getPostsListShoudlReturnAStringListWithAllThePosts(){
        Wall wall = new Wall("Alice");
        String msg1 = "Hello World!";
        String msg2 = "today is Friday";
        wall.getPosts().add(new Message(msg1));
        wall.getPosts().add(new Message(msg2));
        assertEquals(Arrays.asList(msg1,msg2),wall.getPostsList());

    }
}
