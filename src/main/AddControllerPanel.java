package main;

import sun.applet.Main;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddControllerPanel extends AddPanel {

    public  AddControllerPanel(MainFrame frame) {
        super(frame) ;
        nameLabel.setText("Nom du contrôleur");
        dirLabel.setText("répertoire contenant le contrôleur (Optionel)");
    }

}
