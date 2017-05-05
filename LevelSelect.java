import greenfoot.*;  

public class LevelSelect extends World
{


    public LevelSelect()
    {    
       
        super(1000, 600, 1); 
        
        for(int i = 0; i < 5; i++){
            addObject(new Button(i+4), 500, 80 + i*80);
        }
        addObject(new Button(3), 500, 550);
    }
}
