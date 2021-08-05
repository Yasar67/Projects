/*
 *	===============================================================================
 *	TableModelAdapter.java : The table model adapter for the program.
 *  YOUR UPI: syas141
 * 	Name: Samin Yasar
 *  Date:08/06/12
 *	=============================================================================== */

import javax.swing.table.AbstractTableModel;

public class TableModelAdapter extends AbstractTableModel {
    private Shape nestedShape;
    private static String[] columnNames = new String[]{"Type", "X-pos", "Y-pos", "Width", "Height"};
    public TableModelAdapter(NestedShape shape){
        nestedShape = shape;
    }
    public int getColumnCount(){
        return columnNames.length;
    }
    public int getRowCount(){
        return ((NestedShape)(nestedShape)).getSize();
    }
    public String getColumnName(int column){
        return columnNames[column];
    }
    public Object getValueAt(int rowIndex, int columnIndex){
        if (columnIndex==0){
            return ((NestedShape)(nestedShape)).getShapeAt(rowIndex).getClass().getName();
        }
        if (columnIndex==1){
            return ((NestedShape)(nestedShape)).getShapeAt(rowIndex).getX();
        }
        if (columnIndex==2){
            return ((NestedShape)(nestedShape)).getShapeAt(rowIndex).getY();
        }
        if (columnIndex==3){
            return ((NestedShape)(nestedShape)).getShapeAt(rowIndex).getWidth();
        }
        if (columnIndex==4){
            return ((NestedShape)(nestedShape)).getShapeAt(rowIndex).getHeight();
        }
        return null;
    }

    public void setNestedShape(Shape nestedShape) {
        this.nestedShape = nestedShape;
    }
}
