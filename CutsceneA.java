import greenfoot.*;  
import java.util.List;

public class CutsceneA extends World
{
    int frames = 0;
    
    StaticImage titleCard;
    StaticImage subtitleCard;
    CutscenePlayer player;
    CutsceneBoss boss;
    
    int moveX = 0;
    int moveY = 0;
    
    GreenfootSound powerDown = new GreenfootSound("sounds/Boss_0.mp3");
    GreenfootSound eyeOpen = new GreenfootSound("sounds/Boss_2.wav");
    GreenfootSound shoot = new GreenfootSound("sounds/Boss_3.mp3");
    GreenfootSound shoot2 = new GreenfootSound("sounds/Boss_4.mp3");
    GreenfootSound shake = new GreenfootSound("sounds/Shake.wav");
    GreenfootSound flyAway = new GreenfootSound("sounds/Boss_5.wav");
    GreenfootSound eat = new GreenfootSound("sounds/Boss_6.mp3");
    
    GreenfootSound dump0 = new GreenfootSound("sounds/Dump_0.mp3");
    GreenfootSound dump1 = new GreenfootSound("sounds/Dump_1.mp3");
    GreenfootSound dump2 = new GreenfootSound("sounds/Dump_2.mp3");
    GreenfootSound dump3 = new GreenfootSound("sounds/Dump_3.mp3");
    GreenfootSound dump4 = new GreenfootSound("sounds/Dump_4.mp3");
    GreenfootSound dump5 = new GreenfootSound("sounds/Dump_5.mp3");
    
    GreenfootSound cry = new GreenfootSound("sounds/cry.wav");
    
    GreenfootSound music = new GreenfootSound("sounds/chibininja.mp3");
    
    
    int part = 0;
    StaticImage fadeOut = new StaticImage(12, Color.BLACK);
    StaticImage sadPlayerImg = new StaticImage(11);
    StaticImage redPlayer = null, orangePlayer = null, yellowPlayer = null, greenPlayer = null, bluePlayer = null, purplePlayer = null;
    public CutsceneA()
    {    
        super(1000, 600, 1, false); 
        prepare();
        music.play();
    }
    
    public void act(){
        frames++;
        
        if(part == 0){
            part1();
        }
    }
    
    
    
