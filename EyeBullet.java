import greenfoot.*;  

public class EyeBullet extends MovableObject
{
    Player player;
    boolean dead = false;
    int touchFrames = 0;
    boolean autoTurn = true;
    public EyeBullet(Player player){
        super();
        this.player = player;
        turnTowards(player.getX(), player.getY());
    }

    public EyeBullet(Player player, boolean autoTurn){
        super();
        this.player = player;
        this.autoTurn = autoTurn;
    }

    public void firstTime(){
        super.firstTime();
        if(autoTurn)
            turnTowards(player.getX(), player.getY());
    }

    public void act() 
    {
        super.act();

        move(10);

        if(isOnscreen() == false || isTouching(Platform.class)){
            dead = true;
        }
        if(isTouching(Player.class)){
            touchFrames++;

            if(touchFrames >= 5){
                dead = true;
                player.addFillColor(-3);
            }
        }

        if(dead){
            world.removeObject(this);
        }
    }    
}
