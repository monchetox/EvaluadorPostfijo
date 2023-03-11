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
     *
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();

        for (int i = 0; i < expresion.size(); i++) {
            String valor = expresion.get(i);


                if (valor.equals("+")) {
                    int numero2 = pila.pop();
                    int numero1 = pila.pop();
                    int resultado = (numero1 + numero2);
                    pila.push(resultado);

                }else if (valor.equals("-")) {
                    int numero2 = pila.pop();
                    int numero1 = pila.pop();
                    int resultado = (numero1 - numero2);
                    pila.push(resultado);

                }else if (valor.equals("*")) {
                    int numero2 = pila.pop();
                    int numero1 = pila.pop();
                    int resultado = (numero1 * numero2);
                    pila.push(resultado);

                }else if (valor.equals("/")) {
                    int numero2 = pila.pop();
                    int numero1 = pila.pop();
                    int resultado = (numero1 / numero2);
                    pila.push(resultado);

                }else if (valor.equals("%")) {
                    int numero2 = pila.pop();
                    int numero1 = pila.pop();
                    int resultado = (numero1 % numero2);
                    pila.push(resultado);

                }else if (valor.equals("^")) {
                    int numero2 = pila.pop();
                    int numero1 = pila.pop();
                    int resultado = (numero1 ^ numero2);
                    pila.push(resultado);

                }else
                    pila.push(Integer.parseInt(valor));
            }

        return pila.pop();
    }

    // TODO: Realiza la evaluación de la expresión en formato postfijo

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
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }

    }
}
