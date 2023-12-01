package tafl.modelo;

import tafl.util.Coordenada;
import tafl.util.Color;
import tafl.util.TipoCelda;

/**
 * La clase Celda representa una celda en el tablero de juego.
 *
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */
public class Celda {

    private Coordenada coordenada;
    private TipoCelda tipoCelda;
    private Pieza pieza;
    private boolean esProvincia;
    private boolean esTrono;

    /**
     * Constructor principal de la clase Celda.
     *
     * @param coordenada Coordenada de la celda en el tablero.
     * @param tipoCelda  Tipo de celda (NORMAL, PROVINCIA, TRONO).
     */
    public Celda(Coordenada coordenada, TipoCelda tipoCelda) {
        this.coordenada = coordenada;
        this.tipoCelda = tipoCelda;
        this.esProvincia = (tipoCelda == TipoCelda.PROVINCIA);
        this.esTrono = (tipoCelda == TipoCelda.TRONO);
    }

    /**
     * Constructor alternativo que asume una celda de tipo NORMAL.
     *
     * @param coordenada Coordenada de la celda en el tablero.
     */
    public Celda(Coordenada coordenada) {
        this(coordenada, TipoCelda.NORMAL);
    }

    /**
     * Crea un clon de la celda actual.
     *
     * @return Clon de la celda.
     */
    public Celda clonar() {
        Celda clon = new Celda(this.coordenada.clonar(), this.tipoCelda);
        clon.esProvincia = this.esProvincia;
        clon.esTrono = this.esTrono;
        if (this.pieza != null) {
            clon.colocar(this.pieza.clonar());
        }
        return clon;
    }

    /**
     * Coloca una pieza en la celda.
     *
     * @param pieza Pieza a colocar en la celda.
     */
    public void colocar(Pieza pieza) {
        this.pieza = pieza;
        
        this.esProvincia = (this.tipoCelda == TipoCelda.PROVINCIA);
        this.esTrono = (this.tipoCelda == TipoCelda.TRONO);

    }

    /**
     * Consulta el color de la pieza en la celda.
     *
     * @return Color de la pieza o null si la celda está vacía.
     */
    public Color consultarColorDePieza() {
        if (this.pieza != null) {
            return this.pieza.consultarColor();
        }
        return null;
    }

    /**
     * Consulta la coordenada de la celda.
     *
     * @return Coordenada de la celda.
     */
    public Coordenada consultarCoordenada() {
        return this.coordenada;
    }

    /**
     * Consulta la pieza en la celda.
     *
     * @return Pieza en la celda o null si está vacía.
     */
    public Pieza consultarPieza() {
        return this.pieza;
    }

    /**
     * Elimina la pieza de la celda.
     */
    public void eliminarPieza() {
        this.pieza = null;
    }

    /**
     * Verifica si la celda está vacía.
     *
     * @return true si la celda está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return this.pieza == null;
    }

    /**
     * Consulta el tipo de celda, considerando si es una provincia o un trono.
     *
     * @return Tipo de celda (NORMAL, PROVINCIA, TRONO).
     */
    public TipoCelda consultarTipoCelda() {
        if (this.esProvincia) {
            return TipoCelda.PROVINCIA;
        } else if (this.esTrono) {
            return TipoCelda.TRONO;
        } else {
            return TipoCelda.NORMAL;
        }
    }

    /**
     * Compara dos celdas para determinar si son iguales.
     *
     * @param obj Objeto a comparar con la celda actual.
     * @return true si las celdas son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Celda celda = (Celda) obj;
        return coordenada.equals(celda.coordenada) && tipoCelda == celda.tipoCelda
                && (pieza != null ? pieza.equals(celda.pieza) : celda.pieza == null);
    }

    /**
     * Genera un código hash para la celda.
     *
     * @return Código hash de la celda.
     */
    @Override
    public int hashCode() {
        int result = coordenada.hashCode();
        result = 31 * result + tipoCelda.hashCode();
        result = 31 * result + (pieza != null ? pieza.hashCode() : 0);
        return result;
    }

    /**
     * Genera una representación de cadena de la celda.
     *
     * @return Representación de cadena de la celda.
     */
    @Override
    public String toString() {
        return "Celda{" + "coordenada=" + coordenada + ", tipoCelda=" + tipoCelda + ", pieza=" + pieza + '}';
    }

}
