
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class Calculadora {
        private static Calculadora instance;

        private AbstractStack<Integer> stack; // Stack que contendrá los operandos

        // Constructor privado para evitar instanciación directa
        private Calculadora(AbstractStack<Integer> stack) {
            this.stack = stack;
        }

        // Método para obtener una instancia de la calculadora (patrón Singleton)
        public static synchronized Calculadora getInstance(AbstractStack<Integer> stack) {
            if (instance == null) {
                instance = new Calculadora(stack);
            }
            return instance;
        }

        // Método principal para realizar el cálculo
        public void calculate() {
            try {
                // Leer la expresión desde un archivo de texto
                String expression = readTXT();
                System.out.println("Expresión infix: " + expression); // Depuración

                // Validar si la expresión es nula
                if (expression == null) {
                    throw new IllegalArgumentException("La expresión es nula.");
                }

                // Dividir la expresión en tokens separados por espacios en blanco
                String[] tokens = expression.split("\\s+");

                // Convertir la expresión infix a postfix
                String postfixExpression = Convert.infixToPostfix(String.join("", tokens));

                // Dividir la expresión postfix en tokens
                String[] postfixTokens = postfixExpression.split("\\s+");

                // Iterar sobre los tokens y realizar las operaciones
                for (String token : postfixTokens) {
                    if (isNumeric(token)) { // Si es un número, agregarlo al stack
                        stack.push(Integer.parseInt(token));
                    } else { // Si es un operador, realizar la operación correspondiente
                        switch (token) {
                            case "+":
                                stack.push(sumar());
                                break;
                            case "-":
                                stack.push(resta());
                                break;
                            case "*":
                                stack.push(multiplicacion());
                                break;
                            case "/":
                                division();
                                break;
                            default:
                                throw new IllegalArgumentException("Operador no válido: " + token);
                        }
                    }
                }

                // Obtener el resultado de la operación
                Integer resultado = stack.pop();

                // Verificar si hay suficientes operadores en el stack
                if (stack.pop() != null) {
                    throw new IllegalArgumentException("La expresión no tiene suficientes operadores.");
                }

                // Imprimir la expresión y el resultado
                System.out.println("Expresion: " + expression);
                System.out.println("Resultado: " + resultado);

            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Método para sumar dos operandos
        private int sumar() {
            Integer operandoB = stack.pop();
            Integer operandoA = stack.pop();
            if (operandoA != null && operandoB != null) {
                return operandoA + operandoB;
            } else {
                throw new IllegalArgumentException("Faltan operandos para la suma.");
            }
        }

        // Método para restar dos operandos
        private int resta() {
            Integer operandoB = stack.pop();
            Integer operandoA = stack.pop();
            if (operandoA != null && operandoB != null) {
                return operandoA - operandoB;
            } else {
                throw new IllegalArgumentException("Faltan operandos para la resta.");
            }
        }

        // Método para multiplicar dos operandos
        private int multiplicacion() {
            Integer operandoB = stack.pop();
            Integer operandoA = stack.pop();
            if (operandoA != null && operandoB != null) {
                return operandoA * operandoB;
            } else {
                throw new IllegalArgumentException("Faltan operandos para la multiplicación.");
            }
        }

        // Método para dividir dos operandos
        private boolean division() {
            try {
                Object operandoB = stack.pop();
                Object operandoA = stack.pop();
                if (operandoA instanceof Integer && operandoB instanceof Integer) {
                    Integer a = (Integer) operandoA;
                    Integer b = (Integer) operandoB;
                    if (b != 0) {
                        stack.push(a / b);
                        return true;
                    } else {
                        throw new ArithmeticException("División por cero.");
                    }
                } else {
                    throw new IllegalArgumentException("Operandos no válidos para la operación de división.");
                }
            } catch (NullPointerException e) {
                throw new IllegalStateException("La pila está vacía, no hay suficientes operandos para la operación de división.");
            }
        }

        // Método para leer la expresión desde un archivo de texto
        private String readTXT() {
            try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Método para verificar si una cadena es numérica
        private boolean isNumeric(String value) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

