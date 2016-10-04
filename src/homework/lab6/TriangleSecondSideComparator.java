package homework.lab6;

import java.util.Comparator;

/**
 * Created by artem on 05.10.2016.
 */
public class TriangleSecondSideComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle o1, Triangle o2) {
        return Double.compare(o1.getB(), o2.getB());
    }
}