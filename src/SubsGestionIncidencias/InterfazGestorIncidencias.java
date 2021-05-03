package SubsGestionIncidencias;

import java.util.ArrayList;

import ModeladoDeDatos.Incidencia;

public interface InterfazGestorIncidencias {

	ArrayList<Incidencia> getIncidencias(String campoFiltro, String valorFiltro);
	Incidencia crearIncidencia(String DNI, String nombre, String tlf, String descripcion, String localizacion, String tipo);
	void actualizarIncidencia(String ID, String campo, String valor);
	void eliminarIncidencia(String ID);
}
