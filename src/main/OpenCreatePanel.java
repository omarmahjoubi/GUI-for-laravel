package main;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by omar_ on 16/04/2017.
 */
public class OpenCreatePanel extends AbstractAction {

    private MainFrame frame ;

    public OpenCreatePanel(String text,MainFrame frame) {
        super(text) ;
        this.frame = frame ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setContentPane(frame.buildContentPane());
        frame.validate();
    }
}
