import greenfoot.*;  

public class WindParticle extends GameObject
{
    
    GreenfootImage[] imgsRight = new GreenfootImage[8];
    GreenfootImage[] imgsLeft = new GreenfootImage[8];
    int frames = 0;
    int curFrame = 0;
    
    boolean rightSide = true;
    
    public WindParticle(char side, int rotation){
        super();
        
        int rand = Greenfoot.getRandomNumber(2);
        
        for(int i = 0; i < imgsRight.length; i++){
            imgsRight[i] = new GreenfootImage("images/wind_"+i+".png");
            
            if(rand == 0){
                imgsRight[i].mirrorVertically();
            }
            
            imgsLeft[i] = new GreenfootImage(imgsRight[i]);
            imgsLeft[i].mirrorHorizontally();
            
            
        }
        
        if(side == 'l'){
            rightSide = false;
            setImage(imgsLeft[0]);
        }
        else{
            rightSide = true;
            setImage(imgsRight[0]);
        }
        
        setRotation(rotation);
    }
    
    public void act() 
    {
        super.act();
        frames++;
        if(frames % 4 == 0){
           if(rightSide){
               setImage(imgsRight[curFrame++]);
           }
           else{
                setImage(imgsLeft[curFrame++]);
           }
           
           if(curFrame >= imgsRight.length){
               getWorld().removeObject(this);
            } 
        }
        
    }    
}
