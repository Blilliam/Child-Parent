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
	
	public Child() {
		this.posX = 0;
		this.posY = 0;
		this.name = "James Bond";
		this.intFacing = 0;
	}
	
	public Child(String name, double posX, double posY, String direction) {
		this.posX = posX;
		this.posY = posY;
		this.name = name;
		this.intFacing = directionToInt(direction);
		
	}
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
	
	public String meet(Child c) {
		double totalposX = c.getPosX() + this.posX;
		double totalposY = c.getPosY() + this.posY;
		
		double meetposX = totalposX / 2;
		double meetposY = totalposY / 2;
		
		totalTraveled += distance(c.getPosX(), c.getPosY(), meetposX, meetposY);
		totalTraveled += distance(posX, posY, meetposX, meetposY);
		
		c.setPosX(meetposX);
		c.setPosY(meetposY);
		
		posX = meetposX;
		posY = meetposY;
		
		return "The children are at (" + meetposX + ", " + meetposY + ")";
	}
	
	public double move(double distance) {
		if (intFacing == 0) {
			if ((posY + distance) > NORTH) {
				double temp = posY;
				posY = NORTH;
				totalTraveled += NORTH - temp;
				return NORTH - temp;
			}
			posY  += distance;
			totalTraveled += Math.abs(distance);
			return Math.abs(distance);
		}
		
		if (intFacing == 1) {
			if ((posX + distance) > EAST) {
				double temp = posX;
				posX = EAST;
				totalTraveled += EAST - temp;
				return EAST - temp;
			}
			posX += distance;
			totalTraveled += Math.abs(distance);
			return Math.abs(distance);
		}
		
		if (intFacing == 2) {
			if ((posY - distance) < SOUTH) {
				double temp = posY;
				posY = SOUTH;
				totalTraveled += Math.abs(SOUTH + temp);
				return Math.abs(SOUTH + temp);
			}
			posY  -= distance;
			totalTraveled += Math.abs(distance);
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
			return Math.abs(distance);
		}
		return -1;
	}
	
	
	public boolean move(double endposX, double endposY) {
		if (endposX >= EAST || endposX <= WEST || endposY >= NORTH || endposY <= SOUTH) {
			return false;
		}
		posX = endposX;
		posY = endposY;
		totalTraveled += distance(posX, posY, endposX, endposY);
		
		
		return true;
	}
	
	public double runBoposX(double distance) {
		int originalIntFacing = this.intFacing;
		
		this.intFacing = 0;
		double boposXposY = this.move(distance);
		
		this.intFacing = 1;
		double boposXposX = this.move(distance);
		
		this.intFacing = 2;
		this.move(boposXposY);
		
		this.intFacing = 3;
		this.move(boposXposX);
		
		this.intFacing = originalIntFacing;
		
		return (2 * boposXposY) + (2 * boposXposX);
}
	
	
	public boolean equals(Child OtherChild) {
		if (Math.abs(this.posX - OtherChild.posX) <= 1) {
			if (Math.abs(this.posX - OtherChild.posY) <= 1) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return name + ": posX = " + posX + ", posY = " + posY + ", Facing = " + intFacing + " / " + directionToString(intFacing) +"]";
	}
	//DIRECTION STRING AND INT CONVERSIONS
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
	//distance
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
	//onlposY one that's public
	public static double getTotalTraveled() {
		return totalTraveled;
	}
	public static void setTotalDistance(double totalDistance) {
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



d
