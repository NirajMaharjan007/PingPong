package activities.operational;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationPanel extends JPanel implements ActionListener {

    private final int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600;
    private boolean run = false;

    public final static int SIZE = 20;

    public static int ballPosX = 5 * SIZE, ballPosY = 5 * SIZE;

    private int ballDirX = -5, ballDirY = -4;

    private int paddleY = 30, paddleY2 = 50;

    private final int paddleWidth = 10, paddleHeight = 8 * SIZE;

    Timer timer;

    Score score;

    public OperationPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        start();
        setLayout(new GridLayout());
        new Controller();
        score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawLine(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);

        drawBall(g);
        drawPaddle(g);
        score.drawScore(g);
        checkCollision();
    }

    protected void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ballPosX, ballPosY, SIZE, SIZE);
    }

    protected void start() {
        run = true;

        timer = new Timer(15, this);
        timer.start();
    }

    protected void drawPaddle(Graphics g) {
        g.setColor(Color.green);
        g.fillRoundRect(2 * SIZE, paddleY, paddleWidth, paddleHeight, 15, 15);

        g.setColor(Color.BLUE);
        g.fillRoundRect(SCREEN_HEIGHT - (3 * SIZE), paddleY2, paddleWidth, paddleHeight, 15, 15);
    }

    protected void checkCollision() {
        int maxY = 440, minY = 0;
        Rectangle ball = new Rectangle(ballPosX, ballPosY, SIZE, SIZE);
        Rectangle paddle = new Rectangle(2 * SIZE, paddleY, paddleWidth, paddleHeight);
        Rectangle paddle2 = new Rectangle(SCREEN_HEIGHT - (3 * SIZE), paddleY2, paddleWidth, paddleHeight);

        if (paddleY > maxY)
            paddleY = maxY;

        if (paddleY < minY)
            paddleY = minY;

        if (paddleY2 > maxY)
            paddleY2 = maxY;

        if (paddleY2 < minY)
            paddleY2 = minY;

        if (paddle.intersects(ball))
            ballDirX = -ballDirX;

        if (paddle2.intersects(ball))
            ballDirX = -ballDirX;
    }

    protected void moveBall() {
        // System.out.println("Ball POSITION X: " + ballPosX + ", Y: " + ballPosY);
        ballPosX += ballDirX;
        ballPosY += ballDirY;

        if (ballPosX < 0)
            run = false;

        if (ballPosX > (SCREEN_HEIGHT - SIZE))
            run = false;

        if (ballPosY < 0)
            ballDirY = -ballDirY;

        if (ballPosY > (SCREEN_WIDTH - SIZE))
            ballDirY = -ballDirY;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (run) {
            moveBall();
        }

        repaint();
    }

    class Controller extends JFrame implements KeyListener {

        TextField control = new TextField(10);
        JLabel label = new JLabel("", JLabel.CENTER);

        public Controller() {
            setTitle("Controller");
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(100, 200);

            control.setEditable(false);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            panel.add(control);
            panel.add(label);

            add(panel);

            control.addKeyListener(this);

            setSize(new Dimension(200, 150));

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    label.setText("UP");
                    paddleY -= SIZE;
                    repaint();
                    break;

                case KeyEvent.VK_DOWN:
                    label.setText("Down");
                    paddleY += SIZE;
                    repaint();
                    break;

                case KeyEvent.VK_W:
                    label.setText("UP");
                    paddleY2 -= SIZE;
                    repaint();
                    break;

                case KeyEvent.VK_S:
                    label.setText("Down");
                    paddleY2 += SIZE;
                    repaint();
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
