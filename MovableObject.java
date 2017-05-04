import greenfoot.*;

/**
 * Write a description of class MovableObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovableObject extends GameObject
{
    
    public double xVel, yVel;
    
    public void act() 
    {
        super.act();
        updateMove();
        
    }
    
    private void updateMove(){
        setLocation(getX() + (int)xVel, getY() + (int)yVel);
    }
}
