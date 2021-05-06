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
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((localizacion == null) ? 0 : localizacion.hashCode());
		result = prime * result + ((nombreCiudadano == null) ? 0 : nombreCiudadano.hashCode());
		result = prime * result + ((proceso == null) ? 0 : proceso.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (localizacion == null) {
			if (other.localizacion != null)
				return false;
		} else if (!localizacion.equals(other.localizacion))
			return false;
		if (nombreCiudadano == null) {
			if (other.nombreCiudadano != null)
				return false;
		} else if (!nombreCiudadano.equals(other.nombreCiudadano))
			return false;
		if (proceso == null) {
			if (other.proceso != null)
				return false;
		} else if (!proceso.equals(other.proceso))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
}
