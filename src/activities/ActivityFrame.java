package activities;

import javax.swing.*;

import activities.operational.OperationPanel;

public class ActivityFrame extends JFrame {
    public ActivityFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new OperationPanel());
        setAlwaysOnTop(true);
        setFocusable(true);
        setLocationRelativeTo(null);
    }
}
