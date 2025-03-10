package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

class WildWestShootout extends Game {
	static int counter = 0;
	private static final double GRID_SIZE = 20;
	private static final int GAME_SIZE = 600;
	
	Background background;
	Obstacle[] obstacles;
	Point[] playerShape = {new Point(5, 0), new Point(10, 5), new Point(7, 5),
            new Point(7, 10), new Point(10, 10), new Point(10, 15),
            new Point(0, 15), new Point(0, 10), new Point(3, 10),
            new Point(3, 5), new Point(0, 5)};
	Playable player;
	private ArrayList<Enemy> bandits;
	
	private Timer timer;
    

  public WildWestShootout() {
    super("Wild West Shootout!", 800, 638);
    this.setFocusable(true);
	this.requestFocus();
	
	background = new Background();
    obstacles = new Obstacle[] {
        new Obstacle(new Point[] { new Point(0, 0), new Point(50, 0), 
        		new Point(50, 50), new Point(0, 50) }, new Point(200, 200), 0),
        new Obstacle(new Point[] { new Point(0, 0), new Point(50, 0), 
        		new Point(50, 50), new Point(0, 50) }, new Point(400, 400), 0)};
    
    bandits = new ArrayList<Enemy>();  
    
    timer = new Timer(2000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            bandits.add(createBandit());
        }
    });
    timer.start();
    
    playerShape = scale(playerShape, 1.0);
	player = new Playable(playerShape, new Point(GAME_SIZE/2,GAME_SIZE/2), 0);
	
    this.addKeyListener(player);
  }
  
  public Enemy createBandit() {
    Point[] banditShape = {
//        new Point(2,1), new Point(2,0), new Point(0,0), //left leg
////        new Point(0,1),
//        new Point(0,0.5), new Point(-1,0.5), new Point(-1,1.5), new Point(-0.5,2),
//        new Point(0,2), //left arm
////        new Point(4.5,1),
//        new Point(0,4.5), new Point(0,5.5), new Point(1.5,5.5), new Point(3,5.5),
//        new Point(4,5), new Point(4.5,4.5), //head
//        new Point(4.5,2), new Point(5,2), new Point(5.5,1.5), new Point(5.5,0.5),
//        new Point(4.5, 0.5), //right arm
//        new Point(4.5,0), new Point(2.5,0), new Point(2.5,1), new Point(2,1) //right leg
        new Point(0, 0), new Point(1, 0), // Right leg
        new Point(1, 2), new Point(0, 2), // Right leg
        new Point(0, 2), new Point(-1, 2), // Left leg
        new Point(-1, 0), new Point(0, 0), // Left leg
        new Point(0, 2), new Point(0, 4), // Body
        new Point(-1, 4), new Point(1, 4), // Arms
        new Point(0, 4), new Point(0, 5), // Neck
        new Point(-0.5, 5), new Point(0.5, 5), // Head
        new Point(0.5, 4.5), new Point(-0.5, 4.5) // Head
    };
    
//    double xPos = Math.random()*600;
    
    //WHERE DO I PUT THIS
    //I need loop/timer to generate new enemies as time goes on
    banditShape = scale(banditShape, 2.0);
    Enemy bandit = new Enemy(banditShape, new Point(Math.random()*(GAME_SIZE-100)+50, 50), 0);
    
    return bandit;
  }
  
  //basically acts as a scaling method for the shape points using scaling multiplier
  //if you want to make your characters bigger, don't multiply their points
  //by a scaling method individually--use the scaling method!
  public Point[] scale(Point[] points, double multiplier) {
	Point[] scaledPts = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
	        scaledPts[i] = new Point(points[i].x * multiplier, points[i].y * multiplier);
	    }
	return scaledPts;
  }
  
  public void paint(Graphics brush) {
    brush.setColor(Color.BLACK);
    brush.fillRect(0,0,width,height);
	background.paint(brush);
	
	for(Obstacle o: obstacles) {
		o.paint(brush);
	}
	
	player.movement();
    player.paint(brush);	
    
    for(Enemy bandit: bandits) {
    	bandit.movement();
    	bandit.paint(brush);
    	
        if (player.collides(bandit)) {
            Playable.lives--;

            if (Playable.lives == 0) {
                brush.setColor(Color.black);
                brush.fillRect(0,0,width,height);
                brush.setColor(Color.white);
                brush.drawString("Game Over.", 20, 20);
            }
        }
    }
    
    // sample code for printing message for debugging
    // counter is incremented and this message printed
    // each time the canvas is repainted
    counter++;
    brush.setColor(Color.white);
    brush.drawString("Counter is " + counter,615,10);
  }
  
  public static void main (String[] args) {
   	WildWestShootout a = new WildWestShootout();
	a.repaint();
  }
  
}