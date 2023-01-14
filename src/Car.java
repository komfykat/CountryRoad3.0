import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Car {
    private double x;
    private double y;
    public double BlockX;
    public double BlockY;
    private double v;
    public double vx;
    public double vy;
    private double angle;
    private double width;
    private double height;
    public Path path;
    public Block block;

    public int PathPosition = 0;
    public boolean inMotion = true;
    public boolean inPath = true;
    public double stopTime = 0;
    public Color color;
    public ArrayList<Block> blocks;
    public ArrayList<Car> cars;

    public Car(double blockX, double blockY, double v, double width, double height, Path path, Block block, ArrayList<Block> blocks) {
        BlockX = blockX;
        BlockY = blockY;
        this.v = v;
        this.width = width;
        this.height = height;
        this.path = path;
        this.block = block;
        this.x = BlockX + this.block.getX();
        this.y = BlockY + this.block.getY();
        this.blocks = blocks;
    }

    public double getX() {
        x = BlockX + block.x;
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        y = BlockY + block.y;
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void update() {
        if (inPath) {
            for (Car car : cars) {
                if (!(car.equals(this))) {
                    if (this.check(car)) {
                        System.out.println("Intersect");
                        if (this.atRight(car)) {
                            this.stop();
                        } else {
                            this.move();
                        }
                    } else {
                        this.move();
                    }
                }
            }
            if (inMotion) {
                double nextX = path.getXs().get(PathPosition);
                double nextY = path.getYs().get(PathPosition);
//                System.out.println(nextX + " " + nextY);
                if (Math.abs(BlockX - nextX) <= Constants.epsilon && Math.abs(BlockY - nextY) <= Constants.epsilon && PathPosition < path.n) {
                    PathPosition++;
                }
                if (Math.abs(BlockX - nextX) <= Constants.epsilon && Math.abs(BlockY - nextY) <= Constants.epsilon && PathPosition == path.n) {
                    inPath = false;
                }
                else{
                    nextX = path.getXs().get(PathPosition);
                    nextY = path.getYs().get(PathPosition);
                }
                double vx = v * (nextX - BlockX) / Math.sqrt(Math.pow(nextX - BlockX, 2) + Math.pow(nextY - BlockY, 2));
                double vy = v * (nextY - BlockY) / Math.sqrt(Math.pow(nextX - BlockX, 2) + Math.pow(nextY - BlockY, 2));
                this.vx = vx;
                this.vy = vy;
//                System.out.println(vx + " " + vy);

                BlockX += vx * Constants.tick;
                BlockY += vy * Constants.tick;
//                System.out.println(BlockX + " " + BlockY + "\n");
            }

        } else {
            findNextBlock();
        }
    }

    public boolean check(Car car){
        if (car.block.equals(this.block)){
//            System.out.println(true);
            double futureX = x + Constants.check * vx;
            double futureY = y + Constants.check * vy;
            double futureX1 = car.x + Constants.check * car.vx;
            double futureY1 = car.y + Constants.check * car.vy;
            Rectangle a = new Rectangle(x, y, futureX + 1, futureY + 1);
            Rectangle b = new Rectangle(car.x, car.y, futureX1 + 1, futureY1 + 1);
            System.out.println(x + " " + y + "  " + futureX + " " + futureY + "   " + car.x + " " + car.y + "  " + futureX1 + " " + futureY1 + "\n" );
            if (a.isIntersects(b)){
                System.out.println(true);
            }
            return a.isIntersects(b);

        }
        else {
            return false;
        }
    }
    public boolean atRight(Car car){
        Vector3D a = new Vector3D(Constants.check * vx, Constants.check * vy, 0);
        Vector3D b = new Vector3D(Constants.check * car.vx, Constants.check * car.vy, 0);
        return (a.right(b));
    }

    public void stop() {
        inMotion = false;
    }

    public void move() {
        inMotion = true;
    }

    public void findNextBlock(){
        for (Block block : blocks){
            for (Path path: block.paths){
                if (Math.abs(path.getXs().get(0) + block.x - getX()) <= Constants.epsilon && Math.abs(path.getYs().get(0) + block.y - getY())<= Constants.epsilon){
                    this.block = block;
                    this.path = path;
                    PathPosition = 0;
                    inPath = true;
                    BlockX = path.getXs().get(0);
                    BlockY = path.getYs().get(0);
                }
            }
        }
    }


//    public double distance(Car car) {
//        return Math.sqrt(Math.pow(x - car.x, 2) + Math.pow(y - car.y, 2));
//    }
//
//    public boolean check(Car car) {
//        if (distance(car) < 100) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void paint(Graphics g) {
//        Graphics2D g2 = (Graphics2D)g;
//        double x1 = x - Math.cos(angle) * width / 2 + Math.cos(Math.PI / 2 - angle) * height / 2;
//        double y1 = y - Math.sin(angle) * width / 2 + Math.sin(Math.PI / 2 - angle) * height / 2;
//        AffineTransform tx = new AffineTransform();
//        tx.rotate(angle);
//        Rectangle shape = new Rectangle((int)x1, (int)y1, (int)width, (int)height);
//        g2.draw(shape);
        g.setColor(color);
        g.fillRect((int) (getX() - width / 2), (int) (getY() - height / 2), (int) width, (int) height);

    }

}
