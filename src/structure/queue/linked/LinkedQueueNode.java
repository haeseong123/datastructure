package structure.queue.linked;

public class LinkedQueueNode<E> {
    private E data;
    private LinkedQueueNode<E> next;

    public LinkedQueueNode(E data) {
        this(data, null);
    }

    public LinkedQueueNode(E data, LinkedQueueNode<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public LinkedQueueNode<E> getNext() {
        return next;
    }

    public void setNext(LinkedQueueNode<E> next) {
        this.next = next;
    }
}
