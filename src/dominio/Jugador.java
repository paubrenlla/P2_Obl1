package dominio;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Jugador {

    private Usuario jugador;
    private String color;
    private int[] caja;

    //Constructor
    public Jugador(Usuario unJugador, String unColor) {
        this.setJugador(unJugador);
        this.setColor(unColor);
        this.caja = new int[2];
        setCeldaCaja(0, 8);
        setCeldaCaja(1, 0);
    }

    //Getters and Setters
    public Usuario getJugador() {
        return jugador;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private int[] getCaja() {
        return caja;
    }

    public void setCaja(int[] caja) {
        this.caja = caja;
    }

    public int getCeldaCaja(int n) {
        return this.caja[n];
    }

    public void setCeldaCaja(int n, int dato) {
        this.caja[n] = dato;
    }

    //Metodos
    public void sacarGatitodeCaja() {
        this.setCeldaCaja(0, this.getCeldaCaja(0) - 1);
    }

    public void ponerGatitoEnCaja() {
        this.setCeldaCaja(0, this.getCeldaCaja(0) + 1);
    }

    public void sacarGatoDeCaja() {
        this.setCeldaCaja(1, this.getCeldaCaja(1) - 1);
    }

    public void ponerGatoEnCaja() {
        this.setCeldaCaja(1, this.getCeldaCaja(1) + 1);
    }

    public void poner3GatosEnCaja() {
        this.setCeldaCaja(1, this.getCeldaCaja(1) + 3);
    }

    /**
     * Dependiendo del tipo de gato, suma uno en la caja correspondiente (0 si
     * es del tipo 'g', 1 si es del tipo 'G').
     *
     * @param tipoGato
     */
    public void ponerFichaEnCaja(char tipoGato) {
        if (tipoGato == 'g') {
            ponerGatitoEnCaja();
        } else {
            ponerGatoEnCaja();
        }
    }

    /**
     * Dependiendo del tipo de gato, resta uno en la caja correspondiente (0 si
     * es del tipo 'g', 1 si es del tipo 'G').
     *
     * @param tipoGato
     */
    public void sacarFichaDeCaja(char tipoGato) {
        if (tipoGato == 'g') {
            sacarGatitodeCaja();
        } else {
            sacarGatoDeCaja();
        }
    }

    /**
     * Muestra la cantidad de fichas y el tipo del jugador.
     *
     * @return String de formato: caja(de color de jugador): cant gatitos, cant
     * gatos.
     */
    public String mostrarCantGatos() {
        return ("En " + this.getColor() + "caja: " + Auxiliar.COLOR_RESET
                + this.getCeldaCaja(0) + " gatitos, " + this.getCeldaCaja(1) + " gatos");
    }

}
