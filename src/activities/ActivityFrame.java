package activities;

import javax.swing.*;
import java.awt.Color;

public class ActivityFrame extends JFrame {
    public ActivityFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setResizable(false);
        setFocusable(true);
        setLocationRelativeTo(null);
    }
}
