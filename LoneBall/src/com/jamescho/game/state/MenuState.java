
package com.jamescho.game.state;
import com.jamescho.game.main.Resources;
import com.jamescho.game.state.PlayState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
 

 /*************************************************************************
 * MenuState
 * ----------------------------------------------------------------------
 * This class extends the State class. It represents the Menu State of
 * the game.
 * ----------------------------------------------------------------------
 ************************************************************************/
public class MenuState extends State{

    @Override
    public void init() {
        System.out.println("Entered MenuState");
    }

    @Override
    public void update() {
    }

    // Draws Resources.welcome to screen
    @Override
    public void render(Graphics g) {
        g.drawImage(Resources.welcome, 0, 0, null);
    }

    @Override
    public void onClick(MouseEvent e) {
        setCurrentState(new PlayState());
    }

    @Override
    public void onKeyPress(KeyEvent e) {
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
    
}
