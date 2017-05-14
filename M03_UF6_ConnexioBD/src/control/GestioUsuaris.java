package control;

import java.sql.Connection;
import java.util.Scanner;
import persistencia.PersistUsuari;
import vista.*;
import model.*;
import persistencia.ConfigConnexio;

public class GestioUsuaris {

    ConfigConnexio cc;
    Connection con;
    PersistUsuari persist;
    Sortida sort = new Sortida();
    Scanner lector = new Scanner(System.in);

    public GestioUsuaris() {
        cc = new ConfigConnexio();
        con = cc.getCon();
        persist = new PersistUsuari(con);
    }

    public void iniciarPrograma() {
        while (true) {
            menuSwich(sort.menuPrincipal());
        }
    }

    public void menuSwich(int n) {
        switch (n) {
            case 1:
                persist.afegirUsuari(sort.pedirUsuario());
                break;
            case 2:
                persist.afegirArray(sort.pedirListaUsuarios());
                break;
            case 3:
                persist.esborrar(sort.pedirNifUsuario());
                break;
            case 4:
                persist.modificar(sort.pedirUsuarioModificar());
                break;    
            case 5:
                persist.cercar(sort.pedirNifUsuario());
                break;    
            case 6:
                persist.cercaTots();
                break;   
            case 7:
                System.exit(0);
                break;
        }

    }

    public int pedirOpcion() {
        int num;
        while (true) {
            if (!lector.hasNextInt()) {
                System.out.println("El valor introducido no es un entero");
                lector.next();
            } else {
                num = Integer.parseInt(lector.nextLine());
                if (num < 8 && num > 0) {
                    break;
                } else {
                    System.out.println("No es un valor entre 0 y 7");
                    lector.nextLine();
                }
            }
        }
        return num;
    }
}
