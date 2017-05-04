import greenfoot.*;

public class SwordWave extends GameObject
{
    private int frames;
    private int curFrame = 0;
    GreenfootImage[] imgs = new GreenfootImage[9];
    GreenfootImage[] imgsLeft = new GreenfootImage[9];
    Player player;
    
    int delX, delY;
    
    public SwordWave(Player player){
       super();
       this.player = player;
       useCollision = false;
       for(int i = imgs.length - 1; i >= 0; i--){
           imgs[imgs.length - i - 1] = new GreenfootImage("swordswing_" + i + ".png");
       }
       
       for(int i = 0; i < imgs.length; i++){
           imgsLeft[i] = (new GreenfootImage(imgs[i]));
           imgsLeft[i].mirrorHorizontally();
        }
       
        setImage(imgs[8]);
        
        //xVel = vel;
    }
    
    public void firstTime(){
        super.firstTime();
        delX = getX() - player.getX();
        delY = getY() - player.getY();

    }
    
    public void act() 
    {
        super.act();
        frames++;
        
        setLocation(player.getX() + delX, player.getY() + delY);
        
        if(frames % 2 == 0 && curFrame < imgs.length){
            if(delX < 0){
                setImage(imgsLeft[curFrame++]);
            }
            else{
                
                setImage(imgs[curFrame++]);
            }
        }
        
        if(frames > 30){
            getWorld().removeObject(this);
        }
    }    
}
