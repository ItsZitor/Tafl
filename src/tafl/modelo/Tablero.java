package tafl.modelo;

/**
 * La clase Tablero representa el tablero de juego.
 * 
 * @author Pablo Citores
 * @author David Santamaria
 * @since 1.0
 * @version 1.0
 */

import tafl.util.Coordenada;
import tafl.util.TipoCelda;
import tafl.util.TipoPieza;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Tablero {
	// Tamaño del tablero
	static final int NUMERO_FILAS = 7;
    static final int NUMERO_COLUMNAS = 7;
    
    // Representación del tablero como matriz de celdas
    private Celda[][] celdas;
    

    /**
     * Constructor de la clase Tablero. Inicializa el tablero llamando al método `inicializarTablero()`.
     */
    public Tablero() {
        celdas = new Celda[NUMERO_FILAS][NUMERO_COLUMNAS];
        inicializarTablero();
    }

    /**
     * Inicializa el tablero, marcando las celdas de las esquinas como provincias y la celda central como trono.
     */
    private void inicializarTablero() {
    	// Inicializa todas las celdas como normales por defecto
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                celdas[i][j] = new Celda(new Coordenada(i, j));
            }
        }

        // Marco las celdas como provincias solo en las esquinas
        celdas[0][0] = new Celda (new Coordenada(0,0),TipoCelda.PROVINCIA);
        celdas[0][6] = new Celda (new Coordenada(0,6),TipoCelda.PROVINCIA);
        celdas[6][0] = new Celda (new Coordenada(6,0),TipoCelda.PROVINCIA);
        celdas[6][6] = new Celda (new Coordenada(6,6),TipoCelda.PROVINCIA);

        // Marco la celda del medio como trono
        celdas[3][3] = new Celda (new Coordenada(3,3),TipoCelda.TRONO);
    }
    
    
    /**
     * Consulta el tipo de celda en una posición específica del tablero.
     *
     * @param fila     Número de fila.
     * @param columna  Número de columna.
     * @return         Tipo de celda en la posición especificada.
     */
    public TipoCelda consultarTipoCeldaEnPosicion(int fila, int columna) {
        if (fila >= 0 && fila < NUMERO_FILAS && columna >= 0 && columna < NUMERO_COLUMNAS) {
            return celdas[fila][columna].consultarTipoCelda();
        }
        return null;
    }
    
    
    /**
     * Convierte el estado actual del tablero a una representación de texto.
     *
     * @return Representación del tablero como cadena de texto.
     */
    public String aTexto() {
        String resultado = "";
        int contador = 7;
        for (int fila = 0; fila<7; fila++) {
        	resultado+=contador;
        	contador--;
            resultado+=" ";
            for (int columna = 0; columna <7; columna++) {
                Celda celda = celdas[fila][columna];
                if (celda.estaVacia()) {
                    resultado+="- ";
                } else {
                    resultado+=celda.consultarPieza().consultarTipoPieza().toChar()+" ";
                }
            }
            resultado+="\n";
        }
        resultado+="  a b c d e f g";
        return resultado;
    }


    /**
     * Clona el tablero actual, creando una copia independiente.
     *
     * @return Tablero clonado.
     */
    public Tablero clonar() {
        Tablero tableroClonado = new Tablero();
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                // Utilizo el método clonar de la clase Celda para clonar cada celda
                tableroClonado.celdas[i][j] = this.celdas[i][j].clonar();
                if (this.celdas[i][j].consultarPieza() != null) {
                    // Clono la pieza y colocarla en la celda clonada
                    Pieza piezaClonada = this.celdas[i][j].consultarPieza().clonar();
                    tableroClonado.celdas[i][j].colocar(piezaClonada);
                }
            }
        }
        return tableroClonado;
    }

    
    /**
     * Coloca una pieza en una posición específica del tablero.
     *
     * @param pieza       Pieza a colocar.
     * @param coordenada  Coordenada donde colocar la pieza.
     */
    public void colocar(Pieza pieza, Coordenada coordenada) {
        if (estaEnTablero(coordenada) && pieza != null) {
            celdas[coordenada.fila()][coordenada.columna()].colocar(pieza);
        }
    }

    
    /**
     * Consulta la celda en una posición específica del tablero y devuelve una copia clonada de la misma.
     *
     * @param coordenada  Coordenada de la celda a consultar.
     * @return            Copia clonada de la celda en la posición especificada.
     *                   Devuelve null si la coordenada está fuera del tablero.
     */
    public Celda consultarCelda(Coordenada coordenada) {
        if (estaEnTablero(coordenada)) {
            return celdas[coordenada.fila()][coordenada.columna()].clonar();
        }
        return null;
    }
    
    
    /**
     * Consulta y devuelve una copia clonada de todas las celdas del tablero en un array.
     *
     * @return Array de celdas clonadas del tablero.
     */
    public Celda[] consultarCeldas() {
        Celda[] celdasClonadas = new Celda[NUMERO_FILAS * NUMERO_COLUMNAS];
        int index = 0;

        for (int fila = 0; fila < NUMERO_FILAS; fila++) {
            for (int columna = 0; columna < NUMERO_COLUMNAS; columna++) {
                celdasClonadas[index++] = celdas[fila][columna].clonar();
            }
        }

        return celdasClonadas;
    }

    
    /**
     * Consulta y devuelve un array de celdas contiguas a una coordenada específica.
     *
     * @param coordenada  Coordenada de referencia.
     * @return            Array de celdas contiguas clonadas.
     *                   Devuelve un array vacío si las coordenadas no están en el tablero.
     */
    public Celda[] consultarCeldasContiguas(Coordenada coordenada) {
        int contador=0;
        
        if (!estaEnTablero(coordenada)) {
            return new Celda[0]; // Devuelve un array vacío si las coordenadas no están en el tablero
        }

        // Coordenadas de las celdas contiguas
        Coordenada[] coordenadasContiguas = {
                new Coordenada(coordenada.fila() - 1, coordenada.columna()), // Arriba
                new Coordenada(coordenada.fila() + 1, coordenada.columna()), // Abajo
                new Coordenada(coordenada.fila(), coordenada.columna() - 1), // Izquierda
                new Coordenada(coordenada.fila(), coordenada.columna() + 1)  // Derecha
        };
        
        for (int i = 0; i < coordenadasContiguas.length; i++) {
            if (estaEnTablero(coordenadasContiguas[i])) {
                Celda celdaContigua = consultarCelda(coordenadasContiguas[i]);
                if (celdaContigua != null) {
                    contador++;
                }
            }
        }

        
        Celda[] celdasContiguas = new Celda[contador];
        
        int contador2 = 0;

        for (Coordenada contigua : coordenadasContiguas) {
            if (estaEnTablero(contigua)) {
                celdasContiguas[contador2] = consultarCelda(contigua);
                contador2++;
            }
        }

        return celdasContiguas;
    }


    /**
     * Consulta y devuelve un array de celdas contiguas en la misma fila que la coordenada especificada.
     *
     * @param coordenada  Coordenada de referencia.
     * @return            Array de celdas contiguas en la misma fila clonadas.
     *                   Devuelve un array vacío si las coordenadas no están en el tablero.
     */
    public Celda[] consultarCeldasContiguasEnHorizontal(Coordenada coordenada) {
        if (!estaEnTablero(coordenada)) {
            return new Celda[0];
        }

        int fila = coordenada.fila();
        int columna = coordenada.columna();

        // Coordenadas de las celdas contiguas en horizontal
        Celda[] contiguasCoordenadas = {
                consultarCelda(new Coordenada(fila-1, columna)),
                consultarCelda(new Coordenada(fila+1, columna))
        };

        return contiguasCoordenadas;
    }

    
    /**
     * Consulta y devuelve un array de celdas contiguas en la misma columna que la coordenada especificada.
     *
     * @param coordenada  Coordenada de referencia.
     * @return            Array de celdas contiguas en la misma columna clonadas.
     *                   Devuelve un array vacío si las coordenadas no están en el tablero.
     */
    public Celda[] consultarCeldasContiguasEnVertical(Coordenada coordenada) {
        if (!estaEnTablero(coordenada)) {
            return new Celda[0];
        }

        int fila = coordenada.fila();
        int columna = coordenada.columna();

        // Coordenadas de las celdas contiguas en vertical
        Celda[] contiguasCoordenadas = {
                consultarCelda(new Coordenada(fila, columna-1)),
                consultarCelda(new Coordenada(fila, columna+1))
        };

        return contiguasCoordenadas;
    }
    
    
    /**
     * Consulta y devuelve el número de filas del tablero.
     *
     * @return Número de filas del tablero.
     */
    public int consultarNumeroFilas() {
        return celdas.length; // Devuelve el número de filas del tablero
    }

    
    /**
     * Consulta y devuelve el número de columnas del tablero.
     *
     * @return Número de columnas del tablero.
     *         Devuelve 0 si no hay filas o si la primera fila es nula.
     */
    public int consultarNumeroColumnas() {
        if (celdas.length == 0 || celdas[0] == null) {
            return 0; // No hay columnas si no hay filas o si la primera fila es nula
        } else {
            return celdas[0].length; // Devuelve el número de columnas (longitud de la primera fila)
        }
    }


    /**
     * Consulta y devuelve el número de piezas de un tipo específico en el tablero.
     *
     * @param tipoPieza Tipo de pieza a contar.
     * @return          Número de piezas del tipo especificado en el tablero.
     */
    public int consultarNumeroPiezas(TipoPieza tipoPieza) {
        int contador = 0;

        for (int fila = 0; fila < NUMERO_FILAS; fila++) {
            for (int columna = 0; columna < NUMERO_COLUMNAS; columna++) {
                Celda celda = celdas[fila][columna];
                if (celda.consultarPieza() != null && celda.consultarPieza().consultarTipoPieza() == tipoPieza) {
                    contador++;
                }
            }
        }

        return contador;
    }

    
    /**
     * Elimina la pieza en la coordenada especificada si esta se encuentra dentro de los límites del tablero.
     *
     * @param coordenada Coordenada de la pieza a eliminar.
     */
    public void eliminarPieza(Coordenada coordenada) {
        if (estaEnTablero(coordenada)) {
            celdas[coordenada.fila()][coordenada.columna()].eliminarPieza();
        }
    }

    
    /**
     * Consulta y devuelve la celda en la coordenada especificada.
     *
     * @param coordenada Coordenada de la celda a consultar.
     * @return           Celda en la posición especificada.
     *                   Devuelve null si la coordenada está fuera del tablero.
     */
    public Celda obtenerCelda(Coordenada coordenada) {
        if (estaEnTablero(coordenada)) {
            return celdas[coordenada.fila()][coordenada.columna()];
        }
        return null;
    }

    
    /**
     * Verifica si una coordenada dada está dentro de los límites del tablero.
     *
     * @param coordenada Coordenada a verificar.
     * @return           true si la coordenada está dentro del tablero, false en caso contrario.
     */
    public boolean estaEnTablero(Coordenada coordenada) {
        return coordenada != null && coordenada.fila() >= 0 && coordenada.fila() < NUMERO_FILAS
                && coordenada.columna() >= 0 && coordenada.columna() < NUMERO_COLUMNAS;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tablero tablero = (Tablero) obj;
        return Arrays.deepEquals(celdas, tablero.celdas);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(celdas);
    }
    
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for (int i = NUMERO_FILAS - 1; i >= 0; i--) {
            resultado.append(i + 1).append(" ");
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                resultado.append(celdas[i][j].toString()).append(" ");
            }
            resultado.append("\n");
        }
        resultado.append("  a b c d e f g");
        return resultado.toString();
    }

}



