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
    private JFrame treeFrame;
    private MyJTree tree ;
    private TreeModel model;
    private JScrollPane scrollPane;
    private String projectPath = "";

    public Arborescence(String s, MainFrame mainFrame) {
        super(s);
        this.frame = frame;
        projectPath = Open.absolutePathProject;
        System.out.println("tree project path: "+projectPath);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        TreeModel model = new FileTreeModel(new File(Open.absolutePathProject));
//        JTree tree = new JTree(model);
        if(Arborescence.this.treeFrame != null){
            Arborescence.this.treeFrame.setVisible(false);
            Arborescence.this.treeFrame.dispose();
        }
        File file = new File(projectPath);
        FileTreeModel.MyFile myFile = new FileTreeModel.MyFile(file);
        this.model = new FileTreeModel(myFile);
        this.treeFrame = new JFrame();

        if(Arborescence.this.treeFrame.isVisible()){
            System.out.println("it's visible");
        }
        this.tree = new MyJTree ( model, treeFrame);
        this.scrollPane = new JScrollPane(tree);
        System.out.println("tree project path: "+Open.absolutePathProject);
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
        this.scrollPane.setBounds(460, 270, 240, 410);
        this.treeFrame.setBounds(960, 170, this.scrollPane.getWidth(), this.scrollPane.getHeight());
        this.tree.setBounds(460, 270, this.scrollPane.getWidth(), this.scrollPane.getHeight());
        this.treeFrame.add(scrollPane);
        this.treeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.treeFrame.setTitle("Arborescence du projet");
        this.treeFrame.pack();
        this.treeFrame.setVisible(true);
        this.tree.setVisible(true);
        this.scrollPane.setVisible(true);
    }
}
