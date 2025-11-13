package project;
public class Parent {
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
		
	}
	
	public String moveToClosest() {
		
	}
	
	public int moveWith(int x, int y) {
		
	}
	
	public boolean compareTo(Parent p) {
		
	}
	
}

