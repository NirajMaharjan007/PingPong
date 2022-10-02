package activities;

import javax.swing.*;

import activities.operational.OperationPanel;

public class ActivityFrame extends JFrame {

    public ActivityFrame() {
        setTitle("Activity");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new OperationPanel());
        pack();
        setResizable(false);
        setAlwaysOnTop(true);
        setFocusable(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
