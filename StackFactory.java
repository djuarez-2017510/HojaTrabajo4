public class StackFactory {
    public static AbstractStack<Integer> createStack(int choice) {
        switch (choice) {
            case 1:
                return new StackArrayList<Integer>();
            case 2:
                return new StackVector<Integer>();
            case 3:
                return new StackList<Integer>();
            default:
                throw new IllegalArgumentException("Opción de pila no válida: " + choice);
        }
    }
}