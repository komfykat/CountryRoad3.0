import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Crossroad extends Block{

    public Crossroad(double x, double y, double width, double height) {
        super(new ArrayList<>(List.of(new Path())), x, y, width, height);
        double x1 = 0.45 * width;
        double x2 = 0.55 * width;
        double y1 = 0.45 * height;
        double y2 = 0.55 * height;
        Path pathLR = new Path(new ArrayList<>(List.of(0.0, width)), new ArrayList<>(List.of(y2, y2)));
        Path pathRL = new Path(new ArrayList<>(List.of(width, 0.0)), new ArrayList<>(List.of(y1, y1)));
        Path pathUD = new Path(new ArrayList<>(List.of(x1, x1)), new ArrayList<>(List.of(0.0, height)));
        Path pathDU = new Path(new ArrayList<>(List.of(x2, x2)), new ArrayList<>(List.of(height, 0.0)));
        Path pathLD = new Path(new ArrayList<>(List.of(0.0, x1, x1)), new ArrayList<>(List.of(y2, y2, height)));
        Path pathLU = new Path(new ArrayList<>(List.of(0.0, x2, x2)), new ArrayList<>(List.of(y2, y2, 0.0)));
        Path pathRU = new Path(new ArrayList<>(List.of(width, x2, x2)), new ArrayList<>(List.of(y1, y1, 0.0)));
        Path pathRD = new Path(new ArrayList<>(List.of(width, x1, x1)), new ArrayList<>(List.of(y1, y1, height)));
        Path pathUL = new Path(new ArrayList<>(List.of(x1, x1, 0.0)), new ArrayList<>(List.of(0.0, y1, y1)));
        Path pathUR = new Path(new ArrayList<>(List.of(x1, x1, width)), new ArrayList<>(List.of(0.0, y2, y2)));
        Path pathDL = new Path(new ArrayList<>(List.of(x2, x2, 0.0)), new ArrayList<>(List.of(height, y1, y1)));
        Path pathDR = new Path(new ArrayList<>(List.of(x1, x1, width)), new ArrayList<>(List.of(height, y2, y2)), Color.red);
        ArrayList<Path> paths = new ArrayList<>(List.of(pathLR, pathRL, pathUD, pathDU, pathLD, pathLU, pathRU, pathRD, pathUL, pathUR, pathDL, pathDR));
        super.paths = paths;
        super.updatePaths();

    }

    @Override
    public void paint(Graphics g) {
        g.drawRect((int) this.x, (int) this.y, (int) width, (int)height);

        int x1 = (int) (0.4 * width) + (int) this.x;
        int x2 = (int) (0.6 * width) + (int) this.x;
        int y1 = (int) (0.4 * height) + (int) this.y;
        int y2 = (int) (0.6 * height) + (int) this.y;
        int w = (int) width + (int) this.x;
        int h = (int) height + (int) this.y;
        g.drawLine((int) this.x, y1, x1, y1);
        g.drawLine(x1, y1, x1, (int) this.y);
        g.drawLine(x2, (int) this.y, x2, y1);
        g.drawLine(x2, y1, w, y1);
        g.drawLine(w, y2, x2, y2);
        g.drawLine(x2, y2, x2, h);
        g.drawLine((int) this.x, y2, x1, y2);
        g.drawLine(x1, y2, x1, h);
    }
}
