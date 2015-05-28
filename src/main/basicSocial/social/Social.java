package social;

import model.Message;
import model.Wall;

import java.util.*;

public class Social {

    private Map<String, Wall> walls = new HashMap<>();


	public List<String> execRequest(String cmd) {
		String[] cmd_line = cmd.split("\\s{1}",3);
		String usr = cmd_line[0].trim();
		if(cmd_line.length >1 ){
            if (cmd_line[1].equals("follow")) {
                return addFollower(usr,cmd_line[2]);
            } else if (cmd_line[1].equals("wall")) {
                Wall user_wall = this.walls.get(usr);
                List<String> follow = user_wall.getFollowers();
                List<String> posts = user_wall.getPostsList();
                for (String user : follow) {
                    posts.addAll(this.walls.get(user).getPostsList());
                }
                return posts;
            } else if (cmd_line[1].equals("->")) {
                checkUser(usr);
                this.walls.get(usr).getPosts().add(new Message(cmd_line[2].trim()));
                return this.walls.get(usr).getPostsList();
            } else {
                return null;
            }
		}
		return this.walls.get(usr).getPostsList();
		
	}

    private void checkUser(String userName){
        if( this.walls.get(userName) == null ){
            this.walls.put(userName,new Wall(userName));
        }
    }


    private List<String> addFollower(String user, String follower){
        checkUser(user);
        List<String> followers = this.walls.get(user).getFollowers();
        if( ! followers.contains(follower) ){
            followers.add(follower);
        }
        return followers;
    }

}
