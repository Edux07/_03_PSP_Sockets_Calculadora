package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {

	public static final int PUERTO = 2068;
	public static final String IP_SERVER = "Localhost";

	public static void main(String[] args) throws SocketException{
		System.out.println("        CALCULADORA         ");
		System.out.println("----------------------------");

		InetSocketAddress direccionServercalcu = new InetSocketAddress(IP_SERVER, PUERTO);

		try (Socket SocketAlServer = new Socket(); Scanner sc = new Scanner(System.in)) {
			SocketAlServer.connect(direccionServercalcu);

			while (true) {
				
				System.out.println("Bienvenido a la calculadora de Sockets:");
				System.out.println("Operaciones disponibles:");
				System.out.println("1. Sumar");
				System.out.println("2. Restar");
				System.out.println("3. Multiplicar");
				System.out.println("4. Dividir");
				System.out.println("5. Salir");
				System.out.print("Seleccione una opcion: ");

				String opcion = sc.nextLine();
				
				PrintStream salida = new PrintStream(SocketAlServer.getOutputStream());
				if (opcion.equals("5")) {
					System.out.println("La aplicacion se cierra");
					salida.println("5");
					System.exit(0);
					

				} else if (opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")) {
					System.out.print("Ingrese el primer número: ");
					double numero1 = Double.parseDouble(sc.nextLine());
					System.out.print("Ingrese el segundo número: ");
					double numero2 = Double.parseDouble(sc.nextLine());

					System.out.println("CLIENTE: Esperando a que el servidor acepte la conexion");
					System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);

					
					salida.println(opcion);
					salida.println(Double.toString(numero1));
					salida.println(Double.toString(numero2));

				}
				InputStreamReader entrada = new InputStreamReader(SocketAlServer.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);

				System.out.println("CLIENTE: Esperando al resultado del servidor...");
				String resultado = bf.readLine();

				System.out.println("CLIENTE: El resultado de la operacion es:" + resultado);
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
