import java.util.*;

public class SocialMediaData {
    
    public static class User {
        String username;
        Map<String, String> attributes; //e.g., age, gender, location
        List<Post> posts;

        public User (String username) {
            this.username = username;
            this.attributes = new HashMap<>();
            this.posts = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public void addAttribute(String key, String value) {
            attributes.put(key, value);
        }

        public String getAttribute(String key) {
            return attributes.get(key);
        }

        public void addPost(Post post) {
            posts.add(post);
        }

        public List<Post> getPosts() {
            return posts;
        }
    }

    public static class Post {
        User author;
        String content;

        public Post(User author, String content) {
            this.author = author;
            this.content = content;
        }

        public User getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }
    }
}