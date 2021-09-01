import java.net.Socket;

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
