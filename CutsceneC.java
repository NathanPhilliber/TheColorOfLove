import greenfoot.*;  

public class CutsceneC extends World
{
    
    StaticImage playerRed = new StaticImage(10, Color.RED);
    StaticImage player = new StaticImage(10, Color.WHITE);
    int frames = 0;
    
    StaticImage titleCard = new StaticImage(14);
    StaticImage subtitleCard = new StaticImage(15);
    
    GreenfootSound zoom = new GreenfootSound("sounds/color_enter_0.mp3");
    GreenfootSound enter = new GreenfootSound("sounds/color_enter_1.mp3");
    GreenfootSound music = new GreenfootSound("sounds/comeandfindme.mp3");
    
    public CutsceneC()
    {    
       
        super(1000, 600, 1, false); 
        
        addObject(new StaticImage(2), getWidth()/2, getHeight()/2);
        
        //playerRed.getImage().setTransparency(255);
        addObject(player, 500, 500);
        addObject(playerRed, 500, -200);
        
        addObject(titleCard, 500, 300);
        addObject(subtitleCard, 500, 300);
        
    }
    
    public void act(){
        frames++;
        
        if(frames < 255){
            playerRed.getImage().setTransparency(frames);
            player.getImage().setTransparency(frames);
            
        }
        
        if(frames > 275 && frames< 450){
            playerRed.setLocation(playerRed.getX() , playerRed.getY() + 4);
        }
        
        if(frames == 300){
            zoom.play();
        }
        
        if(frames == 450){
            enter.play();
            playerRed.setLocation(player.getX(), player.getY());
        }
        
        if(frames > 430 && frames < 550){
            playerRed.getImage().setTransparency(685 - frames);
        }
        
        if(frames == 555){
            music.playLoop();
        }
        
        if(frames > 550 && frames < 805){
            titleCard.getImage().setTransparency(frames-550);
        }
        if(frames > 650 && frames < 905){
            subtitleCard.getImage().setTransparency(frames-650);
        }
        
        if(frames == 950){
            addObject(new StaticImage(12, Color.ORANGE), 500, 300);
        }
        
        if(frames == 1220){
            //SetWorld
            Greenfoot.setWorld(new OrangeWorld(music));
        }
    }
}
