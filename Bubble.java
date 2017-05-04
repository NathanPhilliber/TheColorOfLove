import greenfoot.*;

/**
 * Write a description of class Bubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bubble extends MovableObject
{
    
    int frames = 0;
    int offscreen = 0;
    int curFrame = 0;
    boolean pop = false;
    GreenfootImage[] imgs = new GreenfootImage[4];
    Color color;
    boolean delete = false;
    
    public Bubble(char dir, Color color){
        super();
        if(dir == 'u'){
            do{
                xVel = Greenfoot.getRandomNumber(2)-1 + ((double)Greenfoot.getRandomNumber(10))/10;
                yVel = Greenfoot.getRandomNumber(2) + ((double)Greenfoot.getRandomNumber(10))/10;
            }while(Math.abs(xVel) < .75 && Math.abs(yVel) < .75);
        }
        else if(dir == 'l'){
            do{
                xVel = Greenfoot.getRandomNumber(2) + ((double)Greenfoot.getRandomNumber(10))/10;
                yVel = Greenfoot.getRandomNumber(2)-1 + ((double)Greenfoot.getRandomNumber(10))/10;
            }while(Math.abs(xVel) < .75 && Math.abs(yVel) < .75);
        }
        else if(dir == 'r'){
            do{
                xVel = -1 * Greenfoot.getRandomNumber(2) - ((double)Greenfoot.getRandomNumber(10))/10;
                yVel = Greenfoot.getRandomNumber(2)-1 + ((double)Greenfoot.getRandomNumber(10))/10;
            }while(Math.abs(xVel) < .75 && Math.abs(yVel) < .75);
        }
        
        
        int delScale = Greenfoot.getRandomNumber(20) - 12;
        for(int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage("bubble_"+i+".png");
            imgs[i].scale(imgs[i].getWidth() + delScale, imgs[i].getHeight() + delScale);
            colorImage(imgs[i], color);
            
        }
        
        setImage(imgs[0]);
        this.color = color; 
        
    }
    
    public Bubble(char dir){
        this(dir, new Color(255, 175, 175));
    }
    
    public void act() 
    {
        // Add your action code here.
        super.act();
        frames++;
        
        turn(1);
        
        if((getX() < 0 || getX() > getWorld().getWidth()) || (getY() < 0 || getY() > getWorld().getHeight())){
            offscreen++;
            
            if(offscreen > 20){
                delete = true;
            }
        }
        else{
            offscreen = 0;
        }
        
        if(pop == false && isTouching(GameObject.class)){
            Player player = (Player)getOneIntersectingObject(Player.class);
            if(player != null){
                
                //GIVE PLAYER COLOR
                player.addFillColor(3);
                
                
            }
            pop = true;
        }
        
        if(pop){
            setImage(imgs[curFrame++]);
            if(curFrame >= imgs.length){
                delete = true;
            }
               
        }
        
        if(delete){
            getWorld().removeObject(this);
        }
    }    
}
