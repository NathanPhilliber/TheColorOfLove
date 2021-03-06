import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class RedWorld extends World
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

    int sign1Offset = 20;
    int sign2Offset = 55;
    
    int generatedTerrain = 0;

    public RedWorld()
    {    

        super(1000, 600, 1, false); 
        setPaintOrder(ParticleEffect.class, StaticImage.class, Button.class,Enemy.class, Dialogue.class,  Player.class, Platform.class, MovableObject.class, Scenery.class);

        prepare();
        //addObject(new StaticImage(2), getWidth()/2, getHeight()/2);
    }

    public void act(){
        frames++;

        //spawn bubbles
        if(Greenfoot.getRandomNumber(200) == 0 && generatedTerrain > 5){
            generatedTerrain--;
            addObject(new Bubble('u'), Greenfoot.getRandomNumber(getWidth()), -10);
        }
        if(Greenfoot.getRandomNumber(200) == 0 && generatedTerrain > 5){
            generatedTerrain--;
            addObject(new Bubble('l'), -10, Greenfoot.getRandomNumber(getHeight()));
        }
        if(Greenfoot.getRandomNumber(200) == 0 && generatedTerrain > 5){
            generatedTerrain--;
            addObject(new Bubble('r'), getWidth() + 10, Greenfoot.getRandomNumber(getHeight()));
        }
        
        if(sign1Offset < -60 && Greenfoot.getRandomNumber(175) == 0 && getObjects(Goop.class).size() < 3){

            addObject(new Goop(), Greenfoot.getRandomNumber(800) + 100, -100);
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

        if(prob >=60 && prob < 90){ //1 change
            gradient = 1;
        }
        else if(prob >= 90){ //2 change
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
            if(leftStack.size() > 0){
                //System.out.println("++++Loading Left");
                loadColumn(x);
                return;
            }
            y = leftMost.getY();
            leftMost = obj1;
        }else{
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
                    addObject(new FakePlatform(), x, obj1.getY()- i*100 - Greenfoot.getRandomNumber(4)*100);
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
        

        if(Greenfoot.getRandomNumber(13) == 0){
            Goop goop = new Goop();
            addObject(goop, x, obj1.getY() - height*100 - 200);

        }
        
        if(sign1Offset-- == 0){
            addObject(new Sign(4), x, obj1.getY() - height*100);
        }
        else if(sign2Offset-- == 0){
            addObject(new Sign(5), x, obj1.getY() - height*100);
        }
        else if(Greenfoot.getRandomNumber(5) == 0){
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
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Enemy){
                Enemy en = (Enemy)list.get(i);
                list.remove(i);
                list.add(en);
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

        addObject(new Player(), 260, 470);

        for(int i = 0; i < 12; i++){
            Platform obj = new Platform(1);

            if(i < 4){
                addObject(obj, i*100, 600);
            }
            int id = 0;
            if(i >= 4){
                id = 1;
            }
            obj = new Platform(id);
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
