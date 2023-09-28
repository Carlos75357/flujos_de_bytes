package flujobytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {

	public static void main(String[] args) {

		File f = new File("datos.dat");
//		escritura(f);
//		lectura(f);
		
		// Ejercicio1. Realiza pruebas de lectura de ficherogrande.txt con diferentes tamaños de bloque y compara tiempos
		// ¿Alguna conclusión?
		long tiempoInicio = System.currentTimeMillis();
		File fg = new File("ficherogrande.txt");
		int tamBloque = 1000;	
		System.out.println("tambloque: "+ tamBloque);
		byte[] lecturaBloque = new byte[tamBloque];
		try (FileInputStream fis = new FileInputStream(fg)) {
			while (fis.available()>0) {
				lecturaBloque = new byte[tamBloque];
				int leidos = fis.read(lecturaBloque);
//				System.out.print("Leidos"+leidos+" ->   ");
				//System.out.println(new String(lecturaBloque));
			}
		} catch (FileNotFoundException ex) {
			System.err.println("No ha sido posible encontrar el fichero: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Error durante el tratamiento del fichero: " + ex.getMessage());
		}
		long tiempoFin = System.currentTimeMillis();
		System.out.println("Tarda " + (tiempoFin - tiempoInicio)+" ms");
		
		// Ejercicio2. realiza un método que copie realice la copia de un fichero
		// duplicaFichero(fOrigen, fDestino);

	}	

	private static void escritura(File f) {		
		try (FileOutputStream fos = new FileOutputStream(f, true)) {
			for (int i = 65; i < 91; i++) {
				fos.write(i);
			}
		} catch (FileNotFoundException ex) {
			System.err.println("No ha sido posible encontrar el fichero: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Error durante el tratamiento del fichero: " + ex.getMessage());
		}
		
		// -------------------------------------------------------------------------------------
		
		String cadena = "\nACCESO A DATOS - DAM - BATOI";
		try (FileOutputStream fos = new FileOutputStream(f, true)) {			
				fos.write(cadena.getBytes());			
		} catch (FileNotFoundException ex) {
			System.err.println("No ha sido posible encontrar el fichero: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Error durante el tratamiento del fichero: " + ex.getMessage());
		}
		System.out.println("Fin escritura fichero.");
	}
	
	private static void lectura(File f) {
		// Lectura byte a byte
		System.out.println("Lectura byte a byte");
		try (FileInputStream fis = new FileInputStream(f)) {
			int r;
			while (fis.available()>0) {
				r = fis.read();
				System.out.print((char) r);
			}
			System.out.println();
		} catch (FileNotFoundException ex) {
			System.err.println("No ha sido posible encontrar el fichero: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Error durante el tratamiento del fichero: " + ex.getMessage());
		}
		
		// Lectura de golpe
		System.out.println("Lectura de golpe");
		try (FileInputStream fis = new FileInputStream(f)) {
//			byte[] lectura = fis.readAllBytes();
			byte[] lectura = new byte[(int)f.length()];
			fis.read(lectura);
			String cadenaLectura = new String(lectura);
			System.out.println(cadenaLectura);
		} catch (FileNotFoundException ex) {
			System.err.println("No ha sido posible encontrar el fichero: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Error durante el tratamiento del fichero: " + ex.getMessage());
		}
		
		// Lectura en bloque
		// Prueba diferentes tamaño de bloque. ¿Ves algún problema en este código?
		System.out.println("Lectura en bloque");
		int tamBloque = 6;		
		byte[] lecturaBloque = new byte[tamBloque];
		try (FileInputStream fis = new FileInputStream(f)) {
			while (fis.available()>0) {
				lecturaBloque = new byte[tamBloque];
				int leidos = fis.read(lecturaBloque);
//				System.out.print("Leidos"+leidos+" ->   ");
				System.out.println(new String(lecturaBloque));
			}
		} catch (FileNotFoundException ex) {
			System.err.println("No ha sido posible encontrar el fichero: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Error durante el tratamiento del fichero: " + ex.getMessage());
		}
		
	}

}
