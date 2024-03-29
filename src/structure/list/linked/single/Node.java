package structure.list.linked.single;

public class Node<E> {
    private E data;
    private Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }

    Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
