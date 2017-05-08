import greenfoot.*;
import java.util.List;

public class Player extends GravityObject
{

    public int rightBorder = 600;
    public int leftBorder = 400;

    public int topBorder = 200;
    public int bottomBorder = 400;

    public double horizontalMovement = 4;
    public double jumpSpeed = 19;

    protected int frames = 0;
    private int walkingFrameNum = 0;
    private boolean walkingAnimationInOrder = true;
    private GreenfootImage[] walkingRight = new GreenfootImage[5];
    private GreenfootImage[] walkingLeft = new GreenfootImage[5];
    private GreenfootImage[] walkingRightSwing = new GreenfootImage[5];
    private GreenfootImage[] walkingLeftSwing = new GreenfootImage[5];
    private GreenfootImage jumpImageRight = new GreenfootImage("images/player_jump.png");
    private GreenfootImage jumpImageLeft = new GreenfootImage("images/player_jump.png");

    private GreenfootImage[] swingingRight = new GreenfootImage[4];
    private GreenfootImage[] swingingLeft = new GreenfootImage[4];

    private GreenfootImage[] idleRight = new GreenfootImage[4];
    private GreenfootImage[] idleLeft = new GreenfootImage[4];

    private GreenfootImage eyesClosedImgRight = new GreenfootImage("images/player_eyesclosed.png");
    private GreenfootImage eyesClosedImgLeft = new GreenfootImage("images/player_eyesclosed.png");

    private GreenfootImage playerFellRight = new GreenfootImage("images/player_fell.png");
    private GreenfootImage playerFellLeft = new GreenfootImage(playerFellRight);

    private GreenfootImage[] allImgs = new GreenfootImage[42];

    public boolean isWalking = false;
    public boolean isWalkingRight = true;
    protected boolean isSwinging = false;
    protected int swingFrame = 0;
    protected int swingCharge = 0;
    protected boolean shiftHeldDown = false;
    protected boolean fireShotsOnClick = true;

    private boolean isBlinking = false;
    private int blinkingFrames = 0;

    protected boolean isIdling = false;
    int idleFrames = 0;
    private int idleDelay = 0;
    private int hitGroundFrames = 0;

    public int curPaintHeight = 0;
    public int targetPaintHeight = 0;
    public Color paintColor = new Color(255, 10, 10);
    public Color originalColor = new Color(210,210,210);

    int jumpNum = 0;
    GreenfootSound jump0Sound = new GreenfootSound("sounds/Jump_0.mp3");
    GreenfootSound jump1Sound = new GreenfootSound("sounds/Jump_1.mp3");

    int hurtDelay = 0;
    int hurtNum = 0;
    GreenfootSound hurt0Sound = new GreenfootSound("sounds/Hurt_0.mp3");
    GreenfootSound hurt1Sound = new GreenfootSound("sounds/Hurt_1.mp3");

    GreenfootSound ding0Sound = new GreenfootSound("sounds/ding_0.mp3");
    GreenfootSound ding1Sound = new GreenfootSound("sounds/ding_1.mp3");
    GreenfootSound ding2Sound = new GreenfootSound("sounds/ding_2.wav");
    GreenfootSound ding3Sound = new GreenfootSound("sounds/ding_3.wav");

    public boolean ignoreBorders = false;

    GreenfootSound walk0 = new GreenfootSound("sounds/Walk_0.mp3");
    GreenfootSound walk1 = new GreenfootSound("sounds/Walk_1.mp3");

    GreenfootSound heal0 = new GreenfootSound("sounds/heal_0.mp3");

    GreenfootSound warning = new GreenfootSound("sounds/warning.mp3");
    GreenfootSound deathSound = new GreenfootSound("sounds/deathsound.mp3");

    Button continueButton = new Button(2);
    boolean buttonOut = false;

    public int paintId = 0;
    StaticImage critical = new StaticImage(18);

    public FinalBoss boss;

    public boolean isDead = false;
    public boolean isContinuing = false;
    boolean criticalUp = true;
    public int deadFrames = 0;
    public boolean hideCritical = false;

