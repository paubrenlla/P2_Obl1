package interfaz;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.*;
import dominio.*;
import java.io.*;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Interfaz {

    /**
     * Despliega el menu del juego en consola.
     *
     * @param unSistema
     */
    public static final void menu(Sistema unSistema) {
        boolean salir = false;
        int opcion;
        while (!salir) {
            System.out.println("*** Bienvenido a SALTITOS ***");
            System.out.println("1. Registrar jugador.");
            System.out.println("2. Jugar SALTITOS simple version.");
            System.out.println("3. Jugar SALTITOS full version.");
            System.out.println("4. Generar reporte.");
            System.out.println("5. Exit.");
            opcion = pedirNumero("Ingrese una opcion:", 1, 5);
            switch (opcion) {
                case 1:    // registar jugador
                    registrarJugador(unSistema);
                    break;
                case 2:    // juego version simple
                    validarCrearPartida(unSistema, "simple");
                    break;
                case 3:    // juego version full
                    validarCrearPartida(unSistema, "full");
                    break;
                case 4:    // generar reporte
                    crearPdf(unSistema);
                    break;
                case 5:    // salir
                    salir = true;
                    break;
            }
        }
    }

    /**
     * Valida que lo ingresado sea unicamente un numero entero y este en el
     * rango esperado. De lo contrario, solicita reingreso.
     *
     * @param texto
     * @param min
     * @param max
     * @return Numero entero entre los parametros solicitados.
     */
    public static final int pedirNumero(String texto, int min, int max) {
        // Chequea que el numero ingresado sea valido y este en rango requerido
        Scanner in = new Scanner(System.in);
        int numero = 0;
        boolean ingresoValido = false;
        while (!ingresoValido) {
            try {
                System.out.println(texto);
                numero = in.nextInt();
                in.nextLine();

                if ((numero < min) || (numero > max)) {
                    System.out.println("Ingreso invalido. Por favor digite un numero valido");
                    ingresoValido = false;
                } else {
                    ingresoValido = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingreso invalido. Por favor digite solamente un numero");
                in.nextLine();
            }
        }
        return numero;
    }

    /**
     * Solicita el ingreso de datos validos de usuario al sistema. Chequea la
     * unicidad de alias y pide reingreso hasta que sea unico.
     *
     * @param unSistema
     */
    public static void registrarJugador(Sistema unSistema) {
        Scanner in = new Scanner(System.in);

        System.out.println("Ingrese nombre.");
        String nombre = in.nextLine();
        while (nombre.isBlank()) {
            System.out.println("Ingreso invalido. Por favor escriba un nombre.");
            nombre = in.nextLine();
        }

        int edad = pedirNumero("Ingrese edad:", 1, 150);

        System.out.println("Ingrese alias.");
        String alias = in.nextLine();
        Usuario j = new Usuario(nombre, edad, alias);

        //chequeo unicidad de alias
        while (unSistema.existeJugador(j)) {
            System.out.println("Ya existe un jugador con ese alias, reingreselo.");
            alias = in.nextLine();
            j.setAlias(alias);
        }

        unSistema.getListaUsuarios().add(j);
        System.out.println("Jugador registrado con exito.");
        System.out.println("");
    }

    /**
     * Valida la creacion de una partida unicamente si hay al menos dos usuarios
     * registrados en el sistema. De lo contrario solicita registro de
     * jugadores.
     *
     * @param unSistema
     * @param unTipo
     */
    public static final void validarCrearPartida(Sistema unSistema, String unTipo) {
        if (unSistema.getListaUsuarios().size() > 1) {
            jugar(crearPartida(unSistema, unTipo));
        } else {
            System.out.println("Debe haber al menos dos jugadores registrados "
                    + "para jugar");
        }
    }

    /**
     * Despliega lista enumerada de usuarios, solicita eleccion de jugadores
     * rojo y azul, valida que el jugador azul sea distinto al rojo jugadores y
     * crea la partida del tipo elegido en el menu.
     *
     * @param unSistema
     * @param unTipo
     * @return Una partida nueva, de el tipo seleccionado con los jugadores
     * escogidos y un tablero.
     */
    public static final Partida crearPartida(Sistema unSistema, String unTipo) {
        int maxJugadores = unSistema.getListaUsuarios().size();
        unSistema.imprimirListaJugadores();
        int nRojo = pedirNumero("Para elegir el jugador " + Auxiliar.COLOR_ROJO + "rojo"
                + Auxiliar.COLOR_RESET + ", ingrese su numero en la lista", 1, maxJugadores);
        Usuario jRojo = unSistema.getListaUsuarios().get(nRojo - 1);
        int nAzul = pedirNumero("Para elegir el jugador " + Auxiliar.COLOR_AZUL + " azul"
                + Auxiliar.COLOR_RESET + ", ingrese su numero en la lista", 1, maxJugadores);
        while (nAzul == nRojo) {
            nAzul = pedirNumero("Ha elegido el mismo jugador, seleccione otro de "
                    + "la lista por su numero", 1, maxJugadores);
        }
        Usuario jAzul = unSistema.getListaUsuarios().get(nAzul - 1);
        System.out.println("Jugadores seleccionados");
        jRojo.agregarPartida();
        jAzul.agregarPartida();
        Jugador rojo = new Jugador(jRojo, Auxiliar.COLOR_ROJO);
        Jugador azul = new Jugador(jAzul, Auxiliar.COLOR_AZUL);
        return new Partida(unTipo, rojo, azul);
    }

    /**
     * Hasta que la partida termine, solicita reingreso de jugadas por turno.
     *
     * @param partida
     */
    public static void jugar(Partida partida) {
        boolean finPartida = false;
        System.out.println("Recuerde: Si se queda sin gatitos, digite X y acepte su derrota!");
        //crear metodo booleano sobre fin de partida
        boolean turno = true;
        while (!finPartida) {
            String colorJugador = "rojo";
            String colorANSI = partida.getJugadorRojo().getColor();
            if (!turno) {
                colorANSI = partida.getJugadorAzul().getColor();
                colorJugador = "azul";
            }
            String unaJugada = ingresarJugadaValida(partida, colorJugador, colorANSI);

            if (unaJugada.equalsIgnoreCase("x")) {
                finPartida = true;
                System.out.println(colorANSI + "El jugador " + colorJugador
                        + " se ha rendido." + Auxiliar.COLOR_RESET);
            } else if ((partida.getJugador(colorANSI).getCeldaCaja(0) == 0) 
                    &&(partida.getJugador(colorANSI).getCeldaCaja(1) == 0)){
                finPartida = true;
                System.out.println(colorANSI + "El jugador " + colorJugador
                        + " se ha quedado sin fichas y ha perdido." + Auxiliar.COLOR_RESET);
            } else {
                Jugada.realizarJugada(partida, unaJugada, colorANSI);
                imprimirTablero(partida);

                if (partida.getTipo().equalsIgnoreCase("full")) {
                    partida.getTableroColcha().tresGatitosJuntosFull(Auxiliar.COLOR_ROJO, partida);
                    partida.getTableroColcha().tresGatitosJuntosFull(Auxiliar.COLOR_AZUL, partida);
                }

                if ((partida.busquedaGanador(Auxiliar.COLOR_ROJO).contains("GANADOR"))
                        && (partida.busquedaGanador(Auxiliar.COLOR_AZUL).contains("GANADOR"))) {
                    System.out.println("Empate!");
                } else if (partida.busquedaGanador(Auxiliar.COLOR_ROJO).contains("GANADOR")) {
                    finPartida = true;
                    System.out.println(partida.busquedaGanador(Auxiliar.COLOR_ROJO));
                } else if (partida.busquedaGanador(Auxiliar.COLOR_AZUL).contains("GANADOR")) {
                    finPartida = true;
                    System.out.println(partida.busquedaGanador(Auxiliar.COLOR_AZUL));
                }
            }
            System.out.println(Auxiliar.COLOR_RESET);
            turno = !turno;
        }
    }

    /**
     * Solicita que el jugador en turno ingrese una jugada hasta que la misma
     * sea valida.
     *
     * @param partida
     * @param colorJugador
     * @param colorANSI
     * @return Un string con el contenido de la jugada validada.
     */
    public static String ingresarJugadaValida(Partida partida, String colorJugador,
            String colorANSI) {
        Scanner in = new Scanner(System.in);
        System.out.println("Jugador " + colorANSI + colorJugador + Auxiliar.COLOR_RESET
                + ", ingrese su jugada");
        System.out.println(partida.getJugador(colorANSI).mostrarCantGatos());
        String unaJugada = in.nextLine();
        while (!(Jugada.validarJugada(unaJugada, partida.getTableroColcha(), partida, colorANSI))) {
            System.out.println(colorANSI + colorJugador + Auxiliar.COLOR_RESET
                    + ": por favor, ingrese una jugada valida");
            unaJugada = in.nextLine();
        }
        return unaJugada;
    }

    /**
     * Escribe en el pdf un usuario por linea con la cantidad de partidas que
     * jugo
     *
     * @param unSistema
     */
    public static final void crearPdf(Sistema unSistema) {
        Document doc = new Document();
        try {
            PdfWriter escritor = PdfWriter.getInstance(doc, new FileOutputStream("juego.pdf"));
            doc.open();
            ArrayList<Usuario> listaJugadores = unSistema.getListaUsuarios();
            for (int i = 0; i < listaJugadores.size(); i++) {
                Usuario user = listaJugadores.get(i);
                doc.add(new Paragraph(user + ", " + user.getCantPartidas() + " partidas jugadas"));
            }
            doc.close();
            escritor.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error de escritura");
        }
    }

    /*Hallamos como crear un archivo PDF y usarlo en:
    * https://www.javatpoint.com/java-create-pdf
     */
    /**
     * Imprime la colcha-tablero con formato.
     *
     * @param partida
     */
    public static void imprimirTablero(Partida partida) {
        String unColor = partida.getTableroColcha().getColorColcha();
        String resetColor = Auxiliar.COLOR_RESET;
        System.out.println("     1       2       3       4       5       6");
        for (int i = 0; i < 6; i++) {
            System.out.println(unColor + " * - - - * - - - * - - - * - - - * - - - * - - - *");
            System.out.print(" ");
            for (int j = 0; j < 6; j++) {
                System.out.print(unColor + "| " + partida.getTableroColcha().getColcha()
                        + " " + partida.getTableroColcha().getColcha() + " "
                        + partida.getTableroColcha().getColcha() + " ");
            }
            System.out.println("|");
            System.out.print(Auxiliar.COLOR_RESET + imprimirCoordenadaLetra(i));
            for (int j = 0; j < 6; j++) {
                System.out.print(unColor + "| " + partida.getTableroColcha().getColcha()
                        + " " + resetColor + partida.getTableroColcha().pedirFichaNoNula(i + 2, j + 2)
                        + unColor + " " + partida.getTableroColcha().getColcha() + " ");
            }
            System.out.println("|");
            System.out.print(" ");
            for (int j = 0; j < 6; j++) {
                System.out.print(unColor + "| " + partida.getTableroColcha().getColcha()
                        + " " + partida.getTableroColcha().getColcha() + " "
                        + partida.getTableroColcha().getColcha() + " ");
            }
            System.out.println("|");
        }
        System.out.println(unColor + " * - - - * - - - * - - - * - - - * - - - * - - - *" + Auxiliar.COLOR_RESET);
    }

    /**
     * Devuelve el caracter del numero pedido, tal que 0-> A y 5-> F.
     *
     * @param i
     * @return La coordenada y en forma numerica de la celda del tablero.
     */
    public static char imprimirCoordenadaLetra(int i) {
        char letra = ' ';
        switch (i) {
            case 0:
                letra = 'A';
                break;
            case 1:
                letra = 'B';
                break;
            case 2:
                letra = 'C';
                break;
            case 3:
                letra = 'D';
                break;
            case 4:
                letra = 'E';
                break;
            case 5:
                letra = 'F';
                break;
        }
        return letra;
    }

    public static int[][] preguntarCualTrioBorrar(ArrayList<int[][]> lista) {
        System.out.println("Elija que trio de gatitos quiere cambiar por gatos: ");
        Iterator<int[][]> it = lista.iterator();
        while (it.hasNext()) {
            int[][] mat = it.next();
            char fila1 = Jugada.desTraducirJugada(mat[0][0]);
            char fila2 = Jugada.desTraducirJugada(mat[1][0]);
            char fila3 = Jugada.desTraducirJugada(mat[2][0]);
            System.out.println("1) " + fila1 + mat[0][1]
                    + ", " + fila2 + mat[1][1]
                    + ", " + fila3 + mat[2][1]);

        }
        int eleccion = pedirNumero("", 1, lista.size());
        return (lista.get(eleccion- 1));
    }
}
