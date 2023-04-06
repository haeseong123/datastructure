package structure.list.linked.single;

import structure.list.List;

import java.util.Iterator;

import static util.MyUtil.checkElementIndex;
import static util.MyUtil.checkPositionIndex;

public class SinglyLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public SinglyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }

    public void addLast(E e) {
        linkLast(e);
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void add(int index, E e) {
        checkPositionIndex(index, size);

        if (index == 0) {
            linkFirst(e);
        } else if (index == size) {
            linkLast(e);
        } else {
            linkBetween(e, index);
        }
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);

        if (index == 0) {
            return unlinkFirst(node(0));
        } else if (index == (size - 1)) {
            return unlinkLast(node(index), index);
        } else {
            return unlinkMiddle(node(index), index);
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) {
                    unlinkMiddle(x, index);
                    return true;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getData())) {
                    unlinkMiddle(x, index);
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, size);
        return node(index).getData();
    }

    @Override
    public void set(int index, E e) {
        checkElementIndex(index, size);
        node(index).setData(e);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getData())) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.getNext();
            x.setData(null);
            x.setNext(null);
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> next = first;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public E next() {
                E data = next.getData();
                next = next.getNext();
                return data;
            }
        };
    }

    private Node<E> node(int index) {
        // assert isElementIndex(index);

        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.getNext();
        }
        return x;
    }

    private void linkFirst(E e) {
        first = new Node<>(e, first);

        if (first.getNext() == null) {
            last = first;
        }
        size++;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, null);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size++;
    }

    private void linkBetween(E e, int eIndex) {
        // assert first_index < index < last_index
        Node<E> prev = node(eIndex - 1);
        Node<E> newNode = new Node<>(e, prev.getNext());
        prev.setNext(newNode);
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        E oldE = f.getData();
        Node<E> next = f.getNext();
        f.setData(null);
        f.setNext(null);
        first = next;
        if (first == null) {
            last = null;
        }
        size--;
        return oldE;
    }

    private E unlinkLast(Node<E> l, int lIndex) {
        // assert l == last && l != null;
        if (lIndex == 0) {
            return unlinkFirst(l);
        }
        E oldE = l.getData();
        Node<E> prev = node(lIndex - 1);
        l.setData(null);
        l.setNext(null);
        last = prev;
        last.setNext(null);
        size--;
        return oldE;
    }

    private E unlinkMiddle(Node<E> x, int xIndex) {
        // assert x != null;
        if (xIndex == 0) {
            return unlinkFirst(x);
        } else if (xIndex == (size - 1)) {
            return unlinkLast(x, xIndex);
        }

        final E element = x.getData();
        final Node<E> next = x.getNext();
        final Node<E> prev = node(xIndex - 1);
        x.setData(null);
        x.setNext(null);
        prev.setNext(next);
        size--;
        return element;
    }
}
