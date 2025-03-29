package avrel.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyIterable implements Iterable<String>{
    private List<String> elements = new ArrayList<String>();

    public String add(String element) {
        this.elements.add(element);

        return element;
    }

    @Override
    public Iterator iterator() {
        return this.elements.iterator();
    }
}
