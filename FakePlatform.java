import greenfoot.*;  

public class FakePlatform extends Scenery
{
    
    int xVel, yVel;
    int frames = 0;
    int turning;
    public FakePlatform(){
        super();
        useCollision = false;
        setImage(new GreenfootImage("images/paneling_9.png"));
        xVel = Greenfoot.getRandomNumber(3)-1;
        yVel = -1;
        turning = Greenfoot.getRandomNumber(2);
        if (turning == 0){
            turning = -1;
        }
    }
    public void act() 
    {
        super.act();
        frames++;
        if(frames % 2 == 0 && isOnscreen()){
            turn(turning);
            setLocation(getX(), getY() + yVel);
        }
    }    
}
