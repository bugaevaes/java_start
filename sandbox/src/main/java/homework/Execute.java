package homework;

public class Execute {
    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 4.0;
        p1.y = 4.0;
        Point p2 = new Point();
        p2.x = 7.0;
        p2.y = 8.0;
        System.out.println("Расстояние между точками равно " + p1.distance(p2));

    }
}