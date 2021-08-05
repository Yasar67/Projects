/*
 *	===============================================================================
 *	NestedShape.java : A shape that is a Rectangle with one or more shapes inside it.
 *  YOUR UPI: syas141
 * 	Name: Samin Yasar
 *  Date:08/06/12
 *	=============================================================================== */

import java.awt.*;
import java.util.ArrayList;

public class NestedShape extends RectangleShape {
    private ArrayList<Shape> nestedShapes = new ArrayList<Shape>();
    private static ShapeType nextShapeType=ShapeType.NESTED;
    public NestedShape(){
        super();
    }
    public NestedShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, PathType pt){
        super(x ,y ,w, h ,mw ,mh, bc, fc, pt);
        createAddInnerShape(nextShapeType = nextShapeType.next());
    }
    public NestedShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, PathType pt, String text){
        super(x ,y ,w, h ,mw ,mh, bc, fc, pt, text);
        createAddInnerShape(nextShapeType = nextShapeType.next());
    }
    public NestedShape(ArrayList<Shape> shapes, Color fc, Color bc){
        super(0,0,DEFAULT_MARGIN_WIDTH,DEFAULT_MARGIN_HEIGHT,DEFAULT_MARGIN_WIDTH,DEFAULT_MARGIN_HEIGHT,bc,fc,PathType.BOUNCE);
         nestedShapes = new ArrayList<Shape>(shapes);
        for (Shape shape : nestedShapes) {
            shape.setParent(this);
        }
    }
    public void createAddInnerShape(ShapeType st){
        switch (st) {
            case RECTANGLE : {
                nestedShapes.add(new RectangleShape(0, 0, this.width/2, this.height/2, this.width, this.height, this.borderColor, this.fillColor, PathType.BOUNCE, this.text));
                break;
            }
            case XRECTANGLE : {
                nestedShapes.add(new XRectangleShape(0, 0, this.width/2, this.height/2, this.width, this.height, this.borderColor, this.fillColor, PathType.BOUNCE, this.text));
                break;
            }
            case OVAL : {
                nestedShapes.add(new OvalShape(0, 0, this.width/2, this.height/2, this.width, this.height, this.borderColor, this.fillColor, PathType.BOUNCE, this.text));
                break;
            }
            case SQUARE : {
                nestedShapes.add(new SquareShape(0, 0, Math.min(this.width/2, this.height/2), this.width, this.height, this.borderColor, this.fillColor, PathType.BOUNCE, this.text));
                break;
            }
            case NESTED: {
                nestedShapes.add(new NestedShape(0, 0, this.width/2, this.height/2, this.width, this.height, this.borderColor, this.fillColor, PathType.BOUNCE, this.text ));
            }
        }
        nestedShapes.get(nestedShapes.size()-1).setParent(this);
    }
    public Shape getShapeAt(int index){
        return nestedShapes.get(index);
    }
    public int getSize(){
        return nestedShapes.size();
    }
    public void draw(Painter painter){
        painter.setPaint(Color.black);
        painter.drawRect(this.x,this.y, this.width,this.height);
        painter.translate(this.x,this.y);
        for (Shape nestedShape : nestedShapes) {
            nestedShape.draw(painter);
        }
        painter.translate(-this.x,-this.y);

    }
    public void move(){
        super.move();
        for (Shape nestedShape : nestedShapes) {
            nestedShape.move();
        }
    }
    public void add(Shape s){
        nestedShapes.add(s);
        s.setParent(this);
    }
    public void remove(Shape s){
        nestedShapes.remove(s);
        s.setParent(null);
    }
    public int indexOf(Shape s){
                return nestedShapes.indexOf(s);
    }
    public Shape[] getChildren(){
        Shape[] list = new Shape[nestedShapes.size()];
        for (int i=0;i<nestedShapes.size();i++){
            list[i] = nestedShapes.get(i);
        }
        return list;
    }
}
