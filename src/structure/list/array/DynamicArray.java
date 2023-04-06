package structure.list.array;

import structure.list.List;

import java.util.Iterator;

import static util.MyUtil.myCheckIndex;

public class DynamicArray<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public DynamicArray() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public DynamicArray(int capacity) {
        if (capacity >= 0) {
            elementData = new Object[capacity];
            size = 0;
        } else {
            throw new IllegalArgumentException("Illegal argument: " +
                    capacity);
        }
    }

    @Override
    public boolean add(E e) {
        if (size >= elementData.length) {
            elementData = grow();
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E e) {
        myCheckIndex(index, size);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(
                elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = e;
        size++;
    }

    @Override
    public E remove(int index) {
        myCheckIndex(index, size);

        @SuppressWarnings("unchecked")
        E oldValue = (E) elementData[index];
        fastRemove(index);

        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }
        fastRemove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        myCheckIndex(index, size);
        return (E) elementData[index];
    }

    @Override
    public void set(int index, E e) {
        myCheckIndex(index, size);
        elementData[index] = e;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
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
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && elementData[currentIndex] != null;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                return (E) elementData[currentIndex++];
            }
        };
    }

    private Object[] grow() {
        Object[] newEs = new Object[newCapacity(elementData.length)];
        System.arraycopy(elementData, 0, newEs, 0, elementData.length);
        return newEs;
    }

    private int newCapacity(int oldCapacity) {
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

    // 인덱스 범위 검사 안 함
    private void fastRemove(int index) {
        if ((size - 1) > index)
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size--] = null;
    }
}
