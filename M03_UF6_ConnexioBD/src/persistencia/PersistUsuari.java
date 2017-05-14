package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuari;
import vista.*;

public class PersistUsuari {

    private Connection con;
    Sortida sortida = new Sortida();

    public PersistUsuari(Connection con) {
        this.con = con;
    }

    public boolean afegirUsuari(Usuari u) {
        boolean afegit;
        String consulta = "Insert into Usuari(nif,nom, cognom) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, u.getNif());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getCognom());
            afegit = ps.executeUpdate() > 0;
            sortida.anadido(u);
            ps.close();
        } catch (SQLException ex) {
            sortida.errorTry();
            afegit = false;
        }

        return afegit;
    }

    public boolean afegirArray(List<Usuari> listaUsu) {
        boolean anadirExito = false;
        try {
            con.setAutoCommit(false);
            String sentencia = "Insert into Usuari (nif,nom,cognoms) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sentencia);
            for (Usuari u : listaUsu) {
                ps.setString(1, String.valueOf(u.getNif()));
                ps.setString(2, u.getNom());
                ps.setString(3, u.getCognom());
            }
            sortida.anadidoLista();
            ps.close();
            con.commit();
            anadirExito = true;
        } catch (SQLException ex) {
            sortida.errorTry();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                sortida.errorTry();
            }
        }
        return anadirExito;
    }

    public boolean esborrar(String nif) {
        boolean esborrado = false;
        String consulta = "Delete from Usuari Where nif = ?";
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, nif);
            ps.executeUpdate();
            sortida.eliminar(nif);
            esborrado = true;
        } catch (SQLException ex) {
            sortida.errorTry();
        }
        return esborrado;
    }

    public boolean modificar(Usuari u) {
        String consulta = "UPDATE Usuari SET nom = ?, cognom = ? WHERE nif = ? ";
        boolean modificado = false;
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getCognom());
            ps.setString(3, u.getNif());
            modificado = ps.executeUpdate() > 0;
            sortida.modificado(u.getNif());
        } catch (SQLException ex) {
            sortida.errorTry();
        }
        return modificado;
    }

    public Usuari cercar(String nif) {
        String consulta = "Select * From Usuari WHERE nif = ?";
        String nom = null, cogn = null;
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, nif);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nom = rs.getString("nom");
                cogn = rs.getString("cognom");
            }
        } catch (SQLException ex) {
            sortida.errorTry();
        }
        return new Usuari(nif, nom, cogn);

    }

    public List<Usuari> cercaTots() {
        String consulta = "From Usuari";
        List<Usuari> llistaUsu = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                llistaUsu.add(new Usuari(rs.getString("nif"), rs.getString("nom"), rs.getString("cognom")));
            }
            sortida.encontradosTodos();
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            sortida.errorTry();
        }
        return llistaUsu;
    }
}
