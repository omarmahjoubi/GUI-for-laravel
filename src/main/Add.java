package main;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by omar_ on 08/05/2017.
 */
public class Add extends AbstractAction {

    private MainFrame frame ;
    private AddPanel panel ;

    public Add(String text,AddPanel panel,MainFrame frame) {
        super(text);
        this.panel=panel;
        this.frame=frame;
    }

    public void actionPerformed(ActionEvent e) {

    }

}
