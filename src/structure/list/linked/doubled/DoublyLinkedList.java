package structure.list.linked.doubled;

import structure.list.List;

import java.util.Iterator;

import static util.MyUtil.checkElementIndex;
import static util.MyUtil.checkPositionIndex;

public class DoublyLinkedList<E> implements List<E> {
    private DoubleNode<E> first;
    private DoubleNode<E> last;
    private int size;

    public DoublyLinkedList() {
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
        linkLast(e);
        return true;
    }

    @Override
    public void add(int index, E e) {
        checkPositionIndex(index, size);

        if (index == size) {
            linkLast(e);
        } else {
            linkBefore(e, doubleNode(index));
        }
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);
        return unlink(doubleNode(index));
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (DoubleNode<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (DoubleNode<E> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getData())) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, size);
        return doubleNode(index).getData();
    }

    @Override
    public void set(int index, E e) {
        checkElementIndex(index, size);
        DoubleNode<E> x = doubleNode(index);
        x.setData(e);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (DoubleNode<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (DoubleNode<E> x = first; x != null; x = x.getNext()) {
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
        return last == null;
    }

    @Override
    public void clear() {
        for (DoubleNode<E> x = first; x != null; ) {
            DoubleNode<E> next = x.getNext();
            x.setData(null);
            x.setPrev(null);
            x.setNext(null);
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private DoubleNode<E> next = first;

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

    /**
     * e를 첫 번째 요소로 연결
     */
    private void linkFirst(E e) {
        final DoubleNode<E> f = first;
        final DoubleNode<E> newNode = new DoubleNode<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.setPrev(newNode);
        }
        size++;
    }

    /**
     * e를 마지막 요소로 연결
     */
    private void linkLast(E e) {
        final DoubleNode<E> l = last;
        final DoubleNode<E> newNode = new DoubleNode<>(l, e, null);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size++;
    }

    /**
     * null이 아닌 노드 succ 앞에
     * 요소 e를 삽입합니다.
     */
    private void linkBefore(E e, DoubleNode<E> succ) {
        // asset succ != null;
        final DoubleNode<E> pred = succ.getPrev();
        final DoubleNode<E> newNode = new DoubleNode<>(pred, e, succ);
        succ.setPrev(newNode);
        if (pred == null) {
            first = newNode;
        } else {
            pred.setNext(newNode);
        }
        size++;
    }

    /**
     * null이 아닌 x를 받아서 Unlink한다.
     */
    private E unlink(DoubleNode<E> x) {
        // assert x != null;
        final E element = x.getData();
        final DoubleNode<E> next = x.getNext();
        final DoubleNode<E> prev = x.getPrev();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
            x.setPrev(null);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
            x.setNext(null);
        }

        x.setData(null);
        size--;
        return element;
    }

    /**
     * 해당 인덱스의 노드를 반환합니다.
     */
    private DoubleNode<E> doubleNode(int index) {
        // assert isElementIndex(index);

        DoubleNode<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.getNext();
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.getPrev();
            }
        }
        return x;
    }
}
