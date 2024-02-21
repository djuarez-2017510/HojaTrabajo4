import java.util.ArrayList;

public class StackArrayList<T> extends AbstractStack<T> {
    private ArrayList<T> list;

    public StackArrayList() {
        list = new ArrayList<>();
    }

    @Override
    public void push(T value) {
        list.add(value);
    }

    @Override
    public T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(list.size() - 1);
    }

}
