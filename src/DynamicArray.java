import java.util.Iterator;

public class DynamicArray<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    public DynamicArray() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public DynamicArray(int capacity) {
        if (capacity >= 0) {
            elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal argument: " +
                    capacity);
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index, size);
        return (E) elements[index];
    }

    public boolean add(E e) {
        if (size >= elements.length) {
            elements = grow();
        }
        elements[size++] = e;
        return true;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        fastRemove(index);
        return true;
    }

    public E remove(int index) {
        checkIndex(index, size);

        @SuppressWarnings("unchecked")
        E oldValue = (E) elements[index];
        fastRemove(index);

        return oldValue;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int to = size, i = size = 0; i < to; i++)
            elements[i] = null;
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

    private Object[] grow() {
        int newCapacity = getNewCapacity(elements.length);
        return arrayCopy(elements, 0,
                new Object[newCapacity], 0,
                elements.length);
    }

    private int getNewCapacity(int oldCapacity) {
        // old 케파가 굳이 바뀌지 않아도 되는 경우
        if (oldCapacity - size > 0) {
            return oldCapacity;
        }

        int newCapacity = oldCapacity << 1;

        // old 케파가 0인 경우
        if (newCapacity == 0) {
            return DEFAULT_CAPACITY;
        } else if (newCapacity - oldCapacity < 0) {
            // overflow
            return Integer.MAX_VALUE;
        } else {
            return newCapacity;
        }
    }

    private void fastRemove(int index) {
        if ((size - 1) == index) {
            elements[--size] = null;
        } else {
            Object[] newArray = new Object[size - 1];

            if (index > 0) {
                newArray = arrayCopy(elements, 0, newArray, 0, index);
            }
            newArray = arrayCopy(elements, index + 1, newArray, index, size - index - 1);
            elements = newArray;
            size--;
        }
    }

    private Object[] arrayCopy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        checkIndex(srcPos + length - 1, src.length);
        checkIndex(destPos + length - 1, dest.length);

        for (int i = 0; i < length; i++) {
            dest[destPos + i] = src[srcPos + i];
        }

        return dest;
    }

    private void checkIndex(int index, int size) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index should be bigger than 0");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("index should be within size");
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
        };
    }
}
