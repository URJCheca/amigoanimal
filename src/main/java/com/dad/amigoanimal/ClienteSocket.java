package com.dad.amigoanimal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.lang.Thread;


public class ClienteSocket {
	public static void enviarSocket (Cliente usuario, HashMap<Producto, Integer> carrito) {
		int port= 9999;
		String confirmacion= "FAIL";
		try {
			Socket socket= new Socket("127.0.0.1",port);

			OutputStream os = socket.getOutputStream();
		
			ObjectOutputStream oos= new ObjectOutputStream (os);
			
			//oos.writeObject(new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30));
			oos.writeObject(usuario.getEmail());
			oos.writeObject(carrito);
			oos.flush();
			System.out.println("Realizando envio");
			os.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		
		}
	}
}
