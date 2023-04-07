package structure.queue.linked.doubled;

import structure.queue.Queue;

public class LinkedDeque<E> implements Queue<E> {
    private LinkedDequeNode<E> first;
    private LinkedDequeNode<E> last;

    private int size;

    public LinkedDeque() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean offer(E e) {
        linkLast(e);
        return true;
    }

    public boolean offerLast(E e) {
        linkLast(e);
        return true;
    }

    public boolean offerFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollLast() {
        return (last == null) ? null : unlinkLast(last);
    }

    public E pollFirst() {
        return (first == null) ? null : unlinkFirst(first);
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekLast() {
        return (last == null) ? null : last.getData();
    }

    public E peekFirst() {
        return (first == null) ? null : first.getData();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        if (o == null) {
            for (LinkedDequeNode<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) {
                    return true;
                }
            }
        } else {
            for (LinkedDequeNode<E> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getData())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (LinkedDequeNode<E> x = first; x != null; ) {
            LinkedDequeNode<E> next = x.getNext();
            x.setPrev(null);
            x.setData(null);
            x.setNext(null);
            x = next;
        }

        first = last = null;
        size = 0;
    }

    private void linkLast(E e) {
        final LinkedDequeNode<E> l = last;
        final LinkedDequeNode<E> newNode = new LinkedDequeNode<E>(l, e, null);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size++;
    }

    private void linkFirst(E e) {
        final LinkedDequeNode<E> f = first;
        final LinkedDequeNode<E> newNode = new LinkedDequeNode<E>(null, e, f);
        first = newNode;

        if (f == null) {
            last = newNode;
        } else {
            f.setPrev(newNode);
        }
        size++;
    }

    private E unlinkLast(LinkedDequeNode<E> l) {
        E item = l.getData();
        final LinkedDequeNode<E> prev = l.getPrev();
        l.setData(null);
        l.setPrev(null);
        last = prev;

        if (prev == null) {
            first = null;
        } else {
            prev.setNext(null);
        }

        size--;
        return item;
    }

    private E unlinkFirst(LinkedDequeNode<E> f) {
        E item = f.getData();
        final LinkedDequeNode<E> next = f.getNext();
        f.setData(null);
        f.setNext(null);
        first = next;

        if (next == null) {
            last = null;
        } else {
            next.setPrev(null);
        }

        size--;
        return item;
    }
}
