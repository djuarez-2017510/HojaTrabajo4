import java.util.Stack;

public class StackVector<T> extends AbstractStack<T> {
    private Stack<T> stack;

    public StackVector() {
        stack = new Stack<>();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }
}

