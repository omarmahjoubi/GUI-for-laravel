package main.actions;

import main.MainFrame;
import main.guielement.Worker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by omar_ on 10/05/2017.
 */
public class LaunchServer extends AbstractAction {

    private MainFrame frame;



    public static boolean launched = false ;

    public LaunchServer(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
                Worker worker = new Worker(this.frame);
                worker.execute();
        }
    }
}