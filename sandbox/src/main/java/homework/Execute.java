package homework;

public class Execute {
    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 4.0;
        p1.y = 4.0;
        Point p2 = new Point();
        p2.x = 7.0;
        p2.y = 8.0;
        System.out.println("Расстояние между точками равно " + distance(p1, p2));
    }

    public static double distance(Point p1, Point p2) {
        double lx = p2.x - p1.x;
        double ly = p2.y - p1.y;
        double z = Math.pow(lx, 2) + Math.pow(ly, 2);
        return Math.sqrt(z);

    }
}