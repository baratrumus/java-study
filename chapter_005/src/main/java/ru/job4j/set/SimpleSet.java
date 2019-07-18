package ru.job4j.set;


import ru.job4j.list.DynamicArray;

import java.util.Iterator;

/**
 * Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать void add(E e) и реализовывать Iterable<E>.
 *  Коллекция не должна хранить дубликаты.
 *  Set - внутри для хранения данных использует обычные массивы.
 */

public class SimpleSet<E> implements Iterable<E>  {
    int size;
    DynamicArray<E>  dA;

    SimpleSet(int size) {
       dA  = new DynamicArray<>(size);
       this.size = size;
    }

    public void add(E value) {
        if (!checkIfExists(value)) {
            dA.add(value);
        }
    }

    private boolean checkIfExists(E value) {
        boolean res = false;
        Iterator it = dA.iterator();
        while (it.hasNext()) {
            if (value.equals(it.next()))  {
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
        return this.size;
    }
}
