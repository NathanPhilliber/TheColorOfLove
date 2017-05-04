import greenfoot.*; 

public class CutsceneB extends World
{

    int frames = 0;
    GreenfootSound music = new GreenfootSound("sounds/ascending.mp3");
    StaticImage ghost = new StaticImage(13);
    Dialogue text = new Dialogue(0);
    Platform platform = new Platform(1);
    StaticImage cutsceneplayer = new StaticImage(11);
    
    StaticImage fadeOut = new StaticImage(12, Color.RED);
    int vol = 100;
    public CutsceneB()
    {    

        super(1000, 600, 1); 
        prepare();
        music.setVolume(100);
    }

    public void act(){
        frames++;
        //System.out.println(frames);
        
        ghost.setLocation(ghost.getX(), ghost.getY() + (int)(Math.sin(frames/12)*2));
        
        if(frames == 1){
            music.play();
        }
        
        if(frames > 50 && frames < 178){
            platform.getImage().setTransparency(platform.getImage().getTransparency() + 2);
            cutsceneplayer.getImage().setTransparency(cutsceneplayer.getImage().getTransparency() + 2);
        }
        
        if(frames == 198){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 400){
            removeObject(text);
            text = new Dialogue(1);
        }
        
        if(frames == 450){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 630){
            removeObject(text);
            text = new Dialogue(2);
        }
        
        if(frames == 680){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 930){
            removeObject(text);
            text = new Dialogue(3);
        }
        
        if(frames == 1050){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames > 1110 && frames < 1365){
            ghost.getImage().setTransparency(ghost.getImage().getTransparency() + 1);
        }
        
        if(frames == 1175){
            removeObject(text);
            text = new Dialogue(4);
        }
        
        if(frames == 1180){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 1450){
            removeObject(text);
            text = new Dialogue(5);
        }
        
        if(frames == 1485){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 1780){
            removeObject(text);
            text = new Dialogue(6);
        }
        
        if(frames == 1810){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 2105){
            removeObject(text);
            text = new Dialogue(7);
        }
        
        if(frames == 2135){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 2390){
            removeObject(text);
            text = new Dialogue(8);
        }
        
        if(frames == 2400){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 2500){
            removeObject(text);
            text = new Dialogue(9);
        }
        
        if(frames == 2530){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 2800){
            removeObject(text);
            text = new Dialogue(10);
        }
        
        if(frames == 2825){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 3020){
            removeObject(text);
            text = new Dialogue(11);
        }
        
        if(frames == 3050){
            addObject(text, 500, 500);
            text.startReveal();
        }
        
        if(frames == 3300){
            addObject(fadeOut, 500, 300);
        }
        
        if(frames == 3570){
            music.stop();
            Greenfoot.setWorld(new RedWorld());
        }
        
       if(frames % 3 == 0 && frames > 3350 && vol > 0){
            music.setVolume(vol--);
       }
       //System.out.println(music.getVolume());
    }


    private void prepare()
    {
        
        addObject(platform,455,207);
        
        addObject(cutsceneplayer,460,57);
        platform.setLocation(307,243);
        cutsceneplayer.setLocation(308,113);
        
        
        platform.getImage().setTransparency(0);
        cutsceneplayer.getImage().setTransparency(0);
        
        
        ghost.getImage().setTransparency(0);
        addObject(ghost,695,170);
        
    }
}
