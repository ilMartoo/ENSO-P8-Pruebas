package SubsGestionOOTT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;
import SubsGestionIncidencias.GestorDeIncidencias;
import SubsGestionProcesos.GestorDeProcesos;

class TestGestorDeOOTT {

	private GestorDeOOTT gestorOT;
	private static GestorDeProcesos gestorP;
	private static GestorDeIncidencias gestorI;

	@BeforeAll
	static void setUpAll() throws Exception {

		gestorP = new GestorDeProcesos();
		gestorI = new GestorDeIncidencias();

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
	static void tearDownAll() throws Exception {
	}

	@Nested
	@DisplayName("Prueba de caja negra 3 - Creacion de ordenes de trabajo")
	class PruebaCajaNegra_3 {

		private String descripcion;
		private ArrayList<String> materiales;
		private ArrayList<Double> presupuestos;
		private Float coste;
		private String responsable;
		private ArrayList<String> personal;
		private String fechaInicio;
		private Integer duracion;
		private String estado;
		private Proceso proceso;

		private OT otResult;

		@BeforeEach
		void setUpEach() throws Exception {
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
		void tearDownEach() throws Exception {
		}

		@Test
		@DisplayName("Caso de prueba 1,2")
		void testPB3_CP1_CP2() {
			String descripcion1 = "Se realiza este trabajo";
			String descripcion2 = null;
			materiales.add("Martillo");
			presupuestos.add(1000.0);
			coste = 0.01f;
			responsable = "Desatranques Jaén";
			personal.add("93218185J");
			fechaInicio = "15/07/21";
			duracion = 1;
			estado = "pendiente";

			OT ot1 = new OT("0", descripcion1, materiales, presupuestos, coste, responsable, personal, fechaInicio,
					duracion, estado, proceso);
			OT ot2 = new OT("0", descripcion2, materiales, presupuestos, coste, responsable, personal, fechaInicio,
					duracion, estado, proceso);

			assertAll(
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion1, materiales, presupuestos, coste,
									responsable, personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertEquals(ot1, otResult, "La orden de trabajo creada no es válida"),
					() -> assertTimeout(Duration.ofSeconds(1),
							() -> otResult = gestorOT.crearOT(descripcion2, materiales, presupuestos, coste,
									responsable, personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertEquals(ot2, otResult, "La orden de trabajo creada no es válida"));
		}

		@Test
		@DisplayName("Caso de prueba 3")
		void testPB3_CP3() {
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
		void testPB3_CP4() {
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
		void testPB3_CP5() {
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
		void testPB3_CP6() {
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
		void testPB3_CP7() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 8")
		void testPB3_CP8() {
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
		void testPB3_CP9() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 10")
		void testPB3_CP10() {
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
		void testPB3_CP11() {
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
		void testPB3_CP12() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 13")
		void testPB3_CP13() {
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
		void testPB3_CP14() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 15")
		void testPB3_CP15() {
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
		void testPB3_CP16() {
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
		void testPB3_CP17() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 18")
		void testPB3_CP18() {
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
		void testPB3_CP19() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 20")
		void testPB3_CP20() {
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
		void testPB3_CP21() {
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
		void testPB3_CP22() {
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
		void testPB3_CP23() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 24")
		void testPB3_CP24() {
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
		void testPB3_CP25() {
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
							() -> otResult = gestorOT.crearOT(descripcion, materiales, presupuestos, coste, responsable,
									personal, fechaInicio, duracion, estado, proceso),
							"Se ha tardado mas de 1 segundo en obtener un resultado"),
					() -> assertNull(otResult, "Se ha creado una orden de trabajo inválida"));
		}

		@Test
		@DisplayName("Caso de prueba 26")
		void testPB3_CP26() {
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
}
