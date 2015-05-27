package basicSocial.social;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Social {
	
	public Map<String,List<String>> messages = new HashMap<String, List<String>>();
	public List<String> followers = new ArrayList<String>();

	public List<String> execRequest(String cmd) {
		String[] cmd_line = cmd.split("\\s{1}",3);
		if(cmd_line.length >1 ){
			if( cmd_line[1].equals("follow")){
				this.followers.add(cmd_line[2]);
			}
			if( this.messages.get(cmd_line[0]) != null)
				this.messages.get(cmd_line[0]).add(cmd_line[1]);
			else{
				this.messages.put(cmd_line[0].trim(), new ArrayList<String>(Arrays.asList(cmd_line[2])));
			}	
		}
		return this.messages.get(cmd_line[0]);
	}

}
