package structure.list.linked.doubled;

public class DoubleNode<E> {
    private E data;
    private DoubleNode<E> prev;
    private DoubleNode<E> next;

    DoubleNode(DoubleNode<E> prev, E data, DoubleNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    DoubleNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public DoubleNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode<E> prev) {
        this.prev = prev;
    }

    public DoubleNode<E> getNext() {
        return next;
    }

    public void setNext(DoubleNode<E> next) {
        this.next = next;
    }
}
