package project;
public class Parent {
	private double totalTraveled = 0;
	Child c1;
	Child c2;
	
	private static final int NORTH = 50;
	private static final int SOUTH = -50;
	private static final int EAST = 50;
	private static final int WEST = -50;
	private double posX;
	private double posY;
	
	public Parent() {
		this.posX = 0;
		this.posY = 0;
		c1 = new Child();
		c2 = new Child();
		
		c1.setName("Child 1");
		c2.setName("Child 2");
	}
	
	public Parent(Child c1, Child c2, double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public void gatherChild() {
		c1.move(posX, posY);
		c2.move(posX, posY);
	}
	
	public String moveToClosest() {
		double c1Distance = this.distance(posX, posY, c1.getPosX(), c1.getPosX());
		double c2Distance = this.distance(posX, posY, c2.getPosX(), c2.getPosX());

		if (c1Distance > c2Distance) {
			move(c1.getPosX(), c1.getPosY());
			return c1.getName();
		} else if (c2Distance > c1Distance) {
			move(c2.getPosX(), c2.getPosY());
			return c2.getName();
		}
		return c1.getName() + " and " + c2.getName() + " are the same distance away";
	}
	
	public int moveWith(int x, int y) {
		if (x >= EAST || x <= WEST || y >= NORTH || y <= SOUTH) {
			return 0;
		}
		
		boolean closeToC1 = false;
		boolean closeToC2 = false;
		
		if (Math.abs(this.posX - c1.getPosX()) <= 1) {
			if (Math.abs(this.posX - c1.getPosY()) <= 1) {
				closeToC1 = true;
			}
		}
		
		if (Math.abs(this.posX - c2.getPosX()) <= 1) {
			if (Math.abs(this.posX - c2.getPosY()) <= 1) {
				closeToC2 = true;
			}
		}
		
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
	
	public boolean compareTo(Parent p) {
		
	}
	
	private double distance(double startposX, double startposY, double endposX, double endposY) {
		double posXDiff = Math.max(endposX, startposX) - Math.min(endposX, startposX);
		double posYDiff = Math.max(endposY, startposY) - Math.min(startposY, endposY);
		return Math.sqrt(Math.pow(posYDiff, 2) + Math.pow(posXDiff, 2));
	}
	
	private boolean move(double endposX, double endposY) {
		if (endposX >= EAST || endposX <= WEST || endposY >= NORTH || endposY <= SOUTH) {
			return false;
		}
		totalTraveled += distance(posX, posY, endposX, endposY);
		posX = endposX;
		posY = endposY;
		
		
		return true;
	}
	

	@Override
	public String toString() {
		return "Parent [totalTraveled=" + totalTraveled + ", c1=" + c1 + ", c2=" + c2 + ", posX=" + posX + ", posY="
				+ posY + "]";
	}

	/**
	 * @return the totalTraveled
	 */
	public double getTotalTraveled() {
		return totalTraveled;
	}

	/**
	 * @param totalTraveled the totalTraveled to set
	 */
	public void setTotalTraveled(double totalTraveled) {
		this.totalTraveled = totalTraveled;
	}

	/**
	 * @return the c1
	 */
	public Child getC1() {
		return c1;
	}

	/**
	 * @param c1 the c1 to set
	 */
	public void setC1(Child c1) {
		this.c1 = c1;
	}

	/**
	 * @return the c2
	 */
	public Child getC2() {
		return c2;
	}

	/**
	 * @param c2 the c2 to set
	 */
	public void setC2(Child c2) {
		this.c2 = c2;
	}

	/**
	 * @return the posX
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public double getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	
}

