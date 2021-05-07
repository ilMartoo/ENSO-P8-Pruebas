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
	class Incidencias{
		
		@BeforeEach
		void setUp() throws Exception {
			
			// Inicializamos los objetos que vamos a emplear
			Incidencia i1 = new Incidencia("I0001", "Ana García", "98765432M", "+01234567891", "Incidencia de ejemplo 1", "Vigo", "prueba");
			Incidencia i2 = new Incidencia("I0002", "Ana García", "98765432M", "+01234567891", "Incidencia de ejemplo 2", "Vigo", "prueba");
			Incidencia i3 = new Incidencia("I0003", "Pedro González", "12345678Z", "+01234567891", "Incidencia de ejemplo 3", "Vigo", "prueba");
			
			ArrayList<Incidencia> incidencias = new ArrayList<>();
			incidencias.add(i1); incidencias.add(i2); incidencias.add(i3);
			
			// Preparamos el comportamiento del mock
			Mockito.when(gi.getIncidencias(null, null)).thenReturn(incidencias);
		}
		
		@Nested
		class Recuento {
		
			@Test
			void recuentoTotal() {
				assertEquals(3,ge.totalIncidencias(null, null),"Error en el recuento total de incidencias.");
			}
			
			@Test
			void recuentoFiltrado() {
				assertEquals(2,ge.totalIncidencias("dni", "98765432C"),"Error en el recuento filtrado por dni de incidencias.");
			}
			
			@Test
			void recuentoCampoFiltradoInvalido() {
				assertNull(ge.totalIncidencias("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");
			}
			
			@Test
			void recuentoValorFiltradoInvalido() {
				assertAll(
						()->{assertNull(ge.totalIncidencias("dni", ""),"El valor de filtrado sí es válido.");},
						()->{assertNull(ge.totalIncidencias("dni", null),"El valor de filtrado sí es válido.");}
						);
			}
		}
		
		@Nested
		class Distribucion {
			
			@Test
			void distribucionTotal() {
				assertEquals(crearMapa(hoy,3),ge.distribucionIncidencias(null, null),"El resultado total no es igual.");
			}
			
			
			@Test
			void distribucionFiltrado() {
				assertEquals(crearMapa(hoy,2),ge.distribucionIncidencias("dni", "98765432C"),"El resultado filtrado no es igual.");
			}
			
			@Test
			void distribucionCampoFiltradoInvalido() {
				assertNull(ge.distribucionIncidencias("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");
			}
			
			@Test
			void distribucionValorFiltradoInvalido() {
				
				assertAll(
						()->{assertNull(ge.distribucionIncidencias("dni", ""),"El valor de filtrado sí es válido.");},
						()->{assertNull(ge.distribucionIncidencias("dni", null),"El valor de filtrado sí es válido.");}
						);
			}
		}
	}
	
	
	
	@Nested
	class Procesos{
		
		@BeforeEach
		void setUp() throws Exception {
			
			// Inicializamos los objetos que vamos a emplear
			Proceso p1 = new Proceso("P1","Proceso 1",(float)50,30,"Bajo prueba","Ana García","Servicio 1",new ArrayList<Incidencia>());
			Proceso p2 = new Proceso("P2","Proceso 2",(float)30,20,"Bajo prueba","Ana García","Servicio 1",new ArrayList<Incidencia>());
			Proceso p3 = new Proceso("P3","Proceso 3",(float)20,10,"Bajo prueba","Pedro González","Servicio 1",new ArrayList<Incidencia>());
			
			ArrayList<Proceso> procesos = new ArrayList<>();
			procesos.add(p1); procesos.add(p2); procesos.add(p3);
			
			// Preparamos el comportamiento del mock
			Mockito.when(gp.getProcesos(null, null)).thenReturn(procesos);
		}
		
		@Nested
		class Recuento {
		
			@Test
			void recuentoTotal() {
				assertEquals(3,ge.totalProcesos(null, null),"Error en el recuento total de incidencias.");
			}
			
			@Test
			void recuentoFiltrado() {
				assertEquals(2,ge.totalProcesos("dni", "98765432C"),"Error en el recuento filtrado por dni de incidencias.");
			}
			
			@Test
			void recuentoCampoFiltradoInvalido() {
				assertNull(ge.totalProcesos("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");
			}
			
			@Test
			void recuentoValorFiltradoInvalido() {
				assertAll(
						()->{assertNull(ge.totalProcesos("dni", ""),"El valor de filtrado sí es válido.");},
						()->{assertNull(ge.totalProcesos("dni", null),"El valor de filtrado sí es válido.");}
						);
			}
		}
		
		@Nested
		class Distribucion{
			
			@Test
			void distribucionTotal() {
				assertEquals(crearMapa(hoy,3),ge.distribucionProcesos(null, null),"El resultado total no es igual.");
			}
			
			
			@Test
			void distribucionFiltrado() {
				assertEquals(crearMapa(hoy,2),ge.distribucionProcesos("responsable", "Ana García"),"El resultado filtrado no es igual.");
			}
			
			@Test
			void distribucionCampoFiltradoInvalido() {
				assertNull(ge.distribucionProcesos("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");
			}
			
			@Test
			void distribucionValorFiltradoInvalido() {
				
				assertAll(
						()->{assertNull(ge.distribucionProcesos("responsable", ""),"El valor de filtrado sí es válido.");},
						()->{assertNull(ge.distribucionProcesos("responsable", null),"El valor de filtrado sí es válido.");}
						);
			}
		}
	}
	
	
	@Nested
	class OrdenesTrabajo{
		
		@BeforeEach
		void setUp() throws Exception {
			
			// Inicializamos los objetos que vamos a emplear
			OT o1 = new OT("OT1","Orden 1",null,null,(float)40,"Ana García",null,format.format(hoy),3,"Bajo prueba",null);
			OT o2 = new OT("OT2","Orden 2",null,null,(float)40,"Ana García",null,format.format(hoy),3,"Bajo prueba",null);
			OT o3 = new OT("OT3","Orden 3",null,null,(float)40,"Pedro Gonzálezs",null,format.format(hoy),3,"Bajo prueba",null);
		
			ArrayList<OT> ordenes = new ArrayList<>();
			ordenes.add(o1); ordenes.add(o2); ordenes.add(o3);
			
			// Preparamos el comportamiento del mock
			Mockito.when(got.getOOTT(null, null)).thenReturn(ordenes);
		}
		
		
		@Nested
		class Recuento {
		
			@Test
			void recuentoTotal() {
				assertEquals(3,ge.totalOOTT(null, null),"Error en el recuento total de OOTT.");
			}
			
			@Test
			void recuentoFiltrado() {
				assertEquals(2,ge.totalOOTT("responsable", "Ana García"),"Error en el recuento filtrado por dni de OOTT.");
			}
			
			@Test
			void recuentoCampoFiltradoInvalido() {
				assertNull(ge.totalOOTT("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");
			}
			
			@Test
			void recuentoValorFiltradoInvalido() {
				assertAll(
						()->{assertNull(ge.totalOOTT("responsable", ""),"El valor de filtrado sí es válido.");},
						()->{assertNull(ge.totalOOTT("responsable", null),"El valor de filtrado sí es válido.");}
						);
			}
		}
		
		@Nested
		class Distribucion {
			
			@Test
			void distribucionTotal() {
				assertEquals(crearMapa(hoy,3),ge.distribucionOOTT(null, null),"El resultado total no es igual.");
			}
			
			
			@Test
			void distribucionFiltrado() {
				assertEquals(crearMapa(hoy,2),ge.distribucionOOTT("responsable", "Ana García"),"El resultado filtrado no es igual.");
			}
			
			@Test
			void distribucionCampoFiltradoInvalido() {
				assertNull(ge.distribucionOOTT("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");
			}
			
			@Test
			void distribucionValorFiltradoInvalido() {
				
				assertAll(
						()->{assertNull(ge.distribucionOOTT("responsable", ""),"El valor de filtrado sí es válido.");},
						()->{assertNull(ge.distribucionOOTT("responsable", null),"El valor de filtrado sí es válido.");}
						);
			}
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

}
