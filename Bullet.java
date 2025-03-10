package game;

import java.awt.*;
import javax.swing.*;

public class Bullet extends Polygon {
	
	private static final double GRID_SIZE = 20;
	
	private Point[] shape;
	private double rateMov;
	private Image bulletImage;

	public Bullet(Point[] inShape, Point inPosition, double inRotation, 
			double rateMov) {
		super(inShape, inPosition, inRotation);
		this.shape = inShape;
		this.rateMov = rateMov;
		bulletImage = new ImageIcon("src/images/piskel_bullet.png").getImage();
	}

    public void movement() {
        position.x = position.x + rateMov * Math.cos(Math.toRadians(rotation));
        position.y = position.y + rateMov * Math.sin(Math.toRadians(rotation));

        position.x = Math.round(position.x / GRID_SIZE) * GRID_SIZE;
        position.y = Math.round(position.y / GRID_SIZE) * GRID_SIZE;
        
        //move cross screen? i'm not sure if i want to implement this
//        if (position.x < 0) {
//        	position.x += 800;
//        }
//        if (position.x > 800) {
//        	position.x -= 800;
//        }
//        if (position.y < 0) {
//        	position.y += 600;
//        }
//        if (position.y > 600) {
//        	position.y -= 600;
//        }
    }

    public void paint(Graphics brush) {
    	brush.setColor(Color.BLACK);
    	
        int[] xPoints = new int[shape.length];
        int[] yPoints = new int[shape.length];
        
        Point[] points = this.getPoints();
        
        for (int i = 0; i < shape.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        
        brush.fillPolygon(xPoints, yPoints, shape.length);
        brush.drawImage(bulletImage, (int) this.position.x, (int) this.position.y, null);
    }
}