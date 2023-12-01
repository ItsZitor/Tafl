package tafl.util;

/**
 * La clase Sentido representa los posibles sentidos en los que se puede realizar un desplazamiento.
 *
 *
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
public enum Sentido {

	/**
     * Desplazamiento horizontal hacia el este.
     */
    HORIZONTAL_E(+0, +1),
    /**
     * Desplazamiento horizontal hacia el oeste.
     */
    HORIZONTAL_O(+0, -1),
    /**
     * Desplazamiento vertical hacia el norte.
     */
    VERTICAL_N(-1, +0),
    /**
     * Desplazamiento vertical hacia el sur.
     */
    VERTICAL_S(+1, +0);

	/**
     * Desplazamiento en filas.
     */
    private final int desplazamientoEnFilas;
    /**
     * Desplazamiento en columnas.
     */
    private final int desplazamientoEnColumnas;

    /**
     * Constructor privado utilizado para inicializar cada constante de la enumeración con sus respectivos desplazamientos.
     *
     * @param despF Desplazamiento en filas.
     * @param despC Desplazamiento en columnas.
     */
    private Sentido(int despF, int despC) {
        this.desplazamientoEnFilas = despF;
        this.desplazamientoEnColumnas = despC;
    }
    

    /**
     * Obtiene el desplazamiento en filas asociado a este sentido.
     *
     * @return Desplazamiento en filas.
     */
    public int consultarDesplazamientoEnFilas() {
        return this.desplazamientoEnFilas;
    }
    

    /**
     * Obtiene el desplazamiento en columnas asociado a este sentido.
     *
     * @return Desplazamiento en columnas.
     */
    public int consultarDesplazamientoEnColumnas() {
        return this.desplazamientoEnColumnas;
    }

}
