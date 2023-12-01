/**
 * La clase Jugada representa un movimiento entre dos celdas.
 * Cada jugada tiene un origen y un destino, y puede ser consultada para determinar
 * su sentido y si es un movimiento horizontal o vertical.
 * 
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
package tafl.modelo;

import tafl.util.Sentido;

public record Jugada (Celda origen, Celda destino){



    /**
     * Constructor de la clase Jugada.
     * 
     * @param origen  Celda de origen de la jugada.
     * @param destino Celda de destino de la jugada.
     */
    public Jugada(Celda origen, Celda destino) {
        this.origen = origen;
        this.destino = destino;
    }

    /**
     * Consulta el sentido de la jugada, que puede ser horizontal este u oeste,
     * vertical norte o sur, o nulo si no es un movimiento horizontal o vertical.
     * 
     * @return Sentido de la jugada (HORIZONTAL_E, HORIZONTAL_O, VERTICAL_N, VERTICAL_S) o null si no es horizontal o vertical.
     */
    public Sentido consultarSentido() {
        int diffX = destino.consultarCoordenada().fila() - origen.consultarCoordenada().fila();
        int diffY = destino.consultarCoordenada().columna() - origen.consultarCoordenada().columna();

        if (diffX == 0 && diffY != 0) {
            return (diffY > 0) ? Sentido.HORIZONTAL_E : Sentido.HORIZONTAL_O;
        } else if (diffY == 0 && diffX != 0) {
            return (diffX > 0) ? Sentido.VERTICAL_S : Sentido.VERTICAL_N;
        }

        return null;
    }

    /**
     * Verifica si la jugada es un movimiento horizontal o vertical.
     * 
     * @return true si la jugada es horizontal o vertical, false en caso contrario.
     */
    public boolean esMovimientoHorizontalOVertical() {
        Sentido sentido = consultarSentido();
        return sentido != null;
    }

}
