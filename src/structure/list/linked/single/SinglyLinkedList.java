package structure.list.linked.single;

import structure.list.List;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import static util.MyUtil.myCheckIndex;

public class SinglyLinkedList<E> implements List<E>, Cloneable {
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
        myCheckIndex(index, size);

        if (index == 0) {
            linkFirst(e);
        } else if (index == (size - 1)) {
            linkLast(e);
        } else {
            linkBetween(e, index);
        }
    }

    @Override
    public E remove(int index) {
        myCheckIndex(index, size);

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
        myCheckIndex(index, size);
        return node(index).getData();
    }

    @Override
    public void set(int index, E e) {
        myCheckIndex(index, size);
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
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public E next() {
                current = current.getNext();
                return current.getData();
            }
        };
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        @SuppressWarnings("unchecked")
        SinglyLinkedList<? super E> clone = (SinglyLinkedList<? super E>) super.clone();

        clone.first = null;
        clone.last = null;
        clone.size = 0;

        for (Node<E> x = first; x != null; x = x.getNext()) {
            clone.addLast(x.getData());
        }

        return clone;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (Node<E> x = first; x != null; x = x.getNext()) {
            array[idx++] = x.getData();
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.getNext()) {
            result[i++] = x.getData();
        }
        return a;
    }


    public void sort() {
        /**
         *  Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
         *  정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable
         *  에러가 발생한다.
         *  만약 구현되어있을 경우 null로 파라미터를 넘기면
         *  Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (Node<E> x = first; x != null; x = x.getNext(), i++) {
            x.setData((E) a[i]);
        }
    }

    private Node<E> node(int index) {
        myCheckIndex(index, size);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.getNext();
        }
        return x;
    }

    private void linkFirst(E e) {
        first = new Node<>(e, first);
        size++;

        if (first.getNext() == null) {
            last = first;
        }
    }

    private void linkLast(E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            first = node;
        } else {
            last.setNext(node);
        }
        last = node;
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
