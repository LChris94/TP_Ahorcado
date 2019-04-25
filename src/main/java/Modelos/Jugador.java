package Modelos;

import java.sql.SQLException;
import java.util.List;

public class Jugador extends Thread{
    private String nombre;
    private int vidas;
    private boolean puede_jugar;
    private Ahorcado ahorcado;

    public Jugador(String nombre, boolean puede_jugar, Ahorcado ahorcado) {
        this.setNombre(nombre);
        this.setPuede_jugar(puede_jugar);
        this.ahorcado = ahorcado;
        this.setVidas(ahorcado.getCantidad_vidas());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas += vidas;
    }

    public boolean isPuede_jugar() {
        return puede_jugar;
    }

    public void setPuede_jugar(boolean puede_jugar) {
        this.puede_jugar = puede_jugar;
    }

    @Override
    public void run() {
        while (puede_jugar && this.getVidas()>0 && ahorcado.getLetras_restantes()>0)
        {
            try {
                this.ahorcado.Turno(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
