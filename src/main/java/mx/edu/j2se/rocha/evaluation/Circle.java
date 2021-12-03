package mx.edu.j2se.rocha.evaluation;

public class Circle {
    private int radius;

    public Circle () {
        this.radius = 1;
    }

    public Circle (int radius) throws IllegalArgumentException {
        if (radius <= 0 || radius > 2147483647) {
            throw new IllegalArgumentException ();
        }
        else {
            this.radius = radius;
        }
    }

    public void setRadius (int radius) throws IllegalArgumentException {
        if (radius <= 0 || radius > 2147483647) {
            throw new IllegalArgumentException();
        }
        else {
            this.radius = radius;
        }
    }

    public int getRadius () {
        return this.radius;
    }

    public double getArea () {
        return Math.PI * (double)(this.radius * this.radius);
    }
}
