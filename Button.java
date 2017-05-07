import greenfoot.*; 
import java.util.List;
public class Button extends UIObject
{
    int id;
    GreenfootImage img;

    boolean updateImg = false;
    GreenfootSound ding = new GreenfootSound("sounds/Select.wav");
    GreenfootSound hover = new GreenfootSound("sounds/buttonhover.mp3");
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
        else if(id >= 4 && id <= 8){
            img = new GreenfootImage("images/colorbutton_"+(id-4)+".png");
        }
        else if(id == 9){
            img = new GreenfootImage("images/LevelSelect.png");
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
                
                List<Enemy> enemies = world.getObjects(Enemy.class);
                for(int i = 0; i < enemies.size(); i++){
                    enemies.get(i).stopAttacking = true;
                }
                
                List<Player> players = world.getObjects(Player.class);
                players.get(0).isContinuing = true;
                
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
                else if(world instanceof GreenWorld){
                    StaticImage fadeOut = new StaticImage(12, Color.GREEN);
                    world.addObject(fadeOut, 500, 300);
                    ((GreenWorld)world).fadeMusic = true;
                }
                else if(world instanceof BlueWorld){
                    StaticImage fadeOut = new StaticImage(12, Color.BLUE);
                    world.addObject(fadeOut, 500, 300);
                }
                else if(world instanceof PurpleWorld){
 

                }
                
            }

        }
        else if(id == 3){
            Greenfoot.setWorld(new TitleScreen());
        }
        else if(id == 4){
            Greenfoot.setWorld(new RedWorld());
        }
        else if(id == 5){
            Greenfoot.setWorld(new OrangeWorld());
        }
        else if(id == 6){
            Greenfoot.setWorld(new GreenWorld());
        }
        else if(id == 7){
            Greenfoot.setWorld(new BlueWorld());
        }
        else if(id == 8){
            Greenfoot.setWorld(new PurpleWorld());
        }
        else if(id == 9){
            Greenfoot.setWorld(new LevelSelect());
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
                        Greenfoot.setWorld(new CutsceneH());
                    }
                    else if(world instanceof OrangeWorld){
                        Greenfoot.setWorld(new CutsceneD());
                    }
                    else if(world instanceof GreenWorld){
                        Greenfoot.setWorld(new CutsceneE());
                    }
                    else if(world instanceof BlueWorld){
                        Greenfoot.setWorld(new CutsceneF());
                    }
                    else if(world instanceof PurpleWorld){
                        Greenfoot.setWorld(new CutsceneG());
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
                hover.play();
                updateImg = true;
            }
        }
        else if(updateImg){
            colorImage(getImage(), Color.WHITE);
            updateImg = false;
        }
    }    
}
