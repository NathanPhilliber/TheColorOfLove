import greenfoot.*;

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
