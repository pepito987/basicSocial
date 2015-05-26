package social;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peppe on 26/05/15.
 */
public class Social {
    public static List<String> messages = new ArrayList<String>();

    public void execRequest(String cmd) {
        String[] splitted_cmd = cmd.split("->");
        this.messages.add(splitted_cmd[1].trim());
    }
}
