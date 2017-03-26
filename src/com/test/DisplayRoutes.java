package com.test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DisplayRoutes extends AbstractAction {

	private Frame frame;

	public DisplayRoutes(String text, Frame frame) {
		super(text);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {

		Process p;
		String res = null ; 
		if (Open.absolutePathProject != null) {
			String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject + " & php artisan route:list";
			try {
				p = Runtime.getRuntime().exec(command4);
				p.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					//System.out.println(line) ;
					res = res + line + "\n";
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				JDialog dialog = new JDialog() ;
				dialog.setTitle("routes");
				JPanel panel = new JPanel() ;
				panel.setLayout(new FlowLayout());
				JTextArea area = new JTextArea() ;
				area.setText(res);
				panel.add(area) ;				
				dialog.setContentPane(panel);
				dialog.pack(); 
				dialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
		}
	}

}
