import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class PI {
	static Object obj = new Object();
	static float pi = 0;

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
		}

		else {
			//algoritmo 3
		}
	}
}
