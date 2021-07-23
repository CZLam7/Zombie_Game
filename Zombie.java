import bagel.*;
import bagel.util.Point;

/**
 * zombie subclass that inherit from static entity that store zombie characteristic
 */
public class Zombie extends staticEntity{

    /**
     * this is a constructor of subclass zombie
     * @param diagram diagram of zombie
     * @param pos position of zombie
     */
    public Zombie(Image diagram, Point pos){
        super(diagram, pos);
    }

    /**
     * this function draws the zombie if the zombie still exists
     */
    @Override
    public void draw(){
        if(this.getVisibility()){
            this.getImage().draw(this.getX(), this.getY());
        }
    }

    /**
     * this functions tells whether the zombie met the bullet
     * @param bullet instance of bullet entity
     * @return boolean value that tells whether the zombie met the bullet
     */
    public boolean hasMet(Bullet bullet){
        return(bullet.meet(this.getPos()));
    }



}
