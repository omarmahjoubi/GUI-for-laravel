package main.panels;

import main.MainFrame;
import main.panels.AddPanel;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddMiddlewarePanel extends AddPanel {

    public  AddMiddlewarePanel(MainFrame frame) {
        super(frame) ;
        nameLabel.setText("Nom du middleware");
        dirLabel.setText("répertoire contenant le middleware (Optionel)");
    }

}

