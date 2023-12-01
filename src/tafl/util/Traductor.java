package tafl.util;

/**
 * La clase Traductor proporciona métodos para la conversión entre coordenadas y notación algebraica.
 *
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
public class Traductor {
	/**
	 * Este metodo no se usa pero se establece para evitar fallos con el JavaDoc
	 */
	private Traductor() {
		
	}
    /**
     * Tamaño predeterminado del tablero. El tablero es cuadrado, por lo que tiene el mismo número de filas y columnas.
     */
    private static final int TAMANO_TABLERO = 7;

    /**
     * Convierte una cadena de texto en notación algebraica a una coordenada en el tablero.
     *
     * @param texto La cadena de texto que representa la notación algebraica (ej. "a1").
     * @return Coordenada correspondiente en el tablero o null si el formato de entrada es incorrecto.
     */
    public static Coordenada consultarCoordenadaParaNotacionAlgebraica(String texto) {
        if (texto == null || texto.length() != 2) {
            return null;
        }
        
        char columna = texto.charAt(0);
        char fila = texto.charAt(1);
        
        if (columna < 'a' || columna >= 'a' + TAMANO_TABLERO ||
            fila < '1' || fila > '0' + TAMANO_TABLERO) {
            return null;
        }
        
        int filaNumerica = '0' + TAMANO_TABLERO - fila;
        int columnaNumerica = columna - 'a';
        
        return new Coordenada(filaNumerica, columnaNumerica);
    }

    /**
     * Convierte una coordenada en el tablero a su representación en notación algebraica.
     *
     * @param coordenada La coordenada en el tablero.
     * @return Notación algebraica correspondiente o null si la coordenada es inválida.
     */
    public static String consultarTextoEnNotacionAlgebraica(Coordenada coordenada) {
        if (coordenada == null || coordenada.fila() < 0 || coordenada.fila() >= TAMANO_TABLERO ||
            coordenada.columna() < 0 || coordenada.columna() >= TAMANO_TABLERO) {
            return null;
        }
        
        char fila = (char) ('0' + TAMANO_TABLERO - coordenada.fila());
        char columna = (char) ('a' + coordenada.columna());
        
        return Character.toString(columna) + Character.toString(fila);
    }

    /**
     * Verifica si una cadena de texto es un formato correcto para representar una coordenada en notación algebraica.
     *
     * @param texto La cadena de texto que se va a verificar.
     * @return true si el formato es correcto, false de lo contrario.
     */
    public static boolean esTextoCorrectoParaCoordenada(String texto) {
        if (texto == null || texto.length() != 2) {
            return false;
        }

        char columna = texto.charAt(0);
        char fila = texto.charAt(1);
        
        return (columna >= 'a' && columna < 'a' + TAMANO_TABLERO) &&
               (fila >= '1' && fila <= '0' + TAMANO_TABLERO);
    }
}