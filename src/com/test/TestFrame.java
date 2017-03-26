package com.test;

import javax.swing.SwingUtilities;



public class TestFrame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Frame fenetre = new Frame() ;
				fenetre.setVisible(true);				
			}			
		});
	

	}

}
