import greenfoot.*;

public class JumpSmoke extends GameObject
{
    GreenfootImage[] imgs = new GreenfootImage[4];
    int curFrame = 0;
    int frames = 0;
    
    public JumpSmoke(){
        for(int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage("smoke_"+i+".png");
        }
        setImage(imgs[0]);
    }
    
    public void act() 
    {
        super.act();
        frames++;
        if(frames % 3 == 0){
            
            setImage(imgs[curFrame++]);
            if(curFrame >= imgs.length){
                getWorld().removeObject(this);
            }
        }
    
    }    
}