    public Player(){
        super();
        int index = 0;
        actorHeight = 160;

        eyesClosedImgLeft.mirrorHorizontally();

        walkingRight[0] = new GreenfootImage("images/player_4.png");
        walkingRight[1] = new GreenfootImage("images/player_3.png");
        walkingRight[2] = new GreenfootImage("images/player_0.png");
        walkingRight[3] = new GreenfootImage("images/player_1.png");
        walkingRight[4] = new GreenfootImage("images/player_2.png");

        walkingRightSwing[0] = new GreenfootImage("images/playerswingwalk_4.png");
        walkingRightSwing[1] = new GreenfootImage("images/playerswingwalk_3.png");
        walkingRightSwing[2] = new GreenfootImage("images/playerswingwalk_0.png");
        walkingRightSwing[3] = new GreenfootImage("images/playerswingwalk_1.png");
        walkingRightSwing[4] = new GreenfootImage("images/playerswingwalk_2.png");

        playerFellLeft.mirrorHorizontally();

        for(int i = 1; i < idleRight.length-1; i++){
            idleRight[i] = new GreenfootImage("player_idle_"+(i-1)+".png");
            idleLeft[i] = new GreenfootImage(idleRight[i]);
            idleLeft[i].mirrorHorizontally();
        }

        idleRight[3] = idleRight[1];
        idleLeft[3] = idleLeft[1];

        for(int i = 0; i < swingingRight.length; i++){
            swingingRight[i] = new GreenfootImage("images/player_swing_" + i + ".png");
            (swingingLeft[i] = new GreenfootImage(swingingRight[i])).mirrorHorizontally();
        }

        for(int i = 0; i < walkingRight.length; i++){
            (walkingLeft[i] = new GreenfootImage(walkingRight[i])).mirrorHorizontally();
            (walkingLeftSwing[i] = new GreenfootImage(walkingRightSwing[i])).mirrorHorizontally();
        }

        jumpImageLeft.mirrorHorizontally();
        idleRight[0] = walkingRight[2];
        idleLeft[0] = walkingLeft[2];

        for(int i = 0; i < walkingRight.length; i++){
            allImgs[i] = walkingRight[i];
            allImgs[i+walkingRight.length] = walkingLeft[i];
            index += 2;
        }

        for(int i = 0; i < walkingRightSwing.length; i++){
            allImgs[i+index] = walkingRightSwing[i];
            allImgs[i+index+walkingRightSwing.length] = walkingLeftSwing[i];
        }
        index += walkingRightSwing.length*2;

        allImgs[index++] = jumpImageRight;
        allImgs[index++] = jumpImageLeft;

        for(int i = 0; i < swingingRight.length; i++){
            allImgs[i+index] = swingingRight[i];
            allImgs[i+index+swingingRight.length] = swingingLeft[i];
        }
        index += swingingRight.length*2;

        for(int i = 0; i < idleRight.length; i++){
            allImgs[i+index] = idleRight[i];
            allImgs[i+index+idleRight.length] = idleLeft[i];
        }
        index += idleRight.length*2;

        allImgs[index++] = eyesClosedImgRight;
        allImgs[index++] = eyesClosedImgLeft;  

        allImgs[index++] = playerFellRight;
        allImgs[index++] = playerFellLeft;

        int heightTemp = 1;

        for(int i = 0; i < allImgs.length; i++){
            //System.out.println(i);
            colorImage(allImgs[i], originalColor);
        }

        addFillColor(25, false);

    }

    public void updatePaintHeight(){
        //System.out.println(curPaintHeight);
        if(frames % 1 == 0){

            if(curPaintHeight < targetPaintHeight && curPaintHeight < getImage().getHeight()){
                //updateSpriteReferences(++curPaintHeight);
                paintSprites(++curPaintHeight, paintColor, true);
            }
            else if(curPaintHeight > targetPaintHeight && curPaintHeight > 0){
                //updateSpriteReferences(--curPaintHeight);
                paintSprites(--curPaintHeight, paintColor, false);
            }

        }
    }

    public void addFillColor(int delta){
        addFillColor(delta, true);
    }

