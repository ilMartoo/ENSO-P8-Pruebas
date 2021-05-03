package SubsAnalisisEstadisticos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SubsGestionIncidencias.GestorDeIncidencias;
import SubsGestionOOTT.GestorDeOOTT;
import SubsGestionProcesos.GestorDeProcesos;

class TestAnalisisEstadisticos {
	
	static GestorDeIncidencias incidencias;
	static GestorDeProcesos procesos;
	static GestorDeOOTT ordenesTrabajo;
	static AnalisisEstadisticos estadisticas;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		incidencias = new GestorDeIncidencias();
		procesos = new GestorDeProcesos();
		ordenesTrabajo = new GestorDeOOTT();
		estadisticas = new AnalisisEstadisticos(incidencias,procesos,ordenesTrabajo);
		
		incidencias.crearIncidencia("98765432C", "Ana García", "+01234567891", "Incidencia de ejemplo 1", "Vigo", "prueba");
		incidencias.crearIncidencia("98765432C", "Ana García", "+01234567891", "Incidencia de ejemplo 2", "Vigo", "prueba");
		incidencias.crearIncidencia("12345678N", "Pedro González", "+01234567891", "Incidencia de ejemplo 3", "Vigo", "prueba");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTotalIncidencias() {
		
		assertAll(
				()->{assertEquals(3,estadisticas.totalIncidencias(null, null),"Error en el recuento total de incidencias.");},
				()->{assertEquals(2,estadisticas.totalIncidencias("dni", "98765432C"),"Error en el recuento filtrado por dni de incidencias.");}
				);
	}

	
	@Test
	void testTotalProcesos() {
		
		// Arrange - preparación
		
		
		// Act - ejecución
		
		
		// Assert - afirmación
		
		
		
		fail("Not yet implemented");
	}

	@Test
	void testTotalOOTT() {
		fail("Not yet implemented");
	}

	@Test
	void testDistribucionIncidencias() {
		fail("Not yet implemented");
	}

	@Test
	void testDistribucionProcesos() {
		fail("Not yet implemented");
	}

	@Test
	void testDistribucionOOTT() {
		fail("Not yet implemented");
	}

}
