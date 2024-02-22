public class StackList<T> extends AbstractStack<T> {
    private Node<T> top;

    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(top);
        top = newNode;
    }

    @Override
    public T pop() {
        if (top == null) {
            return null;
        }
        T value = top.getValue();
        top = top.getNext();
        return value;
    }
}