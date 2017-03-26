package com.test.guielement;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RoutesTable extends JFrame {
	    private JTable tableau ;
		public RoutesTable() {
	        super();
	 
	        setTitle("Routes");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        tableau = new JTable(new DynamicModel());
	 
	        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	 
	        pack();
	        
	        
	    }
		public JTable getTableau() {
			return tableau;
		}
	 
	    
	}


