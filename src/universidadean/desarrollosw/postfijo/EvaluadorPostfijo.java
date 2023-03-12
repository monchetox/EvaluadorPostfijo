/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();

        for (String elemento : expresion) {
            if (esNumero(elemento)) {
                pila.push(Integer.parseInt(elemento));
            } else if (esOperador(elemento)) {
                int b = pila.pop();
                int a = pila.pop();
                int resultado = aplicarOperador(elemento.charAt(0), a, b);
                pila.push(resultado);
            } else {
                throw new IllegalArgumentException("Elemento '" + elemento + "' desconocido");
            }
        }

        return pila.pop();
    }

    private static boolean esNumero(String elemento) {
        return elemento.matches("-?\\d+");
    }

    private static boolean esOperador(String elemento) {
        return elemento.matches("[+\\-*/%^]");
    }

    private static int aplicarOperador(char operador, int a, int b) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("División por cero");
                }
                return a / b;
            case '%':
                if (b == 0) {
                    throw new IllegalArgumentException("Módulo por cero");
                }
                return a % b;
            case '^':
                return (int) Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Operador '" + operador + "' desconocido");
        }
    }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");
        String linea = teclado.nextLine();

        try {
            List<String> expresion = Token.dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        } catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }
    }
}

