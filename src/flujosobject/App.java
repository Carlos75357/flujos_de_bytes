package flujosobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {
		Partida p1 = new Partida(10, "Sergio", 1030, 2132.20);
		Partida p2 = new Partida(11, "Lluis", 1005, 2032.53);
		Partida p3 = new Partida(11, "Lluis", 1090, 2092.10);
		Partida p4 = new Partida(10, "Sergio", 1022, 2135.34);
		Partida p5 = new Partida(10, "Sergio", 1000, 2400.24);

		File f = new File("partidas.oos");
		// Escritura objeto a objeto
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f, false))) {
			oos.writeObject(p1);
			oos.writeObject(p2);
			oos.writeObject(p3);
			oos.writeObject(p4);
			oos.writeObject(p5);
		} catch (IOException e) {
			System.err.println("Error guardando partidas..." + e);
		}

		// Lectura objeto a objeto
		Partida p;
		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
			while (fis.available()>0) {
				p = (Partida) ois.readObject();
				System.out.println(p);
			}
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Error leyendo partidas..." + e);
		}
		
		System.out.println("-----------------------------------");
		
		// Escritura / lectura todas las partidas a la vez. Podría guardarse directamente un array de objetos (partidas)
		File f1 = new File("partidasArray.oos");
		List<Partida> partidas = new ArrayList<Partida>();
		partidas.add(p1);
		partidas.add(p2);
		partidas.add(p3);
		partidas.add(p4);
		partidas.add(p5);
		
		// Escritura
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f1, false))) {
			oos.writeObject(partidas);
		} catch (IOException e) {
			System.err.println("Error guardando partidas..." + e);
		}
		
		// Lectura
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f1))) {
			List<Partida> partidasLeidas = (List<Partida>) ois.readObject();
			for(Partida partida: partidasLeidas) {
				System.out.println(partida);
			}
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Error guardando partidas..." + e);
		}

	}

}
