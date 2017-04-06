package main;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by Moslah_Hamza on 03/04/2017.
 */
public class Arborescence extends AbstractAction {
    private MainFrame frame;
    private JFrame treeFrame = new JFrame();
    private String projectPath = "";

    public Arborescence(String s, MainFrame mainFrame) {
        super(s);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeModel model = new FileTreeModel(new File(Open.absolutePathProject));
        JTree tree = new JTree(model);
        this.treeFrame.add(tree);
        this.treeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.treeFrame.setTitle("Arborescence du projet");
        this.treeFrame.pack();
        this.treeFrame.setVisible(true);
    }
}
