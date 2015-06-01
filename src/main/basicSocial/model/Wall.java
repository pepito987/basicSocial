package basicSocial.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wall wall = (Wall) o;

        if (!followers.equals(wall.followers)) return false;
        if (!posts.equals(wall.posts)) return false;
        if (!userName.equals(wall.userName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + followers.hashCode();
        result = 31 * result + posts.hashCode();
        return result;
    }
}
