package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by omar_ on 16/04/2017.
 */
public class OpenAddMigrationPanel extends AbstractAction {
    private MainFrame frame ;

    public OpenAddMigrationPanel(String text,MainFrame frame) {
        super(text);
        this.frame=frame ;
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this.frame.buildAddMigrationPanel());
        this.frame.validate();

    }

}
