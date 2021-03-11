package servidorg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Ezequiel Bustos Álvarez
 */
public class RunServidor implements Runnable{
    
    private Socket skCliente;
    
    RunServidor(Socket skCliente){
        this.skCliente=skCliente;
    }

    @Override
    public void run() {
    	String cadMensaje;
    	boolean salir=false;
    	try {
    		DataInputStream flujo_entrada=new DataInputStream(skCliente.getInputStream());
    		DataOutputStream flujo_salida=new DataOutputStream(skCliente.getOutputStream());
    		//Se notifica al cliente la conexión
    		flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");
    		do {
    			//Leer el texto y enviar
    			cadMensaje=flujo_entrada.readUTF();
    			// Si el cliente manda el mensaje de cierre, la ejecución finaliza
    			if(cadMensaje.equalsIgnoreCase("exit")) {
    				salir=true;	
    			}
    		}while(!salir);
    		
    	}catch(IOException ex) {
    		System.out.println("Error de E/S: "+ex.getMessage());
    		
    	}finally {
    		//Se cierra la conexión
    		System.out.println("Cliente desconectado");
    	}
    }
    
}