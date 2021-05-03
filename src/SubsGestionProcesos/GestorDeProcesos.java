package SubsGestionProcesos;

import java.util.ArrayList;
import java.util.List;

import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;

public class GestorDeProcesos implements InterfazGestorProcesos {

	ArrayList<Proceso> procesos;
	
	//Constructors
	public GestorDeProcesos() {
		super();
		this.procesos = new ArrayList<>();
	}
	
	public GestorDeProcesos(ArrayList<Proceso> procesos) {
		super();
		this.procesos = procesos;
	}


	@Override
	public ArrayList<Proceso> getProcesos(String campoFiltro, String valorFiltro) {

		ArrayList<Proceso> toret =new ArrayList<>();
		
		if (campoFiltro == null) {
			return this.procesos;
		}
		
		if (valorFiltro == null && !campoFiltro.equals("descripcion")) {
			System.out.println("El valor introducido no es válido");
			return null;
		}

		if (valorFiltro.length() <= 0) {
			System.out.println("El valor introducido no es valido.");
			return null;
		}


		if (this.procesos.isEmpty()) {
			System.out.println("No hay procesos almacenados todavía.");
			return null;
		}

		switch (campoFiltro) {
			case "nombre":
				if (valorFiltro.length() <= 50) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getNombre().equals(valorFiltro)) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				} else {
					System.out.println("El nombre introducido sobrepasa los 50 caracteres.");
				}
				break;
			case "descripcion":
				if (valorFiltro.length() <= 200) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getDescripcion().equals(valorFiltro)) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				} else {
					System.out.println("La descripcion introducida sobrepasa los 200 caracteres.");
				}
				break;
			case "coste":
				if(Float.parseFloat(valorFiltro) > 0) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getCoste().equals(Float.parseFloat(valorFiltro))) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				}
				else {
					System.out.println("El coste introducido es menor o igual que 0.");
				}
				break;
			case "estimado":
				if(Integer.parseInt(valorFiltro) > 0) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getEstimado().equals(Integer.parseInt(valorFiltro))) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				}
				else {
					System.out.println("El estimado introducido es menor o igual que 0.");
				}
				break;
			case "estado":
				if (valorFiltro.length() <= 20) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getEstado().equals(valorFiltro)) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				} else {
					System.out.println("El estado introducido sobrepasa los 20 caracteres.");
				}
				break;
			case "responsable":
				if (valorFiltro.length() <= 50) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getResponsable().equals(valorFiltro)) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				} else {
					System.out.println("El estado introducido sobrepasa los 50 caracteres.");
				}
				break;
			case "servicio":
				if (valorFiltro.length() <= 50) {
					for(int i=0;i<this.procesos.size();i++) {
						if(this.procesos.get(i).getServicio().equals(valorFiltro)) {
							toret.add(this.procesos.get(i));
						}
					}
					return toret;
				} else {
					System.out.println("El estado introducido sobrepasa los 50 caracteres.");
				}
				break;
			default:
				System.out.println("El campo indicado no existe");
				return null;
		}
		return null;
	}

	@Override
    public Proceso crearProceso(String nombre, String descripcion, Float coste, Integer estimado, String estado,
            String responsable, String servicio, List<Incidencia> incidencia) {
        if(nombre!=null && nombre.length()>0 && nombre.length()<=50) {
            Boolean enUso=false;
            for(int i=0;i<this.procesos.size();i++) {
                if(this.procesos.get(i).getNombre().equals(nombre)) {
                    enUso=true;
                }
            }
            if(!enUso) {
                if(descripcion.length()>0 && descripcion.length()<=200) {
                    if(coste!=null && coste>0) {
                        if(estimado!=null && estimado>0) {
                            if(estado!=null && estado.length()>0 && estado.length()<=20) {
                                if(responsable!=null && responsable.length()>0 && responsable.length()<=50) {
                                    if(servicio!=null && servicio.length()>0 && servicio.length()<=50) {
                                        if(incidencia!=null && !incidencia.isEmpty()) {
                                            boolean hayNull=false;
                                            for(int i=0;i<incidencia.size();i++) {
                                                if(incidencia.get(i)==null) {
                                                    hayNull=true;
                                                }
                                            }
                                            if(!hayNull) {
                                                Proceso toret=new Proceso(nombre, descripcion, coste, estimado, estado, responsable,
                                                        servicio, new ArrayList<Incidencia>(incidencia));
                                                for(int i=0;i<incidencia.size();i++) {
                                                    incidencia.get(i).setProceso(toret);
                                                }
                                                this.procesos.add(toret);
                                                return toret;
                                            }
                                            System.out.println("El valor de 'incidencia' es incorrecto (contiene nulos)");
                                            return null;
                                        }
                                        System.out.println("El valor de 'incidencia' es incorrecto (nulo o vacio)");
                                        return null;
                                    }
                                    System.out.println("El valor de 'servicio' es incorrecto (nulo, vacio o demasiado largo)");
                                    return null;
                                }
                                System.out.println("El valor de 'responsable' es incorrecto (nulo, vacio o demasiado largo)");
                                return null;
                            }
                            System.out.println("El valor de 'estado' es incorrecto (nulo, vacio o demasiado largo)");
                            return null;
                        }
                        System.out.println("El valor de 'estimado' es incorrecto (nulo o menor que 0)");
                        return null;
                    }
                    System.out.println("El valor de 'coste' es incorrecto (nulo o menor que 0)");
                    return null;
                }
                System.out.println("El valor de 'descripcion' es incorrecto (demasiado largo)");
                return null;
            }
            System.out.println("El valor de 'nombre' ya está en uso");
            return null;
        }
        System.out.println("El valor de 'nombre' es incorrecto (nulo, vacio o demasiado largo)");
        return null;
    }

	@Override
	public void actualizarProceso(String nombre, String campo, String valor) {
		
		Proceso proceso=null;
		
		if(nombre==null || nombre.length()<=0 || nombre.length()>50) {
			System.out.println("El nombre introducido es incorrecto");
			return;
		}
		
		for (int i = 0; i < this.procesos.size(); i++) {
			if (this.procesos.get(i).getNombre().equals(nombre)) {
				proceso = this.procesos.get(i);
			}
		}
		if (proceso == null) {
			System.out.println("No existe ningún proceso con el nombre proporcionado.");
			return;
		}

		if (campo == null || campo.length() <= 0) {
			System.out.println("El campo a cambiar es nulo o vacio");
			return;
		}

		if (valor == null && !campo.equals("descripcion")) {
			System.out.println("El valor introducido no es válido");
			return;
		}

		if (valor.length()<=0) {
			System.out.println("El valor introducido no es válido");
			return;
		}
		
		switch (campo) {
			case "descripcion":
				if (valor.length() <= 200) {
					proceso.setDescripcion(valor);
				} else {
					System.out.println("La descripcion introducida sobrepasa los 200 caracteres.");
				}
				break;
			case "coste":
				if(Float.parseFloat(valor) > 0) {
					proceso.setCoste(Float.parseFloat(valor));
				}
				else {
					System.out.println("El coste introducido es menor o igual que 0.");
				}
				break;
			case "estimado":
				if(Integer.parseInt(valor) > 0) {
					proceso.setEstimado(Integer.parseInt(valor));
				}
				else {
					System.out.println("El estimado introducido es menor o igual que 0.");
				}
				break;
			case "estado":
				if (valor.length() <= 20) {
					proceso.setDescripcion(valor);
				} else {
					System.out.println("El estado introducido sobrepasa los 20 caracteres.");
				}
				break;
			case "responsable":
				if (valor.length() <= 50) {
					proceso.setResponsable(valor);
				} else {
					System.out.println("El estado introducido sobrepasa los 50 caracteres.");
				}
				break;
			case "servicio":
				if (valor.length() <= 50) {
					proceso.setServicio(valor);
				} else {
					System.out.println("El estado introducido sobrepasa los 50 caracteres.");
				}
				break;
			case "nombre":
				System.out.println("El nombre de un proceso no puede ser cambiado una vez creado");
				break;
			default:
				System.out.println("No existe ningún campo con ese nombre");
				break;
		}
	}

	@Override
    public void eliminarProceso(String nombre) {
        if(nombre!=null && nombre.length()>0 && nombre.length()<=20) {
            Boolean borrado=false;
            for(int i=0;i<this.procesos.size();i++) {
                if(this.procesos.get(i).getNombre().equals(nombre)) {
                    this.procesos.remove(i);
                    borrado=true;
                    break;
                }
            }
            if(borrado) {
                System.out.println("Se ha borrado el proceso");
                return;
            }
            else {
                System.out.println("El proceso con el nombre indicado no existe");
                return;
            }
        }
        System.out.println("El nombre introducido no es correcto");
        return;
    }
	
    @Override
    public void vincularIncidencia(String nombre, Incidencia incidencia) {
        if(incidencia!=null) {
            if(nombre!=null && nombre.length()>0 && nombre.length()<=20) {
                Boolean vinculado=false;
                for(int i=0;i<this.procesos.size();i++) {
                    if(this.procesos.get(i).getNombre().equals(nombre)) {
                        this.procesos.get(i).getIncidencias().add(incidencia);
                        incidencia.setProceso(this.procesos.get(i));
                        vinculado=true;
                        break;
                    }
                }
                if(vinculado) {
                    System.out.println("Se ha vinculado la incidencia al proceso");
                    return;
                }
                else {
                    System.out.println("El proceso con el nombre indicado no existe");
                    return;
                }
            }
            System.out.println("El nombre introducido no es correcto");
            return;
        }
        System.out.println("La incidencia seleccionada no es válida");
    }
}
	

