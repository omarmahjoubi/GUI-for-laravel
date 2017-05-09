package main;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddModelPanel extends AddPanel {

    public  AddModelPanel(MainFrame frame) {
        super(frame) ;
        nameLabel.setText("Nom du modéle");
        dirLabel.setText("répertoire contenant le modéle (Optionel)");
    }

}
