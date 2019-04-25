package DAO;

import Modelos.Jugador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AhorcadoDaoMysql {
    private final String TABLE_NAME = "palabras";
    private final String TABLE_NAME_GANADORES = "resultados_juego";
    private static final AhorcadoDaoMysql INSTANCE = new AhorcadoDaoMysql();

    private AhorcadoDaoMysql(){
        ConexionMySQL c = ConexionMySQL.getInstance();

    }

    public static AhorcadoDaoMysql getInstance(){
        return INSTANCE;
    }

    public String get_palabra(){
        String rta = "";
        ConexionMySQL c = ConexionMySQL.getInstance();

        try {
            Statement statement = c.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY RAND() LIMIT 1");

            while(resultSet.next()){
                rta = resultSet.getString("palabra");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rta;
    }

    public void crear_ganador(Jugador jugador, String palabra) throws SQLException{
        ConexionMySQL c = ConexionMySQL.getInstance();
        try {

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            PreparedStatement preparedStatement = c.getConnection().prepareStatement(
                    "INSERT INTO " + TABLE_NAME_GANADORES + " (nombre, fecha, palabra) VALUES (?,?,?)");
            preparedStatement.setString(1, jugador.getNombre());
            preparedStatement.setString(2, dateFormat.format(date));
            preparedStatement.setString(3,palabra);

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            throw e;
        }
    }

}
