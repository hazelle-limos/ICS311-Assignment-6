import java.util.*;
import java.util.stream.Collectors;

public class WordCloudGenerator {

    // Stopwords to ignore
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
        "the","is","and","a","to","of","in","for","on","with","as","at","an","it",
        "by","this","that","from","or","be","are","was","were","have","has","had",
        "i","you","he","she","they","we","me","my","your","our","their"
    ));

    // Generates a word frequency map for posts based on filters
    public static Map<String, Integer> generateWordCloud(
            List<SocialMediaData.Post> posts,
            Set<String> includeKeywords,
            Set<String> excludeKeywords,
            Map<String, String> userAttributeFilters) {

        Map<String, Integer> wordFrequency = new HashMap<>();

        for (SocialMediaData.Post post : posts) {
            // Filter posts by user attributes
            boolean matchesAttributes = true;
            for (Map.Entry<String, String> filter : userAttributeFilters.entrySet()) {
                String value = post.getAuthor().getAttribute(filter.getKey());
                if (value == null || !value.equalsIgnoreCase(filter.getValue())) {
                    matchesAttributes = false;
                    break;
                }
            }
            if (!matchesAttributes) continue;

            // Filter posts by keywords (include)
            if (!includeKeywords.isEmpty()) {
                boolean containsInclude = false;
                for (String keyword : includeKeywords) {
                    if (post.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                        containsInclude = true;
                        break;
                    }
                }
                if (!containsInclude) continue;
            }

            // Exclude posts with exclude keywords
            boolean containsExclude = false;
            for (String keyword : excludeKeywords) {
                if (post.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                    containsExclude = true;
                    break;
                }
            }
            if (containsExclude) continue;

            // Tokenize and count words
            String[] words = post.getContent().toLowerCase().split("\\W+");
            for (String word : words) {
                if (word.isEmpty() || STOPWORDS.contains(word)) continue;
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Sort by frequency descending
        return wordFrequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}
