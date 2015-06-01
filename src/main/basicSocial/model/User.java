package basicSocial.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	public static final String COLLECTION = "user";

	private String name;
	private List<String> followers;
	
	
	public User() {
		this.followers = new ArrayList<String>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	
	public void addFollower(String name){
		this.followers.add(name);
	}
	
	


}
