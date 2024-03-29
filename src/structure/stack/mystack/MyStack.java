package structure.stack.mystack;

import structure.stack.Stack;

import static util.MyUtil.checkElementIndex;

public class MyStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public MyStack() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyStack(int capacity) {
        elements = new Object[capacity];
        size = 0;
    }

    public E push(E e) {
        if (size >= elements.length) {
            elements = grow();
        }
        elements[size++] = e;
        return e;
    }

    public E pop() {
        return remove(size - 1);
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        checkElementIndex(0, size);
        return (E) elements[size - 1];
    }

    @Override
    public int search(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return size - i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) {
                    return size - i;
                }
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public boolean empty() {
        return size <= 0;
    }

    public E remove(int index) {
        checkElementIndex(index, size);

        @SuppressWarnings("unchecked")
        E oldValue = (E) elements[index];
        fastRemove(index);
        return oldValue;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }

        fastRemove(index);
        return true;
    }

    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    // index 체크 안 하는 메서드
    private void fastRemove(int index) {
        if ((size - 1) == index) {
            elements[--size] = null;
        } else {
            Object[] newElements = new Object[size - 1];

            // index 기준 왼쪽 복사
            if (index > 0) {
                arrayCopy(elements, 0, newElements, 0, index);
            }

            // index 기준 오른쪽 복사
            arrayCopy(elements, index + 1, newElements, index, size - index - 1);

            // 교체
            elements = newElements;
            size--;
        }
    }

    private Object[] grow() {
        Object[] newElements = new Object[newCapacity(elements.length)];
        arrayCopy(elements, 0, newElements, 0, elements.length);
        return newElements;
    }

    private int newCapacity(int oldCapacity) {
        int newCapacity = oldCapacity << 1;

        if (newCapacity == 0) {
            return DEFAULT_CAPACITY;
        } else if (oldCapacity >= newCapacity) {
            // overflow
            return Integer.MAX_VALUE;
        }
        return newCapacity;
    }

    private void arrayCopy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
        checkElementIndex(srcPos, src.length);
        checkElementIndex(destPos, dest.length);
        checkElementIndex(srcPos + length - 1, src.length);
        checkElementIndex(destPos + length - 1, dest.length);

        for (int i = 0; i < length; i++) {
            dest[destPos + i] = src[srcPos + i];
        }

    }
}
