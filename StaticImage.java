import greenfoot.*;  

public class StaticImage extends UIObject
{

    public int id;
    int frames = 0;
    Color color = Color.RED;
    public Player player = null;
    public int delX, delY;
    int touchTimer = 0;

    
    //16, color = fade out
    
    public StaticImage(int id, Color color){ //fade in
        super();
        this.id = id;

        if(id == 6){
            sideScroll = false;
            GreenfootImage image = new  GreenfootImage(1000, 600);
            image.setColor(color);
            image.fill();
            setImage(image);
            this.color = color;
        }

        if(id == 10){
            setImage(new GreenfootImage("images/player_0.png"));
            colorImage(getImage(), color);
            getImage().setTransparency(0);
        }

        if(id == 12){
            sideScroll = false;
            GreenfootImage image = new  GreenfootImage(1000, 600);
            image.setColor(color);
            image.fill();
            setImage(image);
            this.color = color;
            getImage().setTransparency(0);
        }

        if(id == 16){
            sideScroll = false;
            GreenfootImage image = new  GreenfootImage(1000, 600);
            image.setColor(color);
            image.fill();
            setImage(image);
            this.color = color;
            //getImage().setTransparency(0);
        }
    }

    public StaticImage(int id){
        super();
        this.id = id;

        if(id == 0){
            setImage(new GreenfootImage("images/ControlsTutorial.png"));
        }
        else if(id == 1 || id == 3){
            setImage(new GreenfootImage("images/MiniHeart.png"));

            if(id == 1){
                colorImage(getImage(), Color.RED);
            }
            else{
                colorImage(getImage(), Color.WHITE);
                color = Color.WHITE;
            }
        }
        else if(id == 2){
            sideScroll = false;
            GreenfootImage image = new  GreenfootImage(1000, 600);
            image.setColor(Color.RED);
            image.fill();
            setImage(image);

        }
        else if(id == 4){
            setImage(new GreenfootImage("images/CreditsImage.png"));
        }
        else if(id == 5){
            sideScroll = false;
            setImage(new GreenfootImage("images/swirl_0.png"));
        }
        else if(id == 7){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter1Card.png"));
            getImage().setTransparency(0);
        }
        else if(id == 8){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter1SubtitleCard.png"));
            getImage().setTransparency(0);
        }
        else if(id == 9){
            setImage(new GreenfootImage("images/BossBall_0.png"));

        }
        else if(id == 11){
            setImage(new GreenfootImage("images/player_sad.png"));

        }

        else if(id == 13){
            setImage(new GreenfootImage("images/ghost.png"));

        }

        else if(id == 14){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter2Card.png"));
            getImage().setTransparency(0);
        }
        else if(id == 15){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter2SubtitleCard.png"));
            getImage().setTransparency(0);
        }
        else if(id == 17){

            setImage(new GreenfootImage("images/sandtop.png"));

        }
        else if(id == 18){
            sideScroll = false;
            setImage(new GreenfootImage("images/criticalhealth.png"));

        }
        else if(id == 19){
            
            setImage(new GreenfootImage("images/cactus_"+Greenfoot.getRandomNumber(2)+".png"));

        }
        else if(id == 20){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter3Card.png"));
            getImage().setTransparency(0);
        }
        else if(id == 21){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter3SubtitleCard.png"));
            getImage().setTransparency(0);
        }
        else if(id == 22){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter4Card.png"));
            getImage().setTransparency(0);
        }
        else if(id == 23){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter4SubtitleCard.png"));
            getImage().setTransparency(0);
        }
        else if(id == 24){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter5Card.png"));
            getImage().setTransparency(0);
        }
        else if(id == 25){
            sideScroll = false;
            setImage(new GreenfootImage("images/Chapter5SubtitleCard.png"));
            getImage().setTransparency(0);
        }

    }

    public StaticImage(){
        this(0);
    }

    
    public void act() 
    {
        super.act();
        frames++;

        if(id == 1 || id == 3){
            setLocation(getX(), getY() - 1);

            if(id == 1){
                colorImage(getImage(), (color = new Color(color.getRed()-5, color.getGreen(), color.getBlue())));
            }
            else{
                colorImage(getImage(), (color = new Color(color.getRed()-5, color.getGreen()-5, color.getBlue()-5)));
            }

            if(frames > 50){
                world.removeObject(this);
            }
        }
        else if(id == 2){
            if(frames > 20){
                //colorImage(getImage(), new Color(255,0,0,255-frames*10));
                GreenfootImage img = new GreenfootImage(world.getWidth(), world.getHeight());
                img.setColor(new Color(255,0,0,255-(frames-20)*2));
                img.fill();
                //img.fillRect(0,0,world.getWidth(), world.getHeight());
                setImage(img);
                if(255-(frames-20)*2 <= 5){
                    world.removeObject(this);
                }
            }
        }
        else if(id == 5){
            setLocation(player.getX() + delX, player.getY() + delY);
        }
        else if(id == 9){
            move(3);
            turnTowards(player.getX(), player.getY());
            if(isTouching(Player.class)){
                touchTimer++;
                if(touchTimer >= 30){
                    world.removeObject(this);
                }
            }
        }

        else if(id == 12){
            if(getImage().getTransparency() < 255){
                getImage().setTransparency(getImage().getTransparency() + 1);
            }
        }
        else if(id == 16){

            sideScroll = false;
            if(getImage().getTransparency() > 0){
                getImage().setTransparency(getImage().getTransparency() - 1);
            }
            else{
                world.removeObject(this);
            }

        }

    }    
}
