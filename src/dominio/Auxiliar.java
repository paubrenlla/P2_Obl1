package dominio;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */

public class Auxiliar {

    // Constantes
    //colores
    public static final String COLOR_ROJO = "\u001B[31m";
    public static final String COLOR_AZUL = "\u001B[34m";
    public static final String COLOR_VERDE = "\u001B[32m";
    public static final String COLOR_VIOLETA = "\u001B[35m";
    public static final String COLOR_CELESTE = "\u001B[36m";
    public static final String COLOR_AMARILLO = "\u001B[33m";
    public static final String COLOR_RESET = "\u001B[0m";

    //g y G en color
    public static final String GATITO_ROJO = COLOR_ROJO + "g" + COLOR_RESET;
    public static final String GATO_ROJO = COLOR_ROJO + "G" + COLOR_RESET;
    public static final String GATITO_AZUL = COLOR_AZUL + "g" + COLOR_RESET;
    public static final String GATO_AZUL = COLOR_AZUL + "G" + COLOR_RESET;

    /* Hallamos como usar colores en un print en:
    https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */
}
