package m09_uf3_activitat4;

import m09_uf3_activitat4.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

public class TimeClient {

    static String HOST = "localhost";
    static int PORT = 8745;
    
    DataOutputStream enviador;
    BufferedReader lectura;
    
    SSLSocketFactory ssl;
    SSLSocket sclient;

    private static final String PROPIETAT1 = "javax.net.ssl.trustStore";
    private static final String v_PROPIETAT1 = "C:\\Users\\ALUMNEDAM\\Desktop\\M09_UF3_Activitat4\\src\\SSL\\ClienteTrustStore.jks";

    private static final String PROPIETAT2 = "javax.net.ssl.trustStorePassword";
    private static final String v_PROPIETAT2 = "123456";

    /**
     * Recibimos el host y el puerto por parametro. A continuacion declaramos las
     * propiedades de System utilizando .setProperty. Seguidamente inicializammos
     * el socket por defecto y creamos sslsocketfactory como sslsocket. Se 
     * inicializa el lector y pedimos el dia, el mes y el año para poder 
     * buscar en el servidor.
     *
     * @param host
     * @param port
     * @throws IOException
     */
    public TimeClient(String host, int port) throws IOException {

        System.setProperty(PROPIETAT1, v_PROPIETAT1);
        System.setProperty(PROPIETAT2, v_PROPIETAT2);

        ssl = (SSLSocketFactory) SSLSocketFactory.getDefault();
        sclient = (SSLSocket) ssl.createSocket(HOST, PORT);
        
        enviador = new DataOutputStream(sclient.getOutputStream());
        
        lectura = new BufferedReader(new InputStreamReader(sclient.getInputStream()));
        
        String dia = JOptionPane.showInputDialog(null, "Número del dia: ", "Datos", 3);
        String mes = JOptionPane.showInputDialog(null, "Número de  mes: ", "Datos", 3);
        String any = JOptionPane.showInputDialog(null, "Any: ", "Datos", 3);
        
        enviarAServer(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(any));

    }

    /**
     * Metodo al que le llega el dia, mes y año y construye un string para 
     * enviarlo al servidor.
     *
     * @param dia
     * @param mes
     * @param any
     * @throws IOException
     */
    public void enviarAServer(int dia, int mes, int any) throws IOException {
        //Enviem el dia, mes i any, separat per espais en forma de string
        enviador.writeBytes(dia + " " + mes + " " + any);
        //Tanquem els recursos

    }

    public static void main(String[] args) throws IOException {
        new TimeClient(HOST, PORT);

    }

}

