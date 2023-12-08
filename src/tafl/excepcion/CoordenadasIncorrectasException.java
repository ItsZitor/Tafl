package tafl.excepcion;

/**
 * Excepción que representa un error en la validación de coordenadas en un contexto defensivo.
 * 
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */

public class CoordenadasIncorrectasException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor predeterminado.
     */
    public CoordenadasIncorrectasException() {
        super();
    }

    /**
     * Constructor con un mensaje personalizado.
     * 
     * @param message Mensaje de la excepción.
     */
    public CoordenadasIncorrectasException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa específica.
     * 
     * @param cause Causa de la excepción.
     */
    public CoordenadasIncorrectasException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con un mensaje personalizado y una causa específica.
     * 
     * @param message Mensaje de la excepción.
     * @param cause Causa de la excepción.
     */
    public CoordenadasIncorrectasException(String message, Throwable cause) {
        super(message, cause);
    }
}
