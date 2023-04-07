package structure.heap;

import java.util.Comparator;

/**
 * 힙 클래스입니다.
 * <p>
 * 배열을 사용하여 이진 트리를 나타냅니다.
 */
public class Heap<E> {
    /**
     * 힙에서 사용하는 queue 배열의 디폴트 사이즈입니다.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * 해당 힙을 어떤 방식으로 정렬할지 정하는 comparator입니다.
     */
    private final Comparator<? super E> comparator;

    /**
     * queue는 완전 이진 트리를 표현할 배열입니다.
     * queue[n]의 두 자식은
     * queue[2n+1]과 queue[2(n+1)] 입니다.
     * comparator에 의해 정렬되며, comparator가 null인 경우
     * 요소의 자연적인 순서에 따라 정렬됩니다.
     */
    private Object[] queue;

    /**
     * 현재 queue에 들어있는 요소들의 개수를 나타냅니다.
     */
    private int size;

    public Heap() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public Heap(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public Heap(int capacity) {
        this(Math.max(capacity, DEFAULT_INITIAL_CAPACITY), null);
    }

    public Heap(int initialCapacity, Comparator<? super E> comparator) {
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
        this.size = 0;
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        if (size >= queue.length) {
            resize(queue.length * 2);
        }

        siftUp(size, e);
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        E result;

        if ((result = (E) queue[0]) != null) {
            E target = (E) queue[--size];
            queue[size] = null;

            if (size > 0) {
                siftDown(0, target);
            }
        }
        return result;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void siftDown(int idx, E target) {
        // comparator가 있으면 사용하고
        // 없으면 사용하지 않는다.
        if (comparator != null) {
            siftDownUsingComparator(idx, target, queue, size, comparator);
        } else {
            siftDownComparable(idx, target, queue, size);
        }
    }

    private void siftUp(int idx, E target) {
        // comparator가 있으면 사용하고
        // 없으면 사용하지 않는다.
        if (comparator != null) {
            siftUpUsingComparator(idx, target, queue, comparator);
        } else {
            siftUpComparable(idx, target, queue);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int idx, E target, Object[] es, Comparator<? super E> cmp) {
        while (idx > 0) {
            int parentIdx = (idx - 1) >>> 1;
            E parent = (E) es[parentIdx];
            if (cmp.compare(target, parent) >= 0) {
                break;
            }
            es[idx] = parent;
            idx = parentIdx;
        }
        es[idx] = target;
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E target, Object[] es) {
        Comparable<? super E> comp = (Comparable<? super E>) target;

        while (idx > 0) {
            int parentIdx = (idx - 1) >>> 1;
            E parent = (E) es[parentIdx];
            if (comp.compareTo(parent) >= 0)
                break;
            es[idx] = parent;
            idx = parentIdx;
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

    private void resize(int newCapacity) {
        Object[] newQueue = new Object[newCapacity];

        System.arraycopy(queue, 0, newQueue, 0, size);

        queue = newQueue;
    }
}
