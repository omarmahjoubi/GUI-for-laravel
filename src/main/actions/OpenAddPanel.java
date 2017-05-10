package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by omar_ on 08/05/2017.
 */
public class OpenAddPanel extends AbstractAction {
    private MainFrame frame ;
    private String type ;

    public OpenAddPanel(String text,MainFrame frame,String type) {
        super(text);
        this.frame=frame ;
        this.type=type ;
    }

    public void actionPerformed(ActionEvent e) {
        if (type.equals("ctrl")) {
            this.frame.setContentPane(this.frame.buildAddControllerPanel());
        } else if (type.equals("mdl")) {
            this.frame.setContentPane(this.frame.buildAddModelPanel());
        } else {
            this.frame.setContentPane(this.frame.buildAddMiddlewarePanel());
        }
        this.frame.validate();

    }

}
