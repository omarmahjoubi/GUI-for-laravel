package com.test;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class Frame extends JFrame {
	
	private JTextField field ;
	private JButton button ;
	private JButton button1 ;
	private JButton button2 ;
	
	public Frame() {
		super() ;
		build() ;
	}
	
	public void build() {
		setTitle("test") ;
		setSize(400,400) ;
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(buildContentPane());
		
	}
	
	public JPanel buildContentPane() {
		JPanel panel = new JPanel() ;
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
		
		JLabel label = new JLabel("Nom du projet")  ;
		panel.add(label) ;
		
		field = new JTextField() ;
		field.setColumns(10);
		panel.add(field);
		
		button = new JButton(new CreateProject("créer le projet",this)) ;
		panel.add(button);
		
		button1 = new JButton(new Open("ouvrir" , this)) ;
		panel.add(button1) ;
		
		button2 = new JButton(new DisplayRoutes("afficher les routes" , this)) ;
		panel.add(button2) ;
		
		return panel ;
	}

	public JTextField getField() {
		return field;
	}

}
