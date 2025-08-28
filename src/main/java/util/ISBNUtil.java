package util;
/**
 * Clase ISBNUtil
 * Clase de utilidades para trabajar con ISBN.
 * - Verifica que un ISBN tenga el formato correcto (6 dígitos numéricos*/

public class ISBNUtil {
    // Método que valida si un ISBN es válido según criterio (6 dígitos numéricos)
    public static boolean esIsbnValido(String isbn) {
        if (isbn == null) {
            return false; // no puede ser nulo
        }
        if (isbn.length() != 6) {
            return false; // debe tener 6 caracteres
        }
        // Recorremos cada caracter para verificar que sea un dígito
        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                return false; // si hay algún caracter que no sea número, es inválido
            }
        }
        return true; // pasó todas las validaciones
    }
}