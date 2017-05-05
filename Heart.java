import greenfoot.*;

public class Heart extends MovableObject{    
    int framesD = 1;
    int charge;
    int frames = 0;

    boolean dead = false;

    GreenfootSound kiss0Sound = new GreenfootSound("sounds/Kiss_0.wav");
    GreenfootSound kiss1Sound = new GreenfootSound("sounds/Kiss_1.wav");
    GreenfootSound kiss2Sound = new GreenfootSound("sounds/Kiss_2.wav");
    GreenfootSound kiss3Sound = new GreenfootSound("sounds/Kiss_3.wav");

    public Heart(char dir, int charge, double vel){
        this(dir, charge);
        //yVel = vel;
        yVel = 0;
        xVel = 0;
    }

    public void firstTime(){
        super.firstTime();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null && mouse.getX() < getX()){
            //xVel = -5;

            getImage().mirrorVertically();
            //System.out.println('l');
        }
        if(mouse != null){
            turnTowards(mouse.getX(), mouse.getY());
        }
    }

    public Heart(char dir, int charge){
        super();
        this.charge = charge;

        if(charge > 150){
            setImage(new GreenfootImage("images/heart_3.png"));
            colorImage(getImage(), new Color(255, 25, 25));
            kiss3Sound.play();
        }
        else if(charge > 100){
            setImage(new GreenfootImage("images/heart_2.png"));
            colorImage(getImage(), new Color(255, 75, 75));
            kiss2Sound.play();
        }
        else if(charge > 50){
            setImage(new GreenfootImage("images/heart_1.png"));
            colorImage(getImage(), new Color(255, 150, 150));
            kiss0Sound.play();
        }
        else{
            setImage(new GreenfootImage("images/heart_0.png"));
            colorImage(getImage(), new Color(255, 220, 220));
            kiss1Sound.play();
        }

    }

    public void pop(){
        if(!dead){
            if(charge >= 100){
                Heart heart = new Heart('l', 10);
                heart.xVel = 2;
                heart.yVel = 2;
                world.addObject(heart, getX(), getY());

                heart = new Heart('l', 10);
                heart.xVel = -2;
                heart.yVel = 2;
                world.addObject(heart, getX(), getY());

                heart = new Heart('l', 10);
                heart.xVel = 2;
                heart.yVel = -2;
                world.addObject(heart, getX(), getY());

                heart = new Heart('l', 10);
                heart.xVel = -2;
                heart.yVel = -2;
                world.addObject(heart, getX(), getY());

                //
                if(charge > 100){
                    heart = new Heart('l', 10);
                    heart.xVel = 0;
                    heart.yVel = 5;
                    world.addObject(heart, getX(), getY());

                    heart = new Heart('l', 10);
                    heart.xVel = 5;
                    heart.yVel = 0;
                    world.addObject(heart, getX(), getY());

                    heart = new Heart('l', 10);
                    heart.xVel = -5;
                    heart.yVel = 0;
                    world.addObject(heart, getX(), getY());

                    heart = new Heart('l', 10);
                    heart.xVel = 0;
                    heart.yVel = -5;
                    world.addObject(heart, getX(), getY());
                }
            }
        }

        dead = true;
    }

    public void act(){
        super.act();
        framesD++;
        frames++;
        move(charge/5);
        if(framesD % 10 == 0){
            framesD = 1;
            xVel += Math.signum(xVel);
        }
        
        if(isTouching(Platform.class)){
            dead = true;
        }
        

        if(!isOnscreen()){
            dead = true;
        }

        if(dead){
            world.removeObject(this);
        }
    }
}
