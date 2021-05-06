package ModeladoDeDatos;

import java.util.ArrayList;

public class OT {

	// Atributos
	private String ID;
	private String descripcion;
	private ArrayList<String> material;
	private ArrayList<Double> presupuestos;
	private Float coste;
	private String responsable;
	private ArrayList<String> personal;
	private String fechaInicio;
	private Integer duracion;
	private String estado;
	private Proceso proceso;
	
	// Constructor
	public OT(String ID, String descripcion, ArrayList<String> material, ArrayList<Double> presupuestos, Float coste,
			String responsable, ArrayList<String> personal, String fechaInicio, Integer duracion, String estado, Proceso proceso) {
		super();
		this.ID = ID;
		this.descripcion = descripcion;
		this.material = material;
		this.presupuestos = presupuestos;
		this.coste = coste;
		this.responsable = responsable;
		this.personal = personal;
		this.fechaInicio = fechaInicio;
		this.duracion = duracion;
		this.estado = estado;
		this.proceso = proceso;
	}

	// Getters y setters
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<String> getMaterial() {
		return material;
	}

	public void setMaterial(ArrayList<String> material) {
		this.material = material;
	}

	public ArrayList<Double> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(ArrayList<Double> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public Float getCoste() {
		return coste;
	}

	public void setCoste(Float coste) {
		this.coste = coste;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public ArrayList<String> getPersonal() {
		return personal;
	}

	public void setPersonal(ArrayList<String> personal) {
		this.personal = personal;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Proceso getProceso() {
		return proceso;
	}
	
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	@Override
	public String toString() {
		return "OT [ID=" + ID + ", descripcion=" + descripcion + ", material=" + material + ", presupuestos="
				+ presupuestos + ", coste=" + coste + ", responsable=" + responsable + ", personal=" + personal
				+ ", fechaInicio=" + fechaInicio + ", duracion=" + duracion + ", estado=" + estado + ", proceso="
				+ ((proceso != null) ? proceso.getNombre():"null") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((coste == null) ? 0 : coste.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((personal == null) ? 0 : personal.hashCode());
		result = prime * result + ((presupuestos == null) ? 0 : presupuestos.hashCode());
		result = prime * result + ((proceso == null) ? 0 : proceso.hashCode());
		result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
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
		OT other = (OT) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (coste == null) {
			if (other.coste != null)
				return false;
		} else if (!coste.equals(other.coste))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (personal == null) {
			if (other.personal != null)
				return false;
		} else if (!personal.equals(other.personal))
			return false;
		if (presupuestos == null) {
			if (other.presupuestos != null)
				return false;
		} else if (!presupuestos.equals(other.presupuestos))
			return false;
		if (proceso == null) {
			if (other.proceso != null)
				return false;
		} else if (!proceso.equals(other.proceso))
			return false;
		if (responsable == null) {
			if (other.responsable != null)
				return false;
		} else if (!responsable.equals(other.responsable))
			return false;
		return true;
	}
	
	
	
}
