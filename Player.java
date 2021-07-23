import bagel.*;
import bagel.util.Point;
import java.util.ArrayList;

/**
 * player class is a subclass inherits from variableEntity which store player character
 */
public class Player extends variableEntity{
    // energy of the player
    private int energy;

    // create playerDirectionVector
    private double playerX, playerY;

    // meet distance between player and particular entity, and stepsize of player
    private final int MEETDIST = 50;
    private final int STEPSIZE = 10;

    // tell whether player meet the particular object
    private boolean meet;

    // min distance between player and zombie to shoot a bullet
    private final int SHOOTING_RANGE = 150;

    /**
     * this is a constructor of subclass player
     * @param diagram diagram of player
     * @param x x coordinate of player
     * @param y y coordinate of player
     * @param energy energy level of player
     */
    public Player(Image diagram, Double x, Double y, int energy){
        super(diagram, x, y);
        this.energy = energy;
    }

    /**
     * this function draw the player image
     */
    @Override
    public void draw(){
        this.getImage().draw(this.getXCoord(), this.getYCoord());
    }

    /**
     * this function makes changes on player position in every tick
     * this function also remove other static entity if player meets them
     * @param infos ShadowTreasure class that contains information about all entity
     */
    public void render(ShadowTreasure infos){
        // indexZom is the nearest index of zombie from player in zombies list
        int indexZom = 0;

        // indexSand is the nearest index of sandwich from player in sandwiches list
        int indexSand = 0;

        if(infos.getZombies().size()!=0){
            indexZom = closestZombie(infos.getZombies());

            // remove particular zombie if bullet meets it and set bullet in non-shooting mode
            // energy level of player decrease by 3
            if(infos.getZombies().get(indexZom).hasMet(infos.getBullet())){
                energy -= 3;
                infos.getZombies().get(indexZom).setVisibility(false);
                infos.removeZombie(infos.getZombies());
                infos.getBullet().setHasTriggered(false);
            }
        }
        if(infos.getSandwiches().size()!=0){
            indexSand = closestSandwich(infos.getSandwiches());

            // remove particular sandwich if player meets it and increase energy level by 5
            if(infos.getSandwiches().get(indexSand).hasMet(infos.getPlayer())){
                energy += 5;
                infos.getSandwiches().get(indexSand).setVisibility(false);
                infos.removeSandwich(infos.getSandwiches());
            }
        }

        // player move towards treasure if no more zombies
        if(infos.getZombies().size() == 0){
            this.setDirection(infos.getTreasure().getPos());
        }
        // player move towards zombie if energy level less than 3 or bullet has been shot
        // shoot bullet if player and zombie is within shooting range
        else if(energy >=3 || infos.getBullet().getHasTriggered()){
            indexZom = closestZombie(infos.getZombies());
            this.setDirection(infos.getZombies().get(indexZom).getPos());
            if(distanceTo(infos.getZombies().get(indexZom).getPos()) < SHOOTING_RANGE){
                shootBullet(infos.getZombies().get(indexZom).getPos(), infos.getBullet());
            }
        }
        // if there are zombies and sandwich and energy level less than 3, move towards sandwich
        else{
            if(infos.getSandwiches().size() != 0) {
                this.setDirection(infos.getSandwiches().get(indexSand).getPos());
            }
        }
    }

    /**
     * this function move the player to specified place
     * @param Dest position of specified place
     */
    public void setDirection(Point Dest){
        double len = new Point(this.getXCoord(), this.getYCoord()).distanceTo(Dest);
        playerX = (Dest.x - this.getXCoord())/len;
        playerY = (Dest.y - this.getYCoord())/len;
        move();
    }

    /**
     * this function checks whether the player meets certain entity
     * @param otherPos position of specified entity
     * @return boolean value that tells whether player meets certain entity
     */
    @Override
    public boolean meet(Point otherPos){
        meet = false;
        if(this.distanceTo(otherPos) <= MEETDIST){
            meet = true;
        }
        return meet;
    }

    /**
     * this function change the coordinate of player position
     */
    @Override
    public void move(){
        this.setXCoord(this.getXCoord() + STEPSIZE * playerX);
        this.setYCoord(this.getYCoord() + STEPSIZE * playerY);
    }

    /**
     * this function finds the nearest zombie from player character
     * @param zombies list of zombies which still exists
     * @return index of zombie which is nearest from player
     */
    public int closestZombie(ArrayList<Zombie> zombies){
        int index = 0, i=0;
        double minDist = this.distanceTo(zombies.get(i).getPos());
        while(i < zombies.size()){
            if(this.distanceTo(zombies.get(i).getPos()) < minDist){
                index = i;
                minDist = this.distanceTo(zombies.get(i).getPos());
            }
            i++;
        }
        return index;
    }

    /**
     * this function finds the nearest sandwich from player character
     * @param sandwiches list of sandwiches which still exists
     * @return index of sandwich which is nearest from player
     */
    public int closestSandwich(ArrayList<Sandwich> sandwiches){
        int index = 0, i=0;
        double minDist = this.distanceTo(sandwiches.get(i).getPos());
        while(i < sandwiches.size()){
            if(this.distanceTo(sandwiches.get(i).getPos()) < minDist){
                index = i;
                minDist = this.distanceTo(sandwiches.get(i).getPos());
            }
            i++;
        }
        // System.out.println("Closest Sandwich" + index);
        return index;
    }

    /**
     * this function change the direction of bullet towards specified zombie
     * @param zombiePos position of specified zombie
     * @param bullet instance of bullet
     */
    public void shootBullet(Point zombiePos, Bullet bullet){
        if(!bullet.getHasTriggered()){
            bullet.setXCoord(this.getXCoord());
            bullet.setYCoord(this.getYCoord());
            bullet.setHasTriggered(true);
        }
        bullet.setDirection(zombiePos);
    }

    /**
     * this function get the energy level of player character
     * @return energy level of player character
     */
    public int getEnergy(){ return this.energy; }

}
