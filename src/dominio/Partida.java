package dominio;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Partida {

    // atributos
    private TableroColcha tablero;
    private String tipo;
    private Jugador jugadorRojo;
    private Jugador jugadorAzul;

    // constructor
    /**
     * Recibe un tipo de partida y dos jugadores y crea una nueva partida,
     * iniciando el tablero.
     *
     * @param unTipo
     * @param unJugadorRojo
     * @param unJugadorAzul
     */
    public Partida(String unTipo, Jugador unJugadorRojo, Jugador unJugadorAzul) {
        this.setTipo(unTipo);
        this.setJugadorRojo(unJugadorRojo);
        this.setJugadorAzul(unJugadorAzul);
        tablero = new TableroColcha();
    }

    //Getters and Setters
    public TableroColcha getTableroColcha() {
        return tablero;
    }

    public void setTableroColcha(TableroColcha tablero) {
        this.tablero = tablero;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String unTipo) {
        this.tipo = unTipo;
    }

    public Jugador getJugadorRojo() {
        return this.jugadorRojo;
    }

    public void setJugadorRojo(Jugador unJugadorRojo) {
        this.jugadorRojo = unJugadorRojo;
    }

    public Jugador getJugadorAzul() {
        return this.jugadorAzul;
    }

    public void setJugadorAzul(Jugador unJugadorAzul) {
        this.jugadorAzul = unJugadorAzul;
    }

    /**
     * Retorna al jugador de color solicitado.
     * @param color
     * @return 
     */
    public Jugador getJugador(String color) {
        Jugador retorno;
        if (color.equalsIgnoreCase(Auxiliar.COLOR_ROJO)) {
            retorno = this.getJugadorRojo();
        } else {
            retorno = this.getJugadorAzul();
        }
        return retorno;
    }

    //Metodos
    /**
     * Luego de cada jugada se fija si hay ganador y se finaliza la jugada en
     * caso positivo, de lo contrario continua la partida.
     *
     * @param colorANSI
     * @return String con el color del jugador ganador si hay, de lo contrrio "0".
     */
    public String busquedaGanador(String colorANSI) {
        String retorno = "0";
        String aux = "0";

        if (this.getTipo().equalsIgnoreCase("simple")) {
            aux = this.getTableroColcha().tresGatitosJuntosSimple(colorANSI, this);
            if (aux.contains("GANADOR")) {
                retorno = aux;
            }
        }

        if (this.getTipo().equalsIgnoreCase("full")) {
            aux = this.getTableroColcha().tresGatosJuntos(colorANSI);
            if (aux.contains("GANADOR")) {
                retorno = aux;
            } else if (this.getTableroColcha().cantGatosGrandes()[0] == 8) {
                retorno = Auxiliar.COLOR_ROJO + "GANADOR: JUGADOR ROJO" + Auxiliar.COLOR_RESET;
            } else if (this.getTableroColcha().cantGatosGrandes()[1] == 8) {
                retorno = Auxiliar.COLOR_AZUL + "GANADOR: JUGADOR AZUL" + Auxiliar.COLOR_RESET;
            } else {
                this.getTableroColcha().tresGatitosJuntosFull(colorANSI, this);
            }
        }

        return retorno;
    }

}
