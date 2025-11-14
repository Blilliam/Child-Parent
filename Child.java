package project;
public class Child {
    private static final int NORTH = 50;
    private static final int SOUTH = -50;
    private static final int EAST = 50;
    private static final int WEST = -50;
    
    private String name;
    
    private static double totalTraveled;
    
    private double posX;
    private double posY;
    
    private int intFacing;
    
    /** 
     * Default constructor: initializes child at (0,0) facing North with name "James Bond"
     */
    public Child() {
        this.posX = 0;
        this.posY = 0;
        this.name = "James Bond";
        this.intFacing = 0;
    }
    
    /** 
     * Parameterized constructor: initializes child with given name, position, and direction
     */
    public Child(String name, double posX, double posY, String direction) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.intFacing = directionToInt(direction);
        
    }
    
    /** 
     * Turns child left or right.
     * @param condition true = turn right, false = turn left
     * @return new direction as a string
     */
    public String turn(boolean condition) {
        if (condition) {
            intFacing++;
            if (intFacing == 4) {
                intFacing = 0;
            }
        } else {
            intFacing--;
            if (intFacing == -1) {
                intFacing = 3;
            }
        }
        return directionToString(intFacing);
    }
    
    /** 
     * Moves both children to their midpoint location.
     * Updates total traveled for both.
     * @param c other child
     * @return string with new location
     */
    public String meet(Child c) {
        double totalposX = c.getPosX() + this.posX;
        double totalposY = c.getPosY() + this.posY;
        
        double meetposX = totalposX / 2;
        double meetposY = totalposY / 2;
        
        totalTraveled += distance(c.getPosX(), c.getPosY(), meetposX, meetposY);
        totalTraveled += distance(posX, posY, meetposX, meetposY);
        Parent.setTotalTraveled(distance(c.getPosX(), c.getPosY(), meetposX, meetposY) + 
                distance(posX, posY, meetposX, meetposY));
        
        c.setPosX(meetposX);
        c.setPosY(meetposY);
        
        posX = meetposX;
        posY = meetposY;
        
        return "The children are at (" + meetposX + ", " + meetposY + ")";
    }
    
    /** 
     * Moves child in current facing direction by given distance.
     * Updates total traveled.
     * @param distance distance to move
     * @return actual distance moved
     */
    public double move(double distance) {
        if (intFacing == 0) {
            if ((posY + distance) > NORTH) {
                double temp = posY;
                posY = NORTH;
                totalTraveled += NORTH - temp;
                Parent.setTotalTraveled(Parent.getTotalTraveled() + (NORTH - temp));
                return NORTH - temp;
            }
            posY  += distance;
            totalTraveled += Math.abs(distance);
            Parent.setTotalTraveled(Parent.getTotalTraveled() + Math.abs(distance));
            return Math.abs(distance);
        }
        
        if (intFacing == 1) {
            if ((posX + distance) > EAST) {
                double temp = posX;
                posX = EAST;
                totalTraveled += EAST - temp;
                Parent.setTotalTraveled(Parent.getTotalTraveled() + (EAST - temp));
                return EAST - temp;
            }
            posX += distance;
            totalTraveled += Math.abs(distance);
            Parent.setTotalTraveled(Parent.getTotalTraveled() + Math.abs(distance));
            return Math.abs(distance);
        }
        
        if (intFacing == 2) {
            if ((posY - distance) < SOUTH) {
                double temp = posY;
                posY = SOUTH;
                totalTraveled += Math.abs(SOUTH + temp);
                Parent.setTotalTraveled(Parent.getTotalTraveled() + Math.abs(distance));
                return Math.abs(SOUTH + temp);
            }
            posY  -= distance;
            totalTraveled += Math.abs(distance);
            Parent.setTotalTraveled(Parent.getTotalTraveled() + Math.abs(distance));
            return Math.abs(distance);
        }
        
        if (intFacing == 3) {
            if ((posX - distance) < WEST) {
                double temp = posX;
                posX = WEST;
                totalTraveled += Math.abs(WEST + temp);
                return Math.abs(WEST + temp);
            }
            posX -= distance;
            totalTraveled += Math.abs(distance);
            Parent.setTotalTraveled(Parent.getTotalTraveled() + Math.abs(distance));
            return Math.abs(distance);
        }
        return -1;
    }
    
    /** 
     * Moves child directly to given coordinates if within bounds.
     * Updates total traveled.
     * @param endposX target x
     * @param endposY target y
     * @return true if move succeeded, false if out of bounds
     */
    public boolean move(double endposX, double endposY) {
        if (endposX > EAST || endposX < WEST || endposY > NORTH || endposY < SOUTH) {
            return false;
        }
        totalTraveled += distance(posX, posY, endposX, endposY);
        Parent.setTotalTraveled(Parent.getTotalTraveled() + distance(posX, posY, endposX, endposY));
        posX = endposX;
        posY = endposY;
        
        
        return true;
    }
    
    /** 
     * Runs child in a box (North, East, South, West) of given distance.
     * Child ends up back at start facing original direction.
     * @param distance side length of box
     * @return total distance traveled
     */
    public double runBox(double distance) {
        int originalIntFacing = this.intFacing;
        
        this.intFacing = 0;
        double boxY = this.move(distance);
        
        this.intFacing = 1;
        double boxX = this.move(distance);
        
        this.intFacing = 2;
        this.move(boxY);
        
        this.intFacing = 3;
        this.move(boxX);
        
        this.intFacing = originalIntFacing;
        
        return (2 * boxY) + (2 * boxX);
}
    
    /** 
     * Checks if two children are equal.
     * Equal if positions are within 1 unit.
     * @param OtherChild other child
     * @return true if equal, false otherwise
     */
    public boolean equals(Child OtherChild) {
        if (Math.abs(this.posX - OtherChild.posX) <= 1) {
            if (Math.abs(this.posX - OtherChild.posY) <= 1) {
                return true;
            }
        }
        return false;
    }
    
    /** 
     * Returns string representation of child
     */
    public String toString() {
        return name + ": totalTraveled = " + totalTraveled + ", posX = " + posX + ", posY = " + posY + ", Facing = " + intFacing + " / " + directionToString(intFacing) +"]";
    }
    
    /** 
     * Converts integer direction to string
     */
    private String directionToString(int direction) {
        if (direction == 0) {
            return "North";
        }
        if (direction == 1) {
            return "East";
        }
        if (direction == 2) {
            return "South";
        }
        if (direction == 3) {
            return "West";
        }
        return "OUT OF RANGE";
    }
    
    /** 
     * Converts string direction to integer
     */
    private int directionToInt(String direction) {
        if (direction.equals("North")) {
            return 0;
        }
        if (direction.equals("East")) {
            return 1;
        }
        if (direction.equals("South")) {
            return 2;
        }
        if (direction.equals("West")) {
            return 3;
        }
        return -1;
    }
    
    /** 
     * Helper method: calculates distance between two points
     */
    private double distance(double startposX, double startposY, double endposX, double endposY) {
        double posXDiff = Math.max(endposX, startposX) - Math.min(endposX, startposX);
        double posYDiff = Math.max(endposY, startposY) - Math.min(startposY, endposY);
        return Math.sqrt(Math.pow(posYDiff, 2) + Math.pow(posXDiff, 2));
    }
    
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/** 
     * both the parent and child share the same Total Traveled distance as it is the total traveled
     */
	public static double getTotalTraveled() {
		return totalTraveled;
	}
	public static void setTotalTraveled(double totalDistance) {
		Child.totalTraveled = totalDistance;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public int getIntFacing() {
		return intFacing;
	}
	public void setIntFacing(int intFacing) {
		this.intFacing = intFacing;
	}
	public String getDirection() {
		return directionToString(intFacing);
	}
	public void setDirection(String direction) {
		intFacing = directionToInt(direction);
	}
	
}
