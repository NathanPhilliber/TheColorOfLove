import greenfoot.*;  

public class Rain extends MovableObject
{
    Player player;
    int frames = 0;
    
    public Rain(){
        super();
        useCollision = false;
        //ignoreGround = true;
        yVel = 10;
    }
    
    public void act() 
    {
        super.act();
        frames++;
        
        
        if(frames % 3 == 0 && (player = (Player)getOneObjectAtOffset(0,0,Player.class)) != null){
            player.addFillColor(-1);
        }        
        else if(getY() > 600 || isTouching(Platform.class)){
            world.removeObject(this);
        }
        
        
    }    
}
