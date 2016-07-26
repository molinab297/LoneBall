package com.jamescho.game.main;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

 /*************************************************************************
 * Resources
 * ----------------------------------------------------------------------
 * This class handles everything resource related, such
 * audio and images files, and assigns them into variables which
 * can be used in other classes.
 * ----------------------------------------------------------------------
 ************************************************************************/
public class Resources {
    
    public static BufferedImage welcome, iconimage, line;
    public static AudioClip hit, bounce;
    public static Color darkBlue, darkRed;
    
    // Instantiates all resource variables
    public static void load()
    {
        welcome   = loadImage("welcome.png");
        iconimage = loadImage("iconimage.png"); 
        line      = loadImage("line.png");
        hit       = loadSound("hit.wav");
        bounce    = loadSound("bounce.wav");
        // Color constructor accepts RGB parameters
        darkBlue  = new Color(25, 83, 105); 
        darkRed   = new Color(105, 13, 13);  
    }
    
    // Fetches sound from resource folder
    private static AudioClip loadSound(String filename)
    {
        URL fileURL = Resources.class.getResource("/resources/" + filename);
        return Applet.newAudioClip(fileURL);
    }
    
    // Fetches image from resource folder
    private static BufferedImage loadImage(String filename)
    {
        BufferedImage img = null;
        try 
        {
            img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
        } 
        catch (Exception e)
        {
            System.out.println("Error while reading: " + filename);
            e.printStackTrace();
        }
        return img;
    }
    
}
