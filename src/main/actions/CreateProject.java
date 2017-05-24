package main.actions; /**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import main.MainFrame;
import main.actions.Open;
import main.guielement.CreateWorker;
import main.guielement.Worker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

public class CreateProject extends AbstractAction {
    private MainFrame frame;
    private String projectPath = "";

    public CreateProject(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        try {

            projectPath = Open.absolutePathProject;
            System.out.println("projectPath = " + projectPath);
            if (!this.frame.getField().getText().equals("")) {


                if (Open.absolutePathProject != null) {
                    JProgressBar jpb = new JProgressBar();
                    jpb.setMaximum(257);
                    jpb.setPreferredSize(new Dimension(300, 20));
                    JLabel label = new JLabel("Loading ....");
                    JPanel panel = new JPanel();
                    JFrame frame1 = new JFrame();
                    panel.setLayout(new GridLayout(3, 1));
                    panel.setBackground(Color.white);


                    // des panels vides pour representer les celluse du grid layout
                    JPanel[][] emptyPanels = new JPanel[3][1];
                    for (int m = 0; m < 3; m++) {
                        for (int l = 0; l < 1; l++) {

                            emptyPanels[m][l] = new JPanel();
                            panel.add(emptyPanels[m][l]);
                        }
                    }

                    emptyPanels[0][0].add(jpb);
                    emptyPanels[1][0].add(label);
                    JButton cancel = new JButton("annuler");
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            Process p1;
                            try {

                                String command2 = "cmd.exe /c " + "taskkill /F /IM php.exe";
                                p1 = Runtime.getRuntime().exec(command2);
                                p1.waitFor();
                                frame1.dispatchEvent(new WindowEvent(frame1, WindowEvent.WINDOW_CLOSING));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    emptyPanels[2][0].add(cancel);
                    frame1.add(panel);
                    frame1.setLocationRelativeTo(null);
                    frame1.setSize(400, 150);
                    frame1.setVisible(true);

                    CreateWorker worker = new CreateWorker(this.frame, jpb, label, frame1);
                    worker.execute();
                } else {
                    JOptionPane.showMessageDialog(frame, "Vous devez spécifier le chemin de votre projet");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Vous devez spécifier le nom du projet");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