    public void checkDead(){
        if(yVel > 100 && !isDead){
            isDead = true;
            deathSound.play();
            world.addObject(new StaticImage(12, Color.BLACK), 500, 300);

        }

        if (curPaintHeight <= 0 && !isDead && !hideCritical){
            if(criticalUp && critical.getImage().getTransparency() == 0){
                warning.play();

            }
            if(criticalUp && critical.getImage().getTransparency() < 250){
                critical.getImage().setTransparency(critical.getImage().getTransparency() + 6);

            }            
            else if( criticalUp == false && critical.getImage().getTransparency() > 5){
                critical.getImage().setTransparency(critical.getImage().getTransparency() - 6);
            }
            else{
                criticalUp = !criticalUp;                
            }

            if(health <= 0){
                //System.out.println("DEAD");
                isDead = true;
                deathSound.play();
                world.addObject(new StaticImage(12, Color.BLACK), 500, 300);
            }
        }
        else if(curPaintHeight < 20 && !isDead && !hideCritical){
            critical.getImage().setTransparency(0);
        }

        if(isDead){
            deadFrames++;

            if(deadFrames >= 255 && isContinuing == false){
                if(world instanceof RedWorld){
                    Greenfoot.setWorld(new RedWorld());
                }
                if(world instanceof OrangeWorld){
                    Greenfoot.setWorld(new OrangeWorld(((OrangeWorld)world).music));
                }
                if(world instanceof GreenWorld){
                    Greenfoot.setWorld(new GreenWorld(((GreenWorld)world).music));
                }
                if(world instanceof BlueWorld){
                    Greenfoot.setWorld(new BlueWorld());
                }
                if(world instanceof PurpleWorld){
                    Greenfoot.setWorld(new PurpleWorld(((PurpleWorld)world).music));
                }
            }
        }
    }

    int health = 50;
    public void addFillColor(int delta, boolean showParticle){
        //System.out.println("D" + delta + " T" + targetPaintHeight + " H"+health);
        if(showParticle){
            if(delta < 0){                
                if(hurtDelay++ >= 5){
                    hurtDelay = 0;
                    if(hurtNum == 0){
                        hurt0Sound.play();
                        hurtNum = 1;
                    }
                    else{
                        hurt1Sound.play();
                        hurtNum = 0;
                    }
                }

                world.addObject(new ParticleEffect(0), getX()+Greenfoot.getRandomNumber(90)-45, getY()+Greenfoot.getRandomNumber(90)-45);
            }   
            else if(delta > 0){
                heal0.play();
                world.addObject(new StaticImage(3), getX()+Greenfoot.getRandomNumber(90)-45, getY()+Greenfoot.getRandomNumber(90)-45);
            }
        }

        if(health < 50){
            if(delta > 0){
                delta += 2;
            }
            health += delta;
            delta = 0;
        }

        targetPaintHeight += delta;
        if(targetPaintHeight < 0){
            health += targetPaintHeight;
            targetPaintHeight = 0;
            if(paintId == 0)
                paintColor = new Color(255, 10, 10);
            else if(paintId == 1)
                paintColor = Color.ORANGE;
            else if(paintId == 2)
                paintColor = Color.GREEN;
            else if(paintId == 3)
                paintColor = Color.BLUE;
            else if(paintId == 4)
                paintColor = Color.MAGENTA;
        }
        else if(targetPaintHeight > 156){
            targetPaintHeight = 156;
            if(paintId == 0)               
                paintColor = new Color(255, 166, 166);
            else if(paintId == 1)
                paintColor = Color.ORANGE;
            else if(paintId == 2)
                paintColor = Color.GREEN;
            else if(paintId == 3)
                paintColor = Color.BLUE;
            else if(paintId == 4)
                paintColor = Color.MAGENTA;

            if(buttonOut == false && paintId != 4){
                continueButton.sideScroll = false;
                world.addObject(continueButton, 500, 100);
                buttonOut = true;
            }
        }

        if(paintId == 0)
            paintColor = new Color(255, paintColor.getGreen() + delta, paintColor.getBlue() + delta);
        else if(paintId == 1){
            paintColor = Color.ORANGE;
        }
        else if(paintId == 2){
            paintColor = Color.GREEN;
        }
        else if(paintId == 3)
            paintColor = Color.BLUE;
        else if(paintId == 4)
            paintColor = Color.MAGENTA;

    }

    public void moveWorldLeft(){

        List<GameObject> objs = world.getObjects(GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            if(cur != this && cur.sideScroll){
                cur.setLocation(cur.getX() - (int)horizontalMovement, cur.getY());
            }
        }

    }

