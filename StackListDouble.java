public class StackListDouble<T> extends AbstractStack<T> {
    private Node<T> top;
    private Node<T> bottom;

    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        if (bottom == null) {
            bottom = newNode;
        }
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
        if (top == null) {
            bottom = null;
        }
        return value;
    }
}