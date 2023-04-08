package structure.set.hash;

public class HashSetNode<E> {
    private final int hash;
    private final E key;
    private HashSetNode<E> next;

    public HashSetNode(int hash, E key, HashSetNode<E> next) {
        this.hash = hash;
        this.key = key;
        this.next = next;
    }

    public int getHash() {
        return hash;
    }

    public E getKey() {
        return key;
    }

    public HashSetNode<E> getNext() {
        return next;
    }

    public void setNext(HashSetNode<E> next) {
        this.next = next;
    }
}
