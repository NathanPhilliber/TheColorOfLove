import greenfoot.*;  


public class TitleScreen extends World
{

    int frames = 0;
    
    public TitleScreen()
    {    

        super(1000, 600, 1, false); 
        prepare();
    }

    private void prepare()
    {
        TitleCard titlecard = new TitleCard();
        addObject(titlecard,500,300);
        addObject(new TitleCardOutline(),500,300);
        
        
        addObject(new Button(0),500,430);
        addObject(new Button(1),500,520);
        
        
    }
    
    public void act(){
        frames++;
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(mouse != null && frames%10 == 0){
            addObject(new StaticImage(1), mouse.getX()+Greenfoot.getRandomNumber(60)-30, mouse.getY()+Greenfoot.getRandomNumber(60)-30);
        }
    }
}
