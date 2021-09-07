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
	static final int PUERTO = 20000;

	static class Worker extends Thread {
		public void run () {
			//algoritmo 1
			try {
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
		Worker w = new Worker();
		w.start();
		
		Socket conexion = null;
		boolean conectado = false;
		int nuevoPuerto = PUERTO + (nodo + 1) % 4;

		while (!conectado) {
			try {
				conexion = new Socket(ip, nuevoPuerto); 
				conectado = true; // de manera similar al break, la siguiente iteración del ciclo no va a ser ejecutada
			}
			catch (Exception e){
				final int MS = 500;
				Thread.sleep(MS);
			}
		}

		DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
		w.join();

		while (true) {
			if (nodo == 0) {
				if (inicio == true) {
					inicio = false;
					token = 1;
				}
				else {
					token = entrada.readLong();
					token++;
					System.out.println("[Nodo] -> " + nodo);
					System.out.println("[Token] -> " + token);
				}
			}
			else {
				token = entrada.readLong();
				token++;
				System.out.println("[Nodo] -> " + nodo);
				System.out.println("[Token] -> " + token);
			}

			if (nodo == 0 && token >= 1000) {
				break;
			}

			salida.writeLong(token);
		}
	}
}
