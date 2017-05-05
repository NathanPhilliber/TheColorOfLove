import greenfoot.*;  

public class PurpleWorld extends World
{

    int frames = 0;
    Player player;
    GreenfootSound music;
    int vol = 100;
    public boolean fadeMusic = false;
    
    public PurpleWorld(){
        this(null);
    }

    public PurpleWorld(GreenfootSound music)
    {    
        super(1000, 600, 1, false); 

        this.music = music;

        setPaintOrder(ParticleEffect.class, StaticImage.class, Player.class, Platform.class, MovableObject.class, Scenery.class);

        prepare();
        addObject(new StaticImage(16, Color.MAGENTA), getWidth()/2, getHeight()/2);

    } 

    public void act(){
        frames++;

        if(frames == 1){
            if(music == null){
                music = new GreenfootSound("sounds/apr.mp3");
                music.playLoop();   //PUT THIS BAC
            }
            
        }
        
        if(fadeMusic && vol > 0){
            music.setVolume(vol--);
        }
        
        if(frames == 10){
            player.addFillColor(155);
        }
    }

    private void prepare()
    {
        Platform platform = new Platform();
        addObject(platform,350,369);
        platform.setLocation(250,426);
        Platform platform2 = new Platform();
        addObject(platform2,451,437);
        Platform platform3 = new Platform();
        addObject(platform3,619,392);
        platform2.setLocation(350,427);
        platform3.setLocation(451,428);
        Platform platform4 = new Platform();
        addObject(platform4,581,442);
        platform4.setLocation(553,429);
        Platform platform5 = new Platform();
        addObject(platform5,712,428);
        platform5.setLocation(654,430);
        Platform platform6 = new Platform();
        addObject(platform6,746,429);
        platform6.setLocation(755,431);

        player = new Player();
        player.paintColor = Color.MAGENTA;
        player.paintId = 4;

        addObject(player,344,177);

        player.setLocation(518,190);
        Platform platform7 = new Platform();
        addObject(platform7,858,321);
        platform7.setLocation(855,330);
        Platform platform8 = new Platform();
        addObject(platform8,951,227);
        platform8.setLocation(956,331);
        Platform platform9 = new Platform();
        addObject(platform9,143,313);
        platform9.setLocation(150,322);
        Platform platform10 = new Platform();
        addObject(platform10,172,208);
        platform10.setLocation(49,321);

        platform7.setLocation(831,167);
        removeObject(platform7);
        platform9.setLocation(216,200);
        removeObject(platform9);

        Platform platform12 = new Platform();
        addObject(platform12,1177,130);
        platform12.setLocation(1058,129);
        addObject(new Platform(), -55, 121);

        FinalBoss finalboss = new FinalBoss(player);
        addObject(finalboss,515,67);

        Platform platform13 = new Platform();
        addObject(platform13,221,70);
        platform13.setLocation(203,8);
        platform13.setLocation(197,-80);
        Platform platform14 = new Platform();
        addObject(platform14,795,69);
        platform14.setLocation(796,-80);
    }
}
