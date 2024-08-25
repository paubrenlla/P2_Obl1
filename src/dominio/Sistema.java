package dominio;

import java.util.*;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Sistema {

    // atributo
    private ArrayList<Usuario> listaUsuarios;

    // constructor
    public Sistema() {
        listaUsuarios = new ArrayList<>();
    }

    // getters y setters
    public ArrayList<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

    public void setListaJugadores(ArrayList<Usuario> unaListaDeUsuarios) {
        this.listaUsuarios = unaListaDeUsuarios;
    }

    // metodos de instancia
    public void agregarJugador(Usuario unUsuario) {
        listaUsuarios.add(unUsuario);
    }

    public boolean existeJugador(Usuario unUsuario) {
        return this.listaUsuarios.contains(unUsuario);
    }

    /**
     * Imprime lista jugadores de forma: n) nombre (alias), edad
     */
    public void imprimirListaJugadores() {    // n) nombre (alias), edad
        Iterator<Usuario> it = listaUsuarios.iterator();
        int n = 1;
        while (it.hasNext()) {
            Usuario j = it.next();
            System.out.print(n + ") ");    // cambiar por toString
            System.out.println(j);
            n++;
        }
    }

    /**
     * Ordena una lista de usuarios teniendo en cuenta el nombre del usuario de
     * forma alfabetica.
     *
     * @return ArrayList de usuarios ordenada por nombre de forma alfabetica.
     */
    public ArrayList<Usuario> criterioDescripcionCreciente() {
        Collections.sort(this.getListaUsuarios(),
                new CriterioNombreAlfabetico());

        return this.getListaUsuarios();
    }

}
