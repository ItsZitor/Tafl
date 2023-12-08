package tafl.excepcion;

/**
 * Excepción personalizada para representar un error relacionado con el tipo de árbitro.
 * 
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
public class TipoArbitroException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto.
     */
    public TipoArbitroException() {
        super();
    }

    /**
     * Constructor con un mensaje descriptivo.
     *
     * @param message Mensaje descriptivo de la excepción.
     */
    public TipoArbitroException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa específica.
     *
     * @param cause Causa de la excepción.
     */
    public TipoArbitroException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con un mensaje descriptivo y una causa específica.
     *
     * @param message Mensaje descriptivo de la excepción.
     * @param cause   Causa de la excepción.
     */
    public TipoArbitroException(String message, Throwable cause) {
        super(message, cause);
    }
}
