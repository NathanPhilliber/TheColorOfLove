import greenfoot.*;  
import java.util.List;

public class FinalBoss extends Enemy
{

    int frames = 0;
    Player player;
    int colorState = 0; //red
    int counter = 0;
    int attackState = 0;
    int counter2 = 0;

    boolean hittable = false;
    boolean damaged = false;

    GreenfootSound fire = new GreenfootSound("sounds/eyefire.mp3");
    GreenfootSound fire2 = new GreenfootSound("sounds/Boss_6.mp3");
    GreenfootSound roar = new GreenfootSound("sounds/roar.mp3");
    GreenfootSound rejected = new GreenfootSound("sounds/Boss_7.mp3");
    //GreenfootSound music = new GreenfootSound("sounds/apr.mp3");

    public FinalBoss(Player player){
        super();
        this.player = player;
        colorImage(getImage(), Color.RED);
    }

    int deg = 0;

    public void act() 
    {
        super.act();

        if(frames == 0){
            fire.setVolume(40);
            //music.playLoop();
        }

        frames++;

        if(frames < 200){
            Heart heart = (Heart)getOneIntersectingObject(Heart.class);
            if(heart != null){
                heart.turn(180);
                rejected.play();
            }
            
            return;
        }

        if(damaged){
            Heart heart = (Heart)getOneIntersectingObject(Heart.class);
            if(heart != null){
                heart.turn(180);

            }

            if(counter == 2){
                roar.play();
            }
            counter++;
            List<GameObject> objs = world.getObjects(GameObject.class);

            for(int i = 0; i < objs.size(); i++){
                GameObject cur = objs.get(i);
                cur.setLocation(cur.getX() - (int)(10*Math.sin(frames)), cur.getY() + (int)(10*Math.sin(frames)));                
            }

            if(counter > 80){
                counter = 0;
                damaged = false;

                if(colorState == 1){
                    colorImage(getImage(), Color.ORANGE);
                }
                else if(colorState == 2){
                    colorImage(getImage(), Color.GREEN);
                }
                else if(colorState == 3){
                    colorImage(getImage(), Color.BLUE);
                }
                else if(colorState == 4){
                    colorImage(getImage(), Color.MAGENTA);
                }

            }
        }
        else if(colorState == 0){ //RED
            setLocation(getX(), getY() + (int)(Math.sin(frames/12)*2));

            if(attackState != 2){
                Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                if(heart != null){
                    heart.turn(180);
                    rejected.play();
                }
            }

            if(attackState == 0){
                if(frames % 5 == 0){
                    fire.play();
                    EyeBullet bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(deg +=5);
                    colorImage(bul.getImage(), new Color(255, 80, 80));

                    bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(180 + deg);
                    colorImage(bul.getImage(), new Color(255, 80, 80));     

                    counter++;

                    if(counter >= 100){
                        attackState++;
                    }
                }
            }
            else if (attackState == 1){
                colorImage(getImage(), Color.WHITE);
                hittable = true;
                attackState++;
                counter = 0;
            }
            else if(attackState == 2){

                if(isTouching(Heart.class)){
                    colorState++;
                    counter = 0;
                    attackState = 0;
                    hittable = false;
                    //play sound
                    damaged = true;

                    Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                    if(heart != null){
                        heart.pop();
                    }
                }

                counter++;
                if(counter > 120){
                    counter = 0;
                    attackState = 0;
                    hittable = false;
                    colorImage(getImage(), Color.RED);
                }
            }
        }
        else if(colorState == 1){ //ORANGE

            if(attackState != 3){
                Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                if(heart != null){
                    heart.turn(180);
                    rejected.play();
                }
            }

            if(attackState == 0){
                setLocation(getX(), getY() + 4);
                counter++;
                if(counter > 60){
                    attackState++;
                }

            }

            else if(attackState == 1){ //pause
                counter++;
                if(counter > 30){
                    counter = 0;
                    attackState++;
                }
            }
            else if(attackState == 2){
                if(counter < 120){
                    setLocation(getX(), getY() - 1);
                }
                if(frames % 4 == 0){
                    fire.play();
                    EyeBullet bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    colorImage(bul.getImage(), new Color(255, 200, 80));

                    bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(180);
                    colorImage(bul.getImage(), new Color(255, 200, 80));     

                    counter++;

                    if(counter >= 140){
                        attackState++;
                        counter= 0;
                        hittable = true;
                        colorImage(getImage(), Color.WHITE);
                    }
                }

            }
            else if (attackState == 3){

                if(isTouching(Heart.class)){
                    colorState++;
                    counter = 0;
                    attackState = 0;
                    hittable = false;
                    //play sound
                    damaged = true;
                    Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                    if(heart != null){
                        heart.pop();
                    }
                }
                counter++;
                if(counter > 135){
                    counter = -60;
                    attackState = 0;
                    hittable = false;
                    colorImage(getImage(), Color.ORANGE);
                }
            }
        }
        else if(colorState == 2){ //GREEN

            if(attackState != 2 || attackState != 4){
                Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                if(heart != null){
                    heart.turn(180);
                    rejected.play();
                }
            }

            if(attackState == 0){ //move down
                setLocation(getX(), getY() + 1);

                counter++;
                if(counter > 180){
                    counter = 0;
                    attackState++;
                }
            }
            else if(attackState == 1){ //clockwise
                counter++;
                setLocation(getX() + (int)(Math.cos(counter/10.0)*2 * counter/10)/10, getY() + (int)(Math.sin(counter/10.0)*2 * counter/10)/10);
                if(frames % 5 == 0){
                    fire.play();
                    EyeBullet bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(70);
                    colorImage(bul.getImage(), new Color(255, 200, 80));

                    bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(250);
                    colorImage(bul.getImage(), new Color(255, 200, 80));  
                }
                if(counter > 600){
                    attackState++;
                    hittable = true;
                    colorImage(getImage(), Color.WHITE);
                }
            }
            else if(attackState == 2){  // pause and wait for heart

                if(isTouching(Heart.class)){
                    colorState++;
                    counter = 0;
                    counter2 = 0;
                    attackState = 0;
                    hittable = false;
                    //play sound
                    damaged = true;
                    Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                    if(heart != null){
                        heart.pop();
                    }
                }

                counter2++;

                if(counter2 > 120){
                    counter2 = 0;
                    attackState++;
                    hittable = false;
                    colorImage(getImage(), Color.GREEN);
                }
            }
            else if(attackState == 3){ //counterclockwise
                if(frames % 4 == 0){
                    fire.play();
                    EyeBullet bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(-70);
                    colorImage(bul.getImage(), new Color(255, 200, 80));

                    bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(110);
                    colorImage(bul.getImage(), new Color(255, 200, 80));  
                }

                counter--;
                setLocation(getX() - (int)(Math.cos(counter/10.0)*2 * counter/10)/10, getY() - (int)(Math.sin(counter/10.0)*2 * counter/10)/10);

                if(counter <= 0){
                    attackState++;
                    hittable = true;
                    colorImage(getImage(), Color.WHITE);
                }
            }
            else if(attackState == 4){  // pause

                if(isTouching(Heart.class)){
                    colorState++;
                    counter = 0;
                    counter2 = 0;
                    attackState = 0;
                    hittable = false;
                    //play sound
                    damaged = true;
                    Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                    if(heart != null){
                        heart.pop();
                    }
                }

                counter2++;

                if(counter2 > 120){
                    counter2 = 0;
                    attackState = 1;
                    hittable = false;
                    colorImage(getImage(), Color.GREEN);
                }
            }
        }
        else if(colorState == 3){ //BLUE

            if(attackState != 1){
                Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                if(heart != null){
                    heart.turn(180);
                    rejected.play();
                }
            }

            setLocation(getX(), getY() + (int)(Math.sin(frames/12)*2));
            if(attackState == 0){
                counter++;

                if(counter % 140 == 0){
                    fire2.play();
                    for(int i = 0; i < 360; i += 5){
                        EyeBullet bul = new EyeBullet(player, false);
                        world.addObject(bul, getX(), getY()-40);
                        bul.turn(i);
                        colorImage(bul.getImage(), new Color(80, 80, 255));
                    }
                }
                if(counter > 420){
                    attackState++;
                    counter = 0;
                    colorImage(getImage(), Color.WHITE);
                }
            }
            else if(attackState == 1){
                if(isTouching(Heart.class)){
                    colorState++;
                    counter = 0;
                    counter2 = 1;
                    attackState = 0;
                    hittable = false;
                    //play sound
                    damaged = true;
                    Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                    if(heart != null){
                        heart.pop();
                    }
                }

                counter++;

                if(counter > 130){
                    counter2 = 0;
                    attackState = 0;
                    hittable = false;
                    colorImage(getImage(), Color.BLUE);
                }
            }

        }

        else if(colorState == 4){ //PURPLE

            if(attackState != 1){
                Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                if(heart != null){
                    heart.turn(180);
                    rejected.play();
                }
            }

            if(attackState == 0){
                counter++;
                setLocation(getX() - 2*counter2, getY() + (int)(Math.sin(frames/12)*2));
                if(frames % 4 == 0){
                    fire.play();
                    EyeBullet bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(90);
                    colorImage(bul.getImage(), new Color(200, 80, 200));

                    bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(0);
                    colorImage(bul.getImage(), new Color(200, 80, 200));

                    bul = new EyeBullet(player, false);
                    world.addObject(bul, getX(), getY()-40);
                    bul.turn(180);
                    colorImage(bul.getImage(), new Color(200, 80, 200));
                }
                if(counter > 180){
                    counter = 0;
                    attackState++;
                    hittable = true;
                    colorImage(getImage(), Color.WHITE);
                }
            }
            else if(attackState == 1){

                setLocation(getX(), getY() + (int)(Math.sin(frames/12)*2));

                if(isTouching(Heart.class)){
                    colorState++;
                    counter = 0;
                    counter2 = 0;
                    attackState = 0;
                    hittable = false;
                    //play sound
                    damaged = true;
                    Heart heart = (Heart)getOneIntersectingObject(Heart.class);
                    if(heart != null){
                        heart.pop();
                    }
                }

                counter++;

                if(counter > 100){
                    counter = -180;
                    counter2 *= -1;
                    attackState = 0;
                    hittable = false;
                    colorImage(getImage(), Color.MAGENTA);
                }
            }

        }
        else if(colorState == 5){
            counter++;
            getImage().setTransparency(256 - counter);
            if(counter == 1){
                world.addObject(new StaticImage(12, Color.WHITE), 500, 300);//fade

                ((PurpleWorld)world).fadeMusic = true;
            }

            if(counter > 255){
                Greenfoot.setWorld(new CutsceneG());
            }
        }

    }    

    public void updateFalling(){
    }
}
