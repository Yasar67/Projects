/*
 *  ============================================================================================
 *  enum which defines the type of shapes in A1
 *  YOUR UPI: syas141
 * 	Name: Samin Yasar
 *  Date:08/06/12
 *  ============================================================================================
 */

import java.util.*;
public enum ShapeType { RECTANGLE, XRECTANGLE, SQUARE, OVAL, NESTED ;
	private static final Random rand = new Random();
	private static final int SIZE = values().length;
	public static final ShapeType getShapeType(int index) { return values()[index]; }
	public ShapeType next() {
		return values()[(ordinal() + 1) % values().length];
		}
	public static ShapeType getRandomShapeType()  {
	    return values()[rand.nextInt(SIZE)];
  }
}