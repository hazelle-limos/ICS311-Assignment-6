import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create users with more attributes
        SocialMediaData.User jane = new SocialMediaData.User("jane");
        jane.addAttribute("age", "25");
        jane.addAttribute("location", "Hawaii");
        jane.addAttribute("gender", "female");

        SocialMediaData.User john = new SocialMediaData.User("john");
        john.addAttribute("age", "30");
        john.addAttribute("location", "California");
        john.addAttribute("gender", "male");

        SocialMediaData.User alice = new SocialMediaData.User("alice");
        alice.addAttribute("age", "40");
        alice.addAttribute("location", "New York");
        alice.addAttribute("gender", "female");

        SocialMediaData.User bob = new SocialMediaData.User("bob");
        bob.addAttribute("age", "22");
        bob.addAttribute("location", "Hawaii");
        bob.addAttribute("gender", "male");

        // Create posts with more variety
        SocialMediaData.Post p1 = new SocialMediaData.Post(jane, "I love programming and coffee!");
        SocialMediaData.Post p2 = new SocialMediaData.Post(john, "Coffee is great, but I also enjoy tea.");
        SocialMediaData.Post p3 = new SocialMediaData.Post(jane, "Learning Java is fun and exciting!");
        SocialMediaData.Post p4 = new SocialMediaData.Post(alice, "Big data and machine learning are the future of tech.");
        SocialMediaData.Post p5 = new SocialMediaData.Post(bob, "This weather in Hawaii is absolutely amazing today!");
        SocialMediaData.Post p6 = new SocialMediaData.Post(bob, "Can't believe how awesome programming can be.");
        SocialMediaData.Post p7 = new SocialMediaData.Post(john, "I hate spam and fake news. It's ruining social media!");
        SocialMediaData.Post p8 = new SocialMediaData.Post(alice, "Politics, economics, and technology are deeply connected.");
        SocialMediaData.Post p9 = new SocialMediaData.Post(jane, "Java programming has made me love coding again!");
        SocialMediaData.Post p10 = new SocialMediaData.Post(bob, "Coffee, coffee, coffee! Best drink ever.");

        List<SocialMediaData.Post> posts = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        // Example 1: Filter by keyword and location
        Set<String> include1 = new HashSet<>(Arrays.asList("coffee"));
        Set<String> exclude1 = new HashSet<>(Collections.singletonList("tea"));
        Map<String, String> filters1 = new HashMap<>();
        filters1.put("location", "Hawaii");

        System.out.println("\n=== Word Cloud: Coffee posts from Hawaii ===");
        Map<String, Integer> wordCloud1 = WordCloudGenerator.generateWordCloud(posts, include1, exclude1, filters1);
        printWordCloud(wordCloud1);

        // Example 2: Filter by gender and keyword
        Set<String> include2 = new HashSet<>(Arrays.asList("programming"));
        Set<String> exclude2 = new HashSet<>();
        Map<String, String> filters2 = new HashMap<>();
        filters2.put("gender", "female");

        System.out.println("\n=== Word Cloud: Programming posts by females ===");
        Map<String, Integer> wordCloud2 = WordCloudGenerator.generateWordCloud(posts, include2, exclude2, filters2);
        printWordCloud(wordCloud2);

        // Example 3: Filter by age
        Set<String> include3 = new HashSet<>();
        Set<String> exclude3 = new HashSet<>();
        Map<String, String> filters3 = new HashMap<>();
        filters3.put("age", "40");

        System.out.println("\n=== Word Cloud: Posts by users aged 40 ===");
        Map<String, Integer> wordCloud3 = WordCloudGenerator.generateWordCloud(posts, include3, exclude3, filters3);
        printWordCloud(wordCloud3);
    }

    // Helper to print word cloud results
    private static void printWordCloud(Map<String, Integer> wordCloud) {
        for (Map.Entry<String, Integer> entry : wordCloud.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
