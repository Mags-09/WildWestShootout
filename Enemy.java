package game;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Enemy extends Polygon {

	//need health bar stat/aspect based on number of collisions with a bullet
		//instantiate class object within enemy constructor
	//need inner class based on randomBehavior of enemy

	private static final double GRID_SIZE = 20;
	private static final int MOVE_TIME = 400;
	
//	private double direction;
	private double rateMov;
	private long lastMovTime = System.currentTimeMillis();
	private Point[] shape;
	private ArrayList<Bullet> bullets;
	private Random rand;
	public boolean alive;
	
	private Image enemySprite;
	
	public Enemy(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
//		this.direction = 0;
		this.rateMov = GRID_SIZE;
		this.lastMovTime = System.currentTimeMillis();
		this.shape = inShape;
		this.bullets = new ArrayList<Bullet>();
		this.rand = new Random();
		enemySprite = new ImageIcon("src/images/piskel_orc.png").getImage();
	}
	
	public void movement() {
		long currentTime = System.currentTimeMillis();
		
		if(currentTime - lastMovTime >= MOVE_TIME) {
	//        if (Math.random() < 0.01) {
	//            rotation += (Math.random() - 0.5) * 20;
	//        }
	        if (rand.nextDouble() < 0.01) {
	            rotation = rand.nextInt(360);
	        }
	        
//	        position.x += rateMov * Math.cos(Math.toRadians(rotation));
//	        position.y += rateMov * Math.sin(Math.toRadians(rotation));
	
	        position.x = Math.round(position.x / GRID_SIZE) * GRID_SIZE;
	        position.y = Math.round(position.y / GRID_SIZE) * GRID_SIZE;
	        
	        lastMovTime = currentTime;
		}
        
        for(Bullet b: bullets) {
        	b.movement();
        }
	}
	
	
	public class RandomRotation {
        private Random r;

        private RandomRotation() {
            this.r = new Random();
        }
        
        public void updateBehavior() {
            if (r.nextDouble() < 0.01) {
//                rotation = r.nextInt(360); this made the direction entirely random
            	rotation = r.nextInt(4) * 90;
            }
        }
	}
	
	  public boolean collides(Polygon other) {
	      for (Point p : this.getPoints()) {
	          if (other.contains(p)) {
	              return true;
	          }
	      }

	      for (Point p : other.getPoints()) {
	          if (this.contains(p)) {
	              return true;
	          }
	      }

	      return false;
	  }
	
	public void paint(Graphics brush) {
	    brush.setColor(Color.red);
	    
        Point[] points = this.getPoints();
        
        int[] xPoints = new int[shape.length];
        int[] yPoints = new int[shape.length];
        
        for (int i = 0; i < shape.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        
        brush.fillPolygon(xPoints, yPoints, shape.length);
        brush.drawImage(enemySprite, (int) this.position.x, (int) this.position.y, null);
        
        for (Bullet b : bullets) {
            b.paint(brush);
        }
	}
}
