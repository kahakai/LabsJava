package homework.lab9;

import java.util.Comparator;

class KeyComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

public class Index {

}