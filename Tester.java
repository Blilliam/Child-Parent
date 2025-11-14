package project;

public class main {
    public static void main(String[] args) {
        // Reset before testing
        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);

        Child a = new Child("Alice", 0, 0, "North");
        Child b = new Child("Bob", 10, 10, "South");

        System.out.println("Initial children:");
        System.out.println(a);
        System.out.println(b);

        // Test move
        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        double moved = a.move(5); // Alice moves north 5
        System.out.println("Move Alice 5 units: " + moved);
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled());
        System.out.println("Total traveled (expected): 5.0\n");

        // Test turn
        System.out.println("Alice turns right: " + a.turn(true));
        System.out.println("Direction now: " + a.getDirection() + "\n");

        // Test move to specific spot
        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        boolean movedTo = a.move(20, 20);
        System.out.println("Move Alice to (20,20): " + movedTo);
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled());
        System.out.println("Total traveled (expected): " +
            Math.sqrt(Math.pow(20-5,2)+Math.pow(20-5,2)) + "\n");

        // Test meet in middle
        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        System.out.println("Meet in middle: " + a.meet(b));
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled());
        System.out.println("Total traveled (expected): " +
            (Math.sqrt(Math.pow(a.getPosX()-20,2)+Math.pow(a.getPosY()-20,2)) +
             Math.sqrt(Math.pow(b.getPosX()-10,2)+Math.pow(b.getPosY()-10,2))) + "\n");

        // Test runBox
        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        double boxDist = a.runBox(5);
        System.out.println("Run box: " + boxDist);
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled());
        System.out.println("Total traveled (expected): 20.0\n"); // 4 sides × 5

        // Test equals
        System.out.println("Equals? " + a.equals(b) + "\n");

        // Test Parent methods
        Parent p = new Parent(a, b, 0, 0);
        System.out.println("Parent initial: " + p);

        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        p.gatherChildren();
        System.out.println("After gather: " + p);
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled());
        System.out.println("Total traveled (expected): " +
            (Math.sqrt(Math.pow(p.getPosX()-a.getPosX(),2)+Math.pow(p.getPosY()-a.getPosY(),2)) +
             Math.sqrt(Math.pow(p.getPosX()-b.getPosX(),2)+Math.pow(p.getPosY()-b.getPosY(),2))) + "\n");

        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        System.out.println("Move to closest: " + p.moveToClosest());
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled() + "\n");

        Parent.setTotalTraveled(0);
        Child.setTotalTraveled(0);
        System.out.println("MoveWith(5,5): " + p.moveWith(5,5));
        System.out.println("Total traveled (actual): " + Parent.getTotalTraveled() + "\n");

        Parent p2 = new Parent(new Child("Charlie", 1,1,"East"),
                               new Child("Dana",2,2,"West"), 0,0);
        System.out.println("Compare parents: " + p.compareTo(p2));
    }
}
