package structure.queue.linked.doubled;

public class LinkedDequeNode<E> {
    private E data;
    private LinkedDequeNode<E> prev;
    private LinkedDequeNode<E> next;

    public LinkedDequeNode(LinkedDequeNode<E> prev, E data, LinkedDequeNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public LinkedDequeNode<E> getPrev() {
        return prev;
    }

    public void setPrev(LinkedDequeNode<E> prev) {
        this.prev = prev;
    }

    public LinkedDequeNode<E> getNext() {
        return next;
    }

    public void setNext(LinkedDequeNode<E> next) {
        this.next = next;
    }
}
