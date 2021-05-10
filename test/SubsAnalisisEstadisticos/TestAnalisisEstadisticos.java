package SubsAnalisisEstadisticos;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;
import SubsGestionIncidencias.GestorDeIncidencias;
import SubsGestionIncidencias.InterfazGestorIncidencias;
import SubsGestionOOTT.GestorDeOOTT;
import SubsGestionOOTT.InterfazGestorOOTT;
import SubsGestionProcesos.GestorDeProcesos;
import SubsGestionProcesos.InterfazGestorProcesos;

class TestAnalisisEstadisticos {	
	
	/*clases a mockear*/
	GestorDeIncidencias gi;
	GestorDeProcesos gp;
	GestorDeOOTT got;
	
	@InjectMocks
	/*clase a probar*/
	AnalisisEstadisticos ge;
	
	static Date hoy;
	static DateFormat format;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		hoy = new Date();
		
		format = new SimpleDateFormat("dd/MM/yy");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		// Mockeamos las clases a injectar
		gi = Mockito.mock(GestorDeIncidencias.class);
		gp = Mockito.mock(GestorDeProcesos.class);
		got = Mockito.mock(GestorDeOOTT.class);
		
		// Inicializa los mocks anotados con su clases inyectadas.
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Nested
	@DisplayName("PN5 - Total incidencias")
	class PN5{
		
		@BeforeEach
		void setUp() throws Exception {
			inicializarIncidencias();
		}
		
		@Test
		@DisplayName("CP2 - Recuento total")
		void PN5_CP2() {
			assertEquals(3,ge.totalIncidencias(null, null),"Error en el recuento total de incidencias.");
		}
		
		@Test
		@DisplayName("CP1 - Recuento filtrado por responsable")
		void PN5_CP1() {
			assertEquals(2,ge.totalIncidencias("responsable", "Ana García"),"Error en el recuento filtrado por responsable de incidencias.");
		}
		
		@Test
		@DisplayName("CP3 - Recuento filtrado por campo inválido")
		void PN5_CP3() {
			assertNull(ge.totalIncidencias("ASDFGH", "Ana García"),"Es un campo de filtrado válido.");
		}
		
		@Test
		@DisplayName("CP4 y CP5 -Recuento filtrado por valor inválido")
		void PN5_CP4_CP5() {
			assertAll(
					()->{assertNull(ge.totalIncidencias("responsable", ""),"Se permite filtrar por valores inválidos.");},
					()->{assertNull(ge.totalIncidencias("responsable", null),"Se permite filtrar por nulos.");}
					);
		}
	}
	
	
	
	@Nested
	@DisplayName("PN6 - Total procesos")
	class PN6{
		
		@BeforeEach
		void setUp() throws Exception {
			inicializarProcesos();
		}

		
		@Test
		@DisplayName("CP2 - Recuento total")
		void PN6_CP2() {
			assertEquals(3,ge.totalProcesos(null, null),"Error en el recuento total de procesos.");
		}
		
		@Test
		@DisplayName("CP1 - Recuento filtrado por responsable")
		void PN6_CP1() {
			assertEquals(2,ge.totalProcesos("responsable", "Ana García"),"Error en el recuento filtrado por responsable de procesos.");
		}
		
		@Test
		@DisplayName("CP3 - Recuento filtrado por campo inválido")
		void PN6_CP3() {
			assertNull(ge.totalProcesos("ASDFGH", "Ana García"),"Es un campo de filtrado válido.");
		}
		
		@Test
		@DisplayName("CP4 y CP5 -Recuento filtrado por valor inválido")
		void PN6_CP4_CP5() {
			assertAll(
					()->{assertNull(ge.totalProcesos("responsable", ""),"Se permite filtrar por valores inválidos.");},
					()->{assertNull(ge.totalProcesos("responsable", null),"Se permite filtrar por nulos.");}
					);
		}
		
	}
	
	
	@Nested
	@DisplayName("PN7 - Total órdenes de trabajo")
	class PN7{
		
		@BeforeEach
		void setUp() throws Exception {
			inicializarOrdenesTrabajo();
		}
		
		@Test
		@DisplayName("CP2 - Recuento total")
		void PN7_CP2() {
			assertEquals(3,ge.totalOOTT(null, null),"Error en el recuento total de órdenes de trabajo.");
		}
		
