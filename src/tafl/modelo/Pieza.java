package tafl.modelo;

/**
 * La clase Pieza representa una pieza.
 * Cada pieza tiene un tipo (Atacante, Defensor o Rey) y un color (Blanco o Negro).
 *
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */

import tafl.util.Color;
import tafl.util.TipoPieza;

public class Pieza {

    /** El tipo de la pieza (Atacante, Defensor o Rey). */
    private final TipoPieza tipoPieza;

    /** El color de la pieza (Blanco o Negro). */
    private final Color color;

    /**
     * Constructor de la clase Pieza. Crea una nueva pieza con el tipo especificado.
     *
     * @param tipoPieza El tipo de la pieza (Atacante, Defensor o Rey).
     */
    public Pieza(TipoPieza tipoPieza) {
        this.tipoPieza = tipoPieza;

        // Asignar color según el tipo de pieza
        if (tipoPieza == TipoPieza.ATACANTE) {
            this.color = Color.NEGRO;
        } else {
            this.color = Color.BLANCO;
        }
    }

    /**
     * Método estático para crear una nueva instancia de Pieza.
     *
     * @param tipoPieza El tipo de la pieza (Atacante, Defensor o Rey).
     * @return Una nueva instancia de Pieza.
     */
    public static Pieza crearPieza(TipoPieza tipoPieza) {
        return new Pieza(tipoPieza);
    }

    /**
     * Método para clonar la pieza actual.
     *
     * @return Una nueva instancia de Pieza con el mismo tipo.
     */
    public Pieza clonar() {
        return new Pieza(this.tipoPieza);
    }

    /**
     * Obtiene el color de la pieza.
     *
     * @return El color de la pieza (Blanco o Negro).
     */
    public Color consultarColor() {
        return this.color;
    }

    /**
     * Obtiene el tipo de la pieza.
     *
     * @return El tipo de la pieza (Atacante, Defensor o Rey).
     */
    public TipoPieza consultarTipoPieza() {
        return this.tipoPieza;
    }

    /**
     * Compara la pieza actual con otro objeto para determinar si son iguales.
     *
     * @param obj El objeto con el que se compara la pieza.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pieza pieza = (Pieza) obj;

        if (tipoPieza != pieza.tipoPieza) return false;
        return color == pieza.color;
    }

    /**
     * Calcula el código hash de la pieza.
     *
     * @return El código hash de la pieza.
     */
    @Override
    public int hashCode() {
        int result = tipoPieza.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    /**
     * Representación de la pieza como cadena de texto.
     *
     * @return Una cadena que representa la pieza (ej. "Pieza{tipoPieza=ATACANTE, color=NEGRO}").
     */
    @Override
    public String toString() {
        return "Pieza{" +
                "tipoPieza=" + tipoPieza +
                ", color=" + color +
                '}';
    }

    /**
     * Marca la pieza como parte del trono.
     */
    public void marcarComoTrono() {
    }

    /**
     * Marca la pieza como parte de una provincia.
     */
    public void marcarComoProvincia() {
    }

    /**
     * Convierte la pieza a una representación de texto simplificada.
     *
     * @return Una cadena de texto que representa la pieza de manera simplificada (ej. "A" para Atacante).
     */
    public String aTexto() {
        switch (tipoPieza) {
            case ATACANTE:
                return "A";
            case DEFENSOR:
                return "D";
            case REY:
                return "R";
            default:
                return "-";
        }
    }
}
