import greenfoot.*;  

public class CutsceneE extends World
{
    
    StaticImage playerGreen = new StaticImage(10, Color.GREEN);
    StaticImage player = new StaticImage(10, Color.WHITE);
    int frames = 0;
    
    StaticImage titleCard = new StaticImage(22);
    StaticImage subtitleCard = new StaticImage(23);
    
    GreenfootSound zoom = new GreenfootSound("sounds/color_enter_0.mp3");
    GreenfootSound enter = new GreenfootSound("sounds/color_enter_1.mp3");
    
    
    public CutsceneE()
    {    
       
        super(1000, 600, 1, false); 
        
        addObject(new StaticImage(16, Color.GREEN), getWidth()/2, getHeight()/2);
        
        //playerGreen.getImage().setTransparency(255);
        addObject(player, 500, 500);
        addObject(playerGreen, 500, -200);
        
        addObject(titleCard, 500, 300);
        addObject(subtitleCard, 500, 300);
        
    }
    
    public void act(){
        frames++;
        
        if(frames < 255){
            playerGreen.getImage().setTransparency(frames);
            player.getImage().setTransparency(frames);
            
        }
        
        if(frames == 256){
            frames += 20;
        }
        
        if(frames > 240 && frames < 434){
            playerGreen.setLocation(playerGreen.getX() , playerGreen.getY() + 4);
        }
        
        if(frames == 300){
            zoom.play();
        }
        
        if(frames == 434){
            enter.play();
            playerGreen.setLocation(player.getX(), player.getY());
        }
        
        if(frames > 395 && frames < 515){
            playerGreen.getImage().setTransparency(650 - frames);
        }
        
        
        if(frames > 515 && frames < 770){
            titleCard.getImage().setTransparency(frames-515);
        }
        if(frames > 615 && frames < 870){
            subtitleCard.getImage().setTransparency(frames-615);
        }
        
        if(frames == 915){
            addObject(new StaticImage(12, Color.BLUE), 500, 300);
        }
        
        if(frames == 1170){
            //SetWorld
            Greenfoot.setWorld(new BlueWorld());
        }
    }
}
