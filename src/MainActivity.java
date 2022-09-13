import javax.swing.UIManager;

import activities.ActivityFrame;

public class MainActivity {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");

        new ActivityFrame();
        System.out.println("MainActivity.main()");
    }
}
