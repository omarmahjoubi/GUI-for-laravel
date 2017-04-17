package main;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Moslah_Hamza on 03/04/2017.
 */
public class Arborescence extends AbstractAction {
    private MainFrame frame;
    private JFrame treeFrame = new JFrame();
    private MyJTree tree ;
    private TreeModel model;
    private JScrollPane scrollPane;
    private String projectPath = "";

    public Arborescence(String s, MainFrame mainFrame) {
        super(s);
        this.frame = frame;
        this.model = new FileTreeModel(new File(Open.absolutePathProject));
        this.tree = new MyJTree ( model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        TreeModel model = new FileTreeModel(new File(Open.absolutePathProject));
//        JTree tree = new JTree(model);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getNewLeadSelectionPath();
                if (tp != null){
                    System.out.println("Selected:  "+tp.getLastPathComponent());
                    ProcessBuilder pb = new ProcessBuilder("Notepad.exe", ""+tp.getLastPathComponent());
                    try {
                        pb.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        this.scrollPane = new JScrollPane(tree);

        this.tree.setBounds(0, 0, this.scrollPane.getWidth(), this.scrollPane.getHeight());
        this.scrollPane.setBounds(460, 270, 240, 410);
        this.treeFrame.add(scrollPane);
        this.treeFrame.setTitle("Arborescence du projet");
        this.treeFrame.pack();
        this.treeFrame.setVisible(true);
        this.tree.setVisible(true);
        this.scrollPane.setVisible(true);
    }
}
