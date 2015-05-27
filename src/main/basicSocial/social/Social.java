package basicSocial.social;

import java.util.ArrayList;
import java.util.List;

public class Social {

	public List<String> messagesList = new ArrayList<String>();

	public List<String> execRequest(String cmd) {
		String[] cmd_line = cmd.split("->");
		if(cmd_line.length >1)
			this.messagesList.add(cmd_line[1]);
		
		return this.messagesList;
	}

}
