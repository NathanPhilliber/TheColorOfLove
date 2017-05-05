import greenfoot.*;  

public class CutsceneG extends World
{

    StaticImage redPlayer = null, orangePlayer = null, greenPlayer = null, bluePlayer = null, purplePlayer = null;
    int frames = 0;
    StaticImage players[] = new StaticImage[5];
    CutscenePlayer player = new CutscenePlayer();
    
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

        for(int i = 0; i < players.length; i++){
            players[i].getImage().setTransparency(255);
            addObject(players[i], 100 + i*200, -200 - 100*i);
        }

    }

    public void act(){
        frames++;
        
        if(frames < 1000){
            for(int i = 0; i < players.length; i++){
                players[i].setLocation(players[i].getX() + (int)(Math.sin(players[i].getY()/12)*2), players[i].getY() + 2);
            }

        }
        
        if(frames == 725){
            addObject(player, 500, 700);
            player.ignoreGround = true;
        }
        
        if(frames > 725 && player.getY() > 300){
            player.setLocation(player.getX(), player.getY() - 3);
        }
        
        if(frames == 900){
            addObject(new Dialogue(12), 500, 450);
        }
        
        if(frames == 1050){
            addObject(new StaticImage(12, Color.BLACK), 500, 300);
        }
        
        if(frames >= 1305){
            Greenfoot.setWorld(new Credits());
        }
        
        
    }
    

}