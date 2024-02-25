import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            int stackChoice = obtenerOpcion("Seleccione la implementación de la pila (1. ArrayList, 2. Vector, 3. Lista):", scanner, 1, 3);
            AbstractStack<Integer> stack = StackFactory.createStack(stackChoice);
            if (stackChoice == 3) {
                int listChoice = obtenerOpcion("Seleccione la implementación de la lista (1. Simplemente encadenada, 2. Doblemente encadenada):", scanner, 1, 2);
                stack = ListFactory.createList(stack, listChoice);
            }

            Calculadora calculator = Calculadora.getInstance(stack);
            calculator.calculate();
            System.out.println("¿Desea continuar? (1. Sí, 2. No):");
            continuar = (obtenerOpcion("", scanner, 1, 2) == 1);
        }
        scanner.close();
    }

    private static int obtenerOpcion(String mensaje, Scanner scanner, int min, int max) {
        int opcion;
        do {
            try {
                System.out.println(mensaje);
                opcion = scanner.nextInt();
                if (opcion < min || opcion > max) {
                    System.out.println("Opción no válida. Por favor, ingrese una opción entre " + min + " y " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.next();
                opcion = -1;
            }
        } while (opcion < min || opcion > max);
        return opcion;
    }
}
