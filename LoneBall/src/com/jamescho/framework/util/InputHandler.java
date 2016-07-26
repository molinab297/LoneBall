
package com.jamescho.framework.util;
import com.jamescho.game.state.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

 /*************************************************************************
 * InputHandler
 * ----------------------------------------------------------------------
 * This class handles everything that requires User Input.
 * 
 * Input: A currentState object (such as a loadState, menuState, ect, will 
 * request its own version of one of three types of inputs that this game will 
 * require. (keyPressed, keyReleased, and mouseClicked).
 * 
 * Output: The particular method of the currentState object will be called.
 *  
 * ----------------------------------------------------------------------
 ************************************************************************/
public class InputHandler implements KeyListener, MouseListener {
    
    private State currentState;
    
    // Sets current state
    public void setCurrentState(State currentState)
    {
        this.currentState = currentState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
         // Do Nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentState.onKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentState.onKeyRelease(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentState.onClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         // Do Nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         // Do Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
         // Do Nothing
    }
    
}
