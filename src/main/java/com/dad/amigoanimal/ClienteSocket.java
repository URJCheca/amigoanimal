package com.dad.amigoanimal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ClienteSocket {
	public static void enviarSocket () {
		int port= 9999;
	try {
		Socket serverSocket = new Socket("127.0.0.1",port) ;
		//Socket socket = serverSocket.accept();
		OutputStream os = serverSocket.getOutputStream();
		Producto producto= new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30);
		ObjectOutputStream oos= new ObjectOutputStream (os);
		oos.writeObject(new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30));

		oos.flush();
		oos.close();
		
		
	}
	catch(IOException e){
		e.printStackTrace();
		
	}
	}
}
