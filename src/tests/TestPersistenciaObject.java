package tests;

import flujosdata.Partida;
import flujosdata.Persistencia;
import flujosdata.PersistenciaObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestPersistenciaObject {
	static PersistenciaObject persistenciaObjectTest;
	static Partida p1, p2, p3, p4, p5;

	@BeforeAll
	static void setUp() {
		File archivo = new File("partidasObj.dat");
		if (archivo.exists()) {
			archivo.delete();
		}
		p1 = new Partida(10, "Sergio", 1030, 2132.20);
		p2 = new Partida(11, "Lluis", 1005, 2032.53);
		p3 = new Partida(11, "Lluis", 1090, 2092.10);
		p4 = new Partida(10, "Sergio", 1022, 2135.34);
		p5 = new Partida(10, "Sergio", 1000, 2400.24);
		try {
			persistenciaObjectTest = new PersistenciaObject();
			persistenciaObjectTest.guardar(p1);
			persistenciaObjectTest.guardar(p2);
			persistenciaObjectTest.guardar(p3);
			persistenciaObjectTest.guardar(p4);
			persistenciaObjectTest.guardar(p5);
		} catch (IOException e) {
			fail("El test falla al preparar el fichero");
		} catch (ClassNotFoundException e) {
			fail("El test, no encuentra la clase");
		}
	}

	@Test
	@Order(1)
	void testLeerMejorPuntacionPorJugador() {
		Partida mejorPartidaJug10 = p1;
		Partida mejorPartidaJug11 = p3;
		try {
			assertEquals(mejorPartidaJug10, persistenciaObjectTest.leerMejorPuntuacion(10));
			assertEquals(mejorPartidaJug11, persistenciaObjectTest.leerMejorPuntuacion(11));
			assertNull(persistenciaObjectTest.leerMejorPuntuacion(12));
		} catch (IOException e) {
			fail("El test falla al ler fichero");
		} catch (ClassNotFoundException e) {
			fail("El test, no encuentra la clase");
        }
    }

	@Test
	@Order(2)
	void testLeerMejorPuntuacion() {
		Partida mejorPartida = p3;
		try {
			assertEquals(mejorPartida, persistenciaObjectTest.leerMejorPuntuacion());
		} catch (IOException e) {
			fail("El test falla al leer fichero");
		} catch (ClassNotFoundException e) {
			fail("El test, no encuentra la clase");
		}
	}

	@Test
	@Order(3)
	void testLeerTodosPorJugador() {		
		Partida[] partidasJug10 = new Partida[3];
		Partida[] partidasJug11 = new Partida[2];
		partidasJug10[0] = p1;
		partidasJug10[1] = p4;
		partidasJug10[2] = p5;
		partidasJug11[0] = p2;
		partidasJug11[1] = p3;
		
		try {
			assertArrayEquals(partidasJug10, persistenciaObjectTest.leerTodos(10).toArray());
			assertArrayEquals(partidasJug11, persistenciaObjectTest.leerTodos(11).toArray());
			assertEquals(persistenciaObjectTest.leerTodos(12).size(), 0);
		} catch (IOException e) {
			fail("El test falla al leer fichero");
		} catch (ClassNotFoundException e) {
			fail("El test, no encuentra la clase");
		}

	}

}
