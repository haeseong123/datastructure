package structure.stack;

import structure.list.array.DynamicArray;

public class StackExtendArrayList<E> extends DynamicArray<E> implements Stack<E> {

    public StackExtendArrayList() {
        super();
    }

    @Override
    public E push(E item) {
        add(item);
        return item;
    }

    @Override
    public E pop() {
        int length = size();
        return remove(length - 1);
    }

    @Override
    public E peek() {
        int length = size();
        return get(length - 1);
    }

    @Override
    public int search(Object o) {
        return indexOf(o);
    }

    @Override
    public boolean empty() {
        return isEmpty();
    }
}
