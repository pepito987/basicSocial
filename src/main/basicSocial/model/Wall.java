package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peppe on 28/05/15.
 */
public class Wall {
    private String userName;
    private List<String> followers;
    private List<Message> posts;

    public Wall(String userName) {
        this.followers = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.userName = userName;
    }

    public List<Message> getPosts() {
        return posts;
    }

    public List<String> getPostsList(){
        List<String> postsList = new ArrayList<>();
        for( Message msg : this.posts){
            postsList.add(msg.getText());
        }
        return postsList;
    }

    public void setPosts(List<Message> posts) {
        this.posts = posts;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
