package m09_uf3_activitat4;

import m09_uf3_activitat4.*;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TimeServer {

    private static final String[] nomDies = {"Diumenge", "Dilluns",
        "Dimarts",
        "Dimecres", "Dijous",
        "Divendres",
        "Dissabte"};

    private static SSLServerSocketFactory ssl;
    private static SSLServerSocket sslserversocket;
    private static SSLSocket sserver;
    static int PORT = 8745;

    private static final String PROPIETAT1 = "javax.net.ssl.keyStore";
    private static final String v_PROPIETAT1 = "C:\\Users\\ALUMNEDAM\\Desktop\\M09_UF3_Activitat4\\src\\SSL\\servidorKey.jks";

    private static final String PROPIETAT2 = "javax.net.ssl.keyStorePassword";
    private static final String v_PROPIETAT2 = "123456";

    /**
     * Establecemos las propiedades del sistema y hacemos lo mismo que en el
     * cliente. Ademas creamos el socket del servidor con el puerto. Declaramos 
     * el lector para introducir en un array lo que recibimos. Una vez hecho eso
     * imprime el dia de la semana en elque cayó ese dia.
     *
     * @param argv
     * @throws Exception
     */

    public static void main(String[] argv) throws Exception {
        try {

            System.setProperty(PROPIETAT1, v_PROPIETAT1);
            System.setProperty(PROPIETAT2, v_PROPIETAT2);

            ssl = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sslserversocket = (SSLServerSocket) ssl.createServerSocket(PORT);

            while (true) {
                sserver = (SSLSocket) sslserversocket.accept();
                Scanner in = new Scanner(sserver.getInputStream());
                int[] data = new int[3];
                boolean ok = true;
                for (int i = 0; i < data.length; i++) {
                    if (in.hasNextInt()) {
                        data[i] = in.nextInt();
                    } else {
                        ok = false;
                    }
                }
                PrintStream out = new PrintStream(sserver.getOutputStream());
                if (ok) {
                    data[1] -= 1;
                    GregorianCalendar cal = new GregorianCalendar(data[2],
                            data[1], data[0]);
                    int dia = cal.get(Calendar.DAY_OF_WEEK) - 1;
                    System.out.println("Aquest dia era " + nomDies[dia] + ".");

                } else {
                    System.out.println("Format de les dades incorrecte.");
                }
                sserver.close();
            }
        } catch (Exception ex) {
            System.out.println("Error en les comuncacions: " + ex);
        }
    }

}
