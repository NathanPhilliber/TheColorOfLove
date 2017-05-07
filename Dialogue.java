import greenfoot.*;  

public class Dialogue extends UIObject
{

    boolean start = true;
    int x = 0;
    int width = 0;
    GreenfootImage org;
    GreenfootSound sound0, sound1, sound2;
    int sound = 0;
    int soundDelay = 0;
    public boolean lines2 = false;

    public Dialogue(int id){
        super();

        sideScroll = false;
        setImage(new GreenfootImage("images/dialog_" + id + ".png"));
        org = new GreenfootImage("images/dialog_" + id + ".png");
        getImage().setColor(Color.BLACK);
        width = getImage().getWidth();
        getImage().fillRect(0, 0, width, org.getHeight());

        if(id == 3 || id ==4|| id ==6|| id ==8|| id ==9|| id ==10|| id ==11){
            sound0 = new GreenfootSound("sounds/text_1.mp3");
            sound1= new GreenfootSound("sounds/text_1.mp3");
            sound2 = new GreenfootSound("sounds/text_1.mp3");
        }
        else if(id == 13){
            sound0 = new GreenfootSound("sounds/text_2.mp3");
            sound1= new GreenfootSound("sounds/text_2.mp3");
            sound2 = new GreenfootSound("sounds/text_2.mp3");
        }
        else{
            sound0 = new GreenfootSound("sounds/text_0.mp3");
            sound1= new GreenfootSound("sounds/text_0.mp3");
            sound2 = new GreenfootSound("sounds/text_0.mp3");
        }

    }

    public void startReveal(){
        start = true;
    }
    int x2 = 0;
    int pauseCounter = 30;
    public void act() 
    {
        super.act();

        if(lines2 == false){
            if(start && x < width){
                soundDelay++;
                if(getY() > 0){
                    if(sound == 0 && soundDelay >= 4){
                        sound++;
                        sound0.play();
                        soundDelay = 0;
                    }
                    else if(sound == 1 && soundDelay >= 4){
                        sound++;
                        sound1.play();
                        soundDelay = 0;
                    }
                    else if(soundDelay >= 4){
                        sound = 0;
                        sound2.play();
                        soundDelay =0;
                    }
                }

                setImage(new GreenfootImage(org));
                getImage().setColor(Color.BLACK);

                if(width - 5 == x){
                    x = width - 1;
                }

                getImage().fillRect(x += 5, 0, width, org.getHeight());

            }
        }
        else{

            if(start && x < width){
                soundDelay++;
                if(getY() > 0){
                    if(sound == 0 && soundDelay >= 4){
                        sound++;
                        sound0.play();
                        soundDelay = 0;
                    }
                    else if(sound == 1 && soundDelay >= 4){
                        sound++;
                        sound1.play();
                        soundDelay = 0;
                    }
                    else if(soundDelay >= 4){
                        sound = 0;
                        sound2.play();
                        soundDelay =0;
                    }
                }

                setImage(new GreenfootImage(org));
                getImage().setColor(Color.BLACK);

                if(width - 5 == x){
                    x = width - 1;
                }

                getImage().fillRect(x += 5, 0, width, org.getHeight()/2);
                getImage().fillRect(0, org.getHeight()/2, width, org.getHeight()/2 + 1);

            }
            else if(pauseCounter-- == 0){

            }
            else if(start && x < width*2 && pauseCounter < 0){

                soundDelay++;
                if(getY() > 0){
                    if(sound == 0 && soundDelay >= 4){
                        sound++;
                        sound0.play();
                        soundDelay = 0;
                    }
                    else if(sound == 1 && soundDelay >= 4){
                        sound++;
                        sound1.play();
                        soundDelay = 0;
                    }
                    else if(soundDelay >= 4){
                        sound = 0;
                        sound2.play();
                        soundDelay =0;
                    }
                }
                setImage(new GreenfootImage(org));
                getImage().setColor(Color.BLACK);

                if(width - 5 == x2){
                    x2 = width - 1;
                }

                x+= 5;

                getImage().fillRect(x2 += 5, org.getHeight()/2, width, org.getHeight()/2 +1 );

            }

        }
    }    
}
