package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by omar_ on 08/05/2017.
 */
public class AddPanel extends JPanel {

    protected MainFrame frame ;
    protected JLabel nameLabel ;
    protected JLabel dirLabel ;
    protected JTextField name ;
    protected JTextField nameDir ;

    public AddPanel(MainFrame frame) {

        super() ;

        this.frame=frame ;

       nameLabel = new JLabel() ;
       dirLabel = new JLabel() ;
       name = new JTextField()  ;
       nameDir = new JTextField() ;

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
        emptyPanels[0][0].add(nameLabel) ;

        name.setColumns(15);
        emptyPanels[0][1].add(name) ;





        emptyPanels[1][0].add(dirLabel);

        nameDir.setColumns(15);
        emptyPanels[1][1].add(nameDir) ;

        JButton confirm = new JButton (new Add("Ajouter",this,this.frame)) ;
        emptyPanels[2][1].add(confirm);

    }


    public JTextField getName1() {                       // getName1 pour la differencier de la méthode getName de JPanel
        return name;
    }

    public JTextField getnameDir() {
        return nameDir;
    }


    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getDirLabel() {
        return dirLabel;
    }
}