		@Test
		@DisplayName("CP1 - Recuento filtrado por responsable")
		void PN7_CP1() {
			assertEquals(2,ge.totalOOTT("responsable", "Ana García"),"Error en el recuento filtrado por responsable de órdenes de trabajo.");
		}
		
		@Test
		@DisplayName("CP3 - Recuento filtrado por campo inválido")
		void PN7_CP3() {
			assertNull(ge.totalOOTT("ASDFGH", "Ana García"),"Es un campo de filtrado válido.");
		}
		
		@Test
		@DisplayName("CP4 y CP5 -Recuento filtrado por valor inválido")
		void PN7_CP4_CP5() {
			assertAll(
					()->{assertNull(ge.totalOOTT("responsable", ""),"Se permite filtrar por valores inválidos.");},
					()->{assertNull(ge.totalOOTT("responsable", null),"Se permite filtrar por nulos.");}
					);
		}
	}
	
	
	@Nested
	@DisplayName("PN8 - Distribución semanal incidencias")
	class PN8 {
		
		@BeforeEach
		void setUp() throws Exception {
			inicializarIncidencias();
		}
		
		@Test
		@DisplayName("CP2 - Distribución total")
		void PN8_CP2() {
			assertEquals(crearMapa(hoy,3),ge.distribucionIncidencias(null, null),"Error en la distribución total de incidencias.");
		}
		
		
		@Test
		@DisplayName("CP1 - Distribución filtrada por responsable")
		void PN8_CP1() {
			assertEquals(crearMapa(hoy,2),ge.distribucionIncidencias("responsable", "Ana García"),"Error en la distribución filtrada por responsable de incidencias.");
		}
		
		@Test
		@DisplayName("CP3 - Distribución filtrada por campo inválido")
		void PN8_CP3() {
			assertNull(ge.distribucionIncidencias("ASDFGH", "Ana García"),"Es un campo de filtrado válido.");
		}
		
		@Test
		@DisplayName("CP4 y CP5 -Distribución filtrada por valor inválido")
		void PN8_CP4_CP5() {
			
			assertAll(
					()->{assertNull(ge.distribucionIncidencias("responsable", ""),"Se permite filtrar por valores inválidos.");},
					()->{assertNull(ge.distribucionIncidencias("responsable", null),"Se permite filtrar por nulos.");}
					);
		}
	}
	
	
	@Nested
	@DisplayName("PN9 - Distribución semanal procesos")
	class PN9{
		
		@BeforeEach
		void setUp() throws Exception {
			inicializarProcesos();
		}
		
		@Test
		@DisplayName("CP2 - Distribución total")
		void PN9_CP2() {
			assertEquals(crearMapa(hoy,3),ge.distribucionProcesos(null, null),"Error en la distribución total de procesos.");
		}
		
		
		@Test
		@DisplayName("CP1 - Distribución filtrada por responsable")
		void PN9_CP1() {
			assertEquals(crearMapa(hoy,2),ge.distribucionProcesos("responsable", "Ana García"),"Error en la distribución filtrada por responsable de procesos.");
		}
		
		@Test
		@DisplayName("CP3 - Distribución filtrada por campo inválido")
		void PN9_CP3() {
			assertNull(ge.distribucionProcesos("ASDFGH", "Ana García"),"Es un campo de filtrado válido.");
		}
		
		@Test
		@DisplayName("CP4 y CP5 -Distribución filtrada por valor inválido")
		void PN9_CP4_CP5() {
			
			assertAll(
					()->{assertNull(ge.distribucionProcesos("responsable", ""),"Se permite filtrar por valores inválidos.");},
					()->{assertNull(ge.distribucionProcesos("responsable", null),"Se permite filtrar por nulos.");}
					);
		}
	}
	
	
	@Nested
	@DisplayName("PN10 - Distribución semanal órdenes de trabajo")
	class PN10 {
		
		@BeforeEach
		void setUp() throws Exception {
			inicializarOrdenesTrabajo();
		}
		
		@Test
		@DisplayName("CP2 - Distribución total")
		void PN10_CP2() {
			assertEquals(crearMapa(hoy,3),ge.distribucionOOTT(null, null),"Error en la distribución total de órdenes de trabajo.");
		}
		
		@Test
		@DisplayName("CP1 - Distribución filtrada por responsable")
		void PN10_CP1() {
			assertEquals(crearMapa(hoy,2),ge.distribucionOOTT("responsable", "Ana García"),"Error en la distribución filtrada por responsable de órdenes de trabajo.");
		}
		
