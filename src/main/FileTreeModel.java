package main;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Moslah_Hamza on 03/04/2017.
 */
public class FileTreeModel implements TreeModel {

    private final ArrayList<TreeModelListener> mListeners = new ArrayList<>();
    private MyFile root;

    public FileTreeModel(MyFile root) {
        this.root = root;
    }

    @Override
    public void addTreeModelListener(javax.swing.event.TreeModelListener l) {
        mListeners.add(l);
    }

    @Override
    public Object getChild(Object parent, int index) {
        MyFile f = (MyFile) parent;
        return f.listFiles()[index];
    }

    @Override
    public int getChildCount(Object parent) {
        MyFile f = (MyFile) parent;
        if (!f.isDirectory()) {
            return 0;
        } else {
            return f.listFiles().length;
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        MyFile par = (MyFile) parent;
        MyFile ch = (MyFile) child;
        final MyFile[] files = par.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i] == ch) return i;
        }
        return -1;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public boolean isLeaf(Object node) {
        MyFile f = (MyFile) node;
        return !f.isDirectory();
    }

    @Override
    public void removeTreeModelListener(javax.swing.event.TreeModelListener l) {
        mListeners.remove(l);
    }

    @Override
    public void valueForPathChanged(javax.swing.tree.TreePath path, Object newValue) {
        final MyFile oldTmp = (MyFile) path.getLastPathComponent();
        final File oldFile = oldTmp.getFile();
        final String newName = (String) newValue;
        final File newFile = new File(oldFile.getParentFile(), newName);
        oldFile.renameTo(newFile);
        System.out.println("Renamed '" + oldFile + "' to '" + newFile + "'.");
        reload();
    }

    public void reload() {
        // Need to duplicate the code because the root can formally be
        // no an instance of the TreeNode.
        final int n = getChildCount(getRoot());
        final int[] childIdx = new int[n];
        final Object[] children = new Object[n];

        for (int i = 0; i < n; i++) {
            childIdx[i] = i;
            children[i] = getChild(getRoot(), i);
        }

        fireTreeStructureChanged(this, new Object[]{getRoot()}, childIdx, children);
    }

    protected void fireTreeStructureChanged(final Object source, final Object[] path, final int[] childIndices, final Object[] children) {
        final TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (final TreeModelListener l : mListeners) {
            l.treeStructureChanged(event);
        }
    }

    static class MyFile {
        private final File mFile;

        public MyFile(final File pFile) {
            mFile = pFile;
        }

        public boolean isDirectory() {
            return mFile.isDirectory();
        }

        public MyFile[] listFiles() {
            final File[] files = mFile.listFiles();
            if (files == null) return null;
            if (files.length < 1) return new MyFile[0];

            final MyFile[] ret = new MyFile[files.length];
            for (int i = 0; i < ret.length; i++) {
                final File f = files[i];
                ret[i] = new MyFile(f);
            }
            return ret;
        }

        public File getFile() {
            return mFile;
        }

        @Override
        public String toString() {
            return mFile.getName();
        }
    }
}
