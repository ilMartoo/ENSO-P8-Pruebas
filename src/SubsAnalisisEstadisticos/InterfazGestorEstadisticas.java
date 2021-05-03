package SubsAnalisisEstadisticos;

import java.util.HashMap;

public interface InterfazGestorEstadisticas {
	//Miguel
	Integer totalIncidencias(String campoFiltro, String valorFiltro);
	Integer totalProcesos(String campoFiltro, String valorFiltro);
	Integer totalOOTT(String campoFiltro, String valorFiltro);
	Float costesProcesos(String campoFiltro, String valorFiltro);
	Float costesOOTT(String campoFiltro, String valorFiltro);
	//Aculledor
	HashMap<String, Integer> distribucionIncidencias(String campoFiltro, String valorFiltro);
	HashMap<String, Integer> distribucionProcesos(String campoFiltro, String valorFiltro);
	HashMap<String, Integer> distribucionOOTT(String campoFiltro, String valorFiltro);
}
