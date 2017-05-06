import greenfoot.*;  
import java.util.List;

public class CutsceneF extends World
{

    StaticImage playerBlue = new StaticImage(10, Color.BLUE);
    //StaticImage player = new StaticImage(10, Color.WHITE);
    CutsceneBoss boss = new CutsceneBoss();
    int frames = 0;
    Dialogue text = new Dialogue(13);
    CutscenePlayer player = new CutscenePlayer();

    StaticImage titleCard = new StaticImage(24);
    StaticImage subtitleCard = new StaticImage(25);

    GreenfootSound zoom = new GreenfootSound("sounds/color_enter_0.mp3");
    GreenfootSound enter = new GreenfootSound("sounds/color_enter_1.mp3");
    GreenfootSound roar = new GreenfootSound("sounds/roar.mp3");
    GreenfootSound music = new GreenfootSound("sounds/apr.mp3");

    int phase = 0;

    public CutsceneF()
    {    

        super(1000, 600, 1, false); 

        setPaintOrder(ParticleEffect.class, StaticImage.class, Player.class, CutscenePlayer.class, Platform.class, MovableObject.class, Scenery.class);

        addObject(new StaticImage(16, Color.BLUE), getWidth()/2, getHeight()/2);    /// PUT BACK

        //playerBlue.getImage().setTransparency(255);
        //addObject(player, 500, 500);
        //addObject(playerBlue, 500, -200);


        for(int i = 0; i < 30; i++){
            addObject(new Platform(), i*100 - 300, 500);
        }

        player.isWhite = true;
        player.ignoreBorders = false;
        addObject(player, -200, 325);

    }

    public void act(){
        frames++;
        //System.out.println(frames);
        if(phase == 0){
            if(frames < 255){
                //playerBlue.getImage().setTransparency(frames);
                //player.getImage().setTransparency(frames);

            }

            if(frames == 100){
                player.goRight = true;
            }

            if(frames == 400){
                player.goRight = false;
                addObject(boss, 1200, 250);
                boss.setImage(new GreenfootImage("images/Boss_1.png"));
            }

            if(frames >= 470 && frames <= 565){
                player.moveWorldLeft();
                player.setLocation(player.getX() - (int)player.horizontalMovement, player.getY());
            }

            if(frames > 400){
                boss.setLocation(boss.getX(), boss.getY() + (int)(Math.sin(frames/12)*2));
            }

            if(frames == 670){
                addObject(text, 500, 100);
                text.startReveal();
            }

            if(frames == 815){
                removeObject(text);
                roar.play();
            }

            if(frames >815 && frames < 900){
                List<GameObject> objs = getObjects(GameObject.class);

                for(int i = 0; i < objs.size(); i++){
                    GameObject cur = objs.get(i);
                    cur.setLocation(cur.getX() + (int)(10*Math.sin(frames)), cur.getY() + (int)(10*Math.sin(frames)));                
                }
            }

            if(frames > 900 && frames < 1000){
                List<GameObject> objs = getObjects(GameObject.class);

                for(int i = 0; i < objs.size(); i++){
                    GameObject cur = objs.get(i);
                    cur.setLocation(cur.getX(), cur.getY() + 6);                
                }
            }
            if(frames == 1000){
                phase = 1;
                frames = 510;
                addObject(titleCard, 500, 300);
                addObject(subtitleCard, 500, 300);
                music.playLoop();
            }
        }

        else if(phase == 1){ //pull down char and display chapter title, then advance
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
                //playerBlue.getImage().setTransparency(650 - frames);
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
                Greenfoot.setWorld(new PurpleWorld(music));
            }
        }
    }
}
