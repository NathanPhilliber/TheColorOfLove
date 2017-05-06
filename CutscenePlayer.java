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
    
    
    

    
}
