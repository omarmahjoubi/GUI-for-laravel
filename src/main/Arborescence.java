package main;

import main.actions.Open;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

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
        File file = new File(Open.absolutePathProject);
        FileTreeModel.MyFile myFile = new FileTreeModel.MyFile(file);
        this.model = new FileTreeModel(myFile);
        this.tree = new MyJTree ( model, treeFrame);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        TreeModel model = new FileTreeModel(new File(Open.absolutePathProject));
//        JTree tree = new JTree(model);
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                                TreePath tp = tree.getSelectionPath();
                               if(e.getClickCount() == 2) {
                                   FileTreeModel.MyFile file1 = (FileTreeModel.MyFile) tp.getLastPathComponent();
                                   File file = file1.getFile() ;
                    System.out.println("Selected:  "+file.getAbsolutePath());
                    ProcessBuilder pb = new ProcessBuilder("Notepad++.exe", file.getAbsolutePath());
                    try {
                        pb.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        tree.addMouseListener(ml);

        this.scrollPane = new JScrollPane(tree);

        this.tree.setBounds(0, 0, this.scrollPane.getWidth(), this.scrollPane.getHeight());
        this.scrollPane.setBounds(460, 270, 240, 410);
        this.treeFrame.add(scrollPane);
        this.treeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.treeFrame.setTitle("Arborescence du projet");
        this.treeFrame.pack();
        this.treeFrame.setVisible(true);
        this.tree.setVisible(true);
        this.scrollPane.setVisible(true);
    }
}
