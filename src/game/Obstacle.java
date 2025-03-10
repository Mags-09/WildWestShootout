package game;

import java.awt.*;

public class Obstacle extends Polygon {
	
	Point[] shape;

	public Obstacle(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		this.shape = inShape;
	}
	

    public void paint(Graphics brush) {
    	
        int[] xPoints = new int[shape.length];
        int[] yPoints = new int[shape.length];
        
        Point[] points = this.getPoints();
        
        for (int i = 0; i < shape.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        
        brush.fillPolygon(xPoints, yPoints, shape.length);
    }
    
}