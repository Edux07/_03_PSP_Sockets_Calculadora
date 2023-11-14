package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {
	public static final int PUERTO = 2068;

	public static void main(String[] args) throws InterruptedException {

		System.out.println("      SERVIDOR      ");
		System.out.println("--------------------");

		InputStreamReader entrada = null;
		PrintStream salida = null;
		Socket socketAlCliente = null;

		InetSocketAddress direccion = new InetSocketAddress(PUERTO);

		try (ServerSocket serverSocket = new ServerSocket()) {
			serverSocket.bind(direccion);
			int peticion = 0;
			socketAlCliente = serverSocket.accept();

			while (true) {

				if (socketAlCliente.isClosed()) {
					socketAlCliente = serverSocket.accept();

				} else {

					System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);

					System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");

					entrada = new InputStreamReader(socketAlCliente.getInputStream());
					BufferedReader bf = new BufferedReader(entrada);

					String opcion = bf.readLine();
					System.out.println("SERVIDOR: Me ha llegado del cliente: " + "La opcion" + opcion);

					if (opcion.equals("5")) {
						System.out.println("La aplicacion se cierra");
						socketAlCliente.close();

					} else if (opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")) {
						double numero1 = Double.parseDouble(bf.readLine());
						double numero2 = Double.parseDouble(bf.readLine());

						double resultado = 0.0;

						if (opcion.equals("1")) {
							resultado = numero1 + numero2;
						} else if (opcion.equals("2")) {
							resultado = numero1 - numero2;
						} else if (opcion.equals("3")) {
							resultado = numero1 * numero2;
						} else if (opcion.equals("4")) {
							resultado = numero1 / numero2;
						}
						System.out.println("SERVIDOR: El calculo de los numeros es: " + resultado);
						salida = new PrintStream(socketAlCliente.getOutputStream());
						salida.println(resultado);

					}

				}

			}

		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error -> " + e);
			e.printStackTrace();
		}

	}
}
