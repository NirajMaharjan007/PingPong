package activities.operational;

import java.awt.*;

public class Score {
    private int score1, score2, screen_width, screen_height, size = OperationPanel.SIZE;

    public Score() {
        score2 = score1 = 0;
    }

    public Score(int width, int height) {
        this.screen_width = width;
        this.screen_height = height;
    }

    public void drawScore(Graphics g) {
        Font font = new Font("Helvetica", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.RED);

        String str1, str2;

        str1 = "Player One Score: " + score1;
        str2 = "Player Two Score: " + score2;

        g.drawString(str1, screen_width / 10, screen_height / 20);
        g.drawString(str2, screen_width - (12 * size), screen_height / 20);
    }
}
