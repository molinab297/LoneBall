
package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Ball;
import com.jamescho.game.model.Paddle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

 /*************************************************************************
 * PlayState
 * ----------------------------------------------------------------------
 * This class extends the State class. It represents the gameplay screen
 * for LoneBall, as well as manages the game objects. 
 * 
 * ----------------------------------------------------------------------
 ************************************************************************/
public class PlayState extends State{
    
    // Constants
    private static final int PADDLE_WIDTH  = 15;
    private static final int PADDLE_HEIGHT = 60;
    private static final int BALL_DIAMETER = 20;
    
    // Objects
    private Paddle paddleLeft, paddleRight;
    private Ball ball;
    private Font scoreFont;
    
    // Misc game variables
    private int playerScore = 0;
    

    // Create the two paddle objects 
    @Override
    public void init() {
        paddleLeft  = new Paddle(0, 195, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddleRight = new Paddle(785, 195, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball        = new Ball(300,200, BALL_DIAMETER, BALL_DIAMETER);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);
        
    }

    @Override
    public void update() {
        paddleLeft.update();
        paddleRight.update();
        ball.update();
        
        // handles collision
        if(ballCollides(paddleLeft))
        {
            playerScore++;
            ball.onCollideWith(paddleLeft);
            Resources.hit.play();
        }
        else if(ballCollides(paddleRight))
        {
            playerScore++;
            ball.onCollideWith(paddleRight);
            Resources.hit.play();
        }
        else if(ball.isDead())
        {
            playerScore -= 3;
            ball.reset();
        }
    }

    // Draws all game objects to screen
    @Override
    public void render(Graphics g) {
        // Draw Background
        g.setColor(Resources.darkBlue);
        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.setColor(Resources.darkRed);
        g.fillRect(GameMain.GAME_WIDTH / 2 , 0, GameMain.GAME_WIDTH / 2,
                        GameMain.GAME_HEIGHT);
        // Draw Separator Line
        g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);
        
        // Draw Paddles
        g.setColor(Color.white);
        g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(),
                        paddleLeft.getHeight());
        g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(),
                        paddleRight.getHeight());
        
        // Draw UI.
        g.setFont(scoreFont);
        g.drawString("" + playerScore, 350, 40);
        
        // Draw ball
        g.drawRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        
    }
    
    // Determines if ball has collided with either paddle
    private boolean ballCollides(Paddle p)
    {
        return ball.getRect().intersects(p.getRect());
    }

    @Override
    public void onClick(MouseEvent e) {
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) 
        {
                paddleLeft.accelUp();
                paddleRight.accelDown();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
        {
                paddleLeft.accelDown();
                paddleRight.accelUp();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) 
        {
                paddleLeft.stop();
                paddleRight.stop();
        }
    }
    
}
