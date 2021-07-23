import bagel.*;
import bagel.util.Point;

/**
 * bullet class is a subclass inherits from variableEntity which store bullet characteristic
 */
public class Bullet extends variableEntity{
    // check whether the bullet has shoot
    private boolean hasTriggered = false;

    // create bulletDirectionVector
    private double bulletx, bullety;

    //stepsize of bullet
    private final int STEPSIZE = 25;

    // death range of zombie meets bullet
    private final int DEATH_RANGE = 25;

    // tell whether bullet meet zombie
    private boolean meet;

    /**
     * this is a constructor of subclass bullet
     * @param diagram diagram of bullet
     * @param x x coordinate of bullet
     * @param y y coordinate of bullet
     */
    public Bullet(Image diagram, Double x, Double y){
        super(diagram, x, y);
    }

    /**
     * this function draws the bullet if the bullet is shot
     */
    public void draw(){
        if(hasTriggered){
            this.getImage().draw(this.getXCoord(), this.getYCoord());
        }
    }

    /**
     * this functiong get whether the bullet is shot
     * @return boolean value that tells whether the bullet is shot
     */
    public boolean getHasTriggered(){ return this.hasTriggered;}

    /**
     * this function reset the boolean value which tells whether the bullet is shot
     * @param hasTriggered new boolean value which tells whether the bullet is shot
     */
    public void setHasTriggered(boolean hasTriggered){
        this.hasTriggered = hasTriggered;
    }

    /**
     * this function move the bullet to specified place
     * @param Dest position of specified place
     */
    public void setDirection(Point Dest){
        double len = new Point(this.getXCoord(), this.getYCoord()).distanceTo(Dest);
        bulletx = (Dest.x - this.getXCoord())/len;
        bullety = (Dest.y - this.getYCoord())/len;
        move();
    }

    /**
     * this function changes the coordinate of bullet position
     */
    public void move(){
        this.setXCoord(this.getXCoord() + STEPSIZE * bulletx);
        this.setYCoord(this.getYCoord() + STEPSIZE * bullety);
    }

    /**
     * this function checks whether the bullet meets certain entity
     * @param otherPos position of specified entity
     * @return boolean value that tells whether bullet meets certain entity
     */
    public boolean meet(Point otherPos){
        meet = false;
        if(this.distanceTo(otherPos) < DEATH_RANGE){
            meet = true;
        }
        return meet;
    }
}
