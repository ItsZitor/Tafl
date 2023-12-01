package tafl.util;

/**
 * Enumeración que representa los diferentes tipos de piezas.
 * 
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
public enum TipoPieza {
    /**
     * Representa a un atacante.
     */
    ATACANTE('A', Color.NEGRO),

    /**
     * Representa a un defensor.
     */
    DEFENSOR('D', Color.BLANCO),

    /**
     * Representa al rey.
     */
    REY('R', Color.BLANCO);

	/**
     * Carácter asociado al tipo de pieza.
     */
	private char caracter; 
	/**
     * Color de la pieza, que puede ser blanco o negro.
     */
    private Color color;

    /**
     * Constructor privado que crea una instancia de TipoPieza con un carácter y un color asociado.
     * 
     * @param letra Carácter que representa el tipo de pieza.
     * @param color Color de la pieza.
     */
    private TipoPieza(char letra, Color color) {
        this.caracter = letra;
        this.color = color;
    }

    /**
     * Devuelve el carácter asociado al tipo de pieza.
     * 
     * @return Carácter representativo del tipo de pieza.
     */
    public char toChar() {
        return caracter;
    }

    /**
     * Consulta y devuelve el color de la pieza.
     * 
     * @return Color de la pieza, que puede ser blanco o negro.
     */
    public Color consultarColor() {
        return color;
    }
}
