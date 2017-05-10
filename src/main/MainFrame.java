package main;
/**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import main.actions.*;
import main.panels.*;

import java.awt.*;

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

        button1 = new JButton(new Open("Spécifier le chemin de votre projet", this, ""));
        panel.add(button1);

        return panel;
    }



    public void changePanel() {
        setContentPane(buildSecondPane());
        validate();
    }

    public JPanel buildSecondPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);


        button4 = new JButton(new Arborescence("Afficher l'arborescence", this));
        panel.add(button4);

        JButton button = new JButton(new MakeAuth("Créer un module d'authentification",this)) ;
        panel.add(button) ;

        JButton button1 = new JButton(new LaunchServer("lancer le serveur",this)) ;
        panel.add(button1);

        return panel;
    }

    public JPanel buildMainPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
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


        button2 = new JButton(new Guide("Guide", this));
        panel.add(button2);

        return panel;
    }

    public JPanel buildAddRoutePanel() {
        this.setSize(500,400);
        AddRoutePanel panel = new AddRoutePanel(this) ;
        return panel ;

    }

    public JPanel buildAddMigrationPanel() {

        this.setSize(1000, 600);

        MigrationPanel panel = new MigrationPanel(this) ;

        return panel ;

    }

    public JPanel buildAddRouteControllerPanel() {

        this.setSize(500,400);
        RouteControllerPanel panel = new RouteControllerPanel(this) ;
        return panel ;
    }

    public JPanel buildAddControllerPanel() {
        this.setSize(600,300);
        AddControllerPanel panel = new AddControllerPanel(this) ;
        return panel ;
    }

    public JPanel buildAddModelPanel() {
        this.setSize(600,300);
        AddModelPanel panel = new AddModelPanel(this) ;
        return panel ;
    }

    public JPanel buildAddMiddlewarePanel() {
        this.setSize(600,300);
        AddMiddlewarePanel panel = new AddMiddlewarePanel(this) ;
        return panel ;
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
