/***
 * Driver class for the S23_ShapeInheritanceHierarchy_Medium module. It creates an object of each class
 * and prints their subsequent values in order to test that the inheritance structure works properly.
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class TestAllShapeObjects {
    public static void main (String args[]){

        RectangularPrism rectangularPrism = new RectangularPrism("box",4,5,6);
        System.out.println("Rectangular prism named "+ rectangularPrism.name()+ " has side lenths of" +rectangularPrism.getWidth()+", "+rectangularPrism.getLength()+", "+rectangularPrism.getHeight()+ " has volume "+ rectangularPrism.getVolume() +" and perimeter "+ rectangularPrism.getSurfaceArea());

        Cube cube = new Cube("better box", 3);
        System.out.println("Cube named "+ cube.name()+ " has a side length of "+ cube.getHeight()+", a volume of "+ cube.getVolume()+", and surface area "+cube.getSurfaceArea());

        TriangularPrism triangularPrism = new TriangularPrism("triangle box",2,3,4,5,6);
        System.out.println("Triangle prism named "+triangularPrism.name()+". has a base width "+triangularPrism.getBaseWidth()+", base side length "+ triangularPrism.getBaseSideLength()+", side length "+triangularPrism.getBaseSideLength()+ ", height of "+triangularPrism.getBaseHeight()+", length "+triangularPrism.getLength()+", with volume "+triangularPrism.getVolume()+", with surface area "+ triangularPrism.getSurfaceArea());

        Sphere sphere = new Sphere("ball", 8);
        System.out.println("Sphere named "+sphere.name()+", with radius "+sphere.getRadius()+", with volume "+ sphere.getVolume()+", and surface area "+sphere.getSurfaceArea());

        Cone cone = new Cone("pointy",3,4);
        System.out.println("Cone named "+cone.name()+", with radius "+cone.getRadius()+", with height "+cone.getHeight()+", with volume "+cone.getVolume()+", and surface area "+cone.getSurfaceArea());

        Torus torus = new Torus("donut",3,4);
        System.out.println("Torus named "+torus.name()+", with minor radius "+torus.getMinorRadius()+", major radius "+torus.getMajorRadius()+", volume "+torus.getVolume()+", and surface area "+torus.getSurfaceArea());

        Cylinder cylinder = new Cylinder("tube",2,3);
        System.out.println("Cylinder named "+cylinder.name()+", with radius "+cylinder.getRadius()+", with height "+cylinder.getHeight()+", volume "+cylinder.getVolume()+", and surface area "+cylinder.getSurfaceArea());

        Semicircle semicircle = new Semicircle("sunrise",2);
        System.out.println("Semicircle named "+semicircle.name()+", with radius "+semicircle.getRadius()+", with area "+semicircle.getArea()+", and perimeter "+semicircle.getPerimeter());

        Circle circle = new Circle("wheel",4);
        System.out.println("Circle named "+circle.name()+", with radius "+circle.getRadius()+", with area "+circle.getArea()+", and perimeter "+circle.getPerimeter());

        Elipse elipse = new Elipse("lazy circle", 4,2);
        System.out.println("Elipse named "+elipse.name()+", with max radius "+elipse.getBigRadius()+", with min radius "+elipse.getSmallRadius()+", area "+elipse.getArea()+", perimeter "+elipse.getPerimeter());

        Square square = new Square("2D box",2);
        System.out.println("square named "+square.name()+", with side legnth "+square.getBase()+", area of "+square.getArea()+", and perimeter "+square.getPerimeter());

        Rectangle rectangle = new Rectangle("Long square", 4,3);
        System.out.println("Rectangle named "+rectangle.name()+", with side lengths "+rectangle.getBase()+" and "+rectangle.getHeight() +", an area of "+rectangle.getArea()+", and perimeter of "+rectangle.getPerimeter());

        Rhombus rhombus = new Rhombus("slanty rectangle", 2,3,4,5,6,7);
        System.out.println("Rhombus named "+rhombus.name()+" with side lenths "+rhombus.getBase()+", "+rhombus.getBase2()+", "+rhombus.getHeight()+", and "+rhombus.getHeight2()+", diagonals of "+rhombus.getDiagonal()+" and "+rhombus.getDiagonal2()+", an area of "+rhombus.getArea()+", and perimeter of "+rhombus.getPerimeter());

        Kite kite = new Kite("diamond", 4,2,3,5);
        System.out.println("Kite named "+kite.name()+", has sides "+kite.getLongerSideLength()+" and "+kite.getShorterSideLength()+". It has an area of "+kite.getArea()+" and perimeter of "+kite.getPerimeter());

        Trapezoid trapezoid = new Trapezoid("weird box thing", 4,2,3,3, 2);
        System.out.println("Trapezoid named "+ trapezoid.name()+" has side lengths of "+ trapezoid.getBase()+", "+ trapezoid.getBase2()+", "+ trapezoid.getRightSideLength()+", "+ trapezoid.getLeftSideLength()+", and a height of "+ trapezoid.getHeight()+". It has an area of "+ trapezoid.getArea()+" and perimeter of "+ trapezoid.getPerimeter());

        Triangle triangle = new Triangle("pointy",2,3,4,5);
        System.out.println("Triangle named "+triangle.name()+" has side lengths of "+triangle.getSide()+", "+triangle.getSide2()+", "+triangle.getBase()+". It has an area of "+triangle.getArea()+" and perimeter of "+triangle.getPerimeter());







    }
}
