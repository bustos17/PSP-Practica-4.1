  
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David Ezequiel Bustos Álvarez
 */
public class ServidorG2 {

    Socket skCliente;
    static final int PUERTO = 15000;

    public static void main (String args []) {
        ServerSocket skServidor = null;
        HiloServidor hServidor;
        HiloEscribir hEscribir;

        try {
            // Inicio el servidor en el puerto
            skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto" + PUERTO);
            
            //Se conecta un cliente
            Socket skCliente=skServidor.accept();
            System.out.println("Cliente conectado"+skCliente.getInetAddress()+" : "+skCliente.getPort());
            
            //crear el hilo que lee del teclado y escribe al cliente y lo inicia
            hServidor = new HiloServidor(skCliente);
            hServidor.start();
            
            
            //crear el hilo que lee del teclado y escribe al cliente y lo inicia
            hEscribir = new HiloEscribir(skCliente);
            hEscribir.start();
            
            //De forma implícita
            //new HiloEscribir(skCliente).start();
            while(hEscribir.isAlive()) {
            	
            	
            	
            }
            
            hServidor.interrupt();
            

        } catch (IOException e) {
            System.out.println("Error E/S en el servidor: " + e.getMessage());
        } finally {
            // Intentar cerrar el socket del servidor por si ocurre cualquier error
            try {
                if (skServidor != null) {
                    skServidor.close();
                }
            } catch (IOException ex) {
                System.out.println("Error E/S al finalizar el servidor: "+ ex.getMessage());
            }
        }
    }

}