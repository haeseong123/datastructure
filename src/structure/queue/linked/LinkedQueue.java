package structure.queue.linked;

import structure.queue.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private LinkedQueueNode<E> first;
    private LinkedQueueNode<E> last;
    private int size;

    public LinkedQueue() {
        this(null, null, 0);
    }

    private LinkedQueue(LinkedQueueNode<E> first,
                        LinkedQueueNode<E> last,
                        int size) {
        this.first = first;
        this.last = last;
        this.size = size;
    }

    @Override
    public boolean offer(E e) {
        final LinkedQueueNode<E> newNode = new LinkedQueueNode<>(e);
        final LinkedQueueNode<E> l = last;
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size++;
        return true;
    }

    // 맨 처음 요소 뽑아내기 없으면 null
    @Override
    public E poll() {
        if (first == null) {
            return null;
        }

        final E element = first.getData();
        final LinkedQueueNode<E> next = first.getNext();
        first.setData(null);
        first.setNext(null);
        first = next;
        if (next == null) {
            last = null;
        }
        size--;
        return element;
    }

    @Override
    public E peek() {
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
            for (LinkedQueueNode<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) {
                    return true;
                }
            }
        } else {
            for (LinkedQueueNode<E> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getData())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (LinkedQueueNode<E> x = first; x != null; ) {
            LinkedQueueNode<E> next = x.getNext();
            x.setData(null);
            x.setNext(null);
            x = next;
        }
        first = last = null;
        size = 0;
    }
}
