import greenfoot.*; 

public class Sign extends Scenery
{
    Dialogue text;
    int frames = 0;
    int lastSeen = 0;
    int id;
    public int lifetime = 250;
    
    
    public Sign(){
        this(0);
    }

    public Sign(int id){
        super();
        this.id = id;
        
        if(id == 0){
            text = new Dialogue(17);
        }
        else if(id == 1){
            text = new Dialogue(18);
        }
        else if(id == 2){
            lifetime = 300;
            text = new Dialogue(19);
        }
        else if(id == 3){
            text = new Dialogue(20);
            text.lines2 = true;
            lifetime = 450;
        }
        else if(id == 4){
            text = new Dialogue(21);
            text.lines2 = true;
            lifetime = 500;
        }
        else if(id == 5){
            text = new Dialogue(22);
        }
    }

    public void act() 
    {
        super.act();
        frames++;

        if((lastSeen == 0 || frames - lastSeen > 2000) && isTouching(Player.class)){
            world.addObject(text, 500, 75);
            lastSeen = frames;
        }
        else if(isTouching(Player.class)){
            text.setLocation(500, 75);
        }
        
        else if(frames - lastSeen > lifetime || getX() < -300 || getX() > 1300){
            text.setLocation(500, -300);
                
            
        }
    }    
}
