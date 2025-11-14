package project;
public class Parent {
    private static double totalTraveled = 0; // static variable tracking total distance traveled
    Child c1;
    Child c2;
    
    private static final int NORTH = 50;
    private static final int SOUTH = -50;
    private static final int EAST = 50;
    private static final int WEST = -50;
    private double posX;
    private double posY;
    
    /** 
     * Default constructor: initializes parent at (0,0) with two children
     */
    public Parent() {
        this.posX = 0;
        this.posY = 0;
        c1 = new Child();
        c2 = new Child();
        
        c1.setName("Child 1");
        c2.setName("Child 2");
    }
    
    /** 
     * Parameterized constructor: initializes parent with given children and position
     */
    public Parent(Child c1, Child c2, double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.c1 = c1;
        this.c2 = c2;
    }
    
    /** 
     * Moves both children to the parent's current position
     */
    public void gatherChildren() {
        c1.move(posX, posY);
        c2.move(posX, posY);
    }
    
    /** 
     * Moves parent to the closest child and returns that child's name
     */
    public String moveToClosest() {
        double c1Distance = this.distance(posX, posY, c1.getPosX(), c1.getPosY());
        double c2Distance = this.distance(posX, posY, c2.getPosX(), c2.getPosY());

        if (c1Distance > c2Distance) {
            move(c1.getPosX(), c1.getPosY());
            return c1.getName();
        } else if (c2Distance > c1Distance) {
            move(c2.getPosX(), c2.getPosY());
            return c2.getName();
        }
        return c1.getName() + " and " + c2.getName() + " are the same distance away";
    }
    
    /** 
     * Moves parent to (x,y). If children are within 1 unit, they move too.
     * @param x new x-coordinate
     * @param y new y-coordinate
     * @return 0=can't move, 1=only parent moved, 2=parent+one child, 3=parent+both children
     */
    public int moveWith(double x, double y) {
        if (x > EAST || x < WEST || y > NORTH || y < SOUTH) {
            return 0; // invalid move
        }

        
        boolean closeToC1 = false;
        boolean closeToC2 = false;
        
        // Check if child1 is within 1 unit of parent
        if (Math.abs(this.posX - c1.getPosX()) <= 1) {
            if (Math.abs(this.posY - c1.getPosY()) <= 1) {
                closeToC1 = true;
            }
        }
        
        // Check if child2 is within 1 unit of parent
        if (Math.abs(this.posX - c2.getPosX()) <= 1) {
            if (Math.abs(this.posY - c2.getPosY()) <= 1) {
                closeToC2 = true;
            }
        }
        
        // Move parent and children depending on proximity
        if (closeToC2 && closeToC1) {
            c2.move(x, y);
            c1.move(x, y);
            move(x, y);
            return 3;
        }
        
        if (closeToC2) {
            c2.move(x, y);
            move(x, y);
            return 2;
        }
        
        if (closeToC1) {
            c1.move(x, y);
            move(x, y);
            return 2;
        }
        
        move(x, y);
        
        return 1;
        
        
    }
    
    
    /** 
     * Compares two parents: returns negative if this parent is closer to kids,
     * positive if other parent is closer, 0 if equal
     */
    public int compareTo(Parent p) {
        double myDist = distance(posX, posY, c1.getPosX(), c1.getPosY()) + distance(posX, posY, c2.getPosX(), c2.getPosY());
        double otherDist = distance(p.getPosX(), p.getPosY(), p.getC1().getPosX(), p.getC1().getPosY()) + distance(p.getPosX(), p.getPosY(), p.getC2().getPosX(), p.getC2().getPosY());
        return Double.compare(otherDist, myDist); // smaller distance = "better"
    }

    
    /** 
     * Helper method: calculates Euclidean distance between two points
     */
    private double distance(double startposX, double startposY, double endposX, double endposY) {
        double posXDiff = Math.max(endposX, startposX) - Math.min(endposX, startposX);
        double posYDiff = Math.max(endposY, startposY) - Math.min(startposY, endposY);
        return Math.sqrt(Math.pow(posYDiff, 2) + Math.pow(posXDiff, 2));
    }
    
    /** 
     * Moves parent to new location and updates total traveled
     */
    private boolean move(double x, double y) {
        if (x > EAST || x < WEST || y > NORTH || y < SOUTH) {
            return false; // invalid move
        }

        totalTraveled += distance(posX, posY, x, y);
        Child.setTotalTraveled(Child.getTotalTraveled() + distance(posX, posY, x, y));
        posX = x;
        posY = y;
        
        
        return true;
    }
    

    /** 
     * Returns string representation of parent and children
     */
    @Override
    public String toString() {
        return "Parent [totalTraveled = " + totalTraveled + ", Child 1 =" + c1 + ", Child 2=" + c2 + ", x cordinate = " + posX + ", y cordinate = "
                + posY + "]";
    }

    /** 
     * both the parent and child share the same Total Traveled distance as it is the total traveled
     */
    public static double getTotalTraveled() {
        return totalTraveled;
    }

    /** 
     * Setter for total traveled
     */
    public static void setTotalTraveled(double totalTraveled) {
        Parent.totalTraveled = totalTraveled;
    }

    /** 
     * Getter for child1
     */
    public Child getC1() {
        return c1;
    }

    /** 
     * Setter for child1
     */
    public void setC1(Child c1) {
        this.c1 = c1;
    }

    /** 
     * Getter for child2
     */
    public Child getC2() {
        return c2;
    }

    /** 
     * Setter for child2
     */
    public void setC2(Child c2) {
        this.c2 = c2;
    }

    /** 
     * Getter for posX
     */
    public double getPosX() {
        return posX;
    }

    /** 
     * Setter for posX
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /** 
     * Getter for posY
     */
    public double getPosY() {
        return posY;
    }

    /** 
     * Setter for posY
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    
}
