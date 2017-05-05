import greenfoot.*;

public class GravityObject extends MovableObject
{

    public boolean falling = false;
    public int actorHeight = 10;
    public double gravity = .75;
    public GameObject standingOn = null;

    boolean showDropParticles = true;
    boolean jumped = false;

    int actorWidth = 36;
    boolean wallStuck = false;

    boolean canMoveX = true;

    int fallFrames = 0;

    boolean hitGround = false;

    boolean showCollisionPoints = false;
    boolean againstLeftWall = false;
    boolean againstRightWall = false;

    public boolean ignoreGround = false;

    public void act(){
        super.act();
        updateFalling();

    }

    public void updateFalling(){
        Actor wall = null;
        if(ignoreGround == false){
            standingOn = getGameObjectAtOffset(-actorWidth/2+10, actorHeight/2-3);
            if(standingOn == null){
                standingOn = getGameObjectAtOffset(-actorWidth/2+10, actorHeight/2-3);            
            }
            if(standingOn == null){
                standingOn = getGameObjectAtOffset(actorWidth/2-10, actorHeight/2-3);            
            }

            if(standingOn == null){
                fallFrames++;

                if(fallFrames > 5){
                    jumped = true;
                }
            }
            else{
                fallFrames = 0;
            }

            if(showCollisionPoints){
                getImage().setColorAt(getImage().getWidth()/2-actorWidth/2, getImage().getHeight()/2+actorHeight/2-3, Color.GREEN);
                //getImage().setColorAt(getImage().getWidth()/2-actorWidth+10, getImage().getHeight()/2+actorHeight/2-3, Color.GREEN);
                getImage().setColorAt(getImage().getWidth()/2+actorWidth-10, getImage().getHeight()/2+actorHeight/2-3, Color.GREEN);
                getImage().setColorAt(getImage().getWidth()/2 + actorWidth/2+5, getImage().getHeight()/2-actorHeight/2+5, Color.RED);
                getImage().setColorAt(getImage().getWidth()/2 - actorWidth/2-5, getImage().getHeight()/2-actorHeight/2+5, Color.RED);
                getImage().setColorAt(getImage().getWidth()/2 + actorWidth/2+15, getImage().getHeight()/2+actorHeight/2-25, Color.RED);
                getImage().setColorAt(getImage().getWidth()/2 - actorWidth/2-15, getImage().getHeight()/2+actorHeight/2-25, Color.RED);
            }

            boolean hitHead = false;
            boolean leftStuck = false;
            boolean rightStuck = false;

            wall = getGameObjectAtOffset(actorWidth/2+5, -actorHeight/2+5);

            if(wall != null){
                rightStuck = true;
                hitHead = true;
            }

            if(wall == null){
                wall = getGameObjectAtOffset(-actorWidth/2-5, -actorHeight/2+5);
                if(wall != null){
                    leftStuck = true;
                    hitHead = true;
                }
            }

            if(wall == null){
                wall = getGameObjectAtOffset(actorWidth/2+15, actorHeight/2-25);
                if(wall != null){
                    rightStuck = true;
                }
            }

            if(wall == null){
                wall = getGameObjectAtOffset(-actorWidth/2-15, actorHeight/2-25);
                if(wall != null){
                    leftStuck = true;
                }
            }

            if(wall != null && ((GameObject)wall).useCollision){
                canMoveX = false;

                if(hitHead && (standingOn == null || standingOn.useCollision == false)){
                    yVel = 5;

                }
                else if(leftStuck){
                    setLocation(getX() + 1, getY());
                    againstLeftWall = true;
                    wallStuck = true;

                }
                else if(rightStuck){
                    setLocation(getX() - 1, getY());
                    wallStuck = true;
                    againstRightWall = true;

                }

            }
            else{
                canMoveX = true;
                wallStuck = false;

            }
        }

        if((standingOn == null || standingOn.useCollision == false) && !ignoreGround){
            yVel += gravity;
            //againstLeftWall = false;
            //againstRightWall = false;
        }
        else if(yVel > 0){
            yVel = 0;

            if(jumped){
                jumped = false;
                if(showDropParticles){
                    getWorld().addObject(new JumpSmoke(), getX(), getY() + actorHeight/2 - 25);
                    hitGround = true;
                    againstLeftWall = false;
                    againstRightWall = false;
                }
            }
        }

        if(yVel < 0){
            jumped = true;
            againstLeftWall = false;
            againstRightWall = false;
        }

        if(yVel > 5){
            againstLeftWall = false;
            againstRightWall = false;

        }

        if((wall = getGameObjectAtOffset(0, actorHeight/2 - 3)) != null && ((GameObject)wall).useCollision){
            setLocation(getX(), getY() - 1);
            //againstLeftWall = false;
            //againstRightWall = false;
        }

        if(againstLeftWall && againstRightWall){
            againstLeftWall = false;
            againstRightWall = false;
        }

    }
}
