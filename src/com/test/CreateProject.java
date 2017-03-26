package com.test;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class CreateProject extends AbstractAction {
	
	private Frame frame ;
	
	
	public CreateProject(String text,Frame frame) {
		super(text) ;
		this.frame = frame ;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Process p ;
		try {
			
			System.out.println("CD=" + System.getProperty("user.dir"));
			System.setProperty("user.dir", "E:\\Google Drive");
			System.out.println("CD=" + System.getProperty("user.dir"));
			
			String command4 = "cmd.exe /c "  + "cd E:\\Google Drive\\Etudes\\GL4\\PPP\\sites & cd & " + "laravel new " + this.frame.getField().getText() ;
			p = Runtime.getRuntime().exec(command4);
			p.waitFor() ;
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
			while ((line = reader.readLine())!= null) {
				System.out.println(line + "\n");
			}
			JOptionPane.showMessageDialog(frame, "le projet " + this.frame.getField().getText() + "été crée avec succés");
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	

}
