package activities.operational;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameMenuBar extends MenuBar implements ActionListener {

    MenuItem settings;
    Menu menu;

    public FrameMenuBar() {
        menu = new Menu("Menu");
        settings = new MenuItem("Settings");

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
