package mx.edu.j2se.rocha.evaluation;

public class Evaluation1 {
    public static void main(String[] args) {
        try {
            Circle c = new Circle (-1);
        }

        catch (IllegalArgumentException e) {
            System.out.println("Sorry, this is not a valid radius value, please try again");
        }
        
        Circle [] circles = new Circle[] {new Circle (7), new Circle (13), new Circle (6)};
        System.out.println("The biggest circle's radius is: " + circles[biggestCircle(circles)].getRadius());

    }

    public static int biggestCircle (Circle [] circles) {
        int indexBiggestCircle = 0;
        for (int i = 0; i < circles.length - 1; i++) {
            if (circles[i+1].getRadius() > circles[indexBiggestCircle].getRadius()) {
                indexBiggestCircle = i+1;
            }
        }

        return indexBiggestCircle;
    }
}
