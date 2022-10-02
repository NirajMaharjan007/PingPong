package activities.operational;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationPanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600;
    private boolean run = false;

    public final static int SIZE = 20;

    public static int ballPosX = 10 * SIZE, ballPosY = 10 * SIZE;

    private int ballDirX = -5, ballDirY = -4;

    private int paddleY = 30, paddleY2 = 50;

    private final int paddleWidth = 10, paddleHeight = 8 * SIZE;

    Timer timer = new Timer(15, this);

    private int score1 = 0,
            score2 = 0;

    public OperationPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        start();
        setLayout(new GridLayout());
        new Controller();

    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawLine(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);

        drawBall(g);
        drawPaddle(g);
        drawScore(g);

        checkCollision();

        if (!run)
            gameOver((Graphics2D) g);
    }

    private void drawScore(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.RED);

        String str1, str2;

        str1 = "Player One Score: " + score1;
        str2 = "Player Two Score: " + score2;

        g.drawString(str1, SCREEN_WIDTH / 10, SCREEN_HEIGHT / 20);
        g.drawString(str2, SCREEN_WIDTH - (12 * SIZE), SCREEN_HEIGHT / 20);

        if (score2 == 5 || score1 == 5) {
            run = false;
        }
    }

    protected void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ballPosX, ballPosY, SIZE, SIZE);
    }

    protected void start() {
        run = true;
        timer.setRepeats(true);
        timer.stop();
    }

    protected void drawPaddle(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(2 * SIZE, paddleY, paddleWidth, paddleHeight);
        g.setColor(Color.BLUE);
        g.fillRect(SCREEN_HEIGHT - (3 * SIZE), paddleY2, paddleWidth, paddleHeight);
    }

    private void gameOver(Graphics2D g) {
        String str = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 45);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(str, 180, SCREEN_HEIGHT / 2);

        if (score1 > score2) {
            String title = "Player One Wins!";
            font = new Font("Helvetica", Font.PLAIN, 24);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString(title, 210, (SCREEN_HEIGHT + 4 * SIZE) / 2);
        } else {
            String title = "Player Two Wins!";
            font = new Font("Helvetica", Font.PLAIN, 24);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString(title, 210, (SCREEN_HEIGHT + 4 * SIZE) / 2);
        }
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

        if (paddle.intersects(ball) || ball.intersects(paddle))
            ballDirX = -ballDirX;

        if (paddle2.intersects(ball) || ball.intersects(paddle2))
            ballDirX = -ballDirX;

    }

    protected void moveBall() {
        ballPosX += ballDirX;
        ballPosY += ballDirY;

        if (ballPosX < 0) {
            // run = false;
            timer.stop();
            restart();
            score2++;
        }

        if (ballPosX > (SCREEN_HEIGHT - SIZE)) {
            // run = false;
            timer.stop();
            restart();
            score1++;
        }

        if (ballPosY < 0)
            ballDirY = -ballDirY;

        if (ballPosY > (SCREEN_WIDTH - SIZE))
            ballDirY = -ballDirY;
    }

    private void restart() {
        if (ballPosX < 0) {
            ballPosX = SCREEN_HEIGHT / 2;
            ballPosY = SCREEN_WIDTH / 2;
        }

        if (ballPosX > SCREEN_WIDTH - SIZE) {
            ballPosX = (SCREEN_HEIGHT / 2) - SIZE;
            ballPosY = SCREEN_WIDTH / 2;
        }
        ballDirX = -ballDirX;
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
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setJMenuBar(new FrameMenuBar());
            control.setEditable(false);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            panel.add(control);
            panel.add(label);

            add(panel);

            control.addKeyListener(this);

            setSize(new Dimension(200, 150));
            setVisible(true);
            setLocation(200, 200);

        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    label.setText("Continue");
                    timer.start();
                    break;

                case KeyEvent.VK_UP:
                    label.setText("UP");
                    paddleY2 -= SIZE;
                    // repaint();
                    break;

                case KeyEvent.VK_DOWN:
                    label.setText("Down");
                    paddleY2 += SIZE;
                    // repaint();
                    break;

                case KeyEvent.VK_W:
                    label.setText("UP");
                    paddleY -= SIZE;
                    // repaint();
                    break;

                case KeyEvent.VK_S:
                    label.setText("Down");
                    paddleY += SIZE;
                    // repaint();
                    break;

                case KeyEvent.VK_ENTER:
                    run = true;
            }
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
