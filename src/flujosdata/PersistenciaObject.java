package flujosdata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaObject {
    private File archivo;

    public PersistenciaObject() {
        archivo = new File("partidasObj.dat");
    }

    public void guardar(Partida partida) throws IOException, ClassNotFoundException {
        ArrayList<Partida> partidas = new ArrayList<>();
        if (archivo.length() > 0){
            FileInputStream fs = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fs);

            while (fs.available() > 0) {
                partidas.add((leerRegistro(ois)));
            }
            ois.close();
            fs.close();
        }

        partidas.add(partida);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.archivo, false));

        for (Partida p: partidas) {
            oos.writeObject(p);
        }
        oos.close();
    }

    public Partida leer(int idJugador) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
        Partida partida = null;
        boolean encontrado = false;
        while (ois.available() > 0 && !encontrado) {
            partida = leerRegistro(ois);
            if (partida.getIdJudador() == idJugador)
                encontrado = true;
        }
        ois.close();
        return partida;
    }

    // Ejercicio1
    public List<Partida> leerTodos(int idJugador) throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(archivo);
        ObjectInputStream ois = new ObjectInputStream(fs);
        ArrayList<Partida> partidas = new ArrayList<>();

        while (fs.available() > 0) {
            Partida partida = leerRegistro(ois);
            if (partida.getIdJudador() == idJugador) {
                partidas.add(partida);
            }
        }

        ois.close();
        fs.close();

        return partidas;
    }

    // Ejercicio2
    public Partida leerMejorPuntuacion() throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(archivo);
        ObjectInputStream ois = new ObjectInputStream(fs);
        ArrayList<Partida> partidas = new ArrayList<>();

        while (fs.available() > 0) {
            Partida partida = leerRegistro(ois);
            partidas.add(partida);
        }

        ois.close();
        fs.close();

        Partida mejorPartida = new Partida();

        for (Partida partida : partidas) {
            if (partida.getPuntos() > mejorPartida.getPuntos()) {
                mejorPartida = partida;
            }
        }

        return mejorPartida;
    }

    // Ejercicio3
    public Partida leerMejorPuntuacion(int idJugador) throws IOException, ClassNotFoundException {
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

    private Partida leerRegistro(ObjectInputStream dis) throws IOException, ClassNotFoundException {
        return (Partida) dis.readObject();
    }
}
