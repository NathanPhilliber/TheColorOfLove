import greenfoot.*;
import java.util.List;

public class GameObject extends Actor
{
    
    private boolean first = true;    
    public boolean useCollision = false;
    
    public boolean showHitboxDebug = false;
    public World world;
    
    public boolean sideScroll = true;
    
    public GameObject(){        
    }
    
    public void firstTime(){                        
        world = getWorld();
    }
    
    public void act() 
    {
        super.act();
        if(first){
            first = false;
            firstTime();
        }
        
        if(showHitboxDebug){
            if(getImage().getColorAt(0,0).getRed() < 100){
                getImage().setColor(new Color(200,50,50,110));
                getImage().fill();
                getImage().drawRect(0,0,getImage().getWidth(), getImage().getHeight());
            }
        }
        
    }   
    
    public void colorImage(GreenfootImage img, Color color){
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                Color src = img.getColorAt(x, y);
                if(src.getRed() > 10 || src.getGreen() > 10 || src.getBlue() > 10){
                        //Color nC = new Color(color.getRed(), color.getGreen(), color.getBlue(), src.getAlpha());
                        img.setColorAt(x, y, color);
                    }
            }
        }
    }
    
    public GameObject getGameObjectAtOffset(int dx, int dy){
        List<GameObject> objs = getObjectsAtOffset(dx, dy, GameObject.class);
        for(int i = 0; i < objs.size(); i++){
            if(!(objs.get(i) instanceof Scenery) && objs.get(i).useCollision){
                return objs.get(i);
            }
        }
        return null;
    }
    
    public boolean isOnscreen(){
        
        if(getX() > -100 && getX() < world.getWidth()+100 && getY() > -100 && getY() < world.getHeight()+100){
            return true;
        }
        return false;
    }
    
    public boolean isOnscreenX(){
        
        if(getX() > -100 && getX() < world.getWidth()+100){
            return true;
        }
        return false;
    }
}
