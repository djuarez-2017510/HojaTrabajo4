import java.util.Stack;

public class Convert {
    // Método para convertir una expresión en notación infija a notación postfix
    public static String infixToPostfix(String expression) {
        // Crear un StringBuilder para almacenar la expresión postfix
        StringBuilder postfix = new StringBuilder();
        // Crear una pila para operadores
        Stack<Character> stack = new Stack<>();
        // Agregar un carácter de final de pila a la pila
        stack.push('#');

        // Iterar sobre cada carácter en la expresión de entrada
        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                // Si el carácter es una letra o un dígito, agregarlo directamente a la expresión postfix
                postfix.append(ch);
                postfix.append(' '); // Agregar espacio después de cada número o dígito
            } else if (ch == '(') {
                // Si el carácter es un paréntesis de apertura, simplemente apílelo
                stack.push(ch);
            } else if (ch == ')') {
                // Si el carácter es un paréntesis de cierre, desapile todos los operadores
                // hasta que se encuentre un paréntesis de apertura correspondiente
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                    postfix.append(' '); // Agregar espacio después de cada operador
                }
                // Eliminar el paréntesis de apertura correspondiente de la pila
                stack.pop();
            } else {
                // Si el carácter es un operador, desapile los operadores con mayor o igual precedencia
                // y luego apile el operador actual
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                    postfix.append(' '); // Agregar espacio después de cada operador
                }
                // Apilar el operador actual
                stack.push(ch);
            }
        }

        // Desapilar y agregar todos los operadores restantes en la pila a la expresión postfix
        while (!stack.isEmpty() && stack.peek() != '#') {
            postfix.append(stack.pop());
            postfix.append(' '); // Agregar espacio después de cada operador restante en la pila
        }

        // Retornar la expresión postfix como una cadena, eliminando los espacios en blanco innecesarios al final
        return postfix.toString().trim();
    }

    // Método para determinar la precedencia de un operador
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1; // Precedencia más baja para la suma y resta
            case '*':
            case '/':
                return 2; // Precedencia más alta para la multiplicación y división
            default:
                return -1; // Otros operadores tienen precedencia -1 (menos prioridad)
        }
    }
}

