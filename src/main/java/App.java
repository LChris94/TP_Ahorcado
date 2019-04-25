import DAO.AhorcadoDaoMysql;
import Modelos.Abecedario;
import Modelos.Ahorcado;
import Modelos.Jugador;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Abecedario abc = new Abecedario();
        Ahorcado ahorcado = new Ahorcado(9,AhorcadoDaoMysql.getInstance().get_palabra());
        Jugador jugador_1 = new Jugador("Alan",true,ahorcado);
        Jugador jugador_2 = new Jugador("Christian",true,ahorcado);
        Jugador jugador_3 = new Jugador("Milan",true,ahorcado);
        jugador_1.start();
        jugador_2.start();
        jugador_3.start();
        jugador_1.join();
        jugador_2.join();
        jugador_3.join();
        if(ahorcado.getLetras_restantes()>0)
        {
            System.out.println("Nadie gano el juego.");
        }
    }
}
