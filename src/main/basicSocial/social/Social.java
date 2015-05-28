package social;

import java.util.*;

public class Social {
	
	public Map<String,List<String>> messages = new HashMap<String, List<String>>();
	public Map<String,List<String>> followers = new HashMap<String, List<String>>();

	public List<String> execRequest(String cmd) {
		String[] cmd_line = cmd.split("\\s{1}",3);
		String usr = cmd_line[0].trim();
		if(cmd_line.length >1 ){
            if (cmd_line[1].equals("follow")) {
                if (this.followers.get(usr) == null) {
                    this.followers.put(usr, new ArrayList<String>());
                }
                this.followers.get(usr).add(cmd_line[2].trim());
                return followers.get(usr);
            } else if (cmd_line[1].equals("wall")) {
                List<String> wall = this.messages.get(usr);
                List<String> follow = this.followers.get(usr);
                for (String user : follow) {
                    wall.addAll(messages.get(user));
                }
                return wall;
            } else if (cmd_line[1].equals("->")) {
                if (this.messages.get(usr) != null)
                    this.messages.get(usr).add(cmd_line[2].trim());
                else {
                    this.messages.put(usr.trim(), new ArrayList<String>(Arrays.asList(cmd_line[2].trim())));
                }
                return this.messages.get(usr);
            } else {
                return null;
            }
		}
		
		return this.messages.get(usr);
		
	}

}
