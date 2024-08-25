package dominio;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Jugada {

    /**
     * Recibe coordenadas en forma de String y lo devuelve como coordenadas
     * numericas de la celda del tablero a la que corresopnde.
     *
     * @param jugada
     * @return Array con enteros: en la celda 0, coordenada de fila, en la celda
     * 1, coordenada de columna.
     */
    public static int[] traducirJugada(String jugada) {
        int[] arrJugada = new int[2];
        int coordFila = 0;
        int coordColu = 0;

        if (jugada.length() == 2) {
            switch (jugada.charAt(0)) {
                case 'A':
                    coordFila = 2;
                    break;
                case 'B':
                    coordFila = 3;
                    break;
                case 'C':
                    coordFila = 4;
                    break;
                case 'D':
                    coordFila = 5;
                    break;
                case 'E':
                    coordFila = 6;
                    break;
                case 'F':
                    coordFila = 7;
                    break;
            }

            switch (jugada.charAt(1)) {
                case '1':
                    coordColu = 2;
                    break;
                case '2':
                    coordColu = 3;
                    break;
                case '3':
                    coordColu = 4;
                    break;
                case '4':
                    coordColu = 5;
                    break;
                case '5':
                    coordColu = 6;
                    break;
                case '6':
                    coordColu = 7;
                    break;
            }
        }

        arrJugada[0] = coordFila;
        arrJugada[1] = coordColu;

        return arrJugada;
    }

    /**
     * Recibe las coordenadas como caracteres y las devuelve como coordenadas
     * numericas de la celda del tablero a la que corresopnde.
     *
     * @param letra
     * @param numero
     * @return Array con enteros: en la celda 0, coordenada de fila, en la celda
     * 1, coordenada de columna.
     */
    public static int[] traducirJugada(char letra, char numero) {
        int[] arrJugada = new int[2];
        int coordFila = 0;
        int coordColu = 0;

        switch (letra) {
            case 'A':
                coordFila = 2;
                break;
            case 'B':
                coordFila = 3;
                break;
            case 'C':
                coordFila = 4;
                break;
            case 'D':
                coordFila = 5;
                break;
            case 'E':
                coordFila = 6;
                break;
            case 'F':
                coordFila = 7;
                break;
        }

        switch (numero) {
            case '1':
                coordColu = 2;
                break;
            case '2':
                coordColu = 3;
                break;
            case '3':
                coordColu = 4;
                break;
            case '4':
                coordColu = 5;
                break;
            case '5':
                coordColu = 6;
                break;
            case '6':
                coordColu = 7;
                break;
        }

        arrJugada[0] = coordFila;
        arrJugada[1] = coordColu;

        return arrJugada;
    }

    public static char desTraducirJugada(int letra) {
        char retorno = '0';
        switch (letra) {
            case 1:
                retorno = 'A';
                break;
            case 2:
                retorno = 'B';
                break;
            case 3:
                retorno = 'C';
                break;
            case 4:
                retorno = 'D';
                break;
            case 5:
                retorno = 'E';
                break;
            case 6:
                retorno = 'F';
                break;
        }
        return retorno;
    }

    /**
     * Valida que las coordenadas no se vayan del rango del tablero.
     *
     * @param fila
     * @param columna
     * @param tabl
     * @return True si las coordenadas estan dentro del rango del tablero, false
     * de lo contrario.
     */
    public static boolean validarCoords(int fila, int columna, String[][] tabl) {
        boolean ok = false;
        if ((fila > '@') && (fila < 'G')) {
            if ((columna > '0') && (columna < '7')) {
                ok = true;
            }
        }
        return ok;
    }

    /**
     * Recibe una jugada y valida que su formato sea correcto y que este en el
     * rango del tablero.
     *
     * @param jugada
     * @param tabCol
     * @param partida
     * @param colorANSI
     * @return True si el fomrato de jugada es valido y se encuentra en el rango
     * del tablero, false de lo contrario.
     */
    public static boolean validarJugada(String jugada, TableroColcha tabCol, Partida partida, String colorANSI) {
        String jugadaMayus = jugada.toUpperCase();
        boolean validar = false;

        if (jugadaMayus.equals("X")) {
            validar = true;
        } else if (partida.getTipo().equalsIgnoreCase("Simple")) {
            if (jugada.length() == 2) {
                if (partida.getJugador(colorANSI).getCeldaCaja(0) >= 1) {
                    validar = validarJugadaSimple(jugadaMayus, tabCol);
                }
            }
        } else if (partida.getTipo().equalsIgnoreCase("Full")) {
            if (jugada.length() == 3) {
                validar = validarJugadaFull(jugada, partida, colorANSI);
            } else if (jugada.length() == 5) {
                if ((partida.getJugador(colorANSI).getCeldaCaja(0) == 0)
                        && (partida.getJugador(colorANSI).getCeldaCaja(1) == 0)) {
                    validar = validarRegAd(jugadaMayus, tabCol, colorANSI);
                }
            }
        } else if (jugadaMayus.equals("X")) {
            validar = true;
        }

        return validar;
    }

    /**
     * Recibe una jugada de regla adicional y la valida si la regla es
     * aplicable.
     *
     * @param jugada
     * @param tabCol
     * @param colorANSI
     * @return True si la regla adicional es aplicable, false de lo contrario.
     */
    public static boolean validarRegAd(String jugada, TableroColcha tabCol, String colorANSI) {
        boolean valida = false;
        //ra1A1 o ra2A1
        if ((jugada.charAt(0) == 'R') && (jugada.charAt(1) == 'A')) {
            if (validarCoords(jugada.charAt(3), jugada.charAt(4), tabCol.getTablero())) {
                int[] coords = traducirJugada(jugada.charAt(3), jugada.charAt(4));
                if (jugada.charAt(2) == '1') {
                    valida = tabCol.hayTipoFicha(coords[0], coords[1], 'g')
                            && (tabCol.getCeldaTablero(0, 1).contains(colorANSI));
                }
                if (jugada.charAt(2) == '2') {
                    valida = tabCol.hayTipoFicha(coords[0], coords[1], 'G')
                            && (tabCol.getCeldaTablero(0, 1).contains(colorANSI));
                }
            }
        }
        return valida;
    }

    /**
     * Recibe una jugada para colocar fichas de una jugada full y valida que la
     * jugada ingresada sea la esperada.
     *
     * @param jugada
     * @param partida.getTableroColcha()
     * @param colorANSI
     * @return True si la jugada ingresada es la esperada, false de lo
     * contrario.
     */
    public static boolean validarJugadaFull(String jugada, Partida partida, String colorANSI) {
        boolean valida = false;
        String jugadaMayus = jugada.toUpperCase();
        if (validarJugadaSimple(jugadaMayus.charAt(0), jugadaMayus.charAt(1), partida.getTableroColcha())) {
            if ((((jugada.charAt(2)) == 'g') && ((partida.getJugador(colorANSI).getCeldaCaja(0)) >= 1))
                    || ((jugada.charAt(2)) == 'G') && ((partida.getJugador(colorANSI).getCeldaCaja(1)) >= 1)) {
                int[] arrJugada = traducirJugada(jugadaMayus.charAt(0), jugadaMayus.charAt(1));
                valida = !(partida.getTableroColcha().hayFicha(arrJugada[0], arrJugada[1]));
            }
        }

        return valida;
    }

    /**
     * Recibe una jugada de partida simple en forma de string y retorna
     * verdadero si su forma es la esperada.
     *
     * @param jugada
     * @param cabCol
     * @return True si la fomra de la jugada es la esperada. False de lo
     * contrario.
     */
    public static boolean validarJugadaSimple(String jugada, TableroColcha cabCol) {
        boolean valida = false;
        if (jugada.length() == 2) {
            if ((jugada.charAt(0) > '@') && (jugada.charAt(0) < 'G')) {
                if ((jugada.charAt(1) > '0') && (jugada.charAt(1) < '7')) {
                    int[] arr = Jugada.traducirJugada(jugada);
                    valida = !(cabCol.hayTipoFicha(arr[0], arr[1], 'g'));
                }
            }
        }
        return valida;
    }

    /**
     * Recibe los caracteres de la jugada de partida simple y retorna si se
     * encuentran dentro del rango del tablero.
     *
     * @param letra
     * @param numero
     * @param tabCol
     * @return True si la jugada se encuentra dentro del rango esperado. False
     * de lo contrario.
     */
    public static boolean validarJugadaSimple(char letra, char numero, TableroColcha tabCol) {
        boolean valida = false;
        if ((letra > '@') && (letra < 'G')) {
            if ((numero > '0') && (numero < '7')) {
                int[] arr = Jugada.traducirJugada(letra, numero);

                valida = !(tabCol.hayTipoFicha(arr[0], arr[1], 'g'));
            }
        }
        return valida;
    }

    /**
     * Realiza la jugada ya validada colocando o quitando gatitos de la colcha y
     * juntando a los que se salen. Aumenta o disminuye la cantidad de fichas en
     * la caja segun corresponda.
     *
     * @param partida
     * @param jugada
     * @param colorANSI
     */
    public static void realizarJugada(Partida partida, String jugada, String colorANSI) {
        String jugadaMayus = jugada.toUpperCase();
        //Jugada de colocar ficha
        if (jugada.length() <= 3) {
            int[] coords = traducirJugada(jugadaMayus.charAt(0), jugadaMayus.charAt(1));
            char tipoGato = '0';
            if ((partida.getTipo()).equalsIgnoreCase("simple")) {
                tipoGato = 'g';
            } else if ((partida.getTipo()).equalsIgnoreCase("full")) {
                tipoGato = jugada.charAt(2);
            }
            partida.getTableroColcha().colocarFicha(partida, colorANSI, coords, tipoGato);

            if ((tipoGato == 'g') || (tipoGato == 'G')) {
                partida.getJugador(colorANSI).sacarFichaDeCaja(tipoGato);
            }

            partida.getTableroColcha().quitarFichasBordeTablero(partida);
        }
        //Jugada de Regla Adicional
        if (jugada.length() == 5) {
            char tipoFicha = 'g';
            if (jugadaMayus.charAt(2) == 'G') {
                tipoFicha = 'G';
            }
            partida.getJugador(colorANSI).ponerFichaEnCaja(tipoFicha);
        }
    }
}
