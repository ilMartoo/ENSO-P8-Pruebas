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
		
		/*
		incidencias = new GestorDeIncidencias();
		procesos = new GestorDeProcesos();
		ordenesTrabajo = new GestorDeOOTT();
		estadisticas = new AnalisisEstadisticos(incidencias,procesos,ordenesTrabajo);
		
		Incidencia i1 = incidencias.crearIncidencia("98765432C", "Ana García", "+01234567891", "Incidencia de ejemplo 1", "Vigo", "prueba");
		Incidencia i2 = incidencias.crearIncidencia("98765432C", "Ana García", "+01234567891", "Incidencia de ejemplo 2", "Vigo", "prueba");
		Incidencia i3 = incidencias.crearIncidencia("12345678N", "Pedro González", "+01234567891", "Incidencia de ejemplo 3", "Vigo", "prueba");
		
		List<Incidencia> listaIncidencias = new ArrayList<>();
		listaIncidencias.add(i1);
		listaIncidencias.add(i2);
		listaIncidencias.add(i3);
		
		Proceso p1 = procesos.crearProceso("Proceso 1", "Proceso de ejmplo 1", (float)20, 50, "prueba", "98765432C", "servicio 1", listaIncidencias);
		Proceso p2 = procesos.crearProceso("Proceso 2", "Proceso de ejmplo 2", (float)40, 20, "prueba", "98765432C", "servicio 1", listaIncidencias);
		Proceso p3 = procesos.crearProceso("Proceso 3", "Proceso de ejmplo 3", (float)30, 10, "prueba", "12345678N", "servicio 1", listaIncidencias);
		
		List<String> material = new ArrayList<>();
		material.add("Material");
		
		List<Double> presupuestos = new ArrayList<>();
		presupuestos.add((double)2000);
		
		List<String> personal = new ArrayList<>();
		personal.add("98765432C");
		//personal.add("12345678N");
		
		hoy = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		
		ordenesTrabajo.crearOT("OT 1", material, presupuestos, (float)30, "98765432C", personal, format.format(hoy), 20, "prueba", p1);
		ordenesTrabajo.crearOT("OT 2", material, presupuestos, (float)20, "98765432C", personal, format.format(hoy), 20, "prueba", p2);
		ordenesTrabajo.crearOT("OT 3", material, presupuestos, (float)10, "12345678N", personal, format.format(hoy), 20, "prueba", p3);
		*/
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
		
		// Inicializamos los objetos que vamos a emplear
		Incidencia i1 = new Incidencia("I0001", "Ana García", "98765432M", "+01234567891", "Incidencia de ejemplo 1", "Vigo", "prueba");
		Incidencia i2 = new Incidencia("I0002", "Ana García", "98765432M", "+01234567891", "Incidencia de ejemplo 2", "Vigo", "prueba");
		Incidencia i3 = new Incidencia("I0003", "Pedro González", "12345678Z", "+01234567891", "Incidencia de ejemplo 3", "Vigo", "prueba");
		
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		incidencias.add(i1); incidencias.add(i2); incidencias.add(i3);
		
		Proceso p1 = new Proceso("P1","Proceso 1",(float)50,30,"Bajo prueba","Ana García","Servicio 1",incidencias);
		Proceso p2 = new Proceso("P2","Proceso 2",(float)30,20,"Bajo prueba","Ana García","Servicio 1",incidencias);
		Proceso p3 = new Proceso("P3","Proceso 3",(float)20,10,"Bajo prueba","Pedro González","Servicio 1",incidencias);
		
		ArrayList<Proceso> procesos = new ArrayList<>();
		procesos.add(p1); procesos.add(p2); procesos.add(p3);
		
		OT o1 = new OT("OT1","Orden 1",null,null,(float)40,"Ana García",null,format.format(hoy),3,"Bajo prueba",p1);
		OT o2 = new OT("OT2","Orden 2",null,null,(float)40,"Ana García",null,format.format(hoy),3,"Bajo prueba",p2);
		OT o3 = new OT("OT3","Orden 3",null,null,(float)40,"Pedro Gonzálezs",null,format.format(hoy),3,"Bajo prueba",p3);
	
		ArrayList<OT> ordenes = new ArrayList<>();
		ordenes.add(o1); ordenes.add(o2); ordenes.add(o3);
		
		// Preparamos el comportamiento de los mocks
		Mockito.when(gi.getIncidencias(null, null)).thenReturn(incidencias);
		Mockito.when(gp.getProcesos(null, null)).thenReturn(procesos);
		Mockito.when(got.getOOTT(null, null)).thenReturn(ordenes);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTotalIncidencias() {
		
		assertAll(
				()->{assertEquals(2,ge.totalIncidencias("dni", "98765432C"),"Error en el recuento filtrado por dni de incidencias.");},
				()->{assertEquals(3,ge.totalIncidencias(null, null),"Error en el recuento total de incidencias.");},
				()->{assertNull(ge.totalIncidencias("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(ge.totalIncidencias("dni", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(ge.totalIncidencias("dni", null),"El valor de filtrado sí es válido.");}
				);
	}


	@Test
	void testTotalOOTT() {
		
		assertAll(
				()->{assertEquals(2,ge.totalOOTT("responsable", "Ana García"),"Error en el recuento filtrado por dni de OOTT.");},
				()->{assertEquals(3,ge.totalOOTT(null, null),"Error en el recuento total de OOTT.");},
				()->{assertNull(ge.totalOOTT("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(ge.totalOOTT("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(ge.totalOOTT("responsable", null),"El valor de filtrado sí es válido.");}
				);
	}

	@Test
	void testDistribucionIncidencias() {
		
		assertAll(
				()->{assertEquals(crearMapa(hoy,2),ge.distribucionIncidencias("dni", "98765432C"),"El resultado filtrado no es igual.");},
				()->{assertEquals(crearMapa(hoy,3),ge.distribucionIncidencias(null, null),"El resultado total no es igual.");},
				()->{assertNull(ge.distribucionIncidencias("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(ge.distribucionIncidencias("dni", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(ge.distribucionIncidencias("dni", null),"El valor de filtrado sí es válido.");}
				);
	}

	@Test
	void testDistribucionProcesos() {
		
		assertAll(
				()->{assertEquals(crearMapa(hoy,2),ge.distribucionProcesos("responsable", "Ana García"),"El resultado filtrado no es igual.");},
				()->{assertEquals(crearMapa(hoy,3),ge.distribucionProcesos(null, null),"El resultado total no es igual.");},
				()->{assertNull(ge.distribucionProcesos("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(ge.distribucionProcesos("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(ge.distribucionProcesos("responsable", null),"El valor de filtrado sí es válido.");}
				);
	}

	@Test
	void testDistribucionOOTT() {
		
		assertAll(
				()->{assertEquals(crearMapa(hoy,2),ge.distribucionOOTT("responsable", "Ana García"),"El resultado filtrado no es igual.");},
				()->{assertEquals(crearMapa(hoy,3),ge.distribucionOOTT(null, null),"El resultado total no es igual.");},
				()->{assertNull(ge.distribucionOOTT("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(ge.distribucionOOTT("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(ge.distribucionOOTT("responsable", null),"El valor de filtrado sí es válido.");}
				);
	}
	
	
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
