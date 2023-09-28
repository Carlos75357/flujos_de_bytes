package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import flujosdata.Partida;
import flujosdata.Persistencia;

class TestPersistencia {
	static Persistencia persistenciaTest;
	static Partida p1, p2, p3, p4, p5;

	@BeforeAll
	static void setUp() {
		File archivo = new File("partidas.dat");
		if (archivo.exists()) {
			archivo.delete();
		}
		p1 = new Partida(10, "Sergio", 1030, 2132.20);
		p2 = new Partida(11, "Lluis", 1005, 2032.53);
		p3 = new Partida(11, "Lluis", 1090, 2092.10);
		p4 = new Partida(10, "Sergio", 1022, 2135.34);
		p5 = new Partida(10, "Sergio", 1000, 2400.24);
		try {
			persistenciaTest = new Persistencia();
			persistenciaTest.guardar(p1);
			persistenciaTest.guardar(p2);
			persistenciaTest.guardar(p3);
			persistenciaTest.guardar(p4);
			persistenciaTest.guardar(p5);
		} catch (IOException e) {
			fail("El test falla al preparar el fichero");
		}
	}

	@Test
	@Order(1)
	void testLeerMejorPuntacionPorJugador() {
		Partida mejorPartidaJug10 = p1;
		Partida mejorPartidaJug11 = p3;
		try {
			assertEquals(mejorPartidaJug10, persistenciaTest.leerMejorPuntuacion(10));
			assertEquals(mejorPartidaJug11, persistenciaTest.leerMejorPuntuacion(11));
			assertNull(persistenciaTest.leerMejorPuntuacion(12));
		} catch (IOException e) {
			fail("El test falla al ler fichero");
		}
	}

	@Test
	@Order(2)
	void testLeerMejorPuntuacion() {
		Partida mejorPartida = p3;
		try {
			assertEquals(mejorPartida, persistenciaTest.leerMejorPuntuacion());
		} catch (IOException e) {
			fail("El test falla al leer fichero");
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
			assertArrayEquals(partidasJug10, persistenciaTest.leerTodos(10).toArray());
			assertArrayEquals(partidasJug11, persistenciaTest.leerTodos(11).toArray());
			assertEquals(persistenciaTest.leerTodos(12).size(), 0);
		} catch (IOException e) {
			fail("El test falla al leer fichero");
		}

	}

}
