package com.dad.amigoanimal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProcesadorSocket implements Runnable {
	Socket socket;
	
	public ProcesadorSocket (Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			OutputStream os = this.socket.getOutputStream();
			//Producto producto= new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30);
			ObjectOutputStream oos= new ObjectOutputStream (os);
			oos.writeObject(new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30));
			System.out.println("Realizando envio");
			oos.flush();
			os.close();
			socket.close();
		}catch (IOException e) {
			System.out.println("Fallo en la conexion"+e);
		}
	}
}
