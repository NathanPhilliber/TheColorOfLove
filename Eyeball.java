import greenfoot.*; 
import java.util.List;

public class Eyeball extends Enemy
{

    public Player player;
    int frames = 0;
    int shots = 0;
    int nextPhase;
    boolean goSweep = false;
    int sweepDir = 0;
    GreenfootSound fire = new GreenfootSound("sounds/eyefire.mp3");
    GreenfootSound moveSound = new GreenfootSound("sounds/eyeballmove.mp3");
    GreenfootSound deathSound = new GreenfootSound("sounds/eyeballdeath.mp3");
    int playerReward = 25;
    
    public Eyeball(){
        super();
        nextPhase = Greenfoot.getRandomNumber(300) + 180;

    }

    public void firstTime(){
        super.firstTime();
        List<Player> objs = world.getObjects(Player.class);
        player = objs.get(0);
        if(world instanceof BlueWorld){
            playerReward = 20;
        }
    }

    public void act() 
    {
        super.act();
        frames++;

        if(goSweep){
            setLocation(getX() + sweepDir*8, getY());
            if(getX() <= 100 || getX() >= 900){
                goSweep = false;
            }
        }
        else{
            checkPhase();
        }

        checkHit();
    }   

    public void checkPhase(){
        nextPhase--;
        if(nextPhase <= 0){
            nextPhase = Greenfoot.getRandomNumber(300) + 180;
            sweepDir = (int)Math.signum(player.getX() - getX());
            goSweep = true;
            moveSound.play();
        }
    }

    public void checkHit(){
        Heart heart = (Heart)getOneObjectAtOffset(0, 0, Heart.class);
        if(heart != null){
            heart.pop();
            deathSound.play();
            world.removeObject(this);
            player.addFillColor(playerReward);
        }
    }

    public void updateFalling(){ //override gravity
        if(getY() > 390){
            setLocation(getX(), getY() - 1);
        }
        else if(getY() < 100){
            setLocation(getX(), getY() + 1);
        }
        else{
            setLocation(getX(), getY() + (int)(Math.sin(frames/15)*2));

            if(goSweep){
                if(frames % 12 == 0){
                    world.addObject(new EyeBullet(player), getX(), getY());
                    fire.play();
                }
            }
            else{

                if(frames % 7 == 0){
                    shots++;
                    if(shots <= 10){
                        world.addObject(new EyeBullet(player), getX(), getY());
                        fire.play();
                    }
                    else if (shots > 50){
                        shots  = 0;
                    }

                }
            }

        }

        Eyeball other = (Eyeball)getOneIntersectingObject(Eyeball.class);
        if(other != null){
            setLocation(getX(), getY() + (int)Math.signum(getY() - other.getY()));
        }

        if(Math.abs(getX() - player.getX()) > 500 || Greenfoot.getRandomNumber(7) == 0){
            setLocation(getX() + 5*(int)Math.signum(player.getX() - getX()), getY());
            if(isOnscreenX() == false){
                setLocation(getX() + 4*(int)Math.signum(player.getX() - getX()), getY());
            }
        }
    }
}
