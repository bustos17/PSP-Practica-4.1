

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author David Ezequiel Bustos Álvarez
 *  Hilo que permitira al servidor mandar mensajes
 */
public class HiloEscribir extends Thread {
    
    private Socket skCliente;

    HiloEscribir(Socket skCliente) {
        this.skCliente=skCliente;
    }
    
    @Override
    public void run(){
    	String cadMensaje;
    	boolean salir=false;
    	
    	try {
    		
    		//Se crea el stream de salida el scanner para leer del teclado
    		DataOutputStream flujo_salida=new DataOutputStream(skCliente.getOutputStream());
    		Scanner teclado=new Scanner(System.in);
    		
    		do {
    			//Lee del teclado
    			cadMensaje=teclado.nextLine();
    			
    			//Lo manda al cliente
    			flujo_salida.writeUTF(cadMensaje);
    			
    			//Si se lee el mensaje de cierre, la ejecución finaliza
    			if(cadMensaje.equalsIgnoreCase("exit")) {
    				salir=true;
    			}
    		}while(!salir);
    		
    		//skCliente.close();
    		
    	}catch(IOException ex) {
    		System.out.println("Error de E/S: "+ex.getMessage());
    		
    	}finally {
    		
    		//Se cierra la conexión
    		System.out.println("Cliente desconectado");
    	}
    }
}