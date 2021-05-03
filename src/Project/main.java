package Project;

import java.util.*;

import ModeladoDeDatos.*;
import SubsAnalisisEstadisticos.*;
import SubsGestionIncidencias.*;
import SubsGestionOOTT.*;
import SubsGestionProcesos.*;


public class main {

	public static void main(String[] args) {
		
		//Incidencia
		InterfazGestorIncidencias gestorIncidencias = (InterfazGestorIncidencias) new GestorDeIncidencias();
		Incidencia incidencia = gestorIncidencias.crearIncidencia("93218185J", "Jos� Taboada", "+34999999999", "Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre", "mobiliario p�blico");
		gestorIncidencias.crearIncidencia("93218185J", "Lois Rego", "+34999999999", "Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre", "mobiliario p�blico");
		gestorIncidencias.crearIncidencia("93218185J", "Noelia Rey", "+34999999999", "Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre", "mobiliario p�blico");
		gestorIncidencias.crearIncidencia("93218185J", "Miguel Torres", "+34999999999", "Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre", "mobiliario p�blico");
		gestorIncidencias.crearIncidencia("93218185J", "Clara Su�rez", "+34999999999", "Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre", "mobiliario p�blico");
		Incidencia incidencia6 = gestorIncidencias.crearIncidencia("93218185J", "Abraham Trashorras", "+34999999999", "Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre", "mobiliario p�blico");
		System.out.println(incidencia);
		
		gestorIncidencias.actualizarIncidencia("0", "localizacion", "ETSE");
		gestorIncidencias.eliminarIncidencia("1");
		System.out.println(gestorIncidencias.getIncidencias(null, null));
		System.out.println("\n\n");
		
		//Procesos
		InterfazGestorProcesos gestorProcesos = (InterfazGestorProcesos)new GestorDeProcesos();
		Proceso proceso = gestorProcesos.crearProceso("Arreglo banco", "Uno de los bancos del parque de Vista Alegre est� roto", 0.01f, 1, "pendiente", "Ana Garc�a", "arreglo", gestorIncidencias.getIncidencias(null, null));
		Proceso proceso2 = gestorProcesos.crearProceso("Arreglo banco 2", "Uno de los bancos del parque de Vista Alegre est� roto", 0.01f, 1, "pendiente", "Ana Garc�a", "arreglo", gestorIncidencias.getIncidencias("nombreCiudadano", "Noelia Rey"));
		gestorProcesos.crearProceso("Arreglo banco 3", "Uno de los bancos del parque de Vista Alegre est� roto", 0.01f, 1, "pendiente", "Ana Garc�a", "arreglo", gestorIncidencias.getIncidencias("nombreCiudadano", "Noelia Rey"));
		System.out.println(proceso);
		System.out.println(proceso2);
		
		gestorProcesos.actualizarProceso("Arreglo banco", "descripcion", "PRUEBA PRUEBA PRUEBA");
		gestorProcesos.eliminarProceso("Arreglo banco 3");
		gestorProcesos.vincularIncidencia("Arreglo banco 2", incidencia6);
		System.out.println(gestorProcesos.getProcesos(null, null));
		System.out.println("\n\n");
		
		//OT
		InterfazGestorOOTT gestorOOTT = (InterfazGestorOOTT) new GestorDeOOTT();
		List<String> listaM = new ArrayList<String>(); listaM.add("ladrillos");
		List<String> listaPe = new ArrayList<String>(); listaPe.add("93218185J");
		List<Double> listaPr = new ArrayList<Double>(); listaPr.add(100.5);
		System.out.println("Crear ot");
		OT ot = gestorOOTT.crearOT("Se realiza este trabajo", listaM, listaPr, 0.01f, "Desatranques Ja�n", listaPe, "15/07/21", 1, "pendiente", proceso);
		System.out.println(ot);
		System.out.println("Crear ot 2");
		OT ot2 = gestorOOTT.crearOT("Se realiza este trabajo", listaM, listaPr, 0.01f, "Desatranques Ja�n", listaPe, "15/07/21", 1, "pendiente", proceso);
		System.out.println(ot2);
		System.out.println("\n");
		
		gestorOOTT.actualizarOT("0", "descripcion", "DESCRIPCION DE PRUEBA");
		gestorOOTT.incluirPresupuesto("0", 666.6f);
		gestorOOTT.incluirPresupuesto("0", 999.9f);
		gestorOOTT.retirarPresupuesto("0", 999.9f);
		gestorOOTT.incluirMaterial("0", "cemento");
		gestorOOTT.incluirMaterial("0", "agua");
		gestorOOTT.retirarMaterial("0", "agua");
		gestorOOTT.incluirPersonal("0", "93218185J");
		gestorOOTT.incluirPersonal("0", "16087624K");
		gestorOOTT.retirarPersonal("0", "16087624K");
		System.out.println("Pre borrado de OT 1\n"+proceso);
		gestorOOTT.eliminarOT("1");
		System.out.println("Post borrado de OT 1\n"+proceso);
		System.out.println("Precios acumulados: "+gestorOOTT.consultarPreciosAcumulados(null, null));
		System.out.println(gestorOOTT.getOOTT(null, null));
		System.out.println("\n\n");
		
		//Estad�sticas
		InterfazGestorEstadisticas gestorEstadisticas = (InterfazGestorEstadisticas) new AnalisisEstadisticos(gestorIncidencias, gestorProcesos, gestorOOTT);
		System.out.println("totalIncidencias: "+gestorEstadisticas.totalIncidencias(null, null));
		System.out.println("totalProcesos: "+gestorEstadisticas.totalProcesos(null, null));
		System.out.println("totalOOTT: "+gestorEstadisticas.totalOOTT(null, null));
		System.out.println("costesProcesos: "+gestorEstadisticas.costesProcesos(null, null));
		System.out.println("costesOOTT: "+gestorEstadisticas.costesOOTT(null, null));
		System.out.println("distribucionIncidencias: "+gestorEstadisticas.distribucionIncidencias(null, null));
		System.out.println("distribucionProcesos: "+gestorEstadisticas.distribucionProcesos(null, null));
		System.out.println("distribucionOOTT: "+gestorEstadisticas.distribucionOOTT(null, null));
		
	}

}