    public void moveWorldRight(){
        List<GameObject> objs = world.getObjects(GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            if(cur != this && cur.sideScroll){
                cur.setLocation(cur.getX() + (int)horizontalMovement, cur.getY());
            }
        }

    }

    public void moveWorldUp(){
        List<GameObject> objs = world.getObjects(GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            if(cur != this && cur.sideScroll){
                cur.setLocation(cur.getX(), cur.getY() + Math.abs((int)yVel));
            }
        }
    }

    public void moveWorldVertical(int delta){
        List<GameObject> objs = world.getObjects(GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            if(cur.sideScroll){
                cur.setLocation(cur.getX(), cur.getY() + delta);
            }
        }
    }

    public void moveWorldHorizontal(int delta){
        List<GameObject> objs = world.getObjects(GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            if(cur.sideScroll){
                cur.setLocation(cur.getX() + delta, cur.getY());
            }
        }
    }

    public void moveWorldDown(){

        List<GameObject> objs = world.getObjects(GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            if(cur != this && cur.sideScroll){
                cur.setLocation(cur.getX(), cur.getY() - Math.abs((int)yVel));
            }
        }
    }

    @Override
    public void firstTime(){
        super.firstTime();
        world.addObject(critical, 500, 200);
        colorImage(critical.getImage(), new Color(255, 10, 10));
        critical.getImage().setTransparency(0);
    }

    int playerStillY = 0;
    boolean goDownExtra = true;
    public void act() 
    {
        super.act();

        if(!isDead){
            updateMovement();
            advanceAnimation();

            if(Greenfoot.isKeyDown("i")){
                if(frames % 2 == 0 && Greenfoot.isKeyDown("j")){
                    addFillColor(1);
                }
                if(frames % 2 == 0 && Greenfoot.isKeyDown("l")){
                    addFillColor(-1);
                }
            }

            updatePaintHeight();

            if(!isDead && !isContinuing && (getOneObjectAtOffset(-20,35, Goop.class) != null || getOneObjectAtOffset(20,35, Goop.class) != null)){
                addFillColor(-1);
            }

            smartCameraMove();
        }
        checkDead();
    }

    public void smartCameraMove(){
        if(goDownExtra && ignoreBorders == false){
            if(isWalkingRight && getY() > 250 && getOneObjectAtOffset(200, 100, Platform.class) == null){
                if(getY() > 200 && getOneObjectAtOffset(400, 200, Platform.class) == null){
                    moveWorldVertical(-2);
                }
                else{
                    moveWorldVertical(-1);
                }

            }
            else if(!isWalkingRight && getY() > 250 && getOneObjectAtOffset(-200, 100, Platform.class) == null){
                if(getY() > 200 && getOneObjectAtOffset(-400, 200, Platform.class) == null){
                    moveWorldVertical(-2);
                }
                else{
                    moveWorldVertical(-1);
                }
            }
        }
        else if(ignoreBorders == false){
            if(yVel == 0 && getY() > 100 && getOneObjectAtOffset(150, 100, Platform.class) == null && getOneObjectAtOffset(-150, 100, Platform.class) == null){

                moveWorldVertical(-1);

            }
            if(isWalkingRight && getX() > 250 && getY() < boss.getY() && getX() < boss.getX()){
                moveWorldHorizontal(-1);
            }
            else if(!isWalkingRight && getX() < 750 && getY() < boss.getY() && getX() > boss.getX()){
                moveWorldHorizontal(1);
            }
        }
    }

    public void paintSprites(int height, Color color, boolean up){

        for(int i = 0; i < allImgs.length; i++){
            GreenfootImage cur = allImgs[i];

            int dHeight = cur.getHeight() - height;
            if(dHeight < 0){
                dHeight = 0;
            }

            if(up){
                for(int x = 0; x < cur.getWidth(); x++){
                    if(cur.getColorAt(x, dHeight).getRed() > 10 || cur.getColorAt(x, dHeight).getGreen() > 10 || cur.getColorAt(x, dHeight).getBlue() > 10){
                        cur.setColorAt(x, dHeight, color);
                    }
                }
            }
            else{
                for(int x = 0; x < cur.getWidth(); x++){
                    if(cur.getColorAt(x, dHeight-1).getRed() > 10|| cur.getColorAt(x, dHeight-1).getGreen() > 10 || cur.getColorAt(x, dHeight-1).getBlue() > 10){
                        cur.setColorAt(x, dHeight-1, originalColor);
                    }

                }
            }

        }
    }

