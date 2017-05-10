package main.actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;


/**
 * Created by omar_ on 27/04/2017.
 */
public class MakeAuth extends AbstractAction {

    private MainFrame frame;

    public MakeAuth(String text, MainFrame frame) {
        super(text);
        this.frame = frame;
    }


    public static boolean verify() throws IOException //retourne true si make:auth n'est pas exécuté
    {
        boolean auth = true;
        BufferedReader br = new BufferedReader(new FileReader(Open.absolutePathProject + "/routes/web.php"));
        String line;
        while (((line = br.readLine()) != null) && (line.indexOf("Route::group(['middleware' => 'auth'], function (){") == -1)&&(line.indexOf("Auth::routes();")==-1)) {
        }
        if (line != null)
            auth = false;
        return auth;
    }

    public static void addMiddleware() throws IOException // ajouter la ligne du middleware d'authentification dans le fichier routes
    {

        BufferedWriter bw = new BufferedWriter(new FileWriter(Open.absolutePathProject + "/routes/web.php", true));
        bw.write("Route::group(['middleware' => 'auth'], function (){\n});");
        bw.newLine();
        bw.flush();

    }

    public void actionPerformed(ActionEvent e) {
        boolean auth = false;
        if (Open.absolutePathProject == null) {
            JOptionPane.showMessageDialog(frame, "vous n'avez pas encore ouvert de projet laravel");
        } else {
            String command4 = "cmd.exe /c " + " cd " + Open.absolutePathProject + " & php artisan make:auth";
            try {
                auth = verify();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (auth == true) {
                try {
                    Process p;
                    p = Runtime.getRuntime().exec(command4);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";

                    line = reader.readLine() ;

                    if (line.contains("successfully")) {
                        addMiddleware();
                        JOptionPane.showMessageDialog(frame, "le module d'authentification a été crée avec succés");
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "une erreur s'est produite durant la création du module d'autentification","ERROR",JOptionPane.ERROR_MESSAGE);
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "vous avez déja un module d'authentification pour ce projet","WARNING",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
