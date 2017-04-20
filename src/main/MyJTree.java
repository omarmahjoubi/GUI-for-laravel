package main;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Moslah_Hamza on 10/04/2017.
 */
class MyJTree extends JTree implements ActionListener {
    JPopupMenu popup = new JPopupMenu();
    JMenuItem mi = new JMenuItem("Ajouter");
    TreeModel treeModel;
    JFrame frame;

    MyJTree(TreeModel dmtn, JFrame frame) {
        super(dmtn);
        mi.addActionListener(this);
        this.frame = frame;
        mi.setActionCommand("insert");
        popup.add(mi);
        mi = new JMenuItem("Supprimer");
        mi.addActionListener(this);
        mi.setActionCommand("remove");
        popup.add(mi);
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show((JComponent) e.getSource(), e.getX(), e.getY());
                }
            }
        });

        treeModel = dmtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // DefaultMutableTreeNode dmtn, node;

        TreePath path = this.getSelectionPath();
        FileTreeModel.MyFile file1 = (FileTreeModel.MyFile) path.getLastPathComponent();
        File file = file1.getFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        // dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
        if (e.getActionCommand().equals("insert")) {
//            node = new DefaultMutableTreeNode("children");
//            dmtn.add(node);
//            ((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
            Process p = null;
            String filename = JOptionPane.showInputDialog(frame, "veuillez entrer le nom complet du fichier", "Ajout d'un fichier",
                    JOptionPane.PLAIN_MESSAGE);
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd " + file.getAbsolutePath() + " && NUL> " + filename);
            builder.redirectErrorStream(true);
            try {
                p = builder.start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getActionCommand().equals("remove")) {
            Process p = null;
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getParent());
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd " + file.getParent() + " && del " + file.getAbsolutePath());
            builder.redirectErrorStream(true);
            try {
                p = builder.start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
//            node = (DefaultMutableTreeNode) dmtn.getParent();
//            int nodeIndex = node.getIndex(dmtn);
//            dmtn.removeAllChildren();
//            node.remove(nodeIndex);
//            ((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
        }
    }
}


