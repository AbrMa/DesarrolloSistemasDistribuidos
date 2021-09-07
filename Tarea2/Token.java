import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Token {
	static DataInputStream entrada;
	static DataOutputStream salida;
	static boolean inicio = true;
	static String ip;
	static int nodo;
	static long token;

	static class Worker extends Thread {
		public void run () {
			//algoritmo 1
			try {
				final int PUERTO = 20000;
				ServerSocket servidor;
				servidor = new ServerSocket(PUERTO + nodo);

				Socket conexion = servidor.accept();
				DataInputStream entrada = new DataInputStream(conexion.getInputStream());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
	}

	public static void main (String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Se debe pasar como parametros el número del nodo y la IP del siguiente nodo en el anillo");
			System.exit(1);
		}
		nodo = Integer.valueOf(args[0]);
		ip = args[1];
		//algoritmo 2
	}
}