    public void part1(){
        
        if(frames > 20 && frames < 400){
            if(titleCard.getImage().getTransparency() < 255){
                titleCard.getImage().setTransparency(titleCard.getImage().getTransparency() + 1);
            }
            if(frames > 120){
                if(subtitleCard.getImage().getTransparency() < 253){
                    subtitleCard.getImage().setTransparency(subtitleCard.getImage().getTransparency() + 2);
                }
            }
        }
        
        if(frames > 300 && frames < 475){
            List<GameObject> objs = getObjects(GameObject.class);
            for(int i = 0; i < objs.size(); i++){
                GameObject cur = objs.get(i);
                cur.setLocation(cur.getX(), cur.getY() - 3);
                
            }
        }
        
        if(frames > 300 && frames < 925){
            
            if(frames % 10 == 0){
                addObject(new StaticImage(1), player.getX()+Greenfoot.getRandomNumber(90)-45, player.getY()+Greenfoot.getRandomNumber(90)-45);
            }
            
            if(frames == 301){
                player.goRight = true;
            }
            
            if(frames % 150 == 0){
                player.goRight = !player.goRight;
                player.goLeft = !player.goLeft;
            }
            
            if(frames % 250 == 0){
                player.goJump = true;
            }
            if(frames % 251 == 0){
                player.goJump = false;
            }
        }
        if(frames == 925){
            player.goRight = false;
            player.goLeft = false;
        }
        
        if(frames > 900 && frames < 960){
            if(frames == 901){
                shake.play();
            }
            
            if(frames == 915){
                music.stop();
            }
            
            List<GameObject> objs = getObjects(GameObject.class);
            
            for(int i = 0; i < objs.size(); i++){
                GameObject cur = objs.get(i);
                cur.setLocation(cur.getX() + (int)(10*Math.sin(frames)), cur.getY() + (int)(10*Math.sin(frames)));                
            }
        }
        
        if(frames > 1000 && frames < 1080){
            if(frames == 1001){
                shake.play();
            }
            
            List<GameObject> objs = getObjects(GameObject.class);
            
            for(int i = 0; i < objs.size(); i++){
                GameObject cur = objs.get(i);
                cur.setLocation(cur.getX() - (int)(10*Math.sin(frames)), cur.getY() + (int)(10*Math.sin(frames)));                
            }
        }
        
        if(frames == 1080){
            boss = new CutsceneBoss();
            addObject(boss, 800, -200);
            powerDown.play();
        }
        
        if(frames > 1080 && frames < 1300){
            boss.setLocation(boss.getX(), boss.getY() + 2);
        }
        
        if(frames >= 1295 && frames < 1650){
            boss.setLocation(boss.getX(), boss.getY() + (int)(10*Math.sin(frames/13.0))/3);
        }
        
        if(frames == 1680){
            boss.setImage(new GreenfootImage("images/Boss_1.png"));
            eyeOpen.play();
        }
        
        if(frames >= 1680 && frames < 1980){
            boss.setLocation(boss.getX() + (int)(10*Math.sin(frames))/3, boss.getY() + (int)(10*Math.sin(frames))/3);
        }
        
        if(frames > 1740 && frames < 2155){
            
            if(frames == 1741){
                shoot.play();
                shoot2.play();
            }
            
            List<GameObject> objs = getObjects(GameObject.class);
            
            for(int i = 0; i < objs.size(); i++){
                GameObject cur = objs.get(i);
                cur.setLocation(cur.getX() + (int)(10*Math.sin(frames)), cur.getY() + (int)(10*Math.sin(frames)));                
            }
            
            StaticImage ball = new StaticImage(9);
            addObject(ball, boss.getX(), boss.getY()-38);
            ball.player = player;
            ball.turnTowards(player.getX(), player.getY());
        }
        
        if(frames == 1955){
            player.setWhite();
            player.targetPaintHeight = 0;
            player.curPaintHeight = 155;
            player.paintColor = player.originalColor;
        }
        
        if(frames == 2155){
            removeObjects(getObjects(StaticImage.class));
        }
        
        
        
        if(frames == 2175){
            dump0.play();
            redPlayer = new StaticImage(10, Color.RED);
            addObject(redPlayer, player.getX(), player.getY());
        }
        
        if(frames > 2176 && frames < 2275){
            redPlayer.setLocation(redPlayer.getX() - 1, redPlayer.getY() - 2);
            if(redPlayer.getImage().getTransparency() < 252){
                redPlayer.getImage().setTransparency(redPlayer.getImage().getTransparency() + 3);
            }
        }
        
        if(frames == 2250){
            dump1.play();
            orangePlayer = new StaticImage(10, Color.ORANGE);
            addObject(orangePlayer, player.getX(), player.getY());
        }
        
        if(frames > 2250 && frames < 2350){
            orangePlayer.setLocation(orangePlayer.getX(), orangePlayer.getY() - 2);
            if(orangePlayer.getImage().getTransparency() < 252){
                orangePlayer.getImage().setTransparency(orangePlayer.getImage().getTransparency() + 3);
            }
        }
        
        if(frames == 2325){
            dump2.play();
            yellowPlayer = new StaticImage(10, Color.YELLOW);
            addObject(yellowPlayer, player.getX(), player.getY());
        }
        
        if(frames > 2325 && frames < 2425){
            yellowPlayer.setLocation(yellowPlayer.getX() + 1, yellowPlayer.getY() - 2);
            if(yellowPlayer.getImage().getTransparency() < 252){
                yellowPlayer.getImage().setTransparency(yellowPlayer.getImage().getTransparency() + 3);
            }
        }
        
        if(frames == 2400){
            dump3.play();
            greenPlayer = new StaticImage(10, Color.GREEN);
            addObject(greenPlayer, player.getX(), player.getY());
        }
        
        if(frames > 2400 && frames < 2500){
            greenPlayer.setLocation(greenPlayer.getX() + 2, greenPlayer.getY() - 2);
            if(greenPlayer.getImage().getTransparency() < 252){
                greenPlayer.getImage().setTransparency(greenPlayer.getImage().getTransparency() + 3);
            }
        }
        
        if(frames == 2475){
            dump4.play();
            bluePlayer = new StaticImage(10, Color.BLUE);
            addObject(bluePlayer, player.getX(), player.getY());
        }
        
        if(frames > 2475 && frames < 2575){
            bluePlayer.setLocation(bluePlayer.getX() + 3, bluePlayer.getY() - 2);
            if(bluePlayer.getImage().getTransparency() < 252){
                bluePlayer.getImage().setTransparency(bluePlayer.getImage().getTransparency() + 3);
            }
        }
        
        if(frames == 2550){
            dump5.play();
            purplePlayer = new StaticImage(10, Color.MAGENTA);
            addObject(purplePlayer, player.getX(), player.getY());
        }
        
        if(frames > 2550 && frames < 2650){
            purplePlayer.setLocation(purplePlayer.getX() + 4, purplePlayer.getY() - 2);
            if(purplePlayer.getImage().getTransparency() < 252){
                purplePlayer.getImage().setTransparency(purplePlayer.getImage().getTransparency() + 3);
            }
        }
        
        if(frames > 2640 && frames < 2690){
            boss.setLocation(boss.getX(), boss.getY() - 2);
        }
        
        if(frames > 2685 && frames < 3000){
            purplePlayer.setLocation(purplePlayer.getX()+3, purplePlayer.getY());
            if(purplePlayer.getX() >= boss.getX()){
                purplePlayer.getImage().setTransparency(0);
                eat.play();
                purplePlayer.setLocation(-1000, -1000);
            }
            
            bluePlayer.setLocation(bluePlayer.getX()+3, bluePlayer.getY());
            if(bluePlayer.getX() >= boss.getX()){
                bluePlayer.getImage().setTransparency(0);
                eat.play();
                bluePlayer.setLocation(-1000, -1000);
            }
            
            greenPlayer.setLocation(greenPlayer.getX()+3, greenPlayer.getY());
            if(greenPlayer.getX() >= boss.getX()){
                greenPlayer.getImage().setTransparency(0);
                eat.play();
                greenPlayer.setLocation(-1000, -1000);
            }
            
            yellowPlayer.setLocation(yellowPlayer.getX()+3, yellowPlayer.getY());
            if(yellowPlayer.getX() >= boss.getX()){
                yellowPlayer.getImage().setTransparency(0);
                eat.play();
                yellowPlayer.setLocation(-1000, -1000);
            }
            
            orangePlayer.setLocation(orangePlayer.getX()+3, orangePlayer.getY());
            if(orangePlayer.getX() >= boss.getX()){
                orangePlayer.getImage().setTransparency(0);
                eat.play();
                orangePlayer.setLocation(-1000, -1000);
            }
            redPlayer.setLocation(redPlayer.getX()+3, redPlayer.getY());
            if(redPlayer.getX() >= boss.getX()){
                redPlayer.getImage().setTransparency(0);
                eat.play();
                redPlayer.setLocation(-1000, -1000);
            }
        }
        
        if(frames > 3000 && frames < 3150){
            if(frames == 3001){
                flyAway.play();
            }
            boss.setLocation(boss.getX(), boss.getY()-3);
        }
        
        if(frames == 3150){
            addObject(sadPlayerImg, player.getX(), player.getY());
            player.setLocation(-150, -300);
            cry.play();
            
        }
        
        if(frames == 3210){
            addObject(fadeOut, 500, 300);
            //music2.play();
            //part++;
            //frames = 0;
        }
        
        if(frames == 3465){
            Greenfoot.setWorld(new CutsceneB());
        }
    }
    
    private void prepare()
    {
        
        titleCard = new StaticImage(7);
        addObject(titleCard, 500, 300);
        subtitleCard = new StaticImage(8);
        addObject(subtitleCard, 500, 300);
        
        for(int i = 0; i < 12; i++){
            addObject(new Platform(1), 48 + i*100 - 100,984);
            addObject(new Platform(), 48 + i*100 - 100,1084);
        }

        player = new CutscenePlayer();
        addObject(player,104,842);
    }
}
