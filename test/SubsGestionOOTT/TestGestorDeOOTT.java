package SubsGestionOOTT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;

class TestGestorDeOOTT {

	private ArrayList<String> materiales;
	private ArrayList<Double> presupuestos;
	private ArrayList<String> personal;
	private Proceso proceso;

	private GestorDeOOTT got;

	@BeforeAll
	void setUpAll() throws Exception {
		materiales = new ArrayList<>();
		presupuestos = new ArrayList<>();
		personal = new ArrayList<>();

		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(new Incidencia("0", "93218185J", "José Taboada", "+34999999999",
				"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
				"mobiliario público"));

		proceso = new Proceso("Arreglo banco", null, 0.01f, 1, "pendiente", "Ana García", "arreglo", incidencias,
				new ArrayList<>());
	}

	@BeforeEach
	void setUpEach() throws Exception {
		materiales.clear();
		presupuestos.clear();
		personal.clear();

		got = new GestorDeOOTT();
	}

	@AfterEach
	void tearDownEach() throws Exception {
	}

	@AfterAll
	void tearDownAll() throws Exception {
	}

	@ParameterizedTest
	@DisplayName("Test para probar la creacion de ordenes de trabajo")
	void testCrearOT(@ConvertWith(NullableConverter.class) String descripcion,
			@ConvertWith(NullableConverter.class) String material,
			@ConvertWith(NullableConverter.class) Double presupuesto, @ConvertWith(NullableConverter.class) Float coste,
			@ConvertWith(NullableConverter.class) String responsable,
			@ConvertWith(NullableConverter.class) String trabajador,
			@ConvertWith(NullableConverter.class) String fechaInicio,
			@ConvertWith(NullableConverter.class) Integer duracion, @ConvertWith(NullableConverter.class) String estado,
			boolean procesoNulo, boolean nulo) {

		materiales.add(material);
		presupuestos.add(presupuesto);
		personal.add(trabajador);

		OT orden = got.crearOT(descripcion, materiales, presupuestos, coste, responsable, personal, fechaInicio,
				duracion, estado, procesoNulo ? null : proceso);

		if (nulo) {

			// Assert de un nulo
			assertNull(orden, "Se ha creado una orden de trabajo invalida");

		} else {

			OT ordenResult = new OT("0", descripcion, materiales, presupuestos, coste, responsable, personal,
					fechaInicio, duracion, estado, proceso);

			// Assert de una orden de trabajo
			assertEquals(ordenResult, orden, "La orden de trabajo no se ha creado correctamente");
		}

	}

}
