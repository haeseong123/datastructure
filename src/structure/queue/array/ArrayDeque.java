package structure.queue.array;

import structure.queue.Queue;

public class ArrayDeque<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 16;

    private Object[] elements;
    private int size;

    private int head;
    private int tail;
    private int capacity;

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayDeque(int capacity) {
        this.capacity = (capacity > 0) ? capacity : DEFAULT_CAPACITY;
        this.elements = new Object[this.capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    private void resize(int newCapacity) {
        Object[] newElements = new Object[newCapacity];

        for (int i = 0, j = head; i < size; i++) {
            newElements[i] = elements[j % capacity];
        }

        capacity = newCapacity;
        elements = newElements;
        head = 0;
        tail = size;
    }


    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    public boolean offerLast(E e) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        elements[tail] = e;
        tail = increment(tail, capacity);
        size++;

        return true;
    }

    public boolean offerFirst(E e) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        elements[head = decrement(head, capacity)] = e;
        size++;
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) elements[head];
        elements[head] = null;
        head = increment(head, capacity);
        size--;

        if (isShrinkNeeded(size, capacity)) {
            resize(Math.max(DEFAULT_CAPACITY, capacity / 2));
        }

        return item;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) elements[tail = decrement(tail, capacity)];
        elements[tail] = null;
        size--;

        if (isShrinkNeeded(size, capacity)) {
            resize(Math.max(DEFAULT_CAPACITY, capacity / 2));
        }

        return item;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @SuppressWarnings("unchecked")
    public E peekFirst() {
        if (size == 0) {
            return null;
        }

        return (E) elements[head];
    }

    @SuppressWarnings("unchecked")
    public E peekLast() {
        if (size == 0) {
            return null;
        }

        final int last = decrement(tail, capacity);
        return (E) elements[last];
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
                int index = (i + head) % capacity;
                if (elements[index] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                int index = (i + head) % capacity;
                if (o.equals(elements[index])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            int index = (i + head) % capacity;
            elements[index] = null;
        }

        head = tail = size = 0;
    }

    private int decrement(int i, int capacity) {
        if (--i < 0) i = capacity - 1;
        return i;
    }

    private int increment(int i, int capacity) {
        if (++i >= capacity) i = 0;
        return i;
    }

    private boolean isShrinkNeeded(int size, int capacity) {
        return size < (capacity / 4);
    }
}
