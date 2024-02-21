public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T val) {
        value = val;
        next = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setValue(T newValue) {
        value = newValue;
    }

    public void setNext(Node<T> nextNode) {
        next = nextNode;
    }

}
