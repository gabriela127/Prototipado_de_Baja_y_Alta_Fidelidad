/**
 * ============================================================
 *  Facultad de Ingeniería en Sistemas de Información
 *  Análisis de Sistemas — Microservicio Validador de Llaves
 * ============================================================
 *
 *  Problema: Dado un String compuesto por '{' y '}',
 *  determinar si la secuencia está correctamente construida,
 *  es decir, que cada llave abierta '{' tiene su cierre '}'
 *  correspondiente, incluso con anidamiento.
 *
 *  Restricciones:
 *    - Usar charAt() o for-each para recorrer el String.
 *    - Toda estructura de datos debe ser codificada por el alumno.
 *    - Lenguaje: Java.
 *
 *  Complejidad:
 *    - Tiempo:  O(n)  — un solo recorrido lineal del String.
 *    - Espacio: O(1)  — sólo un entero como contador.
 * ============================================================
 */
public class BraceValidator {

    // ─────────────────────────────────────────────────────────
    //  SOLUCIÓN 1: usando charAt(i)
    //  Lectura explícita por índice.
    // ─────────────────────────────────────────────────────────
    /**
     * Verifica si la secuencia de llaves es válida.
     *
     * @param  s  cadena de caracteres '{' y '}'
     * @return    true  si la secuencia es correctamente construida
     *            false si hay llaves sin cerrar o cierres sin apertura
     */
    public static boolean isValid(String s) {
        int contador = 0;   // actúa como pila: sube con '{', baja con '}'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '{') {
                contador++;                    // apertura: apilamos
            } else if (c == '}') {
                contador--;                    // cierre: desapilamos
                if (contador < 0) {
                    // Hay un '}' sin '{' previo → inválido de inmediato
                    return false;
                }
            }
            // Cualquier otro carácter se ignora (robustez)
        }

        // Al final, si el contador es 0 todas las aperturas fueron cerradas
        return contador == 0;
    }

    // ─────────────────────────────────────────────────────────
    //  SOLUCIÓN 2: usando sintaxis for-each
    //  Recorre el arreglo de caracteres del String.
    // ─────────────────────────────────────────────────────────
    /**
     * Versión alternativa con for-each (lógica idéntica).
     *
     * @param  s  cadena de caracteres '{' y '}'
     * @return    true  si válida, false en caso contrario
     */
    public static boolean isValidForEach(String s) {
        int contador = 0;

        for (char c : s.toCharArray()) {
            if (c == '{') {
                contador++;
            } else if (c == '}') {
                if (--contador < 0) {
                    return false;             // cierre sin apertura
                }
            }
        }

        return contador == 0;
    }

    // ─────────────────────────────────────────────────────────
    //  MÉTODO MAIN — pruebas de consola
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {

        // Casos de prueba
        String[] validas   = { "{}", "{{}}", "{}{}", "{}{}{{}}", "{{{}}}" };
        String[] invalidas = { "{}}", "{{}",  "{{{}}",  "}{}", "{{" };

        System.out.println("=== Secuencias VÁLIDAS ===");
        for (String s : validas) {
            System.out.printf("  isValid(\"%s\") = %b  |  forEach = %b%n",
                    s, isValid(s), isValidForEach(s));
        }

        System.out.println("\n=== Secuencias INVÁLIDAS ===");
        for (String s : invalidas) {
            System.out.printf("  isValid(\"%s\") = %b  |  forEach = %b%n",
                    s, isValid(s), isValidForEach(s));
        }
    }
}
