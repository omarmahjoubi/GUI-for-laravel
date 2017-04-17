package main;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
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
    TreeModel treeModel;
    JFrame frame;
    JMenuItem mi = new JMenuItem("Ajouter");

    MyJTree(TreeModel dmtn, JFrame frame) {
        super(dmtn);
        this.frame = frame;
        mi.addActionListener(this);
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
        //DefaultMutableTreeNode dmtn, node;
        TreePath path = this.getSelectionPath();
        File file = (File)path.getLastPathComponent();
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        //dmtn = (DefaultMutableTreeNode) this.getModel();
        if (e.getActionCommand().equals("insert")) {
            Process p = null;
            String filename = JOptionPane.showInputDialog(frame, "veuillez entrer le nom complet du fichier", "Ajout d'un fichier", JOptionPane.PLAIN_MESSAGE);
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd "+file.getAbsolutePath() +" && NUL> "+ filename);
            builder.redirectErrorStream(true);
            try {
                p = builder.start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //node = new DefaultMutableTreeNode(file.getAbsolutePath());
            //dmtn.add(node);
            //((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
        }
        if (e.getActionCommand().equals("remove")) {
            Process p = null;
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getParent());
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd "+file.getParent() +" && del "+ file.getAbsolutePath());
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


