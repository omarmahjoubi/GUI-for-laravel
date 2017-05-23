package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by omar_ on 23/05/2017.
 */
public class OpenHome extends AbstractAction {

    private MainFrame frame;
    private String status = "";

    public OpenHome(String text, MainFrame frame, String status) {
        super(text);
        this.status = status;
        this.frame = frame;
    }
     public void actionPerformed(ActionEvent e) {
        if (status.equals("opened")) {
            this.frame.changePanel();
        } else {
            this.frame.setContentPane(frame.buildMainPane());
            this.frame.validate() ;
        }

    }
}