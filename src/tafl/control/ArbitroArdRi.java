package tafl.control;

import tafl.excepcion.CoordenadasIncorrectasException;
import tafl.util.TipoPieza;
import tafl.modelo.Tablero;

public class ArbitroArdRi {
    private Tablero tablero;
    private int numeroJugada;

    /**
     * Constructor de la clase ArbitroArdRi.
     *
     * @param tablero Tablero del juego.
     * @throws IllegalArgumentException Si el tablero es nulo.
     */
    public ArbitroArdRi(Tablero tablero) {
        if (tablero == null) {
            throw new IllegalArgumentException("El tablero no puede ser nulo.");
        }
        this.tablero = tablero;
        this.numeroJugada = 0;
    }

    /**
     * Coloca las piezas correspondientes a la configuración de inicio del juego (variante Ard-Ri).
     * Inicializa siempre el turno para el atacante con piezas negras.
     *
     * @throws CoordenadasIncorrectasException Si alguna coordenada está fuera del tablero.
     */
    public void colocarPiezasConfiguracionInicial() throws CoordenadasIncorrectasException {
        // Configuración inicial según la variante Ard-Ri
        TipoPieza[][] configuracionInicial = {
                {null, null, null, TipoPieza.ATACANTE, null, null, null},
                {null, null, null, TipoPieza.ATACANTE, null, null, null},
                {null, null, null, TipoPieza.DEFENSOR, null, null, null},
                {TipoPieza.ATACANTE, TipoPieza.ATACANTE, TipoPieza.DEFENSOR, TipoPieza.REY, TipoPieza.DEFENSOR, TipoPieza.ATACANTE, TipoPieza.ATACANTE},
                {null, null, null, TipoPieza.DEFENSOR, null, null, null},
                {null, null, null, TipoPieza.ATACANTE, null, null, null},
                {null, null, null, TipoPieza.ATACANTE, null, null, null}
        };

     Object piezaOrigen;
		// Comprobar si la pieza pertenece al defensor
        if (piezaOrigen != null && piezaOrigen.consultarTipoPieza() == TipoPieza.DEFENSA) {
            Object origen;
			Object destino;
			// Verificar si el movimiento es a distancia uno
            if (origen.distanciaEuclidiana(destino) == 1) {
                // Realizar el movimiento
                tablero.eliminarPieza(origen);  // Eliminar la pieza de la coordenada de origen
                tablero.colocar(new Pieza(TipoPieza.DEFENSA), destino);  // Colocar la pieza en la coordenada de destino
                numeroJugada++;  // Incrementar el número de jugadas
            } else {
                throw new CoordenadasIncorrectasException("El movimiento no es a distancia uno.");
            }
        } else {
            throw new IllegalArgumentException("La pieza en la coordenada de origen no pertenece al defensor.");
        }
        
        
        // Coloca las piezas en el tablero
        tablero.colocarPiezas(configuracionInicial, TipoPieza.ATACANTE);
    }

    // Otros métodos requeridos según las especificaciones...

}
