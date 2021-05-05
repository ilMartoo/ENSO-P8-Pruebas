package SubsGestionOOTT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;

class TestGestorDeOOTT {

	private GestorDeOOTT gestorOT;
	
	@BeforeAll
	static void setUpAll() throws Exception {
		

		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(new Incidencia("0", "93218185J", "José Taboada", "+34999999999",
				"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
				"mobiliario público"));

		proceso = new Proceso("Arreglo banco", null, 0.01f, 1, "pendiente", "Ana García", "arreglo", incidencias,
				new ArrayList<>());
	}

	@AfterAll
	static void tearDownAll() throws Exception {
	}

	@Nested
	@DisplayName("Prueba de caja negra 3 - Creacion de ordenes de trabajo")
	class PN3 {
		
		private String descripcion;
		private List<String> materiales;
		private List<Double> presupuestos;
		private Float coste;
		private String responsable;
		private List<String> personal;
		private String fechaInicio;
		private Integer duracion;
		private String estado;
		private Proceso proceso;
		
		@BeforeEach
		void setUpEach() throws Exception {
			gestorOT = new GestorDeOOTT();
			
			materiales = new ArrayList<>();
			presupuestos = new ArrayList<>();
			personal = new ArrayList<>();
		}
		
		@AfterEach
		void tearDownEach() throws Exception {
		}
		
		@Test
		@DisplayName("Test para probar la creacion de ordenes de trabajo")
		void testPB3_N() {

			

		}
	}
}
