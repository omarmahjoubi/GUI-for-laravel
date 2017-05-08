package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddPanel extends JPanel {

    protected MainFrame frame ;
    protected JLabel name_label ;
    protected JLabel dir_label ;
    protected JTextField name ;
    protected JTextField name_dir ;

    public AddPanel(MainFrame frame) {

        super() ;

        this.frame=frame ;

       name_label = new JLabel() ;
       dir_label = new JLabel() ;
       name = new JTextField()  ;
       name_dir = new JTextField() ;

        setLayout(new GridLayout(3, 2));
        setBackground(Color.white);


        // des panels vides pour representer les celluse du grid layout
        JPanel[][] emptyPanels = new JPanel[3][2];
        for (int m = 0; m < 3; m++) {
            for (int l = 0; l < 2; l++) {

                emptyPanels[m][l] = new JPanel();
                add(emptyPanels[m][l]);
            }
        }
        emptyPanels[0][0].add(name_label) ;

        name.setColumns(15);
        emptyPanels[0][1].add(name) ;





        emptyPanels[1][0].add(dir_label);

        name_dir.setColumns(15);
        emptyPanels[1][1].add(name_dir) ;

        JButton confirm = new JButton (new Add("Ajouter",this,this.frame)) ;
        emptyPanels[2][1].add(confirm);

    }


    public JTextField getName1() {                       // getName1 pour la differencier de la méthode getName de JPanel
        return name;
    }

    public JTextField getName_dir() {
        return name_dir;
    }
}
