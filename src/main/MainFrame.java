package main;
/**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import main.actions.*;
import main.panels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class MainFrame extends JFrame {

    private JTextField field;
    private JTextField route;
    private JTextField view;
    private JButton Confirm;
    private JButton button;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel vueName;
    private JComboBox methodList;
    private JButton server ;
    
    public MainFrame() {
        super();
        build();
    }

    public void build() {
        setTitle("Laravel GUI");
        setSize(400, 400);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(buildMainPane());
    }

    public JPanel buildContentPane() {
        JButton home ;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));
        panel.setBackground(Color.white);

        JPanel[][] emptyPanels = new JPanel[4][2];
        for (int m = 0; m < 4; m++) {
            for (int l = 0; l < 2; l++) {

                emptyPanels[m][l] = new JPanel();
                panel.add(emptyPanels[m][l]);
            }
        }

        if (Open.absolutePathProject!=null) {
            home =  new JButton( new OpenHome("Acceuil",this,"opened")) ;
        } else {
            home =  new JButton( new OpenHome("Acceuil",this,"")) ;
        }

        emptyPanels[0][1].add(home) ;


        JLabel label = new JLabel("Nom du projet");
        emptyPanels[1][0].add(label) ;

        field = new JTextField();
        field.setColumns(20);
        emptyPanels[1][1].add(field);



        button1 = new JButton(new Open("choisir chemin du projet", this, ""));
        emptyPanels[2][1].add(button1) ;

        button = new JButton(new CreateProject("créer le projet", this));
        emptyPanels[3][1].add(button) ;

        return panel;
    }

    public void changePanel() {
        setContentPane(buildSecondPane());
        validate();
    }

    public JPanel buildSecondPane() {

        this.setSize(530,400);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        panel.setLayout(new GridLayout(3,1));
        panel.setBackground(Color.white);

        JPanel[][] emptyPanels = new JPanel[3][2];
        for (int m = 0; m < 3; m++) {
            for (int l = 0; l < 1; l++) {

                emptyPanels[m][l] = new JPanel();
                panel.add(emptyPanels[m][l]);
            }
        }

        button4 = new JButton(new Arborescence("Afficher l'arborescence", this));
        emptyPanels[0][0].add(button4);



        JButton button = new JButton(new MakeAuth("Créer un module d'authentification", this));
        emptyPanels[1][0].add(button);


        if (LaunchServer.launched == false) {
            server = new JButton(new LaunchServer("lancer le serveur", this));
        } else {

           server = new JButton(new StopServer("arreter le serveur", this));
        }
        emptyPanels[2][0].add(server);


        return panel;
    }

    public JPanel buildMainPane() {

        setSize(530,400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.setBackground(Color.white);
        JFrame frame = this;

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Projet");

        JMenuItem create = new JMenuItem(new OpenCreatePanel("Créer un projet", this));
        menu1.add(create);

        JMenuItem open = new JMenuItem(new Open("Ouvrir un projet", this, "old"));
        menu1.add(open);

        menuBar.add(menu1);

        JMenu menu2 = new JMenu("Routage");

        JMenuItem addRoute = new JMenuItem(new OpenAddRoutePanel("ajouter une route retournant un vue", this));
        menu2.add(addRoute);

        JMenuItem addRouteController = new JMenuItem(new OpenAddRouteControllerPanel("ajouter une route faisant appel a un controleur", this));
        menu2.add(addRouteController);

        JMenuItem displayRoute = new JMenuItem(new DisplayRoutes("afficher les routes", this));
        menu2.add(displayRoute);

        menuBar.add(menu2);

        JMenu menu3 = new JMenu("Migrations");

        JMenuItem addMigration = new JMenuItem(new OpenAddMigrationPanel("ajouter une migration", this));
        menu3.add(addMigration);

        JMenuItem makeMigration = new JMenuItem(new MakeMigration("effectuer les migrations",this)) ;
        menu3.add(makeMigration) ;

        JMenuItem  rollBack = new JMenuItem(new RollBack("effectuer un Roll Back",this)) ;
        menu3.add(rollBack) ;

        menuBar.add(menu3);

        JMenu menu4 = new JMenu("Ajouter");

        JMenuItem addController = new JMenuItem(new OpenAddPanel("ajouter un contrôleur", this,"ctrl"));
        menu4.add(addController);

        JMenuItem addModel = new JMenuItem(new OpenAddPanel("ajouter un modéle", this,"mdl"));
        menu4.add(addModel);

        JMenuItem addMiddleware = new JMenuItem(new OpenAddPanel("ajouter un middleware", this,"mdlw"));
        menu4.add(addMiddleware);

        menuBar.add(menu4);

        setJMenuBar(menuBar);


        JPanel[][] emptyPanels = new JPanel[3][1];
        for (int m = 0; m < 3; m++) {
            for (int l = 0; l < 1; l++) {

                emptyPanels[m][l] = new JPanel();
                panel.add(emptyPanels[m][l]);
            }
        }

        JLabel label = new JLabel("Bienvenue sur Larvel GUI") ;
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        emptyPanels[0][0].add(label) ;

        JLabel label1 = new JLabel("<html>Vous pouvez créér votre projet et utiliser Laravel GUI <br>" +
                " pour vous aider à developper le meilleur site web</html>") ;
        emptyPanels[1][0].add(label1);
        JButton cr = new JButton(new OpenCreatePanel("Créer un projet", this)) ;
        emptyPanels[1][0].add(cr) ;

        JLabel label2 = new JLabel("<html>Ou bien vous pouvez travailler sur un projet larval existant <br>"
                +" pour y ajouter de nouvelles fonctionnalités<html>");
        emptyPanels[2][0].add(label2);
        JButton op = new JButton(new Open("Ouvrir un projet", this, "old")) ;
        emptyPanels[2][0].add(op) ;




        return panel;
    }

    public JPanel buildAddRoutePanel() {

        this.setSize(530, 400);
        AddRoutePanel panel = new AddRoutePanel(this);
        return panel;


    }

    public JPanel buildAddMigrationPanel() {

        this.setSize(1000, 600);

        MigrationPanel panel = new MigrationPanel(this) ;

        return panel ;

    }

    public JPanel buildAddRouteControllerPanel() {


        this.setSize(530, 400);
        RouteControllerPanel panel = new RouteControllerPanel(this);
        return panel;
    }

    public JPanel buildAddControllerPanel() {
        this.setSize(530, 400);
        AddControllerPanel panel = new AddControllerPanel(this);
        return panel;
    }

    public JPanel buildAddModelPanel() {
        this.setSize(530, 400);
        AddModelPanel panel = new AddModelPanel(this);
        return panel;
    }

    public JPanel buildAddMiddlewarePanel() {
        this.setSize(530, 400);
        AddMiddlewarePanel panel = new AddMiddlewarePanel(this);
        return panel;

    }

    public JTextField getRoute() {
        return route;
    }

    public JTextField getView() {
        return view;
    }

    public JTextField getField() {
        return field;
    }

    public JLabel getVueName() {
        return vueName;
    }

    public JComboBox getMethodList() {
        return methodList;
    }



}
