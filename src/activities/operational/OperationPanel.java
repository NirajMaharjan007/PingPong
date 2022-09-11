package activities.operational;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationPanel extends JPanel implements ActionListener {

    private final int SCREEN_WIDTH = 600, SCREEN_HIGHT = 600, SIZE = 20;
    private boolean run = false;

    Timer timer;

    private int ballPosX = 5 * SIZE, ballPosY = 5 * SIZE;

    private int ballDirX = -2, ballDirY = -5;

    public OperationPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HIGHT));
        setBackground(Color.BLACK);
        start();
        setLayout(new GridLayout());
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
    }

    protected void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ballPosX, ballPosY, SIZE, SIZE);
    }

    protected void start() {
        run = true;
        timer = new Timer(7, this);
        timer.start();
    }

    protected void moveBall() {
        System.out.println("Ball POSITION X: " + ballPosX + ", Y: " + ballPosY);
        ballPosX += ballDirX;
        ballPosY += ballDirY;

        if (ballPosX < 0) {
            ballDirX = -ballDirX;
        }

        if (ballPosY < 0) {
            ballDirY = -ballDirY;
        }

        if (ballPosX > (SCREEN_HIGHT - SIZE)) {
            ballDirX = -ballDirX;
        }

        if (ballPosY > (SCREEN_WIDTH - SIZE)) {
            ballDirY = -ballDirY;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (run) {
            moveBall();
        }
        repaint();
    }
}
