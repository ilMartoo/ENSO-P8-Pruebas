package SubsGestionProcesos;

import java.util.ArrayList;
import java.util.List;

import ModeladoDeDatos.*;

public interface InterfazGestorProcesos {
	ArrayList<Proceso> getProcesos(String campoFiltro, String valorFiltro);
	Proceso crearProceso(String nombre, String descripcion, Float coste, Integer estimado, String estado, String
	responsable, String servicio, List<Incidencia> incidencia);
	void actualizarProceso(String nombre, String campo, String valor);
	void eliminarProceso(String nombre);
	void vincularIncidencia(String nombre, Incidencia incidencia);
}
