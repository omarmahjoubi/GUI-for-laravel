package com.test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.test.guielement.DynamicModel;
import com.test.guielement.RoutesTable;

public class DisplayRoutes extends AbstractAction {

	private Frame frame;

	public DisplayRoutes(String text, Frame frame) {
		super(text);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {

		Process p;
		String res = "" ; 
		ArrayList<Route> routes = new ArrayList<Route>();
		if (Open.absolutePathProject != null) {
			String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject + " & php artisan route:list";
			try {
				p = Runtime.getRuntime().exec(command4);
				p.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = "";
				String line1 = null ;
				line = reader.readLine() ;
				line = reader.readLine() ;
				line = reader.readLine() ;

				
				while ((line = reader.readLine()) != null) {
				
				if (line.charAt(0) == '+') {
					break ;
				}
				
				String [] list = line.split("\\|") ;
				String method = list[2] + "," + list[3] ;
				String uri = list[4] ;
				String action = list[6] ;
				String middleware = list[7] ;
				
				res = res + "<html>uri => " + uri + " | method => " + method + " | action => " + action + " | middleware => " + middleware + "<br>" ;
				Route route = new Route(uri,method,action,middleware) ;
				routes.add(route) ;
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				res = res + "</html>" ;
				RoutesTable rt = new RoutesTable() ;
				DynamicModel dm = (DynamicModel) rt.getTableau().getModel() ;
				for (Route r : routes) {
					dm.addRoute(r);
				}
				rt.setVisible(true);
				
		} else {
			JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
		}
	}

}
