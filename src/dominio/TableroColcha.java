package dominio;

import interfaz.Interfaz;
import java.awt.Toolkit;
import java.util.*;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class TableroColcha {

    private String[][] tablero;
    private char colcha;
    private String colorColcha;

    public TableroColcha() {
        this.randomColcha();
        this.randomColor();
        this.tablero = new String[10][10];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                this.setCeldaTablero("0", i, j);
            }
        }
    }

    // Getters y Setters
    public String[][] getTablero() {
        return this.tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public char getColcha() {
        return this.colcha;
    }

    public void setColcha(char colcha) {
        this.colcha = colcha;
    }

    public String getColorColcha() {
        return this.colorColcha;
    }

    public void setColorColcha(String unColorColcha) {
        this.colorColcha = unColorColcha;
    }

    /**
     * Retorna el contenido de una celda determinada.
     *
     * @param fila
     * @param columna
     * @return String con contenido de la celda.
     */
    public String getCeldaTablero(int fila, int columna) {
        return this.tablero[fila][columna];
    }

    /**
     * recibe la fila y columna de una matriz y coloca la informacion deseada la
     * informacion debe ser unicamente de tipo string
     *
     * @param info
     * @param fila
     * @param columna
     */
    public void setCeldaTablero(String info, int fila, int columna) {
        this.tablero[fila][columna] = info;
    }

    /**
     * recibe un array con coordenadasde una matriz y coloca la informacion
     * deseada en la celda correspondiente la informacion debe ser unicamente de
     * tipo string
     *
     * @param info
     * @param coords
     */
    public void setCeldaTablero(String info, int[] coords) {
        this.tablero[coords[0]][coords[1]] = info;
    }

    /**
     * Recibe una celda con una ficha y devuelve su tipo.
     *
     * @param contCelda
     * @return Caracter con el tipo de ficha que tiene la celda.
     */
    public char getTipoFicha(String contCelda) {
        char tipo = 'g';
        if (contCelda.contains("G")) {
            tipo = 'G';
        }
        return tipo;
    }

    /**
     * Recibe una celda con una fihcha y devuelve su color.
     *
     * @param contCelda
     * @return String con el codigo de color de la ficha que tiene la celda.
     */
    public String getColorFicha(String contCelda) {
        String color = Auxiliar.COLOR_ROJO;
        if (contCelda.contains(Auxiliar.COLOR_AZUL)) {
            color = Auxiliar.COLOR_AZUL;
        }
        return color;
    }

    // metodos de instancia
    /**
     * Selecciona de forma aleatoria una colcha para el tablero del juego.
     */
    public void randomColcha() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0:
                this.setColcha('~');
                break;
            case 1:
                this.setColcha('#');
                break;
            case 2:
                this.setColcha('^');
                break;
            case 3:
                this.setColcha('o');
                break;
            case 4:
                this.setColcha('*');
                break;
        }
    }

    /**
     * Selecciona de forma aleatoria un color para la colcha del tablero del
     * juego.
     */
    public void randomColor() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0:
                this.setColorColcha(Auxiliar.COLOR_AMARILLO);
                break;
            case 1:
                this.setColorColcha(Auxiliar.COLOR_CELESTE);
                break;
            case 2:
                this.setColorColcha(Auxiliar.COLOR_VERDE);
                break;
            case 3:
                this.setColorColcha(Auxiliar.COLOR_VIOLETA);
                break;
            case 4:
                this.setColorColcha(Auxiliar.COLOR_RESET);
                break;
        }
    }

    /**
     * Devuelve si hay una ficha en el tablero, sin importar su tipo.
     *
     * @param fila
     * @param colu
     * @return True si hay una ficha en la celda, false de lo contrario.
     */
    public boolean hayFicha(int fila, int colu) {
        return (!(this.getCeldaTablero(fila, colu)).equalsIgnoreCase("0"));
    }

    /**
     * Devuelve si hay un tipo esfecifica de ficha en una celda.
     *
     * @param fila
     * @param columna
     * @param tipo
     * @return True si hay el tipo de ficha en la celda, false de lo contrario.
     */
    public boolean hayTipoFicha(int fila, int columna, char tipo) {
        String tipoFicha = Character.toString(tipo);
        return ((this.hayFicha(fila, columna))
                && (this.getCeldaTablero(fila, columna).contains(tipoFicha)));
    }

    /**
     * Devuelve si hay un tipo de ficha especifica de un color determinado en la
     * celda dada.
     *
     * @param fila
     * @param columna
     * @param tipo
     * @param colorANSI
     * @return True si en la celda hay una ficha del color y tipo dados, false
     * de lo contrario.
     */
    public boolean hayTipoFichaYColor(int fila, int columna, char tipo, String colorANSI) {
        String tipoFicha = Character.toString(tipo);
        String color = this.getColorFicha(this.getCeldaTablero(fila, columna));

        return ((this.hayFicha(fila, columna)) && (color.equals(colorANSI))
                && (this.getCeldaTablero(fila, columna).contains(tipoFicha)));
    }

    /**
     * Si hay una ficha en una celda, la devuelve, de lo contrario retorna un
     * string con un espacio (" ").
     *
     * @param fila
     * @param columna
     * @return String con contenido de ficha, de lo contrario string " ".
     */
    public String pedirFichaNoNula(int fila, int columna) {
        String devolver = " ";
        if (hayFicha(fila, columna)) {
            devolver = this.getCeldaTablero(fila, columna);
        }
        return devolver;
    }

    /**
     * Recorre los bordes (i=0 to i=1)&(j=0 to j=1) de la matriz reseteandolos a
     * su valor inicial ("0").
     *
     * @param partida
     */
    public void quitarFichasBordeTablero(Partida partida) {
        for (int i = 0; i < this.getTablero().length; i++) {
            for (int j = 0; j <= 1; j++) {
                //celda(i, j)
                String contenidoCelda = this.getCeldaTablero(i, j);
                if (contenidoCelda.contains("G")) {
                    partida.getJugador(this.getColorFicha(contenidoCelda)).ponerGatoEnCaja();
                }
                if (contenidoCelda.contains("g")) {
                    partida.getJugador(this.getColorFicha(contenidoCelda)).ponerGatitoEnCaja();
                }
                this.setCeldaTablero("0", i, j);

                //celda(j, i)
                String contenidoOtraCelda = this.getCeldaTablero(j, i);
                if (contenidoOtraCelda.contains("G")) {
                    partida.getJugador(this.getColorFicha(contenidoOtraCelda)).ponerGatoEnCaja();
                }
                if (contenidoOtraCelda.contains("g")) {
                    partida.getJugador(this.getColorFicha(contenidoOtraCelda)).ponerGatitoEnCaja();
                }
                this.setCeldaTablero("0", j, i);
            }
        }
    }

    /**
     * Establece al formato inicial ("0") una celda.
     *
     * @param coords
     */
    public void quitarFichaDelTablero(int[] coords) {
        int fila = coords[0];
        int columna = coords[1];

        this.setCeldaTablero("0", fila, columna);
    }

    /**
     * Recibe tres coordenadas de celdas y resetea sus valores a "0".
     *
     * @param fila1
     * @param columna1
     * @param fila2
     * @param columna2
     * @param fila3
     * @param columna3
     */
    public void quitarTresFichasDelTablero(int fila1, int columna1, int fila2, int columna2, int fila3, int columna3) {
        this.setCeldaTablero("0", fila1, columna1);
        this.setCeldaTablero("0", fila2, columna2);
        this.setCeldaTablero("0", fila3, columna3);
    }

    /**
     * Coloca un gato o gatito de un color determinado en la celda deseada.
     *
     * @param partida
     * @param colorANSI
     * @param ubicacionNuevaFicha
     * @param tipoGatito
     */
    public void colocarFicha(Partida partida, String colorANSI, int[] ubicacionNuevaFicha, char tipoGatito) {
        if (colorANSI.equalsIgnoreCase(Auxiliar.COLOR_ROJO)) {
            if (tipoGatito == 'G') {
                this.setCeldaTablero(
                        Auxiliar.GATO_ROJO,
                        ubicacionNuevaFicha[0],
                        ubicacionNuevaFicha[1]);
            } else if (tipoGatito == 'g') {
                this.setCeldaTablero(
                        Auxiliar.GATITO_ROJO,
                        ubicacionNuevaFicha[0],
                        ubicacionNuevaFicha[1]);
            }
        } else if (colorANSI.equalsIgnoreCase(Auxiliar.COLOR_AZUL)) {
            if (tipoGatito == 'G') {
                this.setCeldaTablero(
                        Auxiliar.GATO_AZUL,
                        ubicacionNuevaFicha[0],
                        ubicacionNuevaFicha[1]);
            } else if (tipoGatito == 'g') {
                this.setCeldaTablero(
                        Auxiliar.GATITO_AZUL,
                        ubicacionNuevaFicha[0],
                        ubicacionNuevaFicha[1]);
            }
        }
        this.salto(ubicacionNuevaFicha);
    }

    /**
     * Recibe un gato en una posicion i,j de una matriz y lo mueve a otra y, x.
     *
     * @param i
     * @param j
     * @param fila
     * @param colu
     */
    public void moverFicha(int i, int j, int fila, int colu) {
        this.setCeldaTablero(this.getCeldaTablero(i, j), fila, colu);
        this.setCeldaTablero("0", i, j);
    }

    /**
     * Si hay una ficha en una celda dada, hace que las de alrededor que esten
     * habilitadas hagan un salto.
     *
     * @param jugada
     */
    public void salto(int[] jugada) {
        int fila = jugada[0];
        int columna = jugada[1];

        if (this.hayTipoFicha(fila, columna, 'g')) {
            for (int i = (fila - 1); i <= (fila + 1); i++) {
                for (int j = (columna - 1); j <= (columna + 1); j++) {
                    if (this.hayTipoFicha(i, j, 'g')) {
                        saltoFichas(fila, columna, i, j);
                    }
                }
            }
        }
        if (this.hayTipoFicha(fila, columna, 'G')) {
            for (int i = (fila - 1); i <= (fila + 1); i++) {
                for (int j = (columna - 1); j <= (columna + 1); j++) {
                    this.saltoFichas(fila, columna, i, j);
                }
            }
        }
    }

    /**
     * Se fija si la celda a la que saltaria la ficha esta libre y lo mueve
     * unicamente si no hay fichas.
     *
     * @param fila
     * @param columna
     * @param i
     * @param j
     */
    public void saltoFichas(int fila, int columna, int i, int j) {
        boolean huboSalto = false;
        if (this.hayFicha(i, j)) {
            //fila superior
            if (i < fila) {
                //superior izquierda
                if ((j < columna) && !(this.hayFicha(i - 1, j - 1))) {
                    this.moverFicha(i, j, i - 1, j - 1);
                    huboSalto = true;
                }
                //superior centro
                if ((j == columna) && !(this.hayFicha(i - 1, j))) {
                    this.moverFicha(i, j, i - 1, j);
                    huboSalto = true;
                }
                //superior derecha
                if ((j > columna) && !(this.hayFicha(i - 1, j + 1))) {
                    this.moverFicha(i, j, i - 1, j + 1);
                    huboSalto = true;
                }
            }
            //fila centro
            if (i == fila) {
                //centro izquiereda
                if ((j < columna) && !(this.hayFicha(i, j - 1))) {
                    this.moverFicha(i, j, i, j - 1);
                    huboSalto = true;
                }
                //centro derecha
                if ((j > columna) && !(this.hayFicha(i, j + 1))) {
                    this.moverFicha(i, j, i, j + 1);
                    huboSalto = true;
                }
            }
            //fila inferior
            if (i > fila) {
                //inferior izquierda
                if ((j < columna) && !(this.hayFicha(i + 1, j - 1))) {
                    this.moverFicha(i, j, i + 1, j - 1);
                    huboSalto = true;
                }
                //inferior derecha
                if ((j == columna) && !(this.hayFicha(i + 1, j))) {
                    this.moverFicha(i, j, i + 1, j);
                    huboSalto = true;
                }
                //inferior izquierda
                if ((j > columna) && !(this.hayFicha(i + 1, j + 1))) {
                    this.moverFicha(i, j, i + 1, j + 1);
                    huboSalto = true;
                }
            }
        }
        if (huboSalto) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /* Hallamos como realizar un sonido de sistema en:
    * https://www.rgagnon.com/javadetails/java-0001.html
     */
    /**
     * Se fija si hay tres fichas del mismo tipo y color juntas, diagonal,
     * horizontal o verticalmente.
     *
     * @param tipoFicha
     * @param i
     * @param j
     * @param colorANSI
     * @return True si hay tres fichas de mismo tipo y color juntasa, false de
     * lo contrario
     */
    public boolean tresFichasIgualesJuntas(char tipoFicha, int i, int j, String colorANSI) {
        boolean hay = false;
        if (this.hayTipoFichaYColor(i, j, tipoFicha, colorANSI)) {
            if (this.hayTipoFichaYColor(i, j + 1, tipoFicha, colorANSI)
                    && this.hayTipoFichaYColor(i, j + 2, tipoFicha, colorANSI)) {
                hay = true;
            }
            if (this.hayTipoFichaYColor(i + 1, j - 1, tipoFicha, colorANSI)
                    && this.hayTipoFichaYColor(i + 2, j - 2, tipoFicha, colorANSI)) {
                hay = true;
            }
            if (this.hayTipoFichaYColor(i + 1, j, tipoFicha, colorANSI)
                    && this.hayTipoFichaYColor(i + 2, j, tipoFicha, colorANSI)) {
                hay = true;
            }
            if (this.hayTipoFichaYColor(i + 1, j + 1, tipoFicha, colorANSI)
                    && this.hayTipoFichaYColor(i + 2, j + 2, tipoFicha, colorANSI)) {
                hay = true;
            }
        }
        return hay;
    }

    /**
     * Recibe una partida y un codigo de color y recorre el tablero de la
     * partida fijandose si hay tres gatitos del mismo color juntos.
     *
     * @param colorANSI
     * @param partida
     * @return Si hay tres gatitos del mismo color juntos, devuelve un string
     * declarandolo ganador, de lo contrario un string "0".
     */
    public String tresGatitosJuntosSimple(String colorANSI, Partida partida) {
        String resultado = "0";

        if ((partida.getTipo()).equalsIgnoreCase("simple")) {
            for (int i = 2; i < 8; i++) {
                for (int j = 2; j < 8; j++) {
                    if (this.hayTipoFichaYColor(i, j, 'g', colorANSI)) {
                        if (this.tresFichasIgualesJuntas('g', i, j, colorANSI)) {
                            resultado = "GANADOR: JUGADOR ROJO";
                            if (colorANSI.equals(Auxiliar.COLOR_AZUL)) {
                                resultado = "GANADOR: JUGADOR AZUL";
                            }
                        }
                    }
                }
            }
        }
        return colorANSI + resultado;
    }

    /**
     * Se fija si la celda que contiene un gatito posee otras dos, en la
     * horizontal, con gatitos del mismo color.
     *
     * @param partida
     * @param colorANSI
     * @param i
     * @param j
     * @return Matriz de enteros con las coordenadas de los tres gatitos
     * seguidos. Si no los hubiera, matriz de 0.
     */
    public int[][] tresGatitosHorizontal(Partida partida, String colorANSI, int i, int j) {
        int[][] retorno = new int[3][2];

        if (this.hayTipoFichaYColor(i, j, 'g', colorANSI)) {
            if ((this.hayTipoFichaYColor(i, j + 1, 'g', colorANSI))
                    && (this.hayTipoFichaYColor(i, j + 2, 'g', colorANSI))) {
                retorno[0][0] = i;
                retorno[0][1] = j;

                retorno[1][0] = i;
                retorno[1][1] = j + 1;

                retorno[2][0] = i;
                retorno[2][1] = j + 2;
            }
        }
        return retorno;
    }

    /**
     * Se fija si la celda que contiene un gatito posee otras dos, en la
     * columna, con gatitos del mismo color.
     *
     * @param partida
     * @param colorANSI
     * @param i
     * @param j
     * @return Matriz de enteros con las coordenadas de los tres gatitos
     * seguidos. Si no los hubiera, matriz de 0.
     */
    public int[][] tresGatitosVertical(Partida partida, String colorANSI, int i, int j) {
        int[][] retorno = new int[3][2];

        if (this.hayTipoFichaYColor(i, j, 'g', colorANSI)) {
            if (this.hayTipoFichaYColor(i + 1, j, 'g', colorANSI)
                    && this.hayTipoFichaYColor(i + 2, j, 'g', colorANSI)) {
                retorno[0][0] = i;
                retorno[0][1] = j;

                retorno[1][0] = i + 1;
                retorno[1][1] = j;

                retorno[2][0] = i + 2;
                retorno[2][1] = j;
            }
        }
        return retorno;
    }

    /**
     * Se fija si la celda que contiene un gatito posee otras dos, en la
     * diagonal inferior derecha, con gatitos del mismo color.
     *
     * @param partida
     * @param colorANSI
     * @param i
     * @param j
     * @return Matriz de enteros con las coordenadas de los tres gatitos
     * seguidos. Si no los hubiera, matriz de 0.
     */
    public int[][] tresGatitosDiagonalDerecha(Partida partida, String colorANSI, int i, int j) {
        int[][] retorno = new int[3][2];

        if (this.hayTipoFichaYColor(i, j, 'g', colorANSI)) {
            if ((this.hayTipoFichaYColor(i + 1, j + 1, 'g', colorANSI)
                    && this.hayTipoFichaYColor(i + 2, j + 2, 'g', colorANSI))) {
                retorno[0][0] = i;
                retorno[0][1] = j;

                retorno[1][0] = i + 1;
                retorno[1][1] = j + 1;

                retorno[2][0] = i + 2;
                retorno[2][1] = j + 2;
            }
        }
        return retorno;
    }

    /**
     * Se fija si la celda que contiene un gatito posee otras dos, en la
     * diagonal inferior izquierda, con gatitos del mismo color.
     *
     * @param partida
     * @param colorANSI
     * @param i
     * @param j
     * @return Matriz de enteros con las coordenadas de los tres gatitos
     * seguidos. Si no los hubiera, matriz de 0.
     */
    public int[][] tresGatitosDiagonalIzquierda(Partida partida, String colorANSI, int i, int j) {
        int[][] retorno = new int[3][2];

        if (this.hayTipoFichaYColor(i, j, 'g', colorANSI)) {
            if (this.hayTipoFichaYColor(i + 1, j - 1, 'g', colorANSI)
                    && this.hayTipoFichaYColor(i + 2, j - 2, 'g', colorANSI)) {
                retorno[0][0] = i;
                retorno[0][1] = j;

                retorno[1][0] = i + 1;
                retorno[1][1] = j - 1;

                retorno[2][0] = i + 2;
                retorno[2][1] = j - 2;
            }
        }
        return retorno;
    }

    /**
     * Recorreel tablero fijandose si hay tres gatitos juntos de mismo color en
     * una partida full. Si los hay, hace el cambio a gato, sino no hace nada.
     *
     * @param partida
     * @param colorANSI
     */
    public void tresGatitosJuntosFull(String colorANSI, Partida partida) {
        int[][] tresSeguidos = new int[3][2];
        ArrayList<int[][]> listaTresGatitos = new ArrayList<int[][]>();
        int[][] trioABorrar = new int[3][2];

        for (int i = 2; i < 8; i++) {
            for (int j = 2; j < 8; j++) {
                if (this.hayTipoFichaYColor(i, j, 'g', colorANSI)) {
                    listaTresGatitos.clear();
                    tresSeguidos = this.tresGatitosDiagonalDerecha(partida, colorANSI, i, j);
                    if (tresSeguidos[0][0] != 0) {
                        listaTresGatitos.add(tresSeguidos);
                        trioABorrar = tresSeguidos;
                    }
                    tresSeguidos = this.tresGatitosHorizontal(partida, colorANSI, i, j);
                    if (tresSeguidos[0][0] != 0) {
                        listaTresGatitos.add(tresSeguidos);
                        trioABorrar = tresSeguidos;
                    }
                    tresSeguidos = this.tresGatitosDiagonalDerecha(partida, colorANSI, i, j);
                    if (tresSeguidos[0][0] != 0) {
                        listaTresGatitos.add(tresSeguidos);
                        trioABorrar = tresSeguidos;
                    }
                    tresSeguidos = this.tresGatitosDiagonalIzquierda(partida, colorANSI, i, j);
                    if (tresSeguidos[0][0] != 0) {
                        listaTresGatitos.add(tresSeguidos);
                        trioABorrar = tresSeguidos;
                    }
                    if (listaTresGatitos.size() > 1) {
                        trioABorrar = Interfaz.preguntarCualTrioBorrar(listaTresGatitos);
                        this.cambiarGatitosPorGatos(trioABorrar, partida, colorANSI);
                    }
                }
            }
        }
    }

    /**
     * Realiza el cambio de tres gatitos a gato, quitando los gattos del tablero
     * y sumando tres gatos a a caja.
     *
     * @param cambiar
     * @param partida
     * @param colorANSI
     */
    public void cambiarGatitosPorGatos(int[][] cambiar, Partida partida, String colorANSI) {
        this.quitarTresFichasDelTablero(cambiar[0][0], cambiar[0][0],
                cambiar[1][0], cambiar[1][1],
                cambiar[2][0], cambiar[2][1]);
        partida.getJugador(colorANSI).poner3GatosEnCaja();
    }

    /**
     * Recorre el tablero de la partida y chequea si hay tres fichas de tipo 'G'
     * juntas del mismo color.
     *
     * @param colorANSI
     * @return Si hay tres gatos del mismo color en celdas adyacentes retorna un
     * string declarando como ganador a ese jugador. De lo contrario devuelve un
     * string "0".
     */
    public String tresGatosJuntos(String colorANSI) {
        String resultado = "0";

        for (int i = 2; i < 8; i++) {
            for (int j = 2; j < 8; j++) {
                if (this.hayTipoFichaYColor(i, j, 'G', colorANSI)) {
                    if (this.tresFichasIgualesJuntas('G', i, j, colorANSI)) {
                        resultado = "GANADOR: JUGADOR ROJO";
                        if (colorANSI.equals(Auxiliar.COLOR_AZUL)) {
                            resultado = "GANADOR: JUGADOR AZUL";
                        }
                    }
                }
            }
        }

        return colorANSI + resultado;
    }

    /**
     * Cuenta la cantidad de gatos grandes que tienen los jugadores en el
     * tablero y las retorna en un array.
     *
     * @return Array de enteros: en la posicion 0, cantidad de gatos rojos, en
     * la posicion 1, cantidad de gatos azules.
     */
    public int[] cantGatosGrandes() {
        int[] cant = new int[2];
        cant[0] = 0;
        cant[1] = 0;
        for (int i = 2; i < 8; i++) {
            for (int j = 2; j < 8; j++) {
                if (this.getCeldaTablero(i, j).contains("G")) {
                    if (this.getCeldaTablero(i, j).contains(Auxiliar.COLOR_ROJO)) {
                        cant[0]++;
                    } else if (this.getCeldaTablero(i, j).contains(Auxiliar.COLOR_AZUL)) {
                        cant[1]++;
                    }
                }
            }
        }
        return cant;
    }
}
