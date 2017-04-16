package main;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Moslah_Hamza on 10/04/2017.
 */
class MyJTree extends JTree implements ActionListener {
    JPopupMenu popup = new JPopupMenu();
    JMenuItem mi = new JMenuItem("Ajouter");

    MyJTree(TreeModel dmtn) {
        super(dmtn);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode dmtn, node;

        TreePath path = this.getSelectionPath();
        dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
        if (e.getActionCommand().equals("insert")) {
            node = new DefaultMutableTreeNode("children");
            dmtn.add(node);
            ((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
        }
        if (e.getActionCommand().equals("remove")) {
            node = (DefaultMutableTreeNode) dmtn.getParent();
            int nodeIndex = node.getIndex(dmtn);
            dmtn.removeAllChildren();
            node.remove(nodeIndex);
            ((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
        }
    }
}


