import bagel.*;
import bagel.util.Point;

/**
 * Class that handled variable entity and this is a superclass
 */
public abstract class variableEntity{
    // the image of variable entity
    private Image diagram;

    // the x and y coordinate of variable entity
    private Double xcoord, ycoord;


    /**
     * this is a constructor of superclass, variableEntity
     * @param diagram this is the image of instance of subclasses
     * @param x this is the x coordinate of instance of subclasses
     * @param y this is y coordinate of instance of subclasses
     */
    public variableEntity(Image diagram, Double x, Double y){
        this.diagram = diagram;
        this.xcoord = x;
        this.ycoord = y;
    }

    /**
     * these two functions helps to return the x and y coordinate of subclasses
     * @return x coordinate or y coordinate
     */
    public double getXCoord(){ return this.xcoord;}
    public double getYCoord(){ return this.ycoord;}

    /**
     * this function reset x coord of subclasses
     * @param x new x coordinate of subclasses
     */
    public void setXCoord(Double x){ this.xcoord = x;}

    /**
     * this function reset y coord of subclasses
     * @param y new y coordinate of subclasses
     */
    public void setYCoord(Double y){this.ycoord = y;}

    /**
     * this function returns the image of subclasses
     * @return image of subclasses
     */
    public Image getImage(){return this.diagram;}

    /**
     * abstract classes that draw the image of subclasses
     */
    public abstract void draw();

    /**
     * abstract classes that aims to change position of instance of subclasses
     */
    public abstract void move();

    /**
     * abstract classes that aims to tell whether the two entity meets
     * @param otherpos position of the other entity
     * @return whether the two entity meets
     */
    public abstract boolean meet(Point otherpos);

    /**
     * this function tells the distance between the subclass and specific entity
     * @param otherPos position of the specific entity
     * @return the distance between the subclass and specific entity
     */
    public double distanceTo(Point otherPos){
        return(new Point(this.getXCoord(), this.getYCoord()).distanceTo(otherPos));
    }

}
