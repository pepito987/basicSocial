package basicSocial.social;

import java.util.ArrayList;
import java.util.List;

public class Social {

	public List<String> messagesList = new ArrayList<String>();

	public void execRequest(String cmd) {
		String[] cmd_line = cmd.split("->");
		this.messagesList.add(cmd_line[1]);
	}

}