    boolean soundToggle = true;
    int soundDelay = 0;

    public void movePlayerLeft(){
        againstRightWall = false;

        soundDelay++;

        if(soundToggle && soundDelay > 10 && standingOn != null){
            soundToggle=false;
            walk0.play();
            soundDelay = 0;
        }
        else if(soundDelay > 10&& standingOn != null){
            soundToggle = true;
            walk1.play();
            soundDelay = 0;
        }

        if(!ignoreBorders && getX() <= leftBorder){
            xVel = 0;
            moveWorldRight();
        }
        else{
            xVel = -horizontalMovement;

        }

        isWalking = true;

        if(isWalkingRight && !isSwinging){
            setImage(walkingRight[2]);
            //setLocation(getX() - 40, getY());
        }
        isWalkingRight = false;
    }

    public void movePlayerRight(){
        againstLeftWall = false;
        soundDelay++;

        if(soundToggle && soundDelay > 10 && standingOn != null){
            soundToggle=false;
            walk0.play();
            soundDelay = 0;
        }
        else if(soundDelay > 10&& standingOn != null){
            soundToggle = true;
            walk1.play();
            soundDelay = 0;
        }
        if(!ignoreBorders && getX() >= rightBorder){
            xVel = 0;
            moveWorldLeft();
        }
        else{
            xVel = horizontalMovement;

        }
        isWalking = true;

        if(isWalkingRight == false){
            setImage(walkingLeft[2]); 
            //setLocation(getX() + 40, getY());
        }
        isWalkingRight = true;
    }

    public void stopPlayerWalking(){
        xVel = 0;
        isWalking = false;
    }

    public void playerJump(){
        yVel = -jumpSpeed;

        if(jumpNum == 0){
            jumpNum = 1;
            jump0Sound.play();
        }
        else{
            jumpNum = 0;
            jump1Sound.play();
        }

        if(isWalkingRight){
            int deltaRot = (int)((yVel / 16)*45);
            world.addObject(new WindParticle('r', deltaRot), getX()-50, getY() + Greenfoot.getRandomNumber(50) - 25);
        }
        else{
            int deltaRot = (int)(-(yVel / 16)*45);
            world.addObject(new WindParticle('l', deltaRot), getX()+50, getY() + Greenfoot.getRandomNumber(50) - 25);
        }
    }

    public void updateCameraVertical(){
        if(yVel != 0){
            if(getY() > bottomBorder){
                moveWorldDown();
                setLocation(getX(), getY() - Math.abs((int)yVel));
            }

            if(getY() < topBorder){
                moveWorldUp();
                setLocation(getX(), getY() + Math.abs((int)yVel));
            }
        }
    }

    public void checkShiftFire(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        //if(Greenfoot.isKeyDown("shift")){

        if(isSwinging){
            //System.out.println("charging" + Greenfoot.getRandomNumber(99));
            if(frames % 22 == 0){
                addFillColor(-1, false);
            }
        }

        if(mouse != null && mouse.getButton() == 1 && !isSwinging){
            //System.out.println("charging" + Greenfoot.getRandomNumber(99));
            isSwinging = true;
            shiftHeldDown = true;

        }
        else if(mouse != null && mouse.getButton() == 1 && isSwinging && Greenfoot.mouseClicked(null)){
            shiftHeldDown = false;
            //swingCharge = 0;
        }

        if(swingCharge != 0){
            if(swingCharge == 7){
                StaticImage img = new StaticImage(5);
                img.player = this;
                ding0Sound.play();
                img.delX = -21;
                img.delY = -95;
                world.addObject(img, getX() - 21, getY() - 95);
            }
            else if(swingCharge == 50){
                StaticImage img = new StaticImage(5);
                ding1Sound.play();
                img.player = this;
                img.delX = -7;
                img.delY = -95;
                world.addObject(img, getX() - 7, getY() - 95);
            }
            else if(swingCharge == 100){
                StaticImage img = new StaticImage(5);
                ding2Sound.play();
                img.player = this;
                img.delX = 7;
                img.delY = -95;
                world.addObject(img, getX() + 7, getY() - 95);
            }
            else if(swingCharge == 150){
                StaticImage img = new StaticImage(5);
                ding3Sound.play();
                img.player = this;
                img.delX = 21;
                img.delY = -95;
                world.addObject(img, getX() + 21, getY() - 95);
            }
        }
    }

