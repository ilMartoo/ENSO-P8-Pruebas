package SubsGestionProcesos;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;

public class TestGestorProcesos {
	Proceso proceso;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		proceso.setNombre("");
		proceso.setDescripcion("");
		proceso.setCoste(0.0f);
		proceso.setEstimado(0);
		proceso.setEstado("");
		proceso.setResponsable("");
		proceso.setServicio("");
		proceso.setIncidencias(new ArrayList<>());
		proceso.setOts(new ArrayList<>());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test para probar la creacion de procesos")
	void testCrearProceso() {

	}
}
