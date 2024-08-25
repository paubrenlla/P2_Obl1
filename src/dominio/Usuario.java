package dominio;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Usuario{

    // Atributos
    private String nombre;
    private int edad;
    private String alias; //unico
    private int cantPartidas;

    // Constructor
    public Usuario(String unNombre, int unaEdad, String unAlias) {
        this.setNombre(unNombre);
        this.setEdad(unaEdad);
        this.setAlias(unAlias);
        this.setCantPartidas(0);
    }

    // Getters y setters
    public int getCantPartidas() {
        return this.cantPartidas;
    }

    private void setCantPartidas(int unaCantPartidas) {
        this.cantPartidas = unaCantPartidas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int unaEdad) {
        this.edad = unaEdad;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String unAlias) {
        this.alias = unAlias;
    }

    // Metodos de instancia
    /**
     * Aumenta una unidad la cantidad de partidas jugadas por un jugador.
     */
    public void agregarPartida() {
        this.cantPartidas++;
    }

    // Metodos sobrecargados
    /**
     * Compara este usuario con otro segun el orden alfabetico del nombre del usuario.
     *
     * @param unUsuario
     * @return 
     */
   // @Override
    public int compareTo(Usuario unUsuario) {
        return this.getNombre().compareTo(unUsuario.getNombre());
    }

    /**
     * Compara si dos usuarios son iguales por su alias.
     * @param unObjeto
     * //@return 
     */
    @Override
    public boolean equals(Object unObjeto) {
        Usuario j = (Usuario) unObjeto;
        return j.getAlias().equals(this.getAlias());
    }

    /**
     * Retorna un string con el formato: nombre (alias), edad
     * @return 
     */
    @Override
    public String toString() {
        return this.getNombre() + "(" + this.getAlias() + "), edad " + this.getEdad();
    }
}
