package flujosdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Persistencia {
	private File archivo;

	public Persistencia() {
		archivo = new File("partidas.dat");
	}

	public void guardar(Partida partida) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo, true));
		dos.writeInt(partida.getIdJudador());
		dos.writeUTF(partida.getNomJugador());
		dos.writeLong(partida.getPuntos());
		dos.writeDouble(partida.getTiempo());
		dos.close();
	}

	public Partida leer(int idJugador) throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		Partida partida = null;
		boolean encontrado = false;
		while (fis.available() > 0 && !encontrado) {
			partida = leerRegistro(dis);
			if (partida.getIdJudador() == idJugador)
				encontrado = true;
		}
		fis.close();
		dis.close();
		return partida;
	}

	// Ejercicio1
	public List<Partida> leerTodos(int idJugador) throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		ArrayList<Partida> partidas = new ArrayList<>();

		while (fis.available() > 0) {
			Partida partida = leerRegistro(dis);
			if (partida.getIdJudador() == idJugador) {
				partidas.add(partida);
			}
		}

		fis.close();
		dis.close();

		return partidas;
	}

	// Ejercicio2
	public Partida leerMejorPuntuacion() throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		ArrayList<Partida> partidas = new ArrayList<>();

		while (fis.available() > 0) {
			Partida partida = leerRegistro(dis);
			partidas.add(partida);
		}

		fis.close();
		dis.close();

		Partida mejorPartida = new Partida();

		for (Partida partida : partidas) {
			if (partida.getPuntos() > mejorPartida.getPuntos()) {
				mejorPartida = partida;
			}
		}

		return mejorPartida;
	}

	// Ejercicio3
	public Partida leerMejorPuntuacion(int idJugador) throws IOException {
		ArrayList<Partida> partidas = (ArrayList<Partida>) leerTodos(idJugador);
		Partida mejorPartida = new Partida();

		for (Partida partida : partidas) {
			if (partida.getPuntos() > mejorPartida.getPuntos()) {
				mejorPartida = partida;
			}
		}

		if (mejorPartida.getNomJugador() == null){
			return null;
		}else {
			return mejorPartida;
		}
	}

	private Partida leerRegistro(DataInputStream dis) throws IOException {
		Partida partida = new Partida();
		partida.setIdJudador(dis.readInt());
		partida.setNomJugador(dis.readUTF());
		partida.setPuntos(dis.readLong());
		partida.setTiempo(dis.readDouble());
		return partida;
	}

}