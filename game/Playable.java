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
		playerSprite = new ImageIcon("src/WildWesterner-1.png (1)-1.png.png").getImage();
	}
	
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
	
	@Override
    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				up = true;
				break;
			case KeyEvent.VK_S:
				down = true;
				break;
			case KeyEvent.VK_A:
				left = true;
				break;
			case KeyEvent.VK_D:
				right = true;
				break;
			case KeyEvent.VK_UP:
				rotation = 90;
				break;
			case KeyEvent.VK_DOWN:
				rotation = 270;
				break;
			case KeyEvent.VK_LEFT:
				rotation = 180;
				break;
			case KeyEvent.VK_RIGHT:
				rotation = 0;
				break;
			case KeyEvent.VK_SPACE:
				shoot();
				break;
		}
    }

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				up = false;
				break;
			case KeyEvent.VK_S:
				down = false;
				break;
			case KeyEvent.VK_A:
				left = false;
				break;
			case KeyEvent.VK_D:
				right = false;
				break;
		}
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		// You can leave this empty if you don't need it
	}

}
