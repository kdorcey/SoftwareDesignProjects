import java.util.ArrayList;
import java.util.Collections;

public class Triangle extends Polygons {
    private double height;
    private double base;
    private double side;
    private double side2;
    private double area;

    public Triangle(String shapeName, double base, double height, double side, double side2) {
        super(shapeName, base,0, side, side2);
        this.base = base;
        this.side = side;
        this.side2 = side2;
        this.height =height;
        this.area = setArea(base, height);
    }

    private static double setArea(double base, double height){
        double area = .5*base*height;
        return area;
    }


    public double getBase(){return this.base;}
    public double getSide(){return this.side;}
    public double getSide2(){return this.side2;}
    public double getHeight() {
        return this.height;
    }
    public double getArea() {
        return this.area;
    }

}
