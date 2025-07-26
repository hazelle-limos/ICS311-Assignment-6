import javax.swing.*;
import java.awt.*;
import java.util.*;

public class WordCloudWindow extends JPanel {

    private final Map<String, Integer> wordCloudData;
    private final int maxFreq;

    public WordCloudWindow(Map<String, Integer> data) {
        this.wordCloudData = data;
        this.maxFreq = data.isEmpty() ? 1 : Collections.max(data.values());
        setPreferredSize(new Dimension(720, 480));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (wordCloudData.isEmpty()) return;

        Random random = new Random();
        int width = getWidth();
        int height = getHeight();

        // Keep track of placed word rectangles
        java.util.List<Rectangle> placedRects = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordCloudData.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();

            // Scale font size
            int fontSize = 14 + (int) ((freq / (double) maxFreq) * 40);
            g.setFont(new Font("Arial", Font.BOLD, fontSize));
            g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));

            FontMetrics fm = g.getFontMetrics();
            int wordWidth = fm.stringWidth(word);
            int wordHeight = fm.getHeight();

            int x, y;
            Rectangle rect;
            boolean overlaps;

            // Try multiple times to find a non-overlapping position
            int attempts = 0;
            do {
                x = random.nextInt(Math.max(1, width - wordWidth - 20));
                y = random.nextInt(Math.max(1, height - wordHeight - 20)) + wordHeight;
                rect = new Rectangle(x, y - wordHeight + 2, wordWidth - 4, wordHeight - 4);

                overlaps = false;
                for (Rectangle r : placedRects) {
                    if (r.intersects(rect)) {
                        overlaps = true;
                        break;
                    }
                }
                attempts++;
            } while (overlaps && attempts < 500); // fail-safe to prevent infinite loops

            // Draw and store rectangle
            g.drawString(word, x, y);
            placedRects.add(rect);
        }
    }

    public static void showWordCloud(Map<String, Integer> data, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new WordCloudWindow(data));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
