import greenfoot.*; 


public class Credits extends World
{
    
    
    public Credits()
    {            
        super(1000, 600, 1); 
        addObject(new StaticImage(4), getWidth()/2, getHeight()/2);
        addObject(new Button(3), getWidth()/2, 520);
    }
}
