package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigConnexio {

    private Connection con = null;
    private String driver = "com.mysql.jdbc.Driver";//Classe Driver
    private String cadenaConnexio = "jdbc:mysql://localhost:3306/prova";
    private String usuari = "AITOR_ROMERO_CONNECTION";
    private String contrasenya = "aitor13aaa";

    public ConfigConnexio() {
    }

    public ConfigConnexio(String driver, String cadenaConnexio, String usuari, String contrasenya) {
        this.driver = driver;
        this.cadenaConnexio = cadenaConnexio;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    /**
     * Carrega la classe Driver de la llibreria jdbc per a mysql, obté una
     * instància de la classe Connection, amb la connexió oberta amb el SGBD a
     * la BD indicada a la cadena de connexió.
     *
     * @return torna true si s'estableix la connexió i false en cas contrari.
     */
    public boolean connectar() {
        boolean comprova = false;

        try {
            Class.forName(driver); //es carrega el la classe Driver
            con = DriverManager.getConnection(cadenaConnexio, usuari, contrasenya);
            comprova = true;
            System.out.println("Connexió establerta");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Problemes amb la connexió: \n" + e.getMessage());
        } finally {
            return comprova;
        }
    }

    /**
     * Comprova si l'objecte Connection s'ha creat i es troba oberta, en aquest
     * cas tanca la connexió.
     *
     * @return true si la connexió estava oberta i es tanca, false en cas de no
     * estar creat l'objecte Connection o que ja estava tancada.
     */
    public boolean tancarConnexio()  {
        boolean comprova = false;
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                comprova = true;
                System.out.println("Connexió tancada.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return comprova;
    }

    /**
     * Torna l'objecte Connection si s'ha creat abans amb el mètode connectar()
     *
     * @return la instància creada de Connection si ha esta creada i null en cas
     * contrari.
     */
    public Connection getCon() {
        return con;
    }
}
