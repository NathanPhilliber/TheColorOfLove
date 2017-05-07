import greenfoot.*;
import java.util.List;

public class Goop extends Enemy
{

    GreenfootImage idle = new GreenfootImage("images/Goop_0.png");
    GreenfootImage jumping = new GreenfootImage("images/Goop_1.png");
    GreenfootSound deathSound = new GreenfootSound("sounds/death_0.wav");
    Player target;
    boolean isJumping = false;
    public Color color;
    boolean dead = false;
    boolean madeAppear = false;
    int playerReward = 20;

    boolean docile = false;

    public Goop(Color color){
        super();
        this.color = color;
        actorHeight = 70;
        showDropParticles = false;
        showCollisionPoints = false;

        colorImage(idle, color);
        colorImage(jumping, color);

        setImage(idle);

        if(color.equals(Color.BLUE)){
            playerReward = 17;
        }
    }

    public Goop(){
        this(new Color (255, 80, 80));
    }

    public void firstTime(){
        super.firstTime();

        List<Player> objs = getWorld().getObjects(Player.class);
        target = objs.get(0);
    }

    int off = 0;

    public void act() 
    {
        super.act();

        if(target.isDead == false){
            if(isOnscreenX()){
                if(docile == false){
                    off = 0;
                    madeAppear = true;
                    if(yVel == 0 && Greenfoot.getRandomNumber((Math.abs(target.getX() - getX())+1) / 10 + 1) == 0){
                        yVel = -20;
                        if(Math.abs(target.getX() - getX()) < 200){
                            yVel += 10;
                        }
                        if(Math.abs(target.getX() - getX()) < 100){
                            yVel += 5;
                        }
                        jump();
                    }

                    if(isJumping && yVel == 0){
                        isJumping = false;
                        setImage(idle);
                        xVel = 0;
                    }
                }
                Heart touched = null;

                if((touched = (Heart)getOneIntersectingObject(Heart.class)) != null){

                    target.addFillColor(playerReward);
                    
                    touched.pop();
                    deathSound.play();
                    for(int i = 0; i < 10; i++){
                        world.addObject(new ParticleEffect(0), getX() + Greenfoot.getRandomNumber(50)-25, getY() + Greenfoot.getRandomNumber(50)-25);
                    }
                    dead = true;
                }

            }
            else{

                if(madeAppear){
                    off++;
                    if(off > 60){
                        dead = true;
                    }
                    yVel = -20;
                    jump();
                }
            }
        }
        else if(isJumping == false){
            if(docile == false){
                setImage(jumping);
                isJumping = true;
                xVel = Math.signum( getX() - target.getX()) * 3;
                yVel = -20;
            }
        }

        if(dead){
            world.removeObject(this);
        }

        else{ //cutscene object

        }
    }  

    public void jump(){
        setImage(jumping);
        isJumping = true;
        xVel = Math.signum(target.getX() - getX()) * 3;
    }
}
