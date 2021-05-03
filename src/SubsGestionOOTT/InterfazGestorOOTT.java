package SubsGestionOOTT;

import java.util.ArrayList;
import java.util.List;

import ModeladoDeDatos.*;

public interface InterfazGestorOOTT {
		ArrayList<OT> getOOTT(String campoFiltro, String valorFiltro);
		OT crearOT(String descripcion, List<String> material, List<Double> presupuestos, Float coste, String
		responsable, List<String> personal, String fechaInicio, Integer duracion, String estado, Proceso proceso);
		void actualizarOT(String ID, String campo, String valor);
		void incluirPresupuesto(String ID, Float presupuesto);
		void retirarPresupuesto(String ID, Float presupuesto);
		void incluirMaterial(String ID, String material);
		void retirarMaterial(String ID, String material);
		void incluirPersonal(String ID, String empleado);
		void retirarPersonal(String ID, String empleado);
		void eliminarOT(String ID);
		Double consultarPreciosAcumulados(String campoFiltro, String valorFiltro);
}
