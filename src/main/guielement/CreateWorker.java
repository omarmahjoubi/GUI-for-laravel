package main.guielement;

import main.MainFrame;
import main.actions.LaunchServer;
import main.actions.Open;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by omar_ on 23/05/2017.
 */
public class CreateWorker extends SwingWorker<Void, Integer> {

    private MainFrame frame;
    private JFrame pbFrame ;
    private JProgressBar jpb;
    private JLabel label;
    private String line;
    private boolean created = false;

    public CreateWorker(MainFrame frame, JProgressBar jpb, JLabel label,JFrame pbFrame) {
        super();
        this.frame = frame;
        this.jpb = jpb;
        this.label = label;
        this.pbFrame=pbFrame ;
    }

    @Override
    protected Void doInBackground() throws Exception {
        Process p = null;
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd " + Open.absolutePathProject + " && composer create-project --prefer-dist laravel/laravel " + this.frame.getField().getText());
        builder.redirectErrorStream(true);
        try {
            p = builder.start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        int i = 0;
        while (true) {
            try {
                line = r.readLine();
                publish(i);
                i++;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        setProgress(30);
        return null;
    }

    protected void done() {

        if (created == true) {


            label.setText("le projet " + this.frame.getField().getText() + " a été crée avec succés");
            Open.absolutePathProject = Open.absolutePathProject + "//" + this.frame.getField().getText();
            pbFrame.dispatchEvent(new WindowEvent(pbFrame, WindowEvent.WINDOW_CLOSING));
            frame.changePanel();
        }
    }

    @Override
    protected void process(List<Integer> chunks) {
        int i = chunks.get(chunks.size() - 1);
        jpb.setValue(i); // The last value in this array is all we care about.
        System.out.println("i =====> " + i);
        double ifl = i;
        double percentage = (ifl / 257) * 100;
        int percentage_i = (int) percentage;
        if (percentage_i > 100) {
            percentage_i = 100 ;
        }
        System.out.println(i);
        if (line != null) {
            if (line.length() < 40) {
                label.setText(percentage_i + "% - " + line);
            }

        }
        if (i > 257) {
            created = true;
        }


    }


}


