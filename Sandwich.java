import bagel.*;
import bagel.util.Point;

/**
 * sandwich subclass that inherit from static entity that store sandwich characteristic
 */
public class Sandwich extends staticEntity{

    /**
     * this is a constructor of subclass sandwich
     * @param diagram diagram of sandwich
     * @param pos position of sandwich
     */
    public Sandwich(Image diagram, Point pos){
        super(diagram, pos);
    }

    /**
     * this function draws the sandwich if the sandwich still exists
     */
    @Override
    public void draw(){
        if(this.getVisibility()){
            this.getImage().draw(this.getX(), this.getY());
        }
    }

    /**
     * this functions tells whether the sandwich met the player
     * @param player instance of player entity
     * @return boolean value that tells whether the sandwich met the player
     */
    public boolean hasMet(Player player){
        return(player.meet(this.getPos()));
    }


}
