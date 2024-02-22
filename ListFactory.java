public class ListFactory {
    public static AbstractStack<Integer> createList(AbstractStack<Integer> stack, int choice) {
        switch (choice) {
            case 1:
                return stack; // No se necesita modificar stack para usar lista simplemente encadenada
            case 2:
                return new StackListDouble<>();
            default:
                throw new IllegalArgumentException("Opción de lista no válida: " + choice);
        }
    }
}