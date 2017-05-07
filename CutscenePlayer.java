import greenfoot.*; 

public class CutscenePlayer extends Player
{

    public boolean goRight = false;
    public boolean goLeft = false;
    public boolean goJump = false;

    int redHeight = 155;
    int orangeHeight = 130;
    int yellowHeight = 104;
    int greenheight = 78;
    int blueHeight = 52;
    int purpleHeight = 26;

    public boolean isWhite = false;

    public CutscenePlayer(){
        super();
        targetPaintHeight = 0;
        ignoreBorders = true;
        hideCritical = true;
        fireShotsOnClick = false;
    }

    public void act() 
    {
        super.act();

        if(!isWhite){
            if(redHeight >= 156){
                redHeight = 0;
            }
            if(orangeHeight >= 156){
                orangeHeight = 0;
            }
            if(yellowHeight >= 156){
                yellowHeight = 0;
            }
            if(greenheight >= 156){
                greenheight = 0;
            }
            if(blueHeight >= 156){
                blueHeight = 0;
            }
            if(purpleHeight >= 156){
                purpleHeight = 0;
            }

            paintSprites(++redHeight, Color.RED, true);
            paintSprites(++orangeHeight, Color.ORANGE, true);
            paintSprites(++yellowHeight, Color.YELLOW, true);
            paintSprites(++greenheight, Color.GREEN, true);
            paintSprites(++blueHeight, Color.BLUE, true);
            paintSprites(++purpleHeight, Color.MAGENTA, true);
        }

        checkForShot();
    }  

    public void setWhite(){
        isWhite = true;

    }

    public void updateMovement(){

        if(goLeft && (canMoveX || isWalkingRight) && !againstLeftWall ){
            movePlayerLeft();
        }
        else if(goRight && (canMoveX || !isWalkingRight) && !againstRightWall ){
            movePlayerRight();
        }
        else{
            stopPlayerWalking();
        }

        if(goJump && yVel == 0 && standingOn != null && !wallStuck){
            playerJump();    
        }

        //checkShiftFire();
    }

    boolean shootNow = false;

    public void fireShot(int x, int y){
        shootNow = true;
        addHeart('r', 20, 5, x, y, getX() + 20, getY());
    }

    public void heal(int delta, Color color){
        for(int i = 0; i < delta; i++){
            paintSprites(i+1, color, true);
        }
    }

    public void checkForShot(){

        if(isSwinging){
            //System.out.println("charging" + Greenfoot.getRandomNumber(99));
            if(frames % 22 == 0){
                addFillColor(-1, false);
            }
        }

        if(shootNow && !isSwinging){

            isSwinging = true;
            shiftHeldDown = true;

        }
        else if(shootNow && isSwinging){
            shiftHeldDown = false;
            shootNow = false;
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

    
}
