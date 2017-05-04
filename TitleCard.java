import greenfoot.*;  


public class TitleCard extends UIObject
{
    

    int frames = 0;
    int r = 255;
    int g = 255;
    int b = 255;
    
    public TitleCard(){
        super();
        setImage(new GreenfootImage("images/Title.png"));
        
    }
    
    public void act() 
    {
        super.act();
        frames++;
        
       
        g += (Greenfoot.getRandomNumber(10) - 5);
        b += (Greenfoot.getRandomNumber(10) - 5);
        
        
        
        if(g < 10){
            g = 10;
        }
        if(g > 255){
            g = 255;
        }
        
        if(b < 10){
            b = 10;
        }
        if(b > 255){
            b = 255;
        }
            
        colorImage(getImage(), new Color(r,g,b));
        
    }    
}
