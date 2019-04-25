package Modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Abecedario {
    static Character[] letras = {'A','B','C','D','E','F','G','H','I','J','K','L','Ñ','N','M','O','P','Q','R','S','T','U','V','X','Y','Z'};

    static public Character dame_letra()
    {

        Random rand = new Random();
        int numero = rand.nextInt(26);
        return letras[numero];
    }

}
