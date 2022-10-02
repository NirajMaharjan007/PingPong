package activities.operational;

import javax.swing.*;
import java.awt.event.*;

public class FrameMenuBar extends JMenuBar implements ActionListener {
    JMenuItem settings;
    JMenu menu;

    public FrameMenuBar() {
        menu = new JMenu("Menu");
        settings = new JMenuItem("Settings");

        menu.add(settings);
        add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        if (e.getSource() == settings) {
            dialog.setTitle("Settings");
            dialog.add(new ShowPanel());
        }
    }

}
