package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {

	public static final int PUERTO = 2068;
	public static final String IP_SERVER = "Localhost";

	public static void main(String[] args) {
		System.out.println("        CALCULADORA         ");
		System.out.println("----------------------------");

		InetSocketAddress direccionServercalcu = new InetSocketAddress(IP_SERVER, PUERTO);


        try (Socket SocketAlServer = new Socket();
             BufferedReader entrada = new BufferedReader(new InputStreamReader(SocketAlServer.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in)) ) {

            while (true) {
                System.out.println("Operaciones disponibles:");
                System.out.println("1. Sumar");
                System.out.println("2. Restar");
                System.out.println("3. Multiplicar");
                System.out.println("4. Dividir");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                String opcion = teclado.readLine();
                
                
                
                
            }
            

		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
	}
}
