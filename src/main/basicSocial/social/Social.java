package basicSocial.social;


import java.util.*;

import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

import basicSocial.DAOImp.BasicSocialDAO;
import basicSocial.DAOImp.BasicSocialDAOImp;
import basicSocial.model.Message;
import basicSocial.model.User;
import basicSocial.utils.MongoUtils;

public class Social {

//    private Map<String, Wall> walls = new HashMap<>();
	
	private static BasicSocialDAO socialDAO = new BasicSocialDAOImp();

	public List<String> execRequest(String cmd) {
		String[] cmd_line = cmd.split("\\s{1}",3);
		String userName = cmd_line[0].trim();
		User user = socialDAO.getUser(userName);
		if(cmd_line.length >1 ){
            if (cmd_line[1].equals("follow")) {
            	user.addFollower(cmd_line[2]);
                return socialDAO.updateFollowerList(user).getFollowers();
            } else if (cmd_line[1].equals("wall")) {
            	if(user != null ){
            		List<Message> msgs = socialDAO.getAllPosts(user,false);
            		List<String> posts = new ArrayList<String>();
            		for (Message message : msgs) {
						posts.add(message.getText());
					}
            		return posts;
            	}
            	return null;
            } else if (cmd_line[1].equals("->")) {
                if(user == null ){
                	user = new User(userName);
                	socialDAO.createUser(user);
                }
                socialDAO.savePost(new Message(userName, cmd_line[2]));
                List<Message> msgs = socialDAO.getAllPosts(user,true);
        		List<String> posts = new ArrayList<String>();
        		for (Message message : msgs) {
					posts.add(message.getText());
				}
        		return posts;
            } else {
                return null;
            }
		}
		if(user != null ){
    		List<Message> msgs = socialDAO.getAllPosts(user,false);
    		List<String> posts = new ArrayList<String>();
    		for (Message message : msgs) {
				posts.add(message.getText());
			}
    		return posts;
    	}
    	return null;
		
	}


}
