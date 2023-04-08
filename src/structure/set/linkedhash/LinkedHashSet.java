package structure.set.linkedhash;

import structure.set.Set;

import java.util.Iterator;

/**
 * HashSet의 구현과 비슷합니다.
 */
public class LinkedHashSet<E> implements Set<E>, Iterable<E> {
    private final static int DEFAULT_CAPACITY = 1 << 4;
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;

    LinkedHashSetNode<E>[] table;
    private int size;
    // 순서 유지를 위한 link
    private LinkedHashSetNode<E> head;
    private LinkedHashSetNode<E> tail;

    @SuppressWarnings("unchecked")
    public LinkedHashSet() {
        table = (LinkedHashSetNode<E>[]) new LinkedHashSetNode[DEFAULT_CAPACITY];
        size = 0;
        head = null;
        tail = null;
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    @Override
    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;
    }

    @Override
    public boolean contains(Object o) {
        int idx = hash(o) % table.length;
        LinkedHashSetNode<E> node = table[idx];

        while (node != null) {
            if (o == node.getKey() || (node.getKey().equals(o))) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) continue;
                table[i].setNext(null);
                table[i].setNextLink(null);
                table[i].setPrevLink(null);
                table[i] = null;
            }
            size = 0;
            head = tail = null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LinkedHashSetNode<E> next = head;

            @Override
            public boolean hasNext() {
                return next != null && next.getKey() != null;
            }

            @Override
            public E next() {
                E key = next.getKey();
                next = next.getNextLink();
                return key;
            }
        };
    }

    private E add(int hash, E key) {
        int idx = hash % table.length;
        LinkedHashSetNode<E> node = table[idx];
        LinkedHashSetNode<E> newNode = new LinkedHashSetNode<>(hash, key, null);

        // 삽입하고자 하는 위치에 아무도 없다면 거기에 그냥 넣으면 됨
        if (node == null) {
            table[idx] = newNode;
        }
        // 해시 충돌 발생하면 해당 요소에 있는 노드의 맨 뒤에 추가함
        else {
            LinkedHashSetNode<E> prev = null;

            while (node != null) {
                // 중복되면 안 됨
                if (node.getHash() == hash && (node.getKey() == key || node.getKey().equals(key))) {
                    return key;
                }
                prev = node;
                node = node.getNext();
            }

            prev.setNext(newNode);
        }
        size++;

        // 다 했으면 순서를 유지하기 위해 tail에 해당 노드를 추가함
        linkLastNode(newNode);
        if (size >= DEFAULT_LOAD_FACTOR * table.length) {
            resize();
        }
        return null;
    }

    private void linkLastNode(LinkedHashSetNode<E> n) {
        LinkedHashSetNode<E> t = tail;
        n.setPrevLink(t);
        tail = n;

        if (t == null) {
            head = n;
        } else {
            t.setNextLink(n);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int oldCapacity = table.length;
        int newCapacity = oldCapacity << 1;
        LinkedHashSetNode<E>[] newTab = (LinkedHashSetNode<E>[]) new LinkedHashSetNode[newCapacity];

        for (int i = 0; i < table.length; i++) {
            // 옛날 capa로 나누었을 때 몫이 홀수면 (옛날 idx + 옛날 capa)
            // 옛날 capa로 나누었을 때 몫이 짝수면 (옛날 idx)
            LinkedHashSetNode<E> node = table[i];

            if (node == null) continue;

            table[i] = null;
            LinkedHashSetNode<E> lowHead = null, lowTail = null;
            LinkedHashSetNode<E> highHead = null, highTail = null;

            while (node != null) {
                // 몫이 짝수인 경우 low에 배치
                if ((node.getHash() & oldCapacity) == 0) {
                    if (lowHead == null) {
                        lowHead = node;
                    } else {
                        lowTail.setNext(node);
                    }
                    lowTail = node;
                }
                // 몫이 홀수인 경우 high에 배치
                else {
                    if (highHead == null) {
                        highHead = node;
                    } else {
                        highTail.setNext(node);
                    }
                    highTail = node;
                }

                node = node.getNext();
            }
            if (lowTail != null) {
                lowTail.setNext(null);
                newTab[i] = lowHead;
            }
            if (highTail != null) {
                highTail.setNext(null);
                newTab[i + oldCapacity] = highHead;
            }
        }
        table = newTab;
    }

    private Object remove(int hash, Object key) {
        int idx = hash % table.length;
        LinkedHashSetNode<E> node = table[idx];
        LinkedHashSetNode<E> prev = null;

        while (node != null) {
            // 같은 거 찾으면 ...
            if (node.getHash() == hash && (node.getKey() == key || node.getKey().equals(key))) {
                if (prev == null) {
                    table[idx] = node.getNext();
                } else {
                    prev.setNext(node.getNext());
                }
                unlinkNode(node);
                size--;
                return node;
            }
            // 같은 거 못 찾으면 ...
            prev = node;
            node = node.getNext();
        }
        return null;
    }

    private void unlinkNode(LinkedHashSetNode<E> n) {
        LinkedHashSetNode<E> prev = n.getPrevLink();
        LinkedHashSetNode<E> next = n.getNextLink();

        if (prev == null) {
            head = next;
        } else {
            prev.setNextLink(next);
        }

        if (next == null) {
            tail = prev;
        } else {
            next.setPrevLink(prev);
        }

        n.setNextLink(null);
        n.setPrevLink(null);
    }
}
