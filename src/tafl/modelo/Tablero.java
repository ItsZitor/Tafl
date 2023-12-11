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
import tafl.excepcion.CoordenadasIncorrectasException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Tablero {
    // Tamaño del tablero
    static final int NUMERO_FILAS = 7;
    static final int NUMERO_COLUMNAS = 7;

    // Representación del tablero como lista de listas de celdas
    private List<List<Celda>> celdas;

    /**
     * Constructor de la clase Tablero. Inicializa el tablero llamando al método `inicializarTablero()`.
     */
    public Tablero() {
        celdas = new ArrayList<>();
        inicializarTablero();
    }

    /**
     * Inicializa el tablero, marcando las celdas de las esquinas como provincias y la celda central como trono.
     */
    private void inicializarTablero() {
        // Inicializa todas las celdas como normales por defecto
        for (int i = 0; i < NUMERO_FILAS; i++) {
            celdas.add(new ArrayList<>());
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                celdas.get(i).add(new Celda(new Coordenada(i, j)));
            }
        }

        // Marco las celdas como provincias solo en las esquinas
        celdas.get(0).set(0, new Celda(new Coordenada(0, 0), TipoCelda.PROVINCIA));
        celdas.get(0).set(6, new Celda(new Coordenada(0, 6), TipoCelda.PROVINCIA));
        celdas.get(6).set(0, new Celda(new Coordenada(6, 0), TipoCelda.PROVINCIA));
        celdas.get(6).set(6, new Celda(new Coordenada(6, 6), TipoCelda.PROVINCIA));

        // Marco la celda del medio como trono
        celdas.get(3).set(3, new Celda(new Coordenada(3, 3), TipoCelda.TRONO));
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
            return celdas.get(fila).get(columna).consultarTipoCelda();
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
        int contador = NUMERO_FILAS;

        for (int fila = 0; fila < NUMERO_FILAS; fila++) {
            resultado += contador + " ";
            contador--;

            for (int columna = 0; columna < NUMERO_COLUMNAS; columna++) {
                Celda celda = celdas.get(fila).get(columna);

                if (celda.estaVacia()) {
                    resultado += "- ";
                } else {
                    resultado += celda.consultarPieza().consultarTipoPieza().toChar() + " ";
                }
            }
            resultado += "\n";
        }

        resultado += "  a b c d e f g";
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
                tableroClonado.celdas.get(i).set(j, this.celdas.get(i).get(j).clonar());

                if (this.celdas.get(i).get(j).consultarPieza() != null) {
                    // Clono la pieza y colocarla en la celda clonada
                    Pieza piezaClonada = this.celdas.get(i).get(j).consultarPieza().clonar();
                    tableroClonado.celdas.get(i).get(j).colocar(piezaClonada);
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
    /**
     * Coloca una pieza en una posición específica del tablero.
     *
     * @param pieza       Pieza a colocar.
     * @param coordenada  Coordenada donde colocar la pieza.
     * @throws IllegalArgumentException      Si la pieza es nula.
     * @throws CoordenadasIncorrectasException Si la coordenada está fuera del tablero.
     */
    public void colocar(Pieza pieza, Coordenada coordenada) throws CoordenadasIncorrectasException {
    	if (pieza == null) {
            throw new IllegalArgumentException("La pieza no puede ser nula.");
        }

        if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }

        if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }

        celdas.get(coordenada.fila()).get(coordenada.columna()).colocar(pieza);
    }

    
    /**
     * Consulta la celda en una posición específica del tablero y devuelve una copia clonada de la misma.
     *
     * @param coordenada  Coordenada de la celda a consultar.
     * @return            Copia clonada de la celda en la posición especificada.
     *                   Devuelve null si la coordenada está fuera del tablero.
     */
    public Celda consultarCelda(Coordenada coordenada) throws CoordenadasIncorrectasException{
    	if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
    	
    	if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }
    	
        if (estaEnTablero(coordenada)) {
            return celdas.get(coordenada.fila()).get(coordenada.columna()).clonar();
        }
        return null;
    }

    
    
    /**
     * Consulta y devuelve una copia clonada de todas las celdas del tablero en un array.
     *
     * @return Array de celdas clonadas del tablero.
     */
    public List<Celda> consultarCeldas() {
        List<Celda> celdasClonadas = new ArrayList<>();

        for (int fila = 0; fila < NUMERO_FILAS; fila++) {
            for (int columna = 0; columna < NUMERO_COLUMNAS; columna++) {
                celdasClonadas.add(celdas.get(fila).get(columna).clonar());
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
    public List<Celda> consultarCeldasContiguas(Coordenada coordenada) throws CoordenadasIncorrectasException {
        List<Celda> celdasContiguas = new ArrayList<>();

        if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }

        if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }

        // Coordenadas de las celdas contiguas
        Coordenada[] coordenadasContiguas = {
                new Coordenada(coordenada.fila() - 1, coordenada.columna()), // Arriba
                new Coordenada(coordenada.fila() + 1, coordenada.columna()), // Abajo
                new Coordenada(coordenada.fila(), coordenada.columna() - 1), // Izquierda
                new Coordenada(coordenada.fila(), coordenada.columna() + 1)  // Derecha
        };

        for (Coordenada contigua : coordenadasContiguas) {
            if (estaEnTablero(contigua)) {
                Celda celdaContigua = consultarCelda(contigua);
                if (celdaContigua != null) {
                    celdasContiguas.add(celdaContigua);
                }
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
    public List<Celda> consultarCeldasContiguasEnHorizontal(Coordenada coordenada) throws CoordenadasIncorrectasException {
    	if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
    	
    	if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }
    	
        List<Celda> contiguasCoordenadas = new ArrayList<>();

        if (estaEnTablero(coordenada)) {
            int fila = coordenada.fila();
            int columna = coordenada.columna();

            // Coordenadas de las celdas contiguas en horizontal
            if (columna - 1 >= 0) {
                contiguasCoordenadas.add(consultarCelda(new Coordenada(fila, columna - 1)));
            }
            if (columna + 1 < consultarNumeroColumnas()) {
                contiguasCoordenadas.add(consultarCelda(new Coordenada(fila, columna + 1)));
            }
        }

        return contiguasCoordenadas;
    }


    
    /**
     * Consulta y devuelve un array de celdas contiguas en la misma columna que la coordenada especificada.
     *
     * @param coordenada  Coordenada de referencia.
     * @return            Array de celdas contiguas en la misma columna clonadas.
     *                   Devuelve un array vacío si las coordenadas no están en el tablero.
     */
    public List<Celda> consultarCeldasContiguasEnVertical(Coordenada coordenada) throws CoordenadasIncorrectasException {
        if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
        
        if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }

        List<Celda> contiguasCoordenadas = new ArrayList<>();

        if (estaEnTablero(coordenada)) {
            int fila = coordenada.fila();
            int columna = coordenada.columna();

            // Coordenadas de las celdas contiguas en vertical
            if (columna - 1 >= 0) {
                contiguasCoordenadas.add(consultarCelda(new Coordenada(fila, columna - 1)));
            }
            if (columna + 1 < consultarNumeroColumnas()) {
                contiguasCoordenadas.add(consultarCelda(new Coordenada(fila, columna + 1)));
            }
        }

        return contiguasCoordenadas;
    }

    

    /**
     * Consulta y devuelve el número de columnas del tablero.
     *
     * @return Número de columnas del tablero.
     *         Devuelve 0 si no hay filas o si la primera fila es nula.
     */
    public int consultarNumeroColumnas() {
        if (celdas.isEmpty() || celdas.get(0) == null) {
            return 0; // No hay columnas si no hay filas o si la primera fila es nula
        } else {
            return celdas.get(0).size(); // Devuelve el número de columnas (longitud de la primera fila)
        }
    }
    
    
    /**
     * Consulta y devuelve el número de filas del tablero.
     *
     * @return Número de filas del tablero.
     */
    public int consultarNumeroFilas() {
        return celdas.size(); // Devuelve el número de filas del tablero
    }
    

    /**
     * Consulta y devuelve el número de piezas de un tipo específico en el tablero.
     *
     * @param tipoPieza Tipo de pieza a contar.
     * @return          Número de piezas del tipo especificado en el tablero.
     */
    public int consultarNumeroPiezas(TipoPieza tipoPieza) {
    	if (tipoPieza == null) {
            throw new IllegalArgumentException("El tipo de pieza no puede ser nulo.");
        }
    	
        int contador = 0;

        for (List<Celda> fila : celdas) {
            for (Celda celda : fila) {
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
    public void eliminarPieza(Coordenada coordenada) throws CoordenadasIncorrectasException {
    	if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
    	
    	if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }
    	
        if (estaEnTablero(coordenada)) {
            int fila = coordenada.fila();
            int columna = coordenada.columna();
            
            if (celdas.size() > fila && celdas.get(fila).size() > columna) {
                celdas.get(fila).get(columna).eliminarPieza();
            }
        }
    }


    
    /**
     * Consulta y devuelve la celda en la coordenada especificada.
     *
     * @param coordenada Coordenada de la celda a consultar.
     * @return Celda en la posición especificada.
     *         Devuelve null si la coordenada está fuera del tablero.
     */
    public Celda obtenerCelda(Coordenada coordenada) throws CoordenadasIncorrectasException {
    	if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
    	
    	if (!estaEnTablero(coordenada)) {
            throw new CoordenadasIncorrectasException("Coordenadas incorrectas: " + coordenada);
        }
    	
        if (estaEnTablero(coordenada)) {
            return celdas.get(coordenada.fila()).get(coordenada.columna());
        }
        return null;
    }

    
    /**
     * Verifica si una coordenada dada está dentro de los límites del tablero.
     *
     * @param coordenada Coordenada a verificar.
     * @return true si la coordenada está dentro del tablero, false en caso contrario.
     */
    public boolean estaEnTablero(Coordenada coordenada) {
    	if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
    	
        return coordenada != null && coordenada.fila() >= 0 && coordenada.fila() < celdas.size()
                && coordenada.columna() >= 0 && coordenada.columna() < celdas.get(0).size();
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tablero tablero = (Tablero) obj;
        // Utiliza el método equals de la interfaz List para comparar las listas de celdas
        return celdas.equals(tablero.celdas);
    }

    @Override
    public int hashCode() {
        // Utiliza el método hashCode de la interfaz List para calcular el hash de las listas de celdas
        return celdas.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for (int i = NUMERO_FILAS - 1; i >= 0; i--) {
            resultado.append(i + 1).append(" ");
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                // Utiliza el método toString de la interfaz List para obtener la representación de las celdas
                resultado.append(celdas.get(i).get(j).toString()).append(" ");
            }
            resultado.append("\n");
        }
        resultado.append("  a b c d e f g");
        return resultado.toString();
    }

	public void colocarPiezas(TipoPieza[][] configuracionInicial, TipoPieza atacante) {
		// TODO Auto-generated method stub
		
	}


}



