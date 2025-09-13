package game;

import java.awt.*;
import javax.swing.*;

public class Background {

    private static final int BUSH_SIZE = 40;
    private static final int OPENING_SIZE = 120;
    private static final int GAME_SIZE = 600;
    
    private Image bushImage;
    
    public Background() {
    	bushImage = new ImageIcon("src/piskel_bushV2.png").getImage();
    }

    public void paint(Graphics brush) {
        //dirt
        brush.setColor(new Color(210, 180, 140));
        brush.fillRect(0, 0, GAME_SIZE, GAME_SIZE);

        
        //top bushes
        for (int i = 0; i < GAME_SIZE - BUSH_SIZE; i += BUSH_SIZE) {
            if (i <= (GAME_SIZE / 2 - OPENING_SIZE / 2 - BUSH_SIZE) || i >= (GAME_SIZE / 2 + OPENING_SIZE / 2)) {
                brush.drawImage(bushImage, i, 0, BUSH_SIZE, BUSH_SIZE, null);
            }
        }

        //bottom bushes
        for (int i = 0; i <= GAME_SIZE - BUSH_SIZE; i += BUSH_SIZE) {
            if (i <= (GAME_SIZE / 2 - OPENING_SIZE / 2 - BUSH_SIZE) || i >= (GAME_SIZE / 2 + OPENING_SIZE / 2)) {
                brush.drawImage(bushImage, i, GAME_SIZE - BUSH_SIZE, BUSH_SIZE, BUSH_SIZE, null);
            }
        }

        //left bushes
        for (int i = 0; i < GAME_SIZE - BUSH_SIZE; i += BUSH_SIZE) {
            if (i <= (GAME_SIZE / 2 - OPENING_SIZE / 2 - BUSH_SIZE) || i >= (GAME_SIZE / 2 + OPENING_SIZE / 2)) {
                brush.drawImage(bushImage, 0, i, BUSH_SIZE, BUSH_SIZE, null);
            }
        }

        //right bushes
        for (int i = 0; i < GAME_SIZE - BUSH_SIZE; i += BUSH_SIZE) {
            if (i <= (GAME_SIZE / 2 - OPENING_SIZE / 2 - BUSH_SIZE) || i >= (GAME_SIZE / 2 + OPENING_SIZE / 2)) {
                brush.drawImage(bushImage, GAME_SIZE - BUSH_SIZE, i, BUSH_SIZE, BUSH_SIZE, null);
            }
        }

//        //debug openings
//        brush.setColor(Color.RED);
//        brush.drawLine(GAME_SIZE / 2 - OPENING_SIZE / 2, 0, GAME_SIZE / 2 - OPENING_SIZE / 2, GAME_SIZE - BUSH_SIZE);
//        brush.drawLine(GAME_SIZE / 2 + OPENING_SIZE / 2, 0, GAME_SIZE / 2 + OPENING_SIZE / 2, GAME_SIZE - BUSH_SIZE);
//        brush.drawLine(0, GAME_SIZE / 2 - OPENING_SIZE / 2, GAME_SIZE - BUSH_SIZE, GAME_SIZE / 2 - OPENING_SIZE / 2);
//        brush.drawLine(0, GAME_SIZE / 2 + OPENING_SIZE / 2, GAME_SIZE - BUSH_SIZE, GAME_SIZE / 2 + OPENING_SIZE / 2);

    }
}
