package homework;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double lx = p.x - this.x;
        double ly = p.y - this.y;
        double z = Math.pow(lx, 2) + Math.pow(ly, 2);
        return Math.sqrt(z);

    }

   }
