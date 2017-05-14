package vista;

import model.*;
import control.*;
import java.util.List;
import java.util.Scanner;

public class Sortida {
    
    GestioUsuaris gu = new GestioUsuaris();
    Scanner lector = new Scanner(System.in);

    public void eliminar(String nif) {
        System.out.println("El usuario con nif "+nif+" se ha eliminado correctamente");
    }

    public void modificado(String nif) {
        System.out.println("El usuario con nif "+nif+" se ha modificado correctamente");
    }

    public void anadido(Usuari u) {
        System.out.println("El usuario "+u.getNom()+", "+u.getCognom()+", con el nif "
        + u.getNif()+"se ha añadido correctamente");
    }

    public void errorTry() {
        System.out.println("Lo sentimos pero el bloque try ha fallado");
    }

    public void anadidoLista() {
        System.out.println("La lista se ha añadido con exito");
    }

    public void encontradosTodos() {
        System.out.println("Encontrados todos los usuarios");
    }
    
    public int menuPrincipal(){               
        System.out.println("\nQue deseas hacer: "
                + "\n1- Añadir usuario"
                + "\n2- Añadir una lista de usuarios"
                + "\n3- Borrar un usuario"
                + "\n4- Modificar un usuario"
                + "\n5- Buscar un usuario por nif"
                + "\n6- Devolver todos los usuarios"
                + "\n7- Salir");        
        return gu.pedirOpcion();
    }
    
    public Usuari pedirUsuario(){
        String nif, nom, cogn;
        System.out.println("Introduce el nif del nuevo usuario");
        nif = lector.nextLine();
        System.out.println("Introduce el nombre del nuevo usuario");
        nom = lector.nextLine();
        System.out.println("Introduce el apellido del nuevo usuario");
        cogn = lector.nextLine();
        
        return new Usuari(nif, nom, cogn);
    }

    public List<Usuari> pedirListaUsuarios() {
        int veces;
        List<Usuari> listaUsuarios = null;
        System.out.println("Introduce el numero de usuarios a introducir");
        veces = lector.nextInt();
        
        for (int i = 0; i < veces; i++) {
            listaUsuarios.add(pedirUsuario());
        }        
        return listaUsuarios;
    }

    public String pedirNifUsuario() {
        String nif;
        System.out.println("Introduce el nif del usuario");
        return nif = lector.nextLine();
    }

    public Usuari pedirUsuarioModificar() {
        String nif = pedirNifUsuario(), nom, cogn;
        
        System.out.println("Introduce el nuevo nombre del usuario");
        nom = lector.nextLine();
        System.out.println("Introduce el nuevo apellido del usuario");
        cogn = lector.nextLine();
        
        return new Usuari(nif, nom, cogn);
    }
    
}
