package main;

import interfaz.*;
import dominio.*;

/**
 * @author Paula Brenlla (311149) & Agustin Russo (286669)
 */
public class Main {

    public static void main(String[] args) {
        Sistema unSistema = new Sistema();

        Interfaz.menu(unSistema);
    }
}
