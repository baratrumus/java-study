package ru.job4j.set;


import ru.job4j.list.DynamicArray;
import java.util.Iterator;

/**
 * Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать void add(E e) и реализовывать Iterable<E>.
 *  Коллекция не должна хранить дубликаты.
 *  Set - внутри для хранения данных использует обычные массивы.
 */

public class SimpleSet<E> implements Iterable<E>  {
    DynamicArray<E>  dA;

    SimpleSet(int maxSize) {
       dA  = new DynamicArray<>(maxSize);
    }

    public void add(E value) {
        if (!checkIfExists(value)) {
            dA.add(value);
        }
    }

    private boolean checkIfExists(E value) {
        boolean res = false; //Integer.valueOf(3).equals(null);

        Iterator it = dA.iterator();
        while (it.hasNext()) {
            E next = (E) it.next();
            if ((value == null) && (next == null)) {
                res = true;
                break;
            } else if ((value != null) && (value.equals(next))) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return dA.iterator();
    }

    public E get(int index) {
        return dA.get(index);
    }

    public int getSize() {
        return dA.getSize();
    }
}
