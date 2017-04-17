package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by omar_ on 16/04/2017.
 */
public class MigrationPanel extends JPanel {

    private JTextField migrationName ;
    private JTextField tableName ;

    private JTextField name1 ;



    private JTextField name2 ;
    private JTextField name3 ;
    private JTextField name4 ;
    private JTextField name5 ;

    private JComboBox type1 ;
    private JComboBox type2 ;
    private JComboBox type3 ;
    private JComboBox type4 ;
    private JComboBox type5 ;

    private JComboBox index1 ;
    private JComboBox index2 ;
    private JComboBox index3 ;
    private JComboBox index4 ;
    private JComboBox index5 ;

    private MainFrame frame ;

    public MigrationPanel(MainFrame frame) {

        super() ;

        this.frame=frame ;

        setLayout(new GridLayout(10, 6));
        setBackground(Color.white);


        // des panels vides pour representer les celluse du grid layout
        JPanel[][] emptyPanels = new JPanel[10][3];
        for (int m = 0; m < 10; m++) {
            for (int l = 0; l < 3; l++) {

                emptyPanels[m][l] = new JPanel();
                add(emptyPanels[m][l]);
            }
        }
        JLabel label = new JLabel("nom de la migration") ;
        emptyPanels[0][0].add(label) ;
        migrationName = new JTextField() ;
        migrationName.setColumns(15);
        emptyPanels[0][1].add(migrationName) ;

        JButton confirm = new JButton (new CreateMigration("Créer la migration",this,this.frame)) ;
        emptyPanels[0][2].add(confirm);

        JLabel label1 = new JLabel("Nom de la table") ;
        emptyPanels[1][0].add(label1);

        tableName = new JTextField() ;
        tableName.setColumns(15);
        emptyPanels[1][1].add(tableName) ;




        JLabel name = new JLabel("nom");
        emptyPanels[2][0].add(name);

        JLabel type = new JLabel("type");
        emptyPanels[2][1].add(type);


        JLabel index = new JLabel("index");
        emptyPanels[2][2].add(index);

        String [] indexes = { "--","primary","unique"} ;
        String [] types = { "integer","char","string","date"} ;


        name1 = new JTextField();
        name1.setColumns(15);
        emptyPanels[3][0].add(name1);


        type1 = new JComboBox(types) ;
        type1.setSelectedItem(0);
        emptyPanels[3][1].add(type1);


        index1 = new JComboBox(indexes) ;
        index1.setSelectedItem(0);
        emptyPanels[3][2].add(index1);

        name2 = new JTextField();
        name2.setColumns(15);
        emptyPanels[4][0].add(name2);


        type2 = new JComboBox(types) ;
        type2.setSelectedItem(0);
        emptyPanels[4][1].add(type2);


        index2 = new JComboBox(indexes) ;
        index2.setSelectedItem(0);
        emptyPanels[4][2].add(index2);

        name3 = new JTextField();
        name3.setColumns(15);
        emptyPanels[5][0].add(name3);


        type3 = new JComboBox(types) ;
        type3.setSelectedItem(0);
        emptyPanels[5][1].add(type3);


        index3 = new JComboBox(indexes) ;
        index3.setSelectedItem(0);
        emptyPanels[5][2].add(index3);

        name4 = new JTextField();
        name4.setColumns(15);
        emptyPanels[6][0].add(name4);


        type4 = new JComboBox(types) ;
        type4.setSelectedItem(0);
        emptyPanels[6][1].add(type4);


        index4 = new JComboBox(indexes) ;
        index4.setSelectedItem(0);
        emptyPanels[6][2].add(index4);

        name5 = new JTextField();
        name5.setColumns(15);
        emptyPanels[7][0].add(name5);


        type5 = new JComboBox(types) ;
        type5.setSelectedItem(0);
        emptyPanels[7][1].add(type5);


        index5 = new JComboBox(indexes) ;
        index5.setSelectedItem(0);
        emptyPanels[7][2].add(index5);


    }

    public JTextField getName1() {
        return name1;
    }

    public JTextField getName2() {
        return name2;
    }

    public JTextField getName3() {
        return name3;
    }

    public JTextField getName4() {
        return name4;
    }

    public JTextField getName5() {
        return name5;
    }

    public JComboBox getType1() {
        return type1;
    }

    public JComboBox getType2() {
        return type2;
    }

    public JComboBox getType3() {
        return type3;
    }

    public JComboBox getType4() {
        return type4;
    }

    public JComboBox getType5() {
        return type5;
    }

    public JComboBox getIndex1() {
        return index1;
    }

    public JComboBox getIndex2() {
        return index2;
    }

    public JComboBox getIndex3() {
        return index3;
    }

    public JComboBox getIndex4() {
        return index4;
    }

    public JComboBox getIndex5() {
        return index5;
    }

    public JTextField getMigrationName() {
        return migrationName;
    }

    public JTextField getTableName() {
        return tableName;
    }
}


