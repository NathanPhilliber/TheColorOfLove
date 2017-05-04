import greenfoot.*;  


public class ParticleEffect extends MovableObject
{
    int id;
    
    public ParticleEffect(int id){
        super();
        
        this.id = id;
        
        if (id == 0){
            setImage(new GreenfootImage("images/Skull.png"));
            yVel = -(Greenfoot.getRandomNumber(2)+1);
        }
    }
    
    public void act() 
    {
        super.act();
        
        if(Greenfoot.getRandomNumber(30) == 0){
            world.removeObject(this);
        }
    }    
}
