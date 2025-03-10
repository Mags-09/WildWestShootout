package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import game.Point;
import java.util.*;
import javax.swing.*;

public class Playable extends Polygon implements KeyListener{

	private static final double GRID_SIZE = 20;
	private static final int MOVE_TIME = 200;
	
	public static int lives = 4;
	
	private Point[] shape;
	private double rateMov;
	private long lastMovTime;
	private ArrayList<Bullet> bullets;
	
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
	
    private Image playerSprite;
    
	public Playable(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
		this.shape = inShape;
		this.rateMov = GRID_SIZE;
		this.lastMovTime = System.currentTimeMillis();
		this.bullets = new ArrayList<Bullet>();
		playerSprite = new ImageIcon("src/images/piskel_bush.png").getImage();
	}
	
	public void movement() {
		long currentTime = System.currentTimeMillis();
		
		if(currentTime - lastMovTime >= MOVE_TIME) {
	        if (up == true) {
	            position.y = position.y - rateMov;
	        }
	        if (down == true) {
	            position.y = position.y + rateMov;
	        }
	        if (left == true) {
	            position.x = position.x - rateMov;
	        }
	        if (right == true) {
	            position.x = position.x + rateMov;
	        }
	        
	        lastMovTime = currentTime;
		}

        for (Bullet b : bullets) {
            b.movement();
        }
	}
	
    public void shoot() {
        Point[] bulletShape = new Point[] {new Point(0, 0), new Point(2, 0),
            new Point(2, 4), new Point(0, 4)};
//        Point bulletPosition = new Point(position.x, position.y);
//        double bulletSpeed = 5.0;
        Bullet bullet = new Bullet(bulletShape, 
        		new Point(position.x, position.y), rotation, GRID_SIZE);
        bullets.add(bullet);
    }
	
    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            shoot();
//        }
        switch (e.getKeyCode()) {
        	case KeyEvent.VK_W -> up = true;
        	case KeyEvent.VK_S -> down = true;
        	case KeyEvent.VK_A -> left = true;
        	case KeyEvent.VK_D -> right = true;
        	case KeyEvent.VK_UP -> rotation = 90;
        	case KeyEvent.VK_DOWN -> rotation = 270;
        	case KeyEvent.VK_LEFT -> rotation = 180;
        	case KeyEvent.VK_RIGHT -> rotation = 0;
        	case KeyEvent.VK_SPACE -> shoot();
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        	case KeyEvent.VK_W -> up = false;
        	case KeyEvent.VK_S -> down = false;
        	case KeyEvent.VK_A -> left = false;
        	case KeyEvent.VK_D -> right = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

	public void paint(Graphics brush) {
	    brush.setColor(Color.blue);
	    
        int[] xPoints = new int[shape.length];
        int[] yPoints = new int[shape.length];
        
        Point[] points = this.getPoints();
        
        for (int i = 0; i < shape.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        
        brush.fillPolygon(xPoints, yPoints, shape.length);
        brush.drawImage(playerSprite, (int) this.position.x, (int) this.position.y, null);
		
        for (Bullet bullet : bullets) {
            bullet.paint(brush);
        }
        
	}
}
