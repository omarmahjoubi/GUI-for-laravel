package main;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddModelPanel extends AddPanel {

    public  AddModelPanel(MainFrame frame) {
        super(frame) ;
        name_label.setText("Nom du modéle");
        dir_label.setText("répertoire contenant le modéle (Optionel)");
    }

}
