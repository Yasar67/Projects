/*
 *	===============================================================================
 *	TreeModelAdapter.java : The tree model adapter for the program.
 *  YOUR UPI: syas141
 * 	Name: Samin Yasar
 *  Date:08/06/12
 *	=============================================================================== */

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class TreeModelAdapter implements TreeModel {
    private NestedShape nestedShape;
    private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
    public TreeModelAdapter(NestedShape shape){
        nestedShape = shape;
    }
    public Object getRoot(){
        return nestedShape;
    }
    public boolean isLeaf(Object node){
        return !(node instanceof NestedShape);
    }
    public Object getChild(Object parent, int index){
        if (!(isLeaf(parent)) && ((NestedShape)(parent)).getSize() >= index && index>=0 ){
           return ((NestedShape)(parent)).getChildren()[index];
        }
        return null;
    }
    public int getChildCount(Object parent){
        if (!(isLeaf(parent))){
            return ((NestedShape)(parent)).getChildren().length;
        }
        return 0;
    }
    public int getIndexOfChild(Object parent, Object child){
        if (!(isLeaf(parent))){
           return ((NestedShape)parent).indexOf((Shape)child);
            }
        return -1;
        }
    public void addTreeModelListener(final TreeModelListener modelListener) {
        treeModelListeners.add(modelListener);
    }
    public void removeTreeModelListener(final TreeModelListener modelListener) {
        treeModelListeners.remove(modelListener);
    }
    public void fireTreeStructureChanged(final Object source, final Object[] path, final int[] childIndices, final Object[] children){
        for (TreeModelListener tml: treeModelListeners){
            tml.treeStructureChanged(new TreeModelEvent(source, path, childIndices, children));
        }
    }
    public void fireTreeNodesInserted(final Object source, final Object[] path, final int[] childIndices, final Object[] children){
        for (TreeModelListener tml: treeModelListeners){
            tml.treeNodesInserted(new TreeModelEvent(source, path, childIndices, children));
        }
    }
    public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices, Object[] children){
        for (TreeModelListener tml: treeModelListeners){
            tml.treeNodesRemoved(new TreeModelEvent(source, path, childIndices, children));
        }
    }
    public void addToRoot(Shape s){
        s.setParent(nestedShape);
        nestedShape.add(s);
        Object[] path = new Object[1];
        path[0] = nestedShape;
        int[] childIndices = new int[1];
        int size = nestedShape.getSize()-1;
        childIndices[0] = size;
        Object[] children = new Object[1];
        children[0] = s;
        fireTreeNodesInserted(this, path, childIndices, children);
    }
    public void valueForPathChanged(TreePath path, Object newValue) {}
    public void fireTreeNodesChanged(TreeModelEvent e) {}
    public Boolean addNode(TreePath selectedPath, ShapeType currentShapeType){
        Object node = selectedPath.getLastPathComponent();
        if (!(isLeaf(node))){
            int[] childIndices = new int[]{((NestedShape)node).getSize()};
            ((NestedShape)node).createAddInnerShape(currentShapeType);
            Shape child = ((NestedShape)node).getShapeAt(((NestedShape)node).getSize()-1);
            Object[] children = new Object[]{child};
            fireTreeNodesInserted(this,selectedPath.getPath(), childIndices, children);
            return true;
        }
        return false;
    }
    public Boolean removeNodeFromParent(TreePath selectedPath){
        Shape node = (Shape) selectedPath.getLastPathComponent();
        if (!(node.equals(nestedShape))){
            NestedShape parent = node.getParent();
            int i = parent.indexOf(node);
            parent.remove(parent.getChildren()[i]);
            fireTreeNodesRemoved(this,selectedPath.getParentPath().getPath(),new int[]{i}, new Object[]{node});
            return true;
        }
        return false;
    }
}
