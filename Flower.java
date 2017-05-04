import greenfoot.*; 

public class Flower extends Scenery
{
    
    int img;
    
    GreenfootImage sprite0;
    GreenfootImage sprite1;
    
    public Flower(){
        super();
        img = Greenfoot.getRandomNumber(4);
        sprite0 = new GreenfootImage("images/flower_"+ img +".png");
        sprite1 = new GreenfootImage("images/flower_"+ img +"_crushed.png");
        setImage(sprite0);
        
        if(Greenfoot.getRandomNumber(2) == 0){
            getImage().mirrorHorizontally();
            sprite1.mirrorHorizontally();
        }
        
    }
    
    public void act() 
    {
        super.act();
        
        if(getOneObjectAtOffset( 0, 0, GravityObject.class) != null){
            setImage(sprite1);
        }
        else{
            setImage(sprite0);
        }
        
    }    
}
