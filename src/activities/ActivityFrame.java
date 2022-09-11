package activities;

import javax.swing.*;

import activities.operational.OperationPanel;

public class ActivityFrame extends JFrame {
    public ActivityFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setFocusable(true);
        add(new OperationPanel());
        pack();
        setLocationRelativeTo(null);
    }
}
