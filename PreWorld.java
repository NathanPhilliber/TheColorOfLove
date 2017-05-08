import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class PreWorld extends World
{

    GameObject rightMost;
    GameObject leftMost;

    int heightRight = 3;
    int targetHeightRight = heightRight;

    int heightLeft = 3;
    int targetHeightLeft = heightRight;
    int frames = -1;

    ArrayDeque<ArrayList<GameObject>> leftStack = new ArrayDeque<ArrayList<GameObject>>();
    ArrayDeque<ArrayList<GameObject>> rightStack = new ArrayDeque<ArrayList<GameObject>>();

    int generatedTerrain = 0;
    
    Player player;
    
    int moved = 0;
    int lastDisplay = 0;
    Dialogue text = null;
    
    public PreWorld()
    {    

        super(1000, 600, 1, false); 
        setPaintOrder(ParticleEffect.class, StaticImage.class, Dialogue.class, Button.class, Player.class, Platform.class, MovableObject.class, Scenery.class);

        prepare();
        addObject(new StaticImage(16, Color.RED), getWidth()/2, getHeight()/2);

    }

    public void act(){
        if(frames == 0){
            player.addFillColor(-25);
            player.hideCritical = true;
        }
        frames++;
        
        if(moved < 0 && frames - lastDisplay > 600){
            addObject(text = new Dialogue(16), 500, 90);
            lastDisplay = frames;
        }
        if(text != null && frames - lastDisplay > 210){
            removeObject(text);
            text = null;
            lastDisplay = frames;
            
        }
        
        //System.out.println(moved);
        //spawn bubbles
        
        if(player.getX() > 1000){
            Greenfoot.setWorld(new CutsceneC());
        }

        if(moved > 15){
            player.ignoreBorders = true;
        }
        if(player.getX() < 200){
            player.ignoreBorders = false;
        }
        
        //check right generation
        if(rightMost.getX() < getWidth() + 500){

            if(heightRight == targetHeightRight){
                targetHeightRight = Greenfoot.getRandomNumber(20)+3;
                //targetHeightLeft = 3;
            }

            if(rightStack.size() == 0){
                heightRight = getRandomHeight(heightRight, targetHeightRight);
            }
            else{
                heightRight = rightStack.peekFirst().size();
            }

            genColumn(rightMost.getX()+100, heightRight);

            if(frames > 10 && leftMost != null && leftMost.getX() < -600){
                removeEdgeColumn(false);
            }
        }

        //check left generation
        if(leftMost.getX() > -500){
            if(heightLeft == targetHeightLeft){
                targetHeightLeft = Greenfoot.getRandomNumber(20)+3;
                //targetHeightRight = 3;
            }

            if(leftStack.size() == 0){
                heightLeft = getRandomHeight(heightLeft, targetHeightLeft);
            }
            else{
                heightLeft = leftStack.peekFirst().size();
            }

            genColumn(leftMost.getX()-100, heightLeft);
            if(frames > 10 && rightMost != null && rightMost.getX() > getWidth() + 600){
                removeEdgeColumn(true);
            }
        }
    }

    //get a random height towards the target height
    public int getRandomHeight(int height, int targetHeight){
        int gradient = 0;
        int prob = Greenfoot.getRandomNumber(101);
        
        if(moved < 0){
           return Math.abs(moved + Greenfoot.getRandomNumber(5) + 8);

        }
        
        if(moved == 10){
            
            return 3;
        }
        else if(moved >= 11){
            return 5;
        }

        if(prob >=90 && prob < 97){ //1 change
            gradient = 1;
        }
        else if(prob >= 97){ //2 change
            gradient = 2;
        }

        if(targetHeight - height > 0){
            return height + gradient;
        }
        else{
            int delta = height - gradient;
            if(delta <= 0){
                delta = 1;
            }
            return delta;
        }

    }

    //load a column from appropriate stack
    public void loadColumn(int x){
        ArrayList<GameObject> list = null;
        GameObject anchor = null;
        //System.out.println("Loading");
        if(x < 0){
            list = leftStack.pop();
            anchor = leftMost;
            //System.out.println("Left Size: " + list.size());
            leftMost = list.get(0);
        }
        else{
            list = rightStack.pop();
            anchor = rightMost;
            //System.out.println("Right Size: " + list.size());
            rightMost = list.get(0);
        }

        for(int i = 0; i < list.size(); i++){
            addObject(list.get(i), x, anchor.getY() - i*100);
        }

    }
    //generate 1 column
    public void genColumn(int x, int height){
        generatedTerrain++;
        int y = 0;
        
        Platform obj1 = new Platform();
        if(x < 0){
            moved--;
            if(leftStack.size() > 0){
  
                //System.out.println("++++Loading Left");
                loadColumn(x);
                return;
            }
            y = leftMost.getY();
            leftMost = obj1;
        }else{
            moved++;
            if(rightStack.size() > 0){

                //System.out.println("++++Loading Right");                
                loadColumn(x);
                return;
            }
            y = rightMost.getY();
            rightMost = obj1;
        }

        addObject(obj1, x, y);
        for(int i = 1; i < height; i++){

            if(i == height - 1){
                Platform obj = new Platform(1);
                addObject(obj, x, obj1.getY() - i*100);
                if(Greenfoot.getRandomNumber(10) == 0){
                    addObject(new FakePlatform(), x, obj1.getY() - i*100 - Greenfoot.getRandomNumber(4)*100);
                }
            }
            else if(height - i > Greenfoot.getRandomNumber(3)+1 && i != 0){
                Platform obj = new Platform(2);
                addObject(obj, x, obj1.getY() - i*100);
            }
            else{
                Platform obj = new Platform();
                addObject(obj, x, obj1.getY() - i*100);

            }

        }
        
        if(moved == 3 && getObjects(Sign.class).size() == 0){
            addObject(new Sign(0), x, obj1.getY() - height*100);
        }
        else if(moved < 20 && Greenfoot.getRandomNumber(5) == 0){
            Flower flower = new Flower();
            addObject(flower, x, obj1.getY() - height*100);

        }        
    }

    public void removeEdgeColumn(boolean right){
        List<GameObject> all = getObjects(GameObject.class);
        ArrayList<GameObject> newList = new ArrayList<GameObject>();

        if(right){
            int max = all.get(0).getX();
            int yMax = all.get(0).getY();
            int index = 0;

            for(int i = 0; i < all.size(); i++){
                if(all.get(i).getX() > max && all.get(i) instanceof Platform){
                    max = all.get(i).getX();
                }
            }

            for(int i = 0; i < all.size(); i++){

                if(all.get(i).getX() == max - 100){
                    if(all.get(i).getY() > yMax){
                        yMax = all.get(i).getY();
                    }
                }
                //System.out.println("X:"+all.get(i).getX());
                if(all.get(i).getX() == max){
                    newList.add(all.get(i));
                    //System.out.println("Added: " + all.get(i).getY());
                    //removeObject(all.get(i));
                    all.set(i, null);
                }
            }
            sortList(newList);
            rightStack.addFirst(newList);
            for(int i = 0; i < all.size(); i++){
                if(all.get(i) != null && all.get(i).getY() == yMax && all.get(i).getX() == max - 100){
                    index = i;
                    break;
                }
            }

            rightMost = all.get(index);

        }

        else{
            int min = all.get(0).getX();
            int yMax = all.get(0).getY();
            int index = 0;

            for(int i = 0; i < all.size(); i++){
                if(all.get(i).getX() < min && all.get(i) instanceof Platform){
                    min = all.get(i).getX();
                }
            }
            //System.out.println(all.size() + " elements");
            for(int i = 0; i < all.size(); i++){

                if(all.get(i).getX() == min + 100){
                    if(all.get(i).getY() > yMax){
                        yMax = all.get(i).getY();
                    }
                }
                //System.out.println("X:"+all.get(i).getX());
                if(all.get(i).getX() == min){
                    newList.add(all.get(i));
                    //System.out.println("Added: " + all.get(i).getY());
                    //removeObject(all.get(i));
                    all.set(i, null);
                }
            }
            sortList(newList);
            leftStack.addFirst(newList);
            for(int i = 0; i < all.size(); i++){
                if(all.get(i) != null && all.get(i).getY() == yMax && all.get(i).getX() == min + 100){
                    index = i;
                    break;
                }
            }
            leftMost = all.get(index);            
        }

    }

    private void sortList(ArrayList<GameObject> list){

        //System.out.println("Sorting list of size " + list.size());

        for(int i = 0; i < list.size(); i++){
            for(int j = i; j < list.size(); j++){
                if(list.get(j).getY() > list.get(i).getY()){
                    //System.out.println("Swapping " + list.get(i).getY() + " and " + list.get(j).getY());
                    GameObject temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                    j = i;
                }
            }
        }
        //System.out.println("===");
        //removeObject(list.get(0));
        //list.remove(0);
        for(int i = 0; i < list.size(); i++){
            //System.out.println("::" +list.get(i).getY());
            removeObject(list.get(i));
        }

        //System.out.println("Added list of size " + list.size());

    }
    

    private void prepare()
    {

        addObject(player = new Player(), 260, 470);
        
        for(int i = 0; i < 6; i++){
            Platform obj = new Platform(1);

            
            addObject(obj, i*100, 600);
            
            obj = new Platform();
            addObject(obj, i*100, 700);

            obj = new Platform();
            addObject(obj, i*100, 800);
            if(i==0){
                leftMost = obj;
            }
            rightMost = obj;
        }

        List<GameObject> objs = getObjects(GameObject.class);
        
        for(int i = 0; i < objs.size(); i++){
            GameObject cur = objs.get(i);
            cur.setLocation(cur.getX()+50, cur.getY() - 200);                
        }

    }
}
