import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random(111);
//        LRBlock block1 = new LRBlock(100, 0, 100, 100);
//        LRBlock block2 = new LRBlock(200, 0, 100, 100);
//        LDBlock block3 = new LDBlock(300, 0, 100, 100);
//        ULBlock block4 = new ULBlock(300, 100, 100, 100);
//        RLBlock block5 = new RLBlock(200, 100, 100, 100);
//        RLBlock block6 = new RLBlock(100, 100, 100, 100);
//        RUBlock block7 = new RUBlock(0, 100, 100, 100);
//        DRBlock block8 = new DRBlock(0, 0, 100, 100);
//        ArrayList<Block> blocks = new ArrayList<>(List.of(block1, block2, block3, block4, block5, block6, block7, block8));
        Vector3D a = new Vector3D(5, 0, 0);
        Vector3D b = new Vector3D(5, -5, 0);
        System.out.println(a.right(b));
        Crossroad crossroad = new Crossroad(100, 100, 100, 100);
        ArrayList<Block> blocks = new ArrayList<>(List.of(crossroad));
        int i = r.nextInt(12);
        int j = r.nextInt(12);
        while (i == j){
            j = r.nextInt(12);
        }
        Car car = new Car(crossroad.paths.get(i).getXs().get(0), crossroad.paths.get(i).getYs().get(0), 30, 10, 10, crossroad.paths.get(i), crossroad, blocks);
        Car car1 = new Car(crossroad.paths.get(j).getXs().get(0), crossroad.paths.get(j).getYs().get(0), 30, 10, 10, crossroad.paths.get(j), crossroad, blocks);
        ArrayList<Car> cars = new ArrayList<>(List.of(car, car1));
        car.cars = cars;
        car1.cars = cars;
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Billboard board = new Billboard(cars, blocks);
        frame.add(board);
        frame.setVisible(true);

        while (true){
            for (Car cari : cars){
               cari.update();
            }
            frame.repaint();
//           System.out.println(car.BlockX + " " + car.BlockY + " " + car.inPath + " " + car.inMotion + " " + car.PathPosition);
            Thread.sleep((long) (Constants.tick * 1000));
        }


    }

}
