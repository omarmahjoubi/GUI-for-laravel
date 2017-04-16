package main.guielement;

/**
 * Created by Moslah_Hamza on 27/03/2017.
 */

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RoutesTable extends JFrame {
    private JTable tableau;

    public RoutesTable() {
        super();
        setTitle("Routes");
        tableau = new JTable(new DynamicModel());
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        pack();
    }

    public JTable getTableau() {
        return tableau;
    }
}

