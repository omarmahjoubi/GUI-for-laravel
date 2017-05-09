package main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by omar_ on 16/04/2017.
 */
public class OpenView extends AbstractAction {
    public static String viewName = "";
    private MainFrame frame ;
    private AddRoutePanel panel ;
    public OpenView(String text,AddRoutePanel panel ,MainFrame frame) {
        super(text) ;
        this.frame=frame ;
        this.panel = panel ;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (Open.absolutePathProject==null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        }
        else {
            fileChooser.setCurrentDirectory(new File(Open.absolutePathProject+"/resources/views"));
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    final String name = f.getName();
                    return name.endsWith(".blade.php") ;
                }

                @Override
                public String getDescription() {
                    return "*.blade.php";
                }
            });
            fileChooser.setApproveButtonText("Selectionne le ficher");
            fileChooser.showOpenDialog(this.frame);
            if (fileChooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
                String pathToView = fileChooser.getSelectedFile().getAbsolutePath();

                pathToView = pathToView.replace("\\","/") ;

                String[] list = pathToView.split("views/") ;
                String[] list1 = list[1].split("/") ;
                viewName = "" ;
                String viewNameTodisplay = list1[list1.length-1] ;
                this.panel.getVueName().setText(viewNameTodisplay);

                for (String el : list1) {
                    viewName = viewName + el + '.' ;
                }
                viewName = viewName.substring(0,viewName.length()-11) ;


            }
        }


    }
}
