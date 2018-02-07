public class ThreeDimensionalShapes extends Shapes {
    private final int dimensions =3;

    public ThreeDimensionalShapes(String shapeName){
        super(shapeName);
    }

    public void getArea(){
        System.out.println("3D obejcts do not have a traditional area. Perhaps you wanted volume or surface area?");
    }
    public void getPerimeter(){
        System.out.println("3D Shapes do not have perimeter. Perhaps you wanted volume?");
    }

    public int getDimensions(){
        return dimensions;
    }

}
