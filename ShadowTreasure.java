import bagel.*;
import bagel.util.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;

/**
 * this is ShadowTreasure class which plays the game and store all static and variable entity
 */
public class ShadowTreasure extends AbstractGame{

    // background image
    private final Image BACKGROUND = new Image("res/images/background.png");

    // background centre point
    private final Point CENTREPOINT = new Point(512, 386);

    // for rounding double number
    private static DecimalFormat df = new DecimalFormat("0.00");

    // instances of entity
    private Player player;
    private ArrayList<Sandwich> sandwiches = new ArrayList<Sandwich>();
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private Treasure treasure;
    private Bullet bullet;

    // font used in context and the colour of font
    private Font font = new Font("res/font/DejaVuSans-Bold.ttf", 20);
    private final DrawOptions COLOUR = new DrawOptions();

    // position of font
    private final Point fontPos = new Point(20, 760);

    // create frame value
    private int frame=0;

    // create output.csv
    FileWriter csvWriter = new FileWriter("res/IO/output.csv");

    /**
     * this is a constructor of shadowTreasure which call load environment method to initialise the value for entities
     * @throws IOException
     */
    public ShadowTreasure() throws IOException{
        this.loadEnvironment("test/test_stdout/test3/environment.csv");
        this.frame = 1;
    }

    /**
     * these functions return the respective instance of entity or list of entities
     * @return respective instance of entity or list of entities
     */
    public Player getPlayer(){ return this.player;}
    public Treasure getTreasure(){ return this.treasure;}
    public ArrayList<Zombie> getZombies(){ return this.zombies;}
    public ArrayList<Sandwich> getSandwiches(){ return this.sandwiches;}
    public Bullet getBullet(){ return this.bullet;}

    /**
     * this function load the file and initialise all entities
     * @param filename name of the file required to load
     * @throws IOException
     */
    private void loadEnvironment(String filename) throws IOException {
        BufferedReader writer = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = writer.readLine()) != null) {
            String[] infos = line.split(",");
            String type = infos[0];
            type = type.replaceAll("[^a-zA-Z0-9]", "");
            Point point = new Point(Double.parseDouble(infos[1]), Double.parseDouble(infos[2]));
            if (type.equals("Player")) {
                this.player = new Player(new Image("res/images/player.png"), point.x, point.y,
                        Integer.parseInt(infos[3]));
                this.bullet = new Bullet(new Image("res/images/shot.png"), point.x, point.y);
            } else if (type.equals("Zombie")) {
                zombies.add(new Zombie(new Image("res/images/zombie.png"), point));
            } else if (type.equals("Sandwich")) {
                sandwiches.add(new Sandwich(new Image("res/images/sandwich.png"), point));
            } else if (type.equals("Treasure")) {
                this.treasure = new Treasure(new Image("res/images/treasure.png"), point);
            } else {
                System.out.println("this is unknown type " + type);
            }
        }
    }

    /**
     * this function update the game in a very small time duration
     * @param input keyboard input
     */
    public void update(Input input){
        // remove the treasure and end the game if player meet treasure and printout success!
        // end the game because player energy level less than 3 while there's no sandwich but zombies
        if(treasure.hasMet(player) ||
                (player.getEnergy()<3 && sandwiches.size()==0 && zombies.size()!=0)){
            treasure.setVisibility(false);
            System.out.print(player.getEnergy());
            if(treasure.hasMet(player)){
                System.out.print(", success!");
            }
            try {
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Window.close();
        }
        else{
            // update the variable entity each tick and print out position of bullet
            if(frame%10 == 0){
                player.render(this);
                if(bullet.getHasTriggered()){
                    try {
                        csvWriter.append(outputString(bullet.getXCoord(), bullet.getYCoord()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        BACKGROUND.draw(CENTREPOINT.x, CENTREPOINT.y);
        player.draw();
        for (Zombie zombie: zombies){
            zombie.draw();
        }
        for (Sandwich sandwich: sandwiches){
            sandwich.draw();
        }
        treasure.draw();
        bullet.draw();
        font.drawString("energy: " + player.getEnergy(), fontPos.x, fontPos.y,
                COLOUR.setBlendColour(0, 0, 0));
        frame++;
    }

    /**
     * this function returns the bullet x and y coordinates in string and separate with commas
     * @param bulletX x coordinate of bullet
     * @param bulletY y coordiante of bullet
     * @return string contains bullet x and y coordinates separated with commas
     */
    public String outputString(Double bulletX, Double bulletY){
        return String.format("%s, %s\n", df.format(bulletX), df.format(bulletY));
    }

    /**
     * this function remove instance of sandwich if the sandwich is not visible anymore
     * @param sandwiches list of sandwiches contains instance of sandwiches
     */
    public void removeSandwich(ArrayList<Sandwich> sandwiches){
        Iterator<Sandwich> itr = sandwiches.iterator();
        int i = 0;
        while(itr.hasNext() && i<sandwiches.size()){
            if(!(sandwiches.get(i).getVisibility())){
                sandwiches.remove(sandwiches.remove(sandwiches.get(i)));
            }
            i++;
        }
    }

    /**
     * this function remove instance of zombie if the zombie is not visible anymore
     * @param zombies list of zombies contains instance of zombies
     */
    public void removeZombie(ArrayList<Zombie> zombies){
        Iterator<Zombie> itr = zombies.iterator();
        int i = 0;
        while(itr.hasNext() && i<zombies.size()){
            if(!(zombies.get(i).getVisibility())){
                zombies.remove(zombies.remove(zombies.get(i)));
            }
            i++;
        }
    }

    /**
     * main function that evoke the game
     * @param args command line input
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ShadowTreasure game = new ShadowTreasure();
        game.run();
    }
}
