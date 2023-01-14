public class Rectangle {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    Rectangle(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }



    boolean isIntersects(Rectangle that) {
        return that.x1 <= this.x2 & that.x2 > this.x1 && that.y1 <= this.y2 & that.y2 >= this.y1;
    }
}
