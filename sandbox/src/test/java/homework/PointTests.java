package homework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance1() {
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(4.0, 5.0);
        Assert.assertEquals(p1.distance(p2), 5.0);

    }
    @Test
    public void testDistance2() {
        Point p1 = new Point(2.0, -1.0);
        Point p2 = new Point(6.0, 2.0);
        Assert.assertEquals(p2.distance(p1), 5.0);

    }
}
