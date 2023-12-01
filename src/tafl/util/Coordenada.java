package tafl.util;

/**
 * La clase Coordenada representa las coordenadas de una celda, con valores de fila y columna.
 *
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */

/**
 * Representa una coordenada con filas y columnas.
 *
 * @param fila La posición en la dimensión vertical.
 * @param columna La posición en la dimensión horizontal.
 */
public record Coordenada(int fila, int columna) {

    /**
     * Crea y devuelve una nueva instancia de Coordenada con los mismos valores de fila y columna
     * que la instancia actual.
     *
     * @return Una nueva instancia de Coordenada clonada.
     */
    public Coordenada clonar() {
        return new Coordenada(this.fila, this.columna);
    }
}
