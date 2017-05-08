import greenfoot.*;  

public class CutsceneG extends World
{

    StaticImage redPlayer = null, orangePlayer = null, greenPlayer = null, bluePlayer = null, purplePlayer = null;
    int frames = 0;
    StaticImage players[] = new StaticImage[5];
    CutscenePlayer player = new CutscenePlayer();
    GreenfootSound win0 = new GreenfootSound("sounds/winzoom.mp3");
    GreenfootSound blip = new GreenfootSound("sounds/blip.mp3");
    GreenfootSound yay = new GreenfootSound("sounds/yay.mp3");
    GreenfootSound[] drop = new GreenfootSound[5];
    //GreenfootSound music = new GreenfootSound("sounds/ending.mp3");
    //int vol = 80;
    int counter = 0;
    //boolean fadeMusic = false;
    public CutsceneG()
    {    

        super(1000, 600, 1, false); 
        
        redPlayer = new StaticImage(10, Color.RED);
        orangePlayer = new StaticImage(10, Color.ORANGE);
        greenPlayer = new StaticImage(10, Color.GREEN);
        bluePlayer = new StaticImage(10, Color.BLUE);
        purplePlayer = new StaticImage(10, Color.MAGENTA);
        players[0] = redPlayer;
        players[1] = orangePlayer;
        players[2] = greenPlayer;
        players[3] = bluePlayer;
        players[4] = purplePlayer;
        
        for(int i = 0; i < drop.length; i++){
            drop[i] = new GreenfootSound("sounds/drop"+i+".mp3");
        }

        for(int i = 0; i < players.length; i++){
            players[i].getImage().setTransparency(255);
            addObject(players[i], 100 + i*200, -200 - 100*i);
        }
        
        addObject(new StaticImage(16, Color.WHITE), getWidth()/2, getHeight()/2); 
        //music.setVolume(80);
    }

    public void act(){
        /*if(frames == 0){
            music.play();
        }

        if(fadeMusic && vol > 0 && frames % 2 ==0){
            music.setVolume(vol--);
        }*/
        
        frames++;
        
        if(frames < 1000){
            for(int i = 0; i < players.length; i++){
                players[i].setLocation(players[i].getX() + (int)(Math.sin(players[i].getY()/12)*2), players[i].getY() + 2);
            }
            
        }
        
        for(int i = 0; i < players.length; i++){
            if(players[i].getY() <= 2 && players[i].getY() >= 0){
                drop[i].play();
            }
            if(players[i].getY() >= 570 && players[i].getY() <= 572){
                blip.play();
            }
        }
        
       
        
        if(frames == 725){
            addObject(player, 500, 700);
            player.ignoreGround = true;
            counter++;
        }
        
        if(frames >= 725 && player.getY() > 300){
            player.setLocation(player.getX(), player.getY() - 3);
        }
        else if(counter == 1){
            yay.play();
            counter++;
        }
        
        
        
        if(frames == 900){
            addObject(new Dialogue(12), 500, 450);
        }
        
        if(frames == 1050){
            addObject(new StaticImage(12, Color.BLACK), 500, 300);
            
        }
        
        if(frames == 1124){
            //fadeMusic = true;
        }
        
        
        if(frames >= 1305){
            //music.stop();
            Greenfoot.setWorld(new CutsceneI());
        }
        
        
    }
    

}