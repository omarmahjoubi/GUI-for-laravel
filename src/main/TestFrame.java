package main; /**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import javax.swing.SwingUtilities;


public class TestFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame fenetre = new MainFrame();
                fenetre.setVisible(true);
            }
        });
    }
}
