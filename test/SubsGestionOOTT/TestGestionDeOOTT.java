package SubsGestionOOTT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
		gestorI = null;
		gestorP = null;
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
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
					() -> assertSame(null, otResult, "Se ha creado una orden de trabajo inválida"));
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
					() -> assertSame(null, otResult, "Se ha creado una orden de trabajo inválida"));
		}
	}

	@Nested
	@DisplayName("Prueba de caja blanca 3 - actualizarOT")
	class PB3 {

		String id;
		String campo;
		String valor;

		ArrayList<String> materiales1;
		ArrayList<Double> presupuestos1;
		ArrayList<String> personal1;
		Proceso proceso1;
		ArrayList<String> materiales2;
		ArrayList<Double> presupuestos2;
		ArrayList<String> personal2;
		Proceso proceso2;

		ArrayList<OT> ordenes;

		OT otResult;
		OT otModificable;

		@BeforeEach
		void setUp() throws Exception {
			gestorOT = new GestorDeOOTT();

			materiales1 = new ArrayList<>();
			presupuestos1 = new ArrayList<>();
			personal1 = new ArrayList<>();

			proceso1 = gestorP.crearProceso(Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(),
					Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
			proceso1.getIncidencias().add(gestorI.crearIncidencia(Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));

			materiales2 = new ArrayList<>();
			presupuestos2 = new ArrayList<>();
			personal2 = new ArrayList<>();

			proceso2 = gestorP.crearProceso(Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat(),
					Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
			proceso2.getIncidencias().add(gestorI.crearIncidencia(Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));

			ordenes = gestorOT.getOOTT(null, null);
		}

		@AfterEach
		void tearDown() throws Exception {
			gestorOT = null;

			id = null;
			campo = null;
			valor = null;

			materiales1 = null;
			presupuestos1 = null;
			personal1 = null;
			proceso1 = null;

			materiales2 = null;
			presupuestos2 = null;
			personal2 = null;
			proceso2 = null;

			ordenes = null;

			otResult = null;
			otModificable = null;
		}

		@Test
		@DisplayName("Camino lógico 1")
		void testPB3_CL1() {
			id = null;
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 2")
		void testPB3_CL2() {
			id = "";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 3")
		void testPB3_CL3() {
			id = "111111111122222222223";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 4")
		void testPB3_CL4() {
			id = "-12";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 5")
		void testPB3_CL5() {
			id = "0";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 6")
		void testPB3_CL6() {
			id = "0";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("10", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("10", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 7")
		void testPB3_CL7() {
			id = "0";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("10", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("10", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);
			ordenes.add(new OT("11", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f, "Desatranques Jaén",
					personal1, "15/07/21", 1, "pendiente", proceso1));

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 8")
		void testPB3_CL8() {
			id = "0";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("10", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("10", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);
			for (int i = 0; i < 9; i++)
				ordenes.add(new OT(String.valueOf(i + 11), "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
						"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1));

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 9")
		void testPB3_CL9() {
			id = "0";
			campo = null;
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 10")
		void testPB3_CL10() {
			id = "0";
			campo = "";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 11")
		void testPB3_CL11() {
			id = "0";
			campo = "main";
			valor = null;

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 12")
		void testPB3_CL12() {
			id = "0";
			campo = "main";
			valor = "";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 13")
		void testPB3_CL13() {
			id = "0";
			campo = "descripcion";
			valor = "Descripcion";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", valor, materiales2, presupuestos2, 0.01f, "Desatranques Jaén", personal2, "15/07/21",
					1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 14")
		void testPB3_CL14() {
			id = "0";
			campo = "descripcion";
			valor = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus metus ex, eleifend vel imperdiet a, "
					+ "tincidunt sed nibh. Duis aliquam accumsan malesuada. Fusce sapien diam, auctor eget est et ligula.";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 15")
		void testPB3_CL15() {
			id = "0";
			campo = "material";
			valor = "Martillo";

			materiales1.add("Peine");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Peine");
			materiales2.add(valor);
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			ArrayList<String> materialesOriginal = (ArrayList<String>) materiales1.clone();

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materialesOriginal, presupuestos2, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 16")
		void testPB3_CL16() {
			id = "0";
			campo = "material";
			valor = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 17")
		void testPB3_CL17() {
			id = "0";
			campo = "presupuestos";
			valor = "100";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			presupuestos2.add(Double.valueOf(valor));
			personal2.add("93218185J");

			ArrayList<Double> presupuestosOriginales = (ArrayList<Double>) presupuestos1.clone();

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestosOriginales, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 18")
		void testPB3_CL18() {
			id = "0";
			campo = "presupuestos";
			valor = "-15";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 19")
		void testPB3_CL19() {
			id = "0";
			campo = "coste";
			valor = "100";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, Float.valueOf(valor),
					"Desatranques Jaén", personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 20")
		void testPB3_CL20() {
			id = "0";
			campo = "coste";
			valor = "0";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 21")
		void testPB3_CL21() {
			id = "0";
			campo = "responsable";
			valor = "María Franco";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, valor, personal2,
					"15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 22")
		void testPB3_CL22() {
			id = "0";
			campo = "responsable";
			valor = "Lorem ipsum dolor sit amet";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 23")
		void testPB3_CL23() {
			id = "0";
			campo = "duracion";
			valor = "10";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", Integer.valueOf(valor), "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 24")
		void testPB3_CL24() {
			id = "0";
			campo = "duracion";
			valor = "0";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 25")
		void testPB3_CL25() {
			id = "0";
			campo = "estado";
			valor = "pendiente";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "en proceso", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "en proceso", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 26")
		void testPB3_CL26() {
			id = "0";
			campo = "estado";
			valor = "Lorem ipsum dolor sit amet";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 27")
		void testPB3_CL27() {
			id = "0";
			campo = "main";
			valor = "Hola mundo";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 28")
		void testPB3_CL28() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/11/2021";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 29")
		void testPB3_CL29() {
			id = "0";
			campo = "fechaInicio";
			valor = "A1/11/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 30")
		void testPB3_CL30() {
			id = "0";
			campo = "fechaInicio";
			valor = "0A/11/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 31")
		void testPB3_CL31() {
			id = "0";
			campo = "fechaInicio";
			valor = "01A11/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 32")
		void testPB3_CL32() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/A1/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 33")
		void testPB3_CL33() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/1A/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 34")
		void testPB3_CL34() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/11A21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 35")
		void testPB3_CL35() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/11/A1";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 36")
		void testPB3_CL36() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/11/2A";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 37")
		void testPB3_CL37() {
			id = "0";
			campo = "fechaInicio";
			valor = "31/11/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 38")
		void testPB3_CL38() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/12/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 39")
		void testPB3_CL39() {
			id = "0";
			campo = "fechaInicio";
			valor = "01/11/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "en proceso", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, valor, 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 40")
		void testPB3_CL40() {
			id = "0";
			campo = "fechaInicio";
			valor = "29/02/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 41")
		void testPB3_CL41() {
			id = "0";
			campo = "fechaInicio";
			valor = "29/11/21";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "en proceso", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, valor, 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}

		@Test
		@DisplayName("Camino lógico 42")
		void testPB3_CL42() {
			id = "0";
			campo = "personal";
			valor = "1";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 43")
		void testPB3_CL43() {
			id = "0";
			campo = "personal";
			valor = "A12345678";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 44")
		void testPB3_CL44() {
			id = "0";
			campo = "personal";
			valor = "1A2345678";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 45")
		void testPB3_CL45() {
			id = "0";
			campo = "personal";
			valor = "12345678-";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 46")
		void testPB3_CL46() {
			id = "0";
			campo = "personal";
			valor = "123456789";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");

			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f, "Desatranques Jaén",
					personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertEquals(otResult, otModificable, "Se ha actualizado la orden de trabajo con valores no validos");
		}

		@Test
		@DisplayName("Camino lógico 47")
		void testPB3_CL47() {
			id = "0";
			campo = "personal";
			valor = "12345678Z";

			materiales1.add("Martillo");
			presupuestos1.add(1000.0);
			personal1.add("93218185J");

			materiales2.add("Martillo");
			presupuestos2.add(1000.0);
			personal2.add("93218185J");
			personal2.add("12345678Z");

			ArrayList<Double> personalOriginal = (ArrayList<Double>) personal1.clone();
			
			otModificable = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "pendiente", proceso1);
			OT otOriginal = new OT("0", "Se realiza este trabajo", materiales1, presupuestos1, 0.01f,
					"Desatranques Jaén", personal1, "15/07/21", 1, "en proceso", proceso1);
			otResult = new OT("0", "Se realiza este trabajo", materiales2, presupuestos2, 0.01f,
					"Desatranques Jaén", personal2, "15/07/21", 1, "pendiente", proceso2);

			ordenes.add(otModificable);

			gestorOT.actualizarOT(id, campo, valor);

			assertAll(() -> assertNotEquals(otOriginal, otModificable, "No se ha cambiado la orden de trabajo"),
					() -> assertEquals(otResult, otModificable,
							"Se ha actualizado la orden de trabajo de forma incorrecta"));
		}
	}
}
