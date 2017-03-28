package main; /**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame {

    private JTextField field;
    private JButton button;
    private JButton button1;
    private JButton button2;

    public MainFrame() {
        super();
        build();
    }

    public void build() {
        setTitle("test");
        setSize(400, 400);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(buildMainPane());
    }

    public JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        JLabel label = new JLabel("Nom du projet");
        panel.add(label);

        field = new JTextField();
        field.setColumns(10);
        panel.add(field);

        button = new JButton(new CreateProject("créer le projet", this));
        panel.add(button);

        button1 = new JButton(new Open("Spécifier le chemin de votre projet", this,""));
        panel.add(button1);

        return panel;
    }

    public JTextField getField() {
        return field;
    }

     void changePanel(){
        setContentPane(buildSecondPane());
        validate();
    }

    public JPanel buildSecondPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        button2 = new JButton(new DisplayRoutes("afficher les routes", this));
        panel.add(button2);

        return panel;
    }

    public JPanel buildMainPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        JFrame frame = this;

        button = new JButton("Créer un projet");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(buildContentPane());
                frame.validate();
            }
        });
        panel.add(button);
        button1 = new JButton(new Open("Ancien projet", this, "old"));
        panel.add(button1);
        button2 = new JButton(new Guide("Guide", this));
        panel.add(button2);

        return panel;
    }
}
