import bagel.*;
import bagel.util.Point;

/**
 *  this class act as a superclass of all static entity
 */
public abstract class staticEntity{
    // image of the static entity
    private Image diagram;

    // position of the static entity
    private Point pos;

    // tells whether the static entity is removed or non-exist
    private boolean visibility = true;

    /**
     * this is the constructor of static entity
     * @param diagram diagram of instance of subclasses
     * @param pos the position of instance of subclasses
     */
    public staticEntity(Image diagram, Point pos){
        this.diagram = diagram;
        this.pos = pos;
    }

    /**
     * this function get the x coordinate of instance of subclasses
     * @return x coordinate of instance of subclass
     */
    public double getX(){return this.pos.x;}

    /**
     * this function get the y coordinate of instance of subclasses
     * @return y coord of instance of subclasses
     */
    public double getY() {return this.pos.y;}

    /**
     * this function get the position of instance of subclasses
     * @return position of instance of subclasses
     */
    public Point getPos(){ return this.pos;}

    /**
     * this function get the image of instance of subclasses
     * @return the image of instance of subclasses
     */
    public Image getImage(){ return this.diagram;}

    /**
     * abstract classes that draw the iamge of subclasses
     */
    public abstract void draw();

    /**
     * this function resets the visibility of the particular instance of subclasses
     * @param visibility new visibility of the particular instance of subclasses
     */
    public void setVisibility(boolean visibility){this.visibility = visibility;}

    /**
     * this function get the visibility of particular instance of subclasses
     * @return visibility of particular instance of subclasses
     */
    public boolean getVisibility(){return this.visibility;}

}
