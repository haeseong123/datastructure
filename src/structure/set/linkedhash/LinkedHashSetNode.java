package structure.set.linkedhash;

public class LinkedHashSetNode<E> {
    private final int hash;
    private final E key;

    private LinkedHashSetNode<E> next; // Separate Chaining을 위한 next입니다.

    private LinkedHashSetNode<E> nextLink; // 순서를 유지하기 위한 link입니다.
    private LinkedHashSetNode<E> prevLink; // 순서를 유지하기 위한 link입니다.

    public LinkedHashSetNode(int hash, E key, LinkedHashSetNode<E> next) {
        this.hash = hash;
        this.key = key;
        this.next = next;

        this.nextLink = null;
        this.prevLink = null;
    }

    public int getHash() {
        return hash;
    }

    public E getKey() {
        return key;
    }

    public LinkedHashSetNode<E> getNext() {
        return next;
    }

    public void setNext(LinkedHashSetNode<E> next) {
        this.next = next;
    }

    public LinkedHashSetNode<E> getNextLink() {
        return nextLink;
    }

    public void setNextLink(LinkedHashSetNode<E> nextLink) {
        this.nextLink = nextLink;
    }

    public LinkedHashSetNode<E> getPrevLink() {
        return prevLink;
    }

    public void setPrevLink(LinkedHashSetNode<E> prevLink) {
        this.prevLink = prevLink;
    }
}
