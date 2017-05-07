import greenfoot.*;  
import java.util.List;
public class CutsceneD extends World
{

    //StaticImage playerOrange = new StaticImage(10, Color.ORANGE);
    //StaticImage player = new StaticImage(10, Color.WHITE);
    CutsceneBoss boss = new CutsceneBoss();
    int frames = 0;
    int phase = 0;
    StaticImage titleCard = new StaticImage(20);
    StaticImage subtitleCard = new StaticImage(21);

    GreenfootSound zoom = new GreenfootSound("sounds/color_enter_0.mp3");
    GreenfootSound enter = new GreenfootSound("sounds/color_enter_1.mp3");
    GreenfootSound roar = new GreenfootSound("sounds/roar.mp3");

    public CutsceneD()
    {    

        super(1000, 600, 1, false); 
        setPaintOrder(StaticImage.class, CutsceneBoss.class, FakePlatform.class);
        addObject(new StaticImage(16, Color.ORANGE), getWidth()/2, getHeight()/2); 

        //playerOrange.getImage().setTransparency(255);
        //addObject(player, 500, 500);
        //addObject(playerOrange, 500, -200);

        addObject(titleCard, 500, 300);
        addObject(subtitleCard, 500, 300);
        
        for(int i = 0; i < 18; i++){
            addObject(new FakePlatform(), Greenfoot.getRandomNumber(1200)-100, Greenfoot.getRandomNumber(1000));
        }

    }

    public void act(){
        frames++;
        int offset = 2;
        //playerOrange.getImage().setTransparency(650 - frames);
        if(phase == 0){
            if(frames == 1){
                addObject(boss, -100, 300);
                boss.setImage(new GreenfootImage("images/Boss_1.png"));
            }
            if(frames > 30){
                boss.setLocation(boss.getX()+ (int)(Math.sin(frames/12)*2) + offset, boss.getY() + (int)(Math.sin(frames/12)*2));
            }

            if(boss.getX() >= 500 && boss.getX() <= 505){
                roar.play();
                offset = 5;
            }

            if(boss.getX() >= 500 && boss.getX() <= 750){

                List<GameObject> objs = getObjects(GameObject.class);

                for(int i = 0; i < objs.size(); i++){
                    GameObject cur = objs.get(i);
                    cur.setLocation(cur.getX() + (int)(10*Math.sin(frames)), cur.getY() + (int)(10*Math.sin(frames)));                
                }

            }
            if(boss.getX() > 1100){
                phase = 1;
                frames = 515;
            }
        }
        else if(phase == 1){
            if(frames > 515 && frames < 770){
                titleCard.getImage().setTransparency(frames-515);
            }
            if(frames > 615 && frames < 870){
                subtitleCard.getImage().setTransparency(frames-615);
            }

            if(frames == 915){
                addObject(new StaticImage(12, Color.GREEN), 500, 300);
            }

            if(frames == 1170){
                //SetWorld
                Greenfoot.setWorld(new GreenWorld());
            }
        }
    }
}
