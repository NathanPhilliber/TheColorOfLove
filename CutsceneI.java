import greenfoot.*;  


public class CutsceneI extends World
{

    int frames = 0;
    Dialogue text = new Dialogue(24);
    
    public CutsceneI()
    {    

        super(1000, 600, 1, false); 
        prepare();
        
        addObject(new StaticImage(16, Color.BLACK), 500, 300);
    }
    
    public void act(){
        frames++;
        
        if(frames == 155){
            addObject(text, 500, 190);
        }
        
        if(frames == 330){
            removeObject(text);
            
            text = new Dialogue(25);
        }
        
        if(frames == 380){
            addObject(text, 500, 190);
        }
        
        if(frames == 600){
            removeObject(text);
            
            text = new Dialogue(26);
        }
        
        if(frames == 730){
            addObject(text, 500, 190);
        }
        
        if(frames == 960){
            removeObject(text);
            
            text = new Dialogue(27);
        }
        
        if(frames == 970){
            addObject(text, 500, 190);
        }
        
        if(frames == 1100){
            addObject(new StaticImage(12, Color.BLACK), 500, 300);
            
        }
        
        if(frames >= 1355){
            //music.stop();
            Greenfoot.setWorld(new Credits());
        }
    }


    private void prepare()
    {
        Platform platform = new Platform(1);
        addObject(platform,472,358);
        platform.setLocation(495,560);
        Sign sign = new Sign();
        addObject(sign,523,417);
        sign.setLocation(494,460);
    }
}
