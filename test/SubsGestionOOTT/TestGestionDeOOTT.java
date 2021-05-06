package SubsGestionOOTT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;
import SubsGestionIncidencias.GestorDeIncidencias;
import SubsGestionProcesos.GestorDeProcesos;

class TestGestionDeOOTT {

	GestorDeOOTT gestorOT;
	static GestorDeProcesos gestorP;
	static GestorDeIncidencias gestorI;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		gestorP = Mockito.mock(GestorDeProcesos.class);
		gestorI = Mockito.mock(GestorDeIncidencias.class);

		Mockito.when(gestorI.crearIncidencia(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new Incidencia("0", "93218185J", "José Taboada", "+34999999999",
						"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
						"mobiliario público"));

		Mockito.when(gestorP.crearProceso(Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(),
				Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyList()))
				.thenReturn(new Proceso("Arreglo banco", null, 0.01f, 1, "pendiente", "Ana García", "arreglo",
						new ArrayList<>()));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}



	@Nested
	@DisplayName("Prueba de caja negra 3 - crearOT")
	class PN3 {
		
		String descripcion;
		ArrayList<String> materiales;
		ArrayList<Double> presupuestos;
		Float coste;
		String responsable;
		ArrayList<String> personal;
		String fechaInicio;
		Integer duracion;
		String estado;
		Proceso proceso;

		OT otResult;

		@BeforeEach
		void setUp() throws Exception {
			gestorOT = new GestorDeOOTT();

			materiales = new ArrayList<>();
			presupuestos = new ArrayList<>();
			personal = new ArrayList<>();

			proceso = gestorP.crearProceso(Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(),
					Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
			proceso.getIncidencias().add(gestorI.crearIncidencia(Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
		}
		
		@AfterEach
		void tearDown() throws Exception {
			gestorOT = null;

			materiales = null;
			presupuestos = null;
			personal = null;
			proceso = null;
			
			otResult = null;
		}

		@Test
		@DisplayName("Caso de prueba 1")
		void testPN3_CP1() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			OT ot = new OT("0", descripcion, materiales, presupuestos, coste, responsable, personal, fechaInicio,
					duracion, estado, proceso);
			
			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
									responsable, personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertEquals(ot, otResult, "La orden de trabajo creada no es válida"));
		}
		
		@Test
		@DisplayName("Caso de prueba 2")
		void testPN3_CP2() {
			descripcion = null;
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			OT ot = new OT("0", descripcion, materiales, presupuestos, coste, responsable, personal, fechaInicio,
					duracion, estado, proceso);
			
			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertEquals(ot, otResult, "La orden de trabajo creada no es válida"));
		}

		@Test
		@DisplayName("Caso de prueba 3")
		void testPN3_CP3() {
			descripcion = "";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 4")
		void testPN3_CP4() {
			descripcion = "Se realiza este trabajo";
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, null, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 5")
		void testPN3_CP5() {
			descripcion = "Se realiza este trabajo";
			materiales.add("");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 6")
		void testPN3_CP6() {
			descripcion = "Se realiza este trabajo";
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 7")
		void testPN3_CP7() {
			descripcion = "Se realiza este trabajo";
			materiales.add(null);
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 8")
		void testPN3_CP8() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(-0.01);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 9")
		void testPN3_CP9() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(null);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 10")
		void testPN3_CP10() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, null, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 11")
		void testPN3_CP11() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 12")
		void testPN3_CP12() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = null;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 13")
		void testPN3_CP13() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 14")
		void testPN3_CP14() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = null;
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 15")
		void testPN3_CP15() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 16")
		void testPN3_CP16() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 17")
		void testPN3_CP17() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add(null);
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 18")
		void testPN3_CP18() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									null, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 19")
		void testPN3_CP19() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = null;
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 20")
		void testPN3_CP20() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "31/02/30";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 21")
		void testPN3_CP21() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/2021";
			duracion = 1;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 22")
		void testPN3_CP22() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 0;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 23")
		void testPN3_CP23() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = null;
			estado = "pendiente";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 24")
		void testPN3_CP24() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "";

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 25")
		void testPN3_CP25() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = null;

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> assertDoesNotThrow(
									() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
											responsable, personal, fechaInicio, duracion, estado, proceso),
									"No se ha tratado correctamente el valor nulo"),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 26")
		void testPN3_CP26() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";
			proceso = null;

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}
	}

	@Nested
	@DisplayName("Prueba de caja blanca 3 - actualizarOT")
	class PB3{

		@BeforeEach
		void setUp() throws Exception {
			gestorOT = new GestorDeOOTT();

			materiales = new ArrayList<>();
			presupuestos = new ArrayList<>();
			personal = new ArrayList<>();

			proceso = gestorP.crearProceso(Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(),
					Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
			proceso.getIncidencias().add(gestorI.crearIncidencia(Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
		}
		
		@AfterEach
		void tearDown() throws Exception {
			gestorOT = null;

			materiales = null;
			presupuestos = null;
			personal = null;
			proceso = null;
			
			otResult = null;
		}

		@Test
		@DisplayName("Caso de prueba 1")
		void testPN3_CP1() {
			descripcion = "Se realiza este trabajo";
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			OT ot = new OT("0", descripcion, materiales, presupuestos, coste, responsable, personal, fechaInicio,
					duracion, estado, proceso);
			
			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste,
									responsable, personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertEquals(ot, otResult, "La orden de trabajo creada no es válida"));
		}
	}
}
