package dominio;

import java.util.Comparator;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class CriterioNombreAlfabetico implements Comparator<Usuario> {

    @Override
    public int compare(Usuario g1, Usuario g2) {
        return g1.getNombre().toUpperCase().compareTo(
                g2.getNombre().toLowerCase()
        );
    }
}
