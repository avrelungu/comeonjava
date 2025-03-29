package avrel.collections;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class MyListIterator<T> implements Iterator {

    private List<T> source = null;
    private int index = 0;

    public MyListIterator(List<T> source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.source.size();
    }

    @Override
    public T next() {
        return this.source.get(index++);
    }

    @Override
    public void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
