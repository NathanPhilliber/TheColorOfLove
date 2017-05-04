import greenfoot.*; 

public class Button extends UIObject
{
    int id;
    GreenfootImage img;

    boolean updateImg = false;
    GreenfootSound ding = new GreenfootSound("sounds/Select.wav");
    boolean pressed = false;
    int framesAfter = 0;

    public Button(){
        this(0);
    }

    public Button(int id){
        super();

        this.id = id;

        if(id == 0){
            img = new GreenfootImage("images/PlayButton.png");
        }
        else if(id == 1){
            img = new GreenfootImage("images/CreditsButton.png");
        }
        else if(id == 2){
            img = new GreenfootImage("images/ContinueButton.png");
        }
        else if(id == 3){
            img = new GreenfootImage("images/BackButton.png");
        }

        setImage(img);
    }

    public void operate(){
        ding.play();
        if(id == 0){
            Greenfoot.setWorld(new Instructions());
        }
        else if(id == 1){
            Greenfoot.setWorld(new Credits());
        }
        else if(id == 2){
            if(pressed == false){
                pressed = true;
                if(world instanceof Instructions){
                    Greenfoot.setWorld(new CutsceneA());
                }
                else if(world instanceof RedWorld){
                    StaticImage fadeOut = new StaticImage(12, Color.RED);
                    world.addObject(fadeOut, 500, 300);
                }
                else if(world instanceof OrangeWorld){
                    StaticImage fadeOut = new StaticImage(12, Color.ORANGE);
                    world.addObject(fadeOut, 500, 300);
                    ((OrangeWorld)world).fadeMusic = true;
                }
            }

        }
        else if(id == 3){
            Greenfoot.setWorld(new TitleScreen());
        }
    }
    GreenfootSound musicFade = null;
    public void act() 
    {
        super.act();

        
        
        if(pressed){
            framesAfter++;

            if(framesAfter > 285){
                if(id == 2){
                    
                    if(world instanceof RedWorld){
                        Greenfoot.setWorld(new CutsceneC());
                    }
                    else if(world instanceof OrangeWorld){
                        Greenfoot.setWorld(new CutsceneD());
                    }
                }
            }
        }

        
        MouseInfo mouse = Greenfoot.getMouseInfo();

        if(mouse != null && mouse.getX() > getX() - img.getWidth()/2 && mouse.getX() < getX() + img.getWidth()/2 && mouse.getY() > getY() - img.getHeight()/2 && mouse.getY() < getY() + img.getHeight()/2){
            if(Greenfoot.mousePressed(null)){
                operate();
            }
            if(!updateImg){
                colorImage(getImage(), new Color(255, 100, 100));
                updateImg = true;
            }
        }
        else if(updateImg){
            colorImage(getImage(), Color.WHITE);
            updateImg = false;
        }
    }    
}
