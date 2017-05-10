package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by omar_ on 22/04/2017.
 */
public class OpenAddRouteControllerPanel extends AbstractAction {
    private MainFrame frame ;

    public OpenAddRouteControllerPanel(String text,MainFrame frame) {
        super(text);
        this.frame=frame ;
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this.frame.buildAddRouteControllerPanel());
        this.frame.validate();

    }

}