		@Test
		@DisplayName("CP3 - Distribución filtrada por campo inválido")
		void PN10_CP3() {
			assertNull(ge.distribucionOOTT("ASDFGH", "Ana García"),"Es un campo de filtrado válido.");
		}
		
		@Test
		@DisplayName("CP4 y CP5 - Distribución filtrada por valor inválido")
		void PN10_CP4_CP5() {
			
			assertAll(
					()->{assertNull(ge.distribucionOOTT("responsable", ""),"Se permite filtrar por valores inválidos.");},
					()->{assertNull(ge.distribucionOOTT("responsable", null),"Se permite filtrar por nulos.");}
					);
		}
	}
	
	
	/* método que nos ayuda a construir el resultado esperado de las distribuciones */
	private HashMap<String, Integer> crearMapa(Date hoy, int num){
		
		HashMap<String,Integer> referencia = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(hoy);
		
		referencia.put("Domingo", 0);
		referencia.put("Lunes", 0);
		referencia.put("Martes", 0);
		referencia.put("Miercoles", 0);
		referencia.put("Jueves", 0);
		referencia.put("Viernes", 0);
		referencia.put("Sabado", 0);
		
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		
		case 1:
			referencia.put("Domingo", num);
			break;
		case 2:
			referencia.put("Lunes", num);
			break;
		case 3:
			referencia.put("Martes", num);
			break;
		case 4:
			referencia.put("Miercoles", num);
			break;
		case 5:
			referencia.put("Jueves", num);
			break;
		case 6:
			referencia.put("Viernes", num);
			break;			
		case 7:
			referencia.put("Sabado", num);
			break;
		}	
		
		return referencia;
	}
	
	/*INICIALIZACIONES CON LOS DATOS PARA PRUEBAS*/
	
	//Incidencias
	private void inicializarIncidencias() {
		// Inicializamos los objetos que vamos a emplear para las pruebas
		Incidencia i1 = new Incidencia("I0001", "Ana García", "98765432M", "+01234567891", "Incidencia de ejemplo 1", "Vigo", "prueba");
		Incidencia i2 = new Incidencia("I0002", "Ana García", "98765432M", "+01234567891", "Incidencia de ejemplo 2", "Vigo", "prueba");
		Incidencia i3 = new Incidencia("I0003", "Pedro González", "12345678Z", "+01234567891", "Incidencia de ejemplo 3", "Vigo", "prueba");
		
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(i1); incidencias.add(i2); incidencias.add(i3);
		
		// Preparamos el comportamiento del mock
		Mockito.when(gi.getIncidencias(null, null)).thenReturn(incidencias);
	}
	
	
	//Procesos
	private void inicializarProcesos() {
		// Inicializamos los objetos que vamos a emplear para las pruebas
		Proceso p1 = new Proceso("P1","Proceso 1",(float)50,30,"Bajo prueba","Ana García","Servicio 1",new ArrayList<Incidencia>());
		Proceso p2 = new Proceso("P2","Proceso 2",(float)30,20,"Bajo prueba","Ana García","Servicio 1",new ArrayList<Incidencia>());
		Proceso p3 = new Proceso("P3","Proceso 3",(float)20,10,"Bajo prueba","Pedro González","Servicio 1",new ArrayList<Incidencia>());
		
		ArrayList<Proceso> procesos = new ArrayList<>();
		procesos.add(p1); procesos.add(p2); procesos.add(p3);
		
		// Preparamos el comportamiento del mock
		Mockito.when(gp.getProcesos(null, null)).thenReturn(procesos);
	}
	
	
	//Órdenes de Trabajo
	private void inicializarOrdenesTrabajo() {
		// Inicializamos los objetos que vamos a emplear para las pruebas
		OT o1 = new OT("OT1","Orden 1",null,null,(float)40,"Ana García",null,format.format(hoy),3,"Bajo prueba",null);
		OT o2 = new OT("OT2","Orden 2",null,null,(float)40,"Ana García",null,format.format(hoy),3,"Bajo prueba",null);
		OT o3 = new OT("OT3","Orden 3",null,null,(float)40,"Pedro Gonzálezs",null,format.format(hoy),3,"Bajo prueba",null);
	
		ArrayList<OT> ordenes = new ArrayList<>();
		ordenes.add(o1); ordenes.add(o2); ordenes.add(o3);
		
		// Preparamos el comportamiento del mock
		Mockito.when(got.getOOTT(null, null)).thenReturn(ordenes);
	}

}
