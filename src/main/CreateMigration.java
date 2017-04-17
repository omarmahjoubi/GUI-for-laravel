package main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        String migrationName = this.panel.getMigrationName().getText().toString();
        String tableName = this.panel.getTableName().getText().toString();
        String name1 = this.panel.getName1().getText().toString();
        String name2 = this.panel.getName2().getText().toString();
        String name3 = this.panel.getName3().getText().toString();
        String name4 = this.panel.getName4().getText().toString();
        String name5 = this.panel.getName5().getText().toString();

        if (migrationName.equals("")) {
            JOptionPane.showMessageDialog(this.frame, "veuillez spécifier le nom de la migration");
        } else if (tableName.equals("")) {
            JOptionPane.showMessageDialog(this.frame, "veuillez spécifier le nom de la table");
        } else if ((name1.equals("")) && (name2.equals("")) && (name3.equals("")) && (name4.equals("")) && (name5.equals(""))) {
            JOptionPane.showMessageDialog(this.frame, "veuillez spécifier au moins un champ de nom de colonne");
        } else {
            Process p;
            if (Open.absolutePathProject != null) {
                String command4 = "cmd.exe /c " + "cd " + Open.absolutePathProject + " & php artisan make:migration " + migrationName;
                try {
                    p = Runtime.getRuntime().exec(command4);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";
                    line = reader.readLine();
                    if (!(line.contains("Created"))) {
                        JOptionPane.showMessageDialog(frame, "une erreur est survenu lors de la création de la migration");
                    } else {
                        String[] list = line.split("39m");
                        String migrationFile = list[1] + ".php";
                        migrationFile = migrationFile.substring(1, migrationFile.length());

                        List<String> fileLines = new ArrayList<String>();
                        Scanner scanner = null;
                        try {
                            scanner = new Scanner(new File(Open.absolutePathProject + "/database/migrations/" + migrationFile));
                            while (scanner.hasNextLine()) {
                                String line1 = scanner.nextLine();
                                fileLines.add(line1);

                                if ((line1.contains("up()"))&&(scanner.nextLine().contains("{"))) {
                                    fileLines.add("    {") ;
                                    fileLines.add("        Schema::create('" + tableName + "', function (Blueprint $table) { });");
                                }

                                if ((line1.contains("down()"))&&(scanner.nextLine().contains("{"))) {
                                    fileLines.add("    {") ;
                                    fileLines.add("        Schema::drop('" + tableName + "');");
                                }
                            }

                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        } finally {
                            if (scanner != null) {
                                scanner.close();
                            }
                        }

                        PrintWriter pw = null;
                        try {
                            pw = new PrintWriter(new File(Open.absolutePathProject + "/database/migrations/" + migrationFile));
                            for (String line2 : fileLines) {
                                pw.println(line2);
                            }
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        } finally {
                            if (pw != null) {
                                pw.close();
                            }

                        }
                        JOptionPane.showMessageDialog(frame, "le fichier de migration a été crée avec succés");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
            }
        }
    }
}
