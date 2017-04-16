package main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by omar_ on 16/04/2017.
 */
public class CreateMigration extends AbstractAction {

    private MigrationPanel panel;
    private MainFrame frame;

    public CreateMigration(String text, MigrationPanel panel, MainFrame frame) {
        super(text);
        this.panel = panel;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        String MigrationName = this.panel.getMigrationName().getText().toString();
        String name1 = this.panel.getName1().getText().toString();
        String name2 = this.panel.getName2().getText().toString();
        String name3 = this.panel.getName3().getText().toString();
        String name4 = this.panel.getName4().getText().toString();
        String name5 = this.panel.getName5().getText().toString();

        if (MigrationName.equals("")) {
            JOptionPane.showMessageDialog(this.frame, "veuillez spécifier le nom de la migration");
        } else if ((name1.equals("")) && (name2.equals("")) && (name3.equals("")) && (name4.equals("")) && (name5.equals(""))) {
            JOptionPane.showMessageDialog(this.frame, "veuillez spécifier au moins un champ de nom de colonne");
        } else {
            Process p;
            if (Open.absolutePathProject != null) {
                String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject + " & php artisan make:migration " + MigrationName;
                try {
                    p = Runtime.getRuntime().exec(command4);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";
                    line = reader.readLine();
                    if (!(line.contains("Created"))) {
                        JOptionPane.showMessageDialog(frame, "une erreur est survenu lors de la création de la migration");
                    }else {
                        String[] list = line.split("39m");
                        String migrationFile = list[1] + ".php";
                        migrationFile = migrationFile.substring(1,migrationFile.length()) ;
                        FileWriter fw = new FileWriter(Open.absolutePathProject + "/database/migrations", true);
                        Writer writer = new BufferedWriter(fw);
                        writer.write("test") ;
                        writer.close();

                    }
                } catch(Exception e1){
                        e1.printStackTrace();
                    }

                } else{
                    JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
                }
            }
        }
    }
