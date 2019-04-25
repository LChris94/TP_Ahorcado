package Modelos;

import DAO.AhorcadoDaoMysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ahorcado {

    private int cantidad_vidas;
    private int letras_restantes;
    private List<Character> palabra = new ArrayList<Character>();
    private List<Character> letras_acertadas = new ArrayList<Character>();
    private List<Character> letras_arrojadas = new ArrayList<Character>();
    private Boolean turno_disponible = true;

    public Ahorcado(int cantidad_vidas, String palabra) {
        this.setCantidad_vidas(cantidad_vidas);
        this.setPalabra(palabra);
        letras_restantes = palabra.length();
        System.out.println("La Palabra a Adivinar es: *** "+palabra+" ***");
    }




    public void setCantidad_vidas(int cantidad_vidas) {
        this.cantidad_vidas = cantidad_vidas;
    }

    public synchronized Jugador Turno(Jugador jugador) throws InterruptedException, SQLException {

        while (!turno_disponible)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }

        this.turno_disponible = false;
        if(getLetras_restantes()>0) {

            Character letra = Abecedario.dame_letra();

            while (this.getLetras_arrojadas().contains(letra)) {
                letra = Abecedario.dame_letra();
            }
            this.setLetras_arrojadas(letra);
            System.out.println(jugador.getNombre()+"["+jugador.getVidas()+"]" + " eligio la letra " + letra);
            if (this.palabra.contains(letra))//TRUE
            {

                System.out.println("La pego la letra " + letra + " se encuentra en la palabra");
                this.setLetras_restantes(this.cantidad_veces_letra(palabra, letra) * -1);
                letras_acertadas.add(letra);
                if (getLetras_restantes() == 0) {
                    System.out.println("Jugador: " + jugador.getNombre() + " gano el juego.");
                    AhorcadoDaoMysql.getInstance().crear_ganador(jugador,palabra_restante());
                    jugador.setPuede_jugar(false);
                }

            } else//FALSE
            {

                if (jugador.getVidas() == 0) {
                    System.out.println("Jugador: " + jugador.getNombre() + " se quedo sin vidas murio.");
                    jugador.setPuede_jugar(false);
                }
                jugador.setVidas(-1);

            }
            System.out.println(palabra_restante());
        }
        this.turno_disponible = true;
        notifyAll();
        TimeUnit.SECONDS.sleep(3);
        return jugador;



    }


    public List<Character> getLetras_arrojadas() {
        return letras_arrojadas;
    }

    public void setLetras_arrojadas(Character letra) {
        this.letras_arrojadas.add(letra);
    }


    public int getLetras_restantes() {
        return letras_restantes;
    }

    public void setLetras_restantes(int letras_restantes) {
        this.letras_restantes += letras_restantes;
    }

    public int cantidad_veces_letra(List<Character> palabra, char letra)
    {
        int rta = 0;
        for(int i=0;i<palabra.size();i++)
        {
            if(palabra.get(i) == letra)
            {
                rta ++;
            }
        }
        return rta;

    }

    public List<Character> getPalabra() {
        return palabra;
    }

    public void setPalabra(String str) {
        for (int n = 0; n <str.length (); n ++)
        {
            char c = str.charAt(n);
            this.palabra.add(c);
        }


    }

    public String palabra_restante()
    {
        char[] rta = new char[palabra.size()];
        for(int j=0;j<letras_acertadas.size();j++) {
            for (int i = 0; i < palabra.size(); i++) {
                if (palabra.get(i) == letras_acertadas.get(j)) {
                    rta[i] = letras_acertadas.get(j);
                }

            }
        }
        return String.valueOf(rta);
    }

    public int getCantidad_vidas() {
        return cantidad_vidas;
    }
}
