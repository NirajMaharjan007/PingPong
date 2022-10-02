package activities.operational;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowPanel extends JPanel implements ActionListener {
    private JButton done;
    private JTextField player1, player2, winning_score;

    public ShowPanel() {
        done = new JButton("Done");

        player1 = new JTextField(10);
        player2 = new JTextField(10);
        winning_score = new JTextField(10);

        JLabel label;
        Font font = new Font("Helvetica", Font.PLAIN, 14);

        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 5));

        JPanel panel = new JPanel(new GridLayout(4, 2, 2, 8));
        panel.setFont(font);

        label = new JLabel("Enter a winning score: ");
        panel.add(label);
        panel.add(winning_score);

        label = new JLabel("Enter a player one color:");
        panel.add(label);
        panel.add(player1);

        label = new JLabel("Enter a player two color:");
        panel.add(label);
        panel.add(player2);

        panel.add(done);

        add(panel);

        done.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == done) {
            JOptionPane.showMessageDialog(done, "Setting is done now");
        }
    }
}
