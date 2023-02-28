package structure;

import java.util.Iterator;

// 고정 크기 배열
class StaticArray<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private final Object[] elements;
    private int size = 0;

    // 생성자
    public StaticArray() {
        this.capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

    public StaticArray(int capacity) {
        if (capacity >= 0) {
            this.capacity = capacity;
            elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal argument: " +
                    capacity);
        }
    }

    // 메서드
    public boolean add(E e) {
        checkIndex(size, capacity);
        elements[size++] = e;
        return true;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public E remove(int index) {
        checkIndex(index, size);
        @SuppressWarnings("unchecked") E oldValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index, size);
        return (E) elements[index];
    }

    public void clear() {
        size = 0;
    }

    private void checkIndex(int index, int length) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index should be up 0");
        } else if (index >= length) {
            throw new IndexOutOfBoundsException("Index should be within size");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && elements[currentIndex] != null;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                return (E) elements[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}