import greenfoot.*;  

public class CutsceneC extends World
{

    StaticImage playerRed = new StaticImage(10, Color.RED);
    //StaticImage player = new StaticImage(10, Color.WHITE);
    CutscenePlayer player = new CutscenePlayer();
    Goop goop = new Goop();
    int frames = 0;

    StaticImage titleCard = new StaticImage(14);
    StaticImage subtitleCard = new StaticImage(15);

    GreenfootSound zoom = new GreenfootSound("sounds/color_enter_0.mp3");
    GreenfootSound enter = new GreenfootSound("sounds/color_enter_1.mp3");
    
    GreenfootSound heal0 = new GreenfootSound("sounds/heal_0.mp3");

    Dialogue text = new Dialogue(14);

    int phase = 0;

    public CutsceneC()
    {    

        super(1000, 600, 1, false); 

        //addObject(new StaticImage(2), getWidth()/2, getHeight()/2);     //PUT BACK

        //addObject(player, 500, 500);
        //addObject(playerRed, 500, -200);

        addObject(titleCard, 500, 300);
        addObject(subtitleCard, 500, 300);

        for(int i = 0; i < 9; i++){
            addObject(new Platform(1), -450 + i*100, 400);
        }
        for(int i = 0; i < 16; i++){
            int id = (i < 6) ? 0 : 1;
            addObject(new Platform(id), -150 + i*100, 500);
            addObject(new Platform(0), -150 + i*100, 600);            
        }

        player.horizontalMovement = 3;
        player.isWhite = true;
        addObject(player, -200, 200);
        player.goRight = true;
        player.health = 50;

        goop.docile = true;
        addObject(goop, 850, 410);
    }
    int counter = 0;

    public void act(){
        frames++;

        if(phase == 0){
            if(player.getX() >= 300){
                if(counter++ > 0){
                    
                    player.goRight = false;
                    player.isIdling = true;
                    
                }
                
                if(counter == 25){
                    addObject(text, 500, 100);
                }
                
                if(counter == 175){
                    removeObject(text);                    
                    
                }
                
                if(counter == 245){
                    addObject(text = new Dialogue(15), 500, 100);
                }
                
                if(counter == 290){
                    player.fireShot(goop.getX(), goop.getY()+700);
                   
                }
                
                if(counter == 320){
                     removeObject(text);
                }
                //System.out.println(counter);
                if(counter == 430){
                    heal0.play();
                    player.heal(19, Color.RED);
                }
                
                if(counter == 500){
                    Greenfoot.setWorld(new RedWorld());
                }
                
            }
        }
        
    }
}
