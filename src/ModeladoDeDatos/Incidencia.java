package ModeladoDeDatos;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Incidencia {
	
	// Atributos
	private String ID;
	private String nombreCiudadano;
	private String DNI;
	private String telefono;
	private String descripcion;
	private String localizacion;
	private String tipo;
	private Proceso proceso;
	private String fechaCreacion;
	
	// Constructor
	public Incidencia(String ID, String nombreCiudadano, String DNI, String telefono, String descripcion,
			String localizacion, String tipo) {
		super();
		this.ID = ID;
		this.nombreCiudadano = nombreCiudadano;
		this.DNI = DNI;
		this.telefono = telefono;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.tipo = tipo;
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		Date hoy = new Date();
		this.fechaCreacion = format.format(hoy);
	}
	public Incidencia(String ID, String nombreCiudadano, String DNI, String telefono, String descripcion,
			String localizacion, String tipo, Proceso proceso) {
		super();
		this.ID = ID;
		this.nombreCiudadano = nombreCiudadano;
		this.DNI = DNI;
		this.telefono = telefono;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.tipo = tipo;
		this.proceso = proceso;
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		Date hoy = new Date();
		this.fechaCreacion = format.format(hoy);
	}
	
	// Getters y setters
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNombreCiudadano() {
		return nombreCiudadano;
	}
	public void setNombreCiudadano(String nombreCiudadano) {
		this.nombreCiudadano = nombreCiudadano;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	@Override
	public String toString() {
		return "Incidencia [ID=" + ID + ", nombreCiudadano=" + nombreCiudadano + ", DNI=" + DNI + ", telefono="
				+ telefono + ", descripcion=" + descripcion + ", localizacion=" + localizacion + ", tipo=" + tipo
				+ ", proceso=" + ((proceso != null) ? proceso.getNombre():"null") + ", fechaCreacion=" + fechaCreacion + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidencia other = (Incidencia) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
	
}
