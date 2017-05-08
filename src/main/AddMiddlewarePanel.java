package main;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddMiddlewarePanel extends AddPanel {

    public  AddMiddlewarePanel(MainFrame frame) {
        super(frame) ;
        name_label.setText("Nom du middleware");
        dir_label.setText("répertoire contenant le middleware (Optionel)");
    }

}