    public void updateMovement(){

        //System.out.println("A: " + Greenfoot.isKeyDown("a") + ", D: " + Greenfoot.isKeyDown("d") + "\nLeft: " + Greenfoot.isKeyDown("left") + ", right: " + Greenfoot.isKeyDown("right"));
        if((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) && (canMoveX || isWalkingRight) && !againstLeftWall){
            movePlayerLeft();

        }
        else if((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) && (canMoveX || !isWalkingRight) && !againstRightWall){

            movePlayerRight();

        }
        else{
            stopPlayerWalking();
        }

        if((Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))){
            if(yVel == 0 && standingOn != null && !wallStuck){
                playerJump();    
            }
        }

        updateCameraVertical();

        checkShiftFire();
    }

    private void advanceAnimation(){
        //System.out.println(isWalking);
        frames++;
        if(isSwinging){
            
            isIdling = false;
            isBlinking = false;
            idleDelay = 0;
            idleFrames = 0;
            blinkingFrames = 0;

            swingCharge++;
            
            if(isWalking){
                if(frames % 3 == 0){
                    if(walkingAnimationInOrder){ //go through array forwards
                        if(isWalkingRight){
                            setImage(walkingRightSwing[++walkingFrameNum]); 
                        }
                        else{
                            setImage(walkingLeftSwing[++walkingFrameNum]); 
                        }
                    }
                    else{ //Go through array backwards
                        if(isWalkingRight){
                            setImage(walkingRightSwing[--walkingFrameNum]); 
                        }
                        else{
                            setImage(walkingLeftSwing[--walkingFrameNum]); 
                        }
                    }

                    if(walkingFrameNum == 0){
                        walkingAnimationInOrder = true;
                    }
                    else if(walkingFrameNum == walkingRightSwing.length - 1){
                        walkingAnimationInOrder = false;
                    }
                }
            }
            //System.out.println(swingFrame);
            if(frames % 3 == 0){
                if(swingFrame >= swingingRight.length){
                    isSwinging = false;
                    swingFrame = 0;
                }
                else{

                    if(shiftHeldDown){
                        if(swingFrame < 2 && !isWalking){
                            if(isWalkingRight){

                                setImage(swingingRight[swingFrame++]);
                            }
                            else{

                                setImage(swingingLeft[swingFrame++]);
                            }
                        }
                        else if(swingFrame < 2){
                            swingFrame++;
                        }
                    }
                    else{
                        if(isWalkingRight){
                            if(swingFrame == 2){
                                if(fireShotsOnClick){
                                    addHeart('r', swingCharge,0,0, yVel/2, getX() + 20, getY());
                                }
                                List<StaticImage> swirls = world.getObjects(StaticImage.class);
                                for(int i = 0; i < swirls.size(); i++){
                                    if(swirls.get(i).id == 5){
                                        world.removeObject(swirls.get(i));
                                    }
                                }
                                if(swingCharge > 50){
                                    world.addObject(new SwordWave(this), getX() + 45, getY());
                                }
                                swingCharge = 0;
                            }
                            if(!isWalking){
                                setImage(swingingRight[swingFrame++]);
                            }
                            else{
                                swingFrame++;
                            }
                        }
                        else{
                            if(swingFrame == 2){
                                if(fireShotsOnClick){
                                    addHeart('l', swingCharge,0,0, yVel/2, getX() - 20, getY());
                                }
                                List<StaticImage> swirls = world.getObjects(StaticImage.class);
                                for(int i = 0; i < swirls.size(); i++){
                                    if(swirls.get(i).id == 5){
                                        world.removeObject(swirls.get(i));
                                    }
                                }
                                if(swingCharge > 50){
                                    world.addObject(new SwordWave(this), getX() - 45, getY());
                                }
                                swingCharge = 0;
                            }
                            if(!isWalking){
                                setImage(swingingLeft[swingFrame++]);
                            }
                            else{
                                swingFrame++;
                            }
                        }

                    }

                }
            }
            else if(shiftHeldDown && !isWalking){
                if(swingFrame >= swingingRight.length){
                    swingFrame = swingingRight.length -1;
                }
                if(isWalkingRight){
                    setImage(swingingRight[swingFrame]);
                }
                else{
                    setImage(swingingLeft[swingFrame]);
                }
            }
        }
        else if(hitGround){
            if(isWalkingRight){
                setImage(playerFellRight);
            }
            else{
                setImage(playerFellLeft);
            }

            hitGroundFrames++;
            if(hitGroundFrames >= 7){
                hitGroundFrames = 0;
                hitGround = false;
            }
        }
        else if(yVel < 0){
            isIdling = false;
            isBlinking = false;
            idleDelay = 0;
            idleFrames = 0;
            blinkingFrames = 0;
            if(isWalkingRight){
                setImage(jumpImageRight);
            }
            else{
                setImage(jumpImageLeft);
            }
        }
        else if(isWalking){

            isIdling = false;
            isBlinking = false;
            idleDelay = 0;
            idleFrames = 0;
            blinkingFrames = 0;

            if(frames % 3 == 0){
                if(walkingAnimationInOrder){ //go through array forwards
                    if(isWalkingRight){
                        setImage(walkingRight[++walkingFrameNum]); 
                    }
                    else{
                        setImage(walkingLeft[++walkingFrameNum]); 
                    }
                }
                else{ //Go through array backwards
                    if(isWalkingRight){
                        setImage(walkingRight[--walkingFrameNum]); 
                    }
                    else{
                        setImage(walkingLeft[--walkingFrameNum]); 
                    }
                }

                if(walkingFrameNum == 0){
                    walkingAnimationInOrder = true;
                }
                else if(walkingFrameNum == walkingRight.length - 1){
                    walkingAnimationInOrder = false;
                }
            }
        }
        else{
            walkingFrameNum = 2;

            if(isIdling == false && Greenfoot.getRandomNumber(100) == 0){
                isBlinking = true;
            }
            if(isBlinking == false && Greenfoot.getRandomNumber(100) == 0){
                isIdling = true;
            }

            if(isWalkingRight){

                if(isBlinking){
                    setImage(eyesClosedImgRight);
                    blinkingFrames++;
                    if(blinkingFrames > 7){
                        blinkingFrames = 0;
                        isBlinking = false;
                    }
                }
                else if(isIdling){
                    if(frames % 5 == 0){
                        if(idleFrames == 3){
                            idleDelay++;
                            if(idleDelay > 20){
                                idleDelay = 0;
                                setImage(idleRight[idleFrames++]);
                            }
                            else{
                                setImage(idleRight[idleFrames-1]);
                            }
                        }
                        else{
                            setImage(idleRight[idleFrames++]);
                        }

                        if(idleFrames >= idleRight.length){
                            isIdling = false;
                            idleFrames = 0;
                        }
                    }

                }
                else{
                    setImage(walkingRight[2]); 
                }

            }
            else{
                if(isBlinking){
                    setImage(eyesClosedImgLeft);
                    blinkingFrames++;
                    if(blinkingFrames > 7){
                        blinkingFrames = 0;
                        isBlinking = false;
                    }
                }
                else if(isIdling){

                    if(frames % 5 == 0){
                        if(idleFrames == 3){
                            idleDelay++;
                            if(idleDelay > 20){
                                idleDelay = 0;
                                setImage(idleLeft[idleFrames++]);
                            }
                            else{
                                setImage(idleLeft[idleFrames-1]);
                            }
                        }
                        else{
                            setImage(idleLeft[idleFrames++]);
                        }
                        if(idleFrames >= idleLeft.length){
                            isIdling = false;
                            idleFrames = 0;
                        }
                    }

                }
                else{
                    setImage(walkingLeft[2]); 
                }
            }
        }
    }

    protected void addHeart(char side, int charge, int xTar, int yTar, double vel, int x, int y){
        Heart heart;
        if(xTar != 0 && yTar != 0){

            heart = new Heart(side, charge);
            heart.useMouse = false;
            world.addObject(heart, x, y);

            heart.turnTowards(xTar, yTar);
            heart.turn(-105); //lazy
            heart.xVel = 0;
            heart.yVel = 0;
        }
        else{
            heart = new Heart(side, charge, vel);   
            world.addObject(heart, x, y);
        }

    }
}
