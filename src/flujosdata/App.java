package flujosdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class App {

	public static void main(String[] args) throws IOException {
//		File f = new File("prueba.datos");
//		DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
//		dos.writeInt(100);
//		dos.writeBoolean(true);
//		dos.writeDouble(32.2332);
//		dos.writeUTF("HOLA MUNDO");
//		dos.writeInt(200);
//		dos.close();
//		DataInputStream dis = new DataInputStream(new FileInputStream(f));
//		System.out.println(dis.readDouble());
//		System.out.println(dis.readInt());
//		System.out.println(dis.readDouble());
//		System.out.println(dis.readUTF());
//		System.out.println(dis.readInt());
//		dis.close();
		
		// --------------------------------

		Persistencia persistenciaJuego = null;
		persistenciaJuego = new Persistencia();
		Partida p1 = new Partida(10, "Sergio", 1030, 2132.20);
		Partida p2 = new Partida(11, "Lluis", 1005, 2032.53);
		Partida p3 = new Partida(11, "Lluis", 1090, 2092.10);
		Partida p4 = new Partida(10, "Sergio", 1022, 2135.34);
		Partida p5 = new Partida(10, "Sergio", 1000, 2400.24);
		if (persistenciaJuego != null) {
			try {
				persistenciaJuego.guardar(p1);
				persistenciaJuego.guardar(p2);
				persistenciaJuego.guardar(p3);
				persistenciaJuego.guardar(p4);
				persistenciaJuego.guardar(p5);
				System.out.println("Guardado con éxito");
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}

			try {
				System.out.println("* Leer primer registro del jugador 10");
				Partida p = persistenciaJuego.leer(10);
				if (p == null)
					System.out.println("No encontrado");
				else
					System.out.println(p);

				System.out.println("* Lectura de todos los registros");
				List<Partida> partidas = persistenciaJuego.leerTodos(10);
				if (partidas.size() == 0)
					System.out.println("No encontrado");
				else {
					for (Partida partida : partidas) {
						System.out.println(partida);
					}
				}

				System.out.println("* Lectura de la mejor puntuación");
				p = persistenciaJuego.leerMejorPuntuacion();
				if (p == null)
					System.out.println("No encontrado");
				else
					System.out.println(p);

				System.out.println("* Lectura de la mejor puntuación del jugador 10");
				p = persistenciaJuego.leerMejorPuntuacion(10);
				if (p == null)
					System.out.println("No encontrado");
				else
					System.out.println(p);

			} catch (IOException e) {
				System.err.println(e.getMessage());
			}

		}

	}

}
