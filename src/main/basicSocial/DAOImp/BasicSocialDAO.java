package basicSocial.DAOImp;

import java.util.List;

import basicSocial.model.Message;
import basicSocial.model.User;

/**
 * Created by peppe on 28/05/15.
 */
public interface BasicSocialDAO {

	public void createUser(User user);
	
	public User updateFollowerList(User user);
	
	public void savePost(Message post );
	
	public User getUser(String name);
	
	public List<Message> getAllPosts(User user, Boolean personal);
	
}
