import greenfoot.*;

public class Platform extends GameObject
{
    
    GreenfootImage sprite;
    private int curFrame = 0;
    private int frames;
    GreenfootImage[] imgs = new GreenfootImage[7];
    boolean animated = false;
    
    public Platform(int i) {
        super();
        
        sprite = new GreenfootImage("paneling_" + i + ".png");
        setImage(sprite);
        useCollision = true;
        
        if(i == 2){
            animated = true;
            for(int j = 0; j < imgs.length; j++){
                imgs[j] = new GreenfootImage("images/paneling_" + (j+2) + ".png");
            }
        }
        
    }
    
    public Platform(){
        this(0);
    }
    
    public void act() 
    {
        super.act();
        frames++;
        if(animated && frames % 8 == 0){
            setImage(imgs[curFrame++]);
            if(curFrame >= imgs.length){
                curFrame = 0;
            }
        }
    }   
    
    
}
