package tafl.util;

/**
 * 
 * Esta clase proporciona métodos para consultar el color y obtener la representación
 * de carácter del color.
 * 
 *
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
public enum Color {
    /**
     * Representa el color blanco en el juego.
     */
    BLANCO('B'),

    /**
     * Representa el color negro en el juego.
     */
    NEGRO('N');

	/**
     * La letra asociada al color.
     */
    private char letra;

    /**
     * Constructor privado para la enumeración Color.
     *
     * @param letra La letra asociada al color.
     */
    private Color(char letra) {
        this.letra = letra;
    }
    

    /**
     * Consulta el color contrario al color actual.
     *
     * @return El color contrario (BLANCO si el color actual es NEGRO, y viceversa).
     */
    public Color consultarContrario() {
        return this.equals(BLANCO) ? NEGRO : BLANCO;
    }
    

    /**
     * Obtiene la representación de carácter asociada al color.
     *
     * @return La letra asociada al color.
     */
    public char toChar() {
        return letra;
    }
}
