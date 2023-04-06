package structure.queue.array;

import structure.queue.Queue;

/**
 * 동적 원형 큐
 */
public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;
    private int capacity;

    private int front;
    private int rear;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        elements = new Object[capacity];
        this.capacity = capacity;
        size = front = rear = 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        elements[rear] = e;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E oldValue = (E) elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        size--;

        if (capacity > DEFAULT_CAPACITY && size < (capacity / 4)) {
            resize(Math.min(DEFAULT_CAPACITY, capacity / 2));
        }

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return (E) elements[front];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        if (value == null) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            int index = (i + front) % capacity;
            if (value.equals(elements[index])) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            int index = (i + front) % capacity;
            elements[index] = null;
        }

        front = rear = size = 0;
    }

    private void resize(int newCapacity) {
        Object[] newElements = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % capacity];
        }

        capacity = newCapacity;
        elements = newElements;
        front = 0;
        rear = size;
    }
}
