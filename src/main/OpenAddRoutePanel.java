package main;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class OpenAddRoutePanel extends AbstractAction {
	
	private MainFrame frame ;
	
	public OpenAddRoutePanel(String text,MainFrame frame) {
		super(text);
		this.frame=frame ;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.frame.setContentPane(this.frame.buildAddRoutePanel());
        this.frame.validate();  
        
	}

}
