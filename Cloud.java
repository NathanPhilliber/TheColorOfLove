import greenfoot.*; 

public class Cloud extends Enemy
{

    GreenfootImage sprite;
    int frames = 0;
    Player player;
    int attackStage = 0;
    Heart heart;
    Color color;
    int playerReward = 20;
    
    public Cloud(Player player){
        super();
        this.player = player;
        sprite = new GreenfootImage("images/cloud_" + Greenfoot.getRandomNumber(3) + ".png");
        setImage(sprite);

    }
    
    public void firstTime(){
        super.firstTime();
        
        if(world instanceof BlueWorld){
            color = Color.BLUE;
            playerReward = 17;
        }
        else{
            color = Color.GREEN;
        }
    }
    
    boolean goRight = true;
    int fallDelay = -20;
    public void act() 
    {
        super.act();
        frames++;

        Cloud other = (Cloud)getOneIntersectingObject(Cloud.class);
        if(other != null){
            setLocation(getX(), getY() + (int)Math.signum(getY() - other.getY()));
        }

        if(attackStage == 0){
            if(Greenfoot.getRandomNumber(75) == 0){
                attackStage = 1;
            }
            if((heart = (Heart)getOneIntersectingObject(Heart.class)) != null){
                heart.pop();
                attackStage = 2;
                colorImage(getImage(), color);
            }
            setLocation(getX(), getY() + (int)(Math.sin(frames/12)*2));

            if(getX() > 900){
                setLocation(getX() - 5, getY());
            }
            else if(getX() < 100){
                setLocation(getX() + 5, getY());
            }

            if(getY() > 200){
                setLocation(getX(), getY() - 4);
            }
            else if(getY() < 0){
                setLocation(getX(), getY() + 4);
            }

            if(frames % 300 < 150 && frames % 7 == 0){
                world.addObject(new Rain(), getX() + Greenfoot.getRandomNumber(10)-5, getY() + 20);
            }

            if(frames % 300 > 150 && getY() > 50){
                setLocation(getX(), getY() - 2);
            }
            else if(frames % 300 < 150 && getY() < 150){
                setLocation(getX(), getY() + 1);
            }

            

        }
        else if(attackStage == 1){
            if(getX() > 900){
                setLocation(getX() - 5, getY());
            }
            else if(getX() < 100){
                setLocation(getX() + 5, getY());
            }

            if(getY() > 200){
                setLocation(getX(), getY() - 4);
            }
            else if(getY() < 0){
                setLocation(getX(), getY() + 4);
            }

            if(goRight){
                setLocation(getX() + 6, getY() + (int)(Math.sin(frames/12)*2));
                if(getX() >= 890){
                    goRight = false;
                }

            }
            else{
                setLocation(getX() - 6, getY() + (int)(Math.sin(frames/12)*2));
                if(getX() <= 110){
                    goRight = true;
                }

            }

            if(frames % 150 < 100 && frames % 7 == 0){
                world.addObject(new Rain(), getX() + Greenfoot.getRandomNumber(10)-5, getY() + 20);
            }

            if(Greenfoot.getRandomNumber(75) == 0){
                attackStage = 0;
            }
            
            if((heart = (Heart)getOneIntersectingObject(Heart.class)) != null){
                attackStage = 2;
                colorImage(getImage(), color);
                heart.pop();
            }

        }
        else if(attackStage == 2){
            fallDelay++;
            
            if(getOneObjectAtOffset(0, 75, Platform.class) == null){
                setLocation(getX(), getY() + 1);
            }

            
            
            if(fallDelay >= 200){
                colorImage(getImage(), Color.WHITE);
                fallDelay = -20;
                attackStage = 0;
            }
            if(fallDelay > -5 && (heart = (Heart)getOneIntersectingObject(Heart.class)) != null){
                heart.pop();
                player.addFillColor(playerReward);
                world.removeObject(this);
            }
        }


    }    

    public void updateFalling(){
    }
}
