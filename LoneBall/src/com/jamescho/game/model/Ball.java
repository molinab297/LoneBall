
package com.jamescho.game.model;

import com.jamescho.framework.util.RandomNumberGenerator;
import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import java.awt.Rectangle;

 /*************************************************************************
 * Ball
 * ----------------------------------------------------------------------
 * This class represents a Ball.
 *
 * Parameters to create a Ball object:
 * x            - starting X coordinate on screen
 * y            - starting Y coordinate on screen
 * width  (int) - Width size of ball object
 * height (int) - Height size of ball object
 * ----------------------------------------------------------------------
 ************************************************************************/
public class Ball {
    private int x, y, width, height, velX, velY;
    private Rectangle rect;
    
    public Ball(int x, int y, int width, int height) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                velX = 5;
                velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
                rect = new Rectangle(x, y, width, height);
        } 
    
    public void update() {
                x += velX;
                y += velY;
                correctYCollisions();
                updateRect();
        }

    // deflects ball if the ball hits the top or bottom of the screen
    private void correctYCollisions() {
                if (y < 0) 
                {
                        y = 0;
                } 
                // if the ball goes past screen
                else if (y + height > GameMain.GAME_HEIGHT) 
                {
                        y = GameMain.GAME_HEIGHT - height;
                }
                else 
                {
                        return;
                }

                // deflect ball and play sound
                velY = -velY;
                Resources.bounce.play();
        }

    private void updateRect() { 
                rect.setBounds(x, y, width, height);
        }
    
    // deflects ball when the ball has collided with a paddle
    public void onCollideWith(Paddle p) {
                // if ball is on left side of screen
                if (x < GameMain.GAME_WIDTH / 2) 
                {
                        x = p.getX() + p.getWidth();
                } 
                // ball is on right side of screen 
                else 
                {
                        x = p.getX() - width;
                }
                // deflect ball 
                velX = -velX;
                velY += RandomNumberGenerator.getRandIntBetween(-2, 3);
        }

    // determines if ball has hit either left or right side of screen
    public boolean isDead() {
                return (x < 0 || x + width > GameMain.GAME_WIDTH);
        } 
    
    // returns ball back to center of screen    
    public void reset() {
                x = 300;
                y = 200;
                velX = 5;
                velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
        } 
    
        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public int getWidth() {
                return width;
        }

        public int getHeight() {
                return height;
        }

        public Rectangle getRect() {
                return rect;
        } 
}
