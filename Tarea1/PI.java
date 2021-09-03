import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

class PI {
	static Object obj = new Object();
	static float pi = 0;

	static int PUERTO = 30000;
	static String SERVIDOR = "localhost";

	static class Worker extends Thread {
		Socket conexion;

		Worker (Socket conexion) {
			this.conexion = conexion;
		}

		public void run () {
			//algoritmo 1
			try {
				System.out.println("[Nueva conexión iniciada]");

				DataInputStream entrada = new DataInputStream(conexion.getInputStream());
				DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

				float suma = entrada.readFloat();

				synchronized (obj) {
					pi += suma;
				}

				entrada.close();
				salida.close();
				conexion.close();
				
				System.out.println("[Conexión finalizada]");
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public static void main (String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Uso:");
			System.err.println("java PI <nodo>");
			System.exit(0);
		}

		int nodo = Integer.valueOf(args[0]);

		if (nodo == 0) {
			//algoritmo 2
			System.out.println("[Servidor Iniciado]");
			ServerSocket servidor = new ServerSocket (PUERTO);
			System.out.println("[Servidor Escuchando]");

			Vector <Worker> v = new Vector <Worker> (4);

			for (int i = 0; i < 4; i++) {
				Socket conexion = servidor.accept();
				v.insertElementAt(new Worker(conexion), i);
				v.get(i).start();
			}

			for (int i = 0; i < 4; i++) {
				v.get(i).join();
			}

			System.out.println("[PI] - >" + pi);
			System.out.println("[Servidor RIP]");

		}

		else {
			//algoritmo 3
			Socket conexion = null;
			boolean conectado = false;

			while (!conectado) {
				try {
					conexion = new Socket(SERVIDOR, PUERTO);
					conectado = true;
				}
				catch (Exception e) {
					Thread.sleep(100);
				}
			}

			DataInputStream entrada = new DataInputStream(conexion.getInputStream());
			DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

			float suma = 0.0f;
			
			for (int i = 0; i < 1e6; i++) {
				suma += 4.0 / (8 * i + 2 * (nodo - 2 ) + 3);
			}

			suma = nodo % 2 == 0 ? -suma : suma;
			salida.writeFloat(suma);

			entrada.close();
			salida.close();
			conexion.close();

		}
	}
}
