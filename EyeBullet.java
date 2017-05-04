import greenfoot.*;  


public class EyeBullet extends MovableObject
{
    Player player;
    boolean dead = false;
    int touchFrames = 0;
    
    public EyeBullet(Player player){
        super();
        this.player = player;
        turnTowards(player.getX(), player.getY());
    }
    
    public void firstTime(){
        super.firstTime();
        turnTowards(player.getX(), player.getY());
    }
    
    public void act() 
    {
        super.act();
        
        move(10);
        
        if(isOnscreen() == false){
            dead = true;
        }
        if(isTouching(Player.class)){
            touchFrames++;
            
            if(touchFrames >= 5){
                dead = true;
                player.addFillColor(-3);
            }
        }
        
        if(dead){
            world.removeObject(this);
        }
    }    
}
