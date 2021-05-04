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
import org.junit.jupiter.api.Test;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.Proceso;
import SubsGestionIncidencias.GestorDeIncidencias;
import SubsGestionIncidencias.InterfazGestorIncidencias;
import SubsGestionOOTT.GestorDeOOTT;
import SubsGestionOOTT.InterfazGestorOOTT;
import SubsGestionProcesos.GestorDeProcesos;
import SubsGestionProcesos.InterfazGestorProcesos;

class TestAnalisisEstadisticos {
	
	static InterfazGestorIncidencias incidencias;
	static InterfazGestorProcesos procesos;
	static InterfazGestorOOTT ordenesTrabajo;
	static InterfazGestorEstadisticas estadisticas;
	
	static Date hoy;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
				()->{assertEquals(2,estadisticas.totalIncidencias("dni", "98765432C"),"Error en el recuento filtrado por dni de incidencias.");},
				()->{assertEquals(3,estadisticas.totalIncidencias(null, null),"Error en el recuento total de incidencias.");},
				()->{assertNull(estadisticas.totalIncidencias("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(estadisticas.totalIncidencias("dni", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(estadisticas.totalIncidencias("dni", null),"El valor de filtrado sí es válido.");}
				);
	}

	
	@Test
	void testTotalProcesos() {
		
		assertAll(
				()->{assertEquals(2,estadisticas.totalProcesos("responsable", "98765432C"),"Error en el recuento filtrado por dni de procesos.");},
				()->{assertEquals(3,estadisticas.totalProcesos(null, null),"Error en el recuento total de procesos.");},
				()->{assertNull(estadisticas.totalProcesos("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(estadisticas.totalProcesos("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(estadisticas.totalProcesos("responsable", null),"El valor de filtrado sí es válido.");}
				);
	}


	@Test
	void testTotalOOTT() {
		
		assertAll(
				()->{assertEquals(2,estadisticas.totalOOTT("responsable", "98765432C"),"Error en el recuento filtrado por dni de OOTT.");},
				()->{assertEquals(3,estadisticas.totalOOTT(null, null),"Error en el recuento total de OOTT.");},
				()->{assertNull(estadisticas.totalOOTT("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(estadisticas.totalOOTT("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(estadisticas.totalOOTT("responsable", null),"El valor de filtrado sí es válido.");}
				);
	}

	@Test
	void testDistribucionIncidencias() {
		
		assertAll(
				()->{assertEquals(crearMapa(hoy,2),estadisticas.distribucionIncidencias("dni", "98765432C"),"El resultado filtrado no es igual.");},
				()->{assertEquals(crearMapa(hoy,3),estadisticas.distribucionIncidencias(null, null),"El resultado total no es igual.");},
				()->{assertNull(estadisticas.distribucionIncidencias("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(estadisticas.distribucionIncidencias("dni", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(estadisticas.distribucionIncidencias("dni", null),"El valor de filtrado sí es válido.");}
				);
	}

	@Test
	void testDistribucionProcesos() {
		
		assertAll(
				()->{assertEquals(crearMapa(hoy,2),estadisticas.distribucionProcesos("responsable", "98765432C"),"El resultado filtrado no es igual.");},
				()->{assertEquals(crearMapa(hoy,3),estadisticas.distribucionProcesos(null, null),"El resultado total no es igual.");},
				()->{assertNull(estadisticas.distribucionProcesos("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(estadisticas.distribucionProcesos("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(estadisticas.distribucionProcesos("responsable", null),"El valor de filtrado sí es válido.");}
				);
	}

	@Test
	void testDistribucionOOTT() {
		
		assertAll(
				()->{assertEquals(crearMapa(hoy,2),estadisticas.distribucionOOTT("responsable", "98765432C"),"El resultado filtrado no es igual.");},
				()->{assertEquals(crearMapa(hoy,3),estadisticas.distribucionOOTT(null, null),"El resultado total no es igual.");},
				()->{assertNull(estadisticas.distribucionOOTT("ASDFGH", "98765432C"),"El campo de filtrado sí es válido.");},
				()->{assertNull(estadisticas.distribucionOOTT("responsable", ""),"El valor de filtrado sí es válido.");},
				()->{assertNull(estadisticas.distribucionOOTT("responsable", null),"El valor de filtrado sí es válido.");}
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
