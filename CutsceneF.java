 import greenfoot.*;  

public class CutsceneF extends World
{
    
    StaticImage playerBlue = new StaticImage(10, Color.BLUE);
    StaticImage player = new StaticImage(10, Color.WHITE);
    int frames = 0;
    
    StaticImage titleCard = new StaticImage(24);
    StaticImage subtitleCard = new StaticImage(25);
    
    GreenfootSound zoom = new GreenfootSound("sounds/color_enter_0.mp3");
    GreenfootSound enter = new GreenfootSound("sounds/color_enter_1.mp3");
    
    
    public CutsceneF()
    {    
       
        super(1000, 600, 1, false); 
        
        addObject(new StaticImage(16, Color.BLUE), getWidth()/2, getHeight()/2);
        
        //playerBlue.getImage().setTransparency(255);
        addObject(player, 500, 500);
        addObject(playerBlue, 500, -200);
        
        addObject(titleCard, 500, 300);
        addObject(subtitleCard, 500, 300);
        
    }
    
    public void act(){
        frames++;
        
        if(frames < 255){
            playerBlue.getImage().setTransparency(frames);
            player.getImage().setTransparency(frames);
            
        }
        
        if(frames == 256){
            frames += 20;
        }
        
        if(frames > 240 && frames < 434){
            playerBlue.setLocation(playerBlue.getX() , playerBlue.getY() + 4);
        }
        
        if(frames == 300){
            zoom.play();
        }
        
        if(frames == 434){
            enter.play();
            playerBlue.setLocation(player.getX(), player.getY());
        }
        
        if(frames > 395 && frames < 515){
            playerBlue.getImage().setTransparency(650 - frames);
        }
        
        
        if(frames > 515 && frames < 770){
            titleCard.getImage().setTransparency(frames-515);
        }
        if(frames > 615 && frames < 870){
            subtitleCard.getImage().setTransparency(frames-615);
        }
        
        if(frames == 915){
            addObject(new StaticImage(12, Color.MAGENTA), 500, 300);
        }
        
        if(frames == 1170){
            //SetWorld
            Greenfoot.setWorld(new PurpleWorld());
        }
    }
}
