package activities.operational;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationPanel extends JPanel implements ActionListener {

    private final int SCREEN_WIDTH = 600, SCREEN_HIGHT = 600;
    private boolean run = false;

    public final static int SIZE = 20;

    Timer timer;

    public static int ballPosX = 5 * SIZE, ballPosY = 5 * SIZE;

    private int ballDirX = -5, ballDirY = -4;

    private int paddleY = 30;

    private final int paddleWidth = 10, paddleHeight = 8 * SIZE;

    public OperationPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HIGHT));
        setBackground(Color.BLACK);
        start();
        setLayout(new GridLayout());
        new Controller();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
        drawPaddle(g);
        checkCollision();
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

    protected void drawPaddle(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(2 * SIZE, paddleY, paddleWidth, paddleHeight);
    }

    protected void checkCollision() {
        if (paddleY >= 490)
            paddleY = 500;

        if (paddleY <= 0)
            paddleY = 0;
    }

    protected void moveBall() {
        // System.out.println("Ball POSITION X: " + ballPosX + ", Y: " + ballPosY);
        ballPosX += ballDirX;
        ballPosY += ballDirY;

        Rectangle ball = new Rectangle(ballPosX, ballPosY, SIZE, SIZE);
        Rectangle paddle = new Rectangle(2 * SIZE, paddleY, paddleWidth, paddleHeight);

        if (paddle.intersects(ball)) {
            ballDirX = -ballDirX;
            // ballDirY = -ballDirY;
        }

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

    class Controller extends JFrame implements KeyListener {

        TextField control = new TextField(10);
        JLabel label = new JLabel("", JLabel.CENTER);

        public Controller() {
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(100, 200);

            control.setEditable(false);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            panel.add(control);
            panel.add(label);

            add(panel);
            pack();

            control.addKeyListener(this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Paddle Y: " + paddleY);
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
