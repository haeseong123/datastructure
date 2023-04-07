package structure.queue.priority;

import structure.queue.Queue;

import java.util.Comparator;

public class PriorityQueue<E> implements Queue<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private final Comparator<? super E> comparator;
    private Object[] queue;
    private int size;

    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public PriorityQueue(int capacity) {
        this(Math.max(capacity, DEFAULT_INITIAL_CAPACITY), null);
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
        this.size = 0;
    }

    @Override
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (size >= queue.length) {
            resize(queue.length * 2);
        }

        siftUp(size, e);
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        E result;

        if ((result = (E) queue[0]) != null) {
            E target = (E) queue[--size];
            queue[size] = null;

            if (size != 0) {
                siftDown(0, target);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (queue[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(queue[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            queue[i] = null;
        }
        size = 0;
    }

    private void resize(int newCapacity) {
        Object[] newQueue = new Object[newCapacity];

        System.arraycopy(queue, 0, newQueue, 0, size);

        queue = newQueue;
    }

    private void siftUp(int idx, E target) {
        if (comparator == null) {
            siftUpComparable(idx, target, queue);
        } else {
            siftUpUsingComparator(idx, target, queue, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E target, Object[] es) {
        Comparable<? super E> comp = (Comparable<? super E>) target;

        while (idx > 0) {
            int parentIdx = (idx - 1) >>> 1;
            Object o = es[parentIdx];
            if (comp.compareTo((E) o) >= 0) {
                break;
            }
            es[idx] = o;
            idx = parentIdx;
        }
        es[idx] = comp;
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int idx, E target, Object[] es, Comparator<? super E> cmp) {
        while (idx > 0) {
            int parentIdx = (idx - 1) >>> 1;
            Object parent = es[parentIdx];
            if (cmp.compare(target, (E) parent) >= 0) {
                break;
            }
            es[idx] = parent;
            idx = parentIdx;
        }
        es[idx] = target;
    }

    private void siftDown(int idx, E target) {
        if (comparator == null) {
            siftDownComparable(idx, target, queue, size);
        } else {
            siftDownUsingComparator(idx, target, queue, size, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target, Object[] es, int size) {
        Comparable<? super E> comp = (Comparable<? super E>) target;
        int half = size >>> 1;

        while (idx < half) {
            int childIdx = (idx << 1) + 1;
            int rightIdx = childIdx + 1;
            Object c = es[childIdx];

            if (rightIdx < size &&
                    ((Comparable<? super E>) c).compareTo((E) es[rightIdx]) > 0) {
                c = es[childIdx = rightIdx];
            }
            if (comp.compareTo((E) c) <= 0) {
                break;
            }
            es[idx] = c;
            idx = childIdx;
        }
        es[idx] = comp;
    }

    @SuppressWarnings("unchecked")
    private void siftDownUsingComparator(int idx, E target, Object[] es, int size, Comparator<? super E> cmp) {
        int half = size >>> 1;
        while (idx < half) {
            int childIdx = (idx << 1) + 1;
            int rightIdx = childIdx + 1;
            Object c = es[childIdx];

            if (rightIdx < size && cmp.compare((E) c, (E) es[rightIdx]) > 0) {
                c = es[childIdx = rightIdx];
            }
            if (cmp.compare(target, (E) c) <= 0) {
                break;
            }
            es[idx] = c;
            idx = childIdx;
        }
        es[idx] = target;
    }
}
