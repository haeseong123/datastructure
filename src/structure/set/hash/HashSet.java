package structure.set.hash;

import structure.set.Set;

public class HashSet<E> implements Set<E> {

    /**
     * 버킷의 기본 크기입니다.
     * <p>
     * 자바에서는 해시 기반의 자료구조에서 초기 용량을 2의 제곱수로 설정하는 것이 일반적으로 권장되는데,
     * <p>
     * 이는 해시 함수의 분배가 고르게 이루어지고 충돌이 최소화되는데 도움을 주기 때문입니다.
     * <p>
     * 이에 따라 버킷의 기본 크기는 2^4입니다.
     */
    private final static int DEFAULT_CAPACITY = 1 << 4;

    /**
     * 버킷의 크기를 키울지 말지 정하는 기준입니다.
     * <p>
     * 부하율이 3/4를 넘으면 버킷을 키웁니다.
     * */
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;

    private HashSetNode<E>[] table;
    private int size;

    /**
     * 주어진 key 객체의 해시 코드를 계산하여 반환하는 함수입니다.
     * 메서드 내부에서 다음과 같은 작업을 수행합니다.
     * <p>
     * 1. `int h`: 정수형 변수 'h'를 선언합니다.
     * <p>
     * 2. `(key == null) ? 0 : ...`: key가 null인 경우 0을 반환하고 그렇지 않은 경우 나머지 부분을 실행합니다.
     * <p>
     * 3. `(h = key.hashCode())`: key 객체의 'hashCode()' 메서드를 호출하여 그 결과를 'h' 변수에 저장합니다. 이는 key 객체의 해시 코드를 얻는 작업입니다.
     * <p>
     * 4. `^ (h >>> 16)`: 'h' 변수의 값을 16비트 오른쪽으로 비트 시프트하여 얻은 결과와 'h' 변수를 비트 XOR(^) 연산합니다.
     * 이는 해시 코드의 상위 16비트와 하위 16비트를 서로 혼합하여 충돌을 최소화하는 작업입니다.
     * <p>
     * 5. 최종적으로 계산된 해시 코드를 반환합니다.
     */
    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @SuppressWarnings("unchecked")
    public HashSet() {
        table = (HashSetNode<E>[]) new HashSetNode[DEFAULT_CAPACITY];
        size = 0;
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
        HashSetNode<E> temp = table[idx];

        while (temp != null) {
            if (o == temp.getKey() || (o != null && (o.equals(temp.getKey())))) {
                return true;
            }
            temp = temp.getNext();
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
        if (table != null && table.length > 0 && size > 0) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) continue;
                table[i].setNext(null);
                table[i] = null;
            }
            size = 0;
        }
    }

    /**
     * 정상적으로 추가되는 경우 null을 반환합니다.
     */
    private E add(int hash, E key) {
        int idx = hash % table.length;

        if (table[idx] == null) {
            table[idx] = new HashSetNode<>(hash, key, null);
        } else {
            HashSetNode<E> curr = table[idx];
            HashSetNode<E> prev = null;

            while (curr != null) {
                if ((curr.getHash() == hash) && (curr.getKey() == key || curr.getKey().equals(key))) {
                    return key;
                }
                prev = curr;
                curr = curr.getNext();
            }

            prev.setNext(new HashSetNode<>(hash, key, null));
        }
        size++;

        if (size >= DEFAULT_LOAD_FACTOR * table.length) {
            resize();
//            resize2();
        }
        return null;
    }

    /**
     * 정상적으로 삭제된 경우 Object를 반환합니다.
     */
    private Object remove(int hash, Object key) {
        int idx = hash % table.length;

        HashSetNode<E> node = table[idx];
        HashSetNode<E> removedNode = null;
        HashSetNode<E> prev = null;

        if (node == null) return null;

        // 해당 인덱스에 연결되어 있는 노드들을 가지고 작업을 진행합니다.
        while (node != null) {
            // 같은 노드를 찾았다면...
            if (node.getHash() == hash && (node.getKey() == key || node.getKey().equals(key))) {
                removedNode = node;

                // 해당 노드의 이전 노드가 존재하지 않는다면...
                // == 해당 노드가 head 노드라면...
                if (prev == null) {
                    table[idx] = node.getNext();
                }
                // 해당 노드의 이전 노드가 존재한다면...
                else {
                    prev.setNext(node.getNext());
                }
                node.setNext(null);

                size--;
                break;
            }
            prev = node;
            node = node.getNext();
        }
        return removedNode;
    }

    /**
     * 버킷의 용량이 2^n일 경우에만 사용 가능합니다.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int oldCapacity = table.length;
        int newCapacity = oldCapacity << 1;
        final HashSetNode<E>[] newTab = (HashSetNode<E>[]) new HashSetNode[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            HashSetNode<E> curr = table[i];

            if (curr == null) continue;

            table[i] = null; // GC

            if (curr.getNext() == null) {
                // 노드가 단 하나일 경우
                newTab[curr.getHash() & (newCapacity - 1)] = curr;
            } else {
                // 노드가 두 개 이상 연결되어 있을 경우
                HashSetNode<E> lowHead = null, lowTail = null;
                HashSetNode<E> highHead = null, highTail = null;
                HashSetNode<E> next;

                do {
                    next = curr.getNext();

                    // oldCapacity의 몫이 짝수일 경우 low에 배치됨
                    if ((curr.getHash() & oldCapacity) == 0) {
                        if (lowHead == null) {
                            lowHead = curr;
                        } else {
                            lowTail.setNext(curr);
                        }
                        lowTail = curr;
                    } else {
                        if (highHead == null) {
                            highHead = curr;
                        } else {
                            highTail.setNext(curr);
                        }
                        highTail = curr;
                    }
                } while ((curr = next) != null);

                if (lowTail != null) {
                    lowTail.setNext(null);
                    newTab[i] = lowHead;
                }
                if (highTail != null) {
                    highTail.setNext(null);
                    newTab[i + oldCapacity] = highHead;
                }
            }
        }
        table = newTab;
    }

    /**
     * 버킷 용량과 상관없이 사용 가능합니다.
     */
    @SuppressWarnings("unchecked")
    private void resize2() {
        int newCapacity = table.length << 1;
        final HashSetNode<E>[] newTable = (HashSetNode<E>[]) new HashSetNode[newCapacity];

        for (int i = 0; i < table.length; i++) {
            // 각 인덱스의 첫 번째 노드(head)
            HashSetNode<E> curr = table[i];

            if (curr == null) continue;

            table[i] = null; // GC

            HashSetNode<E> nextNode = curr.getNext();
            curr.setNext(null);

            // 현재 인덱스에 있는 연결된 노드들을 순회함
            while (curr != null) {
                int idx = curr.getHash() % newCapacity;

                /*
                 * 새로 담을 index에 노드가 이미 존재하는 경우
                 * 다시 말해, 해시 충돌이 일어나는 경우
                 * */
                if (newTable[idx] != null) {
                    HashSetNode<E> tail = newTable[idx];

                    while (tail.getNext() != null) {
                        tail = tail.getNext();
                    }
                    tail.setNext(curr);
                }
                // 충돌이 없는 경우
                else {
                    newTable[idx] = curr;
                }

                curr = nextNode;
            }
        }
        table = newTable;
    }
}
