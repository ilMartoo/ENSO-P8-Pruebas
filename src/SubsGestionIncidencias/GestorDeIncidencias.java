package SubsGestionIncidencias;

import java.util.ArrayList;

import ModeladoDeDatos.Incidencia;

public class GestorDeIncidencias implements InterfazGestorIncidencias {

	ArrayList<Incidencia> incidencias;
	private Integer idCount;

	// Constructors
	public GestorDeIncidencias(ArrayList<Incidencia> incidencias) {
		super();
		this.incidencias = incidencias;
		idCount = 0;
	}

	public GestorDeIncidencias() {
		super();
		this.incidencias = new ArrayList<>();
		idCount = 0;
	}

	@Override
	public ArrayList<Incidencia> getIncidencias(String campoFiltro, String valorFiltro) {
		ArrayList<Incidencia> resultado = new ArrayList<Incidencia>();
		// si campoFiltro es null, devuelvo todas las incidencias
		if (campoFiltro == null) {
			return this.incidencias;
		} else {
			switch (campoFiltro) {
			case "id":
				// se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() > 0 && valorFiltro.length() <= 20) {
						// Se recorre la lista
						for (Incidencia i : this.incidencias)
							// Se añade a la lista "resultado" cada elemento que cumpla el filtro
							if (i.getID().equals(valorFiltro))
								resultado.add(i);
						// Se devuelve la lista
						return resultado;
					} else {
						System.out.println("El valor del filtro tiene un valor o formato incorrecto");
						return null;
					}
				}
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;
			case "nombreCiudadano":
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() > 0 && valorFiltro.length() <= 25) {
						// Se recorre la lista
						for (Incidencia i : incidencias)
							// Se aáde a la lista el elemento que cumpla el filtro
							if (i.getNombreCiudadano().equals(valorFiltro))
								resultado.add(i);
						// Se devuelve la lista
						return resultado;
					} else {
						// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
						// usuario y se devuelve un null
						System.out.println("El valor del filtro tiene un valor o formato incorrecto");
						return null;
					}
				} // Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;
			case "DNI":
				int sum = 0, dniCount = 0, mod;
				Character mayus, minus;
				String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() == 9) {
						// contamos cuentos numeros hay y los sumamos
						for (int i = 0; i < valorFiltro.length(); i++) {
							if (Character.isDigit(valorFiltro.charAt(i))) {
								dniCount++;
								sum += Character.getNumericValue(valorFiltro.charAt(i));
							}
						}
						// si el dni tiene 8 números
						if (dniCount == 8) {
							// comprobamos que el caracter final es una letra
							if (Character.isAlphabetic(valorFiltro.charAt(valorFiltro.length() - 1))) {
								// comprobamos que la letra es la que le corresponde
								mod = sum % 23 - 1;
								// obtenemos la letra esperada
								mayus = letras.charAt(mod);
								// pasamos la letra esperada a minuscula
								minus = letras.toLowerCase().charAt(mod);
								// si la letra introducida no es la esperdada en mayuscula
								if (mayus.equals(valorFiltro.charAt(valorFiltro.length() - 1))
										|| minus.equals(valorFiltro.charAt(valorFiltro.length() - 1))) {
									// Se recorre la lista
									for (Incidencia i : incidencias)
										// Se añade a la lista cada elemento que cumpla el filtro
										if (i.getDNI().equals(valorFiltro))
											resultado.add(i);
									// Se devuelve la lista
									return resultado;
								} else {
									//si el valor dni es incorrecto se avisa al usuario y se devuelve null
									System.out.println("El dni tiene un formato incorrecto. La letra no coincide.");
									return null;
								}
							} else {
								//si el valor dni es incorrecto se avisa al usuario y se devuelve null
								System.out.println(
										"El dni tiene un formato incorrecto. Debe contener 8 cifras + 1 letra.");
								return null;
							}
						} else {
							//si el valor dni es incorrecto se avisa al usuario y se devuelve null
							System.out.println("El dni tiene un formato incorrecto. Debe contener 8 cifras + 1 letra.");
							return null;
						}
					} else {
						//si el valor dni es incorrecto se avisa al usuario y se devuelve null
						System.out.println("El dni tiene un formato incorrecto. Debe ser una cadena de 9 caracetres");
						return null;
					}
				}
				// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;

			case "telefono":
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() == 12 && valorFiltro.charAt(0) == '+'
							&& Character.isDigit(valorFiltro.charAt(1)) && Character.isDigit(valorFiltro.charAt(2))
							&& Character.isDigit(valorFiltro.charAt(3)) && Character.isDigit(valorFiltro.charAt(4))
							&& Character.isDigit(valorFiltro.charAt(5)) && Character.isDigit(valorFiltro.charAt(6))
							&& Character.isDigit(valorFiltro.charAt(7)) && Character.isDigit(valorFiltro.charAt(8))
							&& Character.isDigit(valorFiltro.charAt(9)) && Character.isDigit(valorFiltro.charAt(10))
							&& Character.isDigit(valorFiltro.charAt(11))) {
						// Se recorre la lista
						for (Incidencia i : incidencias)
							// Se añade a la lista cada elemento que cumpla el filtro
							if (i.getTelefono().equals(valorFiltro))
								resultado.add(i);
						// Se devuelve la lista
						return resultado;
					}
					// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
					// usuario y se devuelve un null
					System.out.println("El valor del filtro tiene un valor o formato incorrecto");
					return null;
				}
				// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;
			case "descripcion":
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro == null || (valorFiltro.length() > 0 && valorFiltro.length() <= 200)) {
					// Se recorre la lista
					for (Incidencia i : incidencias)
						// Se añade al array cada elemento que cumpla el filtro
						if (i.getDescripcion().equals(valorFiltro))
							resultado.add(i);
					// Se devuelve el contador
					return resultado;
				}
				// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
				// usuario y se devuelve un null
				System.out.println("El valor del filtro tiene un valor o formato incorrecto");
				return null;
			case "localizacion":
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() > 0 && valorFiltro.length() <= 50) {
						// Se recorre la lista
						for (Incidencia i : incidencias)
							// Se añade al array cada elemento que cumpla el filtro
							if (i.getLocalizacion().equals(valorFiltro))
								resultado.add(i);
						// Se devuelve el contador
						return resultado;
					}
					// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
					// usuario y se devuelve un null
					System.out.println("El valor del filtro tiene un valor o formato incorrecto");
					return null;
				}
				// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;
			case "tipo":
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() > 0 && valorFiltro.length() <= 20) {
						// Se recorre la lista
						for (Incidencia i : incidencias)
							// Se añade al array cada elemento que cumpla el filtro
							if (i.getTipo().equals(valorFiltro))
								resultado.add(i);
						// Se devuelve el contador
						return resultado;
					}
					// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
					// usuario y se devuelve un null
					System.out.println("El valor del filtro tiene un valor o formato incorrecto");
					return null;
				}
				// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;
			case "proceso":
				// Se comprueba que el valor del filtro no sea nulo
				if (valorFiltro != null) {
					// Se comprueba que se emplea el formato correcto
					if (valorFiltro.length() > 9) {
						if (valorFiltro.length() <= 50) {
							// Se recorre la lista
							for (Incidencia i : incidencias)
								// Se aáde al array cada elemento que cumpla el filtro
								if (i.getProceso().getNombre().equals(valorFiltro))
									resultado.add(i);
							// Se devuelve el contador
							return resultado;
						}
					}
					// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
					// usuario y se devuelve un null
					System.out.println("El valor del filtro tiene un valor o formato incorrecto");
					return null;
				}
				// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
				System.out.println("El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
				return null;
			default:
				//si no existe el campo se avisa al usuario
				System.out.println("El campo indicado no existe");
				return null;
			}
		}
	}

	@Override
	public Incidencia crearIncidencia(String DNI, String nombre, String tlf, String descripcion, String localizacion,
			String tipo) {
		Incidencia inc;
		int dniCount = 0, sum = 0, mod;
		Character minus, mayus;
		// array de letras para comprobar validez de dni según el módulo
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		String id;
		// compruebo validez de cada campo por orden
		// compruebo valided del dni
		if (DNI != null) {
			if (DNI.length() == 9) {
				// contamos cuentos numeros hay y los sumamos
				for (int i = 0; i < DNI.length(); i++) {
					if (Character.isDigit(DNI.charAt(i))) {
						dniCount++;
						sum += Character.getNumericValue(DNI.charAt(i));
					}
				}
				// si el dni tiene 8 números
				if (dniCount == 8) {
					// comprobamos que el caracter final es una letra
					if (!Character.isAlphabetic(DNI.charAt(DNI.length() - 1))) {
						System.out.println("el dni debe contener 8 números seguidos de una letra");
						return null;
					}
					// comprobamos que la letra es la que le corresponde
					mod = sum % 23 - 1;
					// obtenemos la letra esperada
					mayus = letras.charAt(mod);
					// pasamos la letra esperada a minuscula
					minus = letras.toLowerCase().charAt(mod);
					// si la letra introducida no es la esperdada en mayuscula
					if (!(mayus.equals(DNI.charAt(DNI.length() - 1)))) {
						// si la letra introducida no es la esperada en minuscula
						if (!(minus.equals(DNI.charAt(DNI.length() - 1)))) {
							// informamos al usuario y enviamos null
							System.out.println("El dni no tiene un formato válido. Letra no coincide.");
							return null;
						}
					}
				} else {
					//si el valor dni es incorrecto se avisa al usuario y se devuelve null
					System.out.println("El dni tiene un formato incorrecto. Debe contener 8 cifras + 1 letra.");
					return null;
				}
			} else {
				//si el valor dni es incorrecto se avisa al usuario y se devuelve null
				System.out.println("El dni tiene un formato incorrecto. Debe contener 9 carácteres.");
				return null;
			}
		} else {
			//si el valor dni es incorrecto se avisa al usuario y se devuelve null
			System.out.println("El dni es null");
			return null;
		}

		// compruebo validez del nombre
		if (nombre == null) {
			System.out.println("El nombre es null");
			return null;
			// si tiene longitud menos que 0
		} else if (!(nombre.length() > 0)) {
			System.out.println("El campo nombre está vacío");
			return null;
		}
		// si tiene longitud mayor que 25
		else if (!(nombre.length() <= 25)) {
			System.out.println("El campo nombre tiene formato incorrecto");
			return null;
		}

		// compruebo validez del telefono
		if (tlf == null) {
			System.out.println("El telefono es null");
			return null;
		} else if (tlf.length() != 12) {
			System.out.println("El telefono no tiene un formato válido");
			return null;
		} else {
			// compruebo que el formato es correcto +012345678912
			if (tlf.charAt(0) != '+') {
				System.out.println("El telefono no tiene un formato válido");
				return null;
			}
			// compruebo que todos son digitos
			for (int i = 1; i < 12; i++) {
				// si no es un dígito, dvuelvo null e informo al usuario
				if (!Character.isDigit(tlf.charAt(i))) {
					System.out.println("El telefono debe contener 12 dígitos");
					return null;
				}
			}
		}
		// compruebo la descripcion
		if (descripcion == null || (descripcion.length() > 0 && descripcion.length() <= 200)) {
			// compruebo la localizacion
			if (localizacion != null) {
				if (localizacion.length() > 0) {
					if (localizacion.length() <= 50) {
						// comrpuebo el tipo
						if (tipo != null) {
							if (tipo.length() > 0) {
								if (tipo.length() <= 20) {
									// si todos los campos son válidos
									// se instancia la Incidencia
									inc = new Incidencia(idCount.toString(), nombre, DNI, tlf, descripcion,
											localizacion, tipo);
									// se incrementa una unidad, de forma que el id se genera secuencialmente
									idCount++;
									// se guarda la incidencia en el array
									incidencias.add(inc);
									// si todos los campos son válidos se devuelve la instancia de la incidencia
									return inc;
								}
							}
						}
						//si el valor tipo es incorrecto se avisa al usuario y se devuelve null
						System.out.println("El valor tipo tiene un formato no valido");
						return null;
					}
				}
			}
			//si el valor localización es incorrecto se avisa al usuario y se devuelve null
			System.out.println("El valor localizacion tiene un formato no valido");
			return null;
		}
		//si el valor descripcion es incorrecto se avisa al usuario y se devuelve null
		System.out.println("El valor descripcion tiene un formato no valido");
		return null;
	}

	@Override
	// solo se pueden actualizar los campos correspondientes a localizacion,
	// descripcion y tipo
	public void actualizarIncidencia(String ID, String campo, String valor) {
		Incidencia inc;
		// comprobamos ID
		if (ID != null) {
			// Se comprueba que se emplea el formato correcto
			if (ID.length() > 0 && ID.length() <= 20) {
				// Se recorre la lista
				for (Incidencia i : incidencias) {
					// la incidencia que coincide con el ID se guarda
					if (i.getID().equals(ID)) {
						inc = i;

						// si el campo a cambiar es null
						if (campo == null) {
							// aviso al usuario
							System.out.println("El campo especificado es null, no se puede realizar esta acción");
							return;
						} else {
							// dependiendo del campo introducido
							switch (campo) {
							case "descripcion":
								// Si tiene valor nulo
								if (valor == null) {
									// Se modifica la incidencia
									inc.setDescripcion(valor);
									return;
									// O si emplea el forrmato correcto
								} else if (valor.length() > 0 && valor.length() <= 200) {
									// Se modifica la incidencia
									inc.setDescripcion(valor);
									return;
								}
								// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
								// usuario
								System.out.println("El valor del filtro tiene un valor o formato incorrecto");
								break;
							case "localizacion":
								// Se comprueba que el valor del filtro no sea nulo
								if (valor != null) {
									// Se comprueba que se emplea el formato correcto
									if (valor.length() > 0 && valor.length() <= 50) {
										// Se recorre la lista
										// Se modifica la incidencia
										inc.setLocalizacion(valor);
										return;
									}
									// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
									// usuario y se devuelve un null
									System.out.println("El valor del filtro tiene un valor o formato incorrecto");

								}
								// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
								System.out.println(
										"El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
								break;
							case "tipo":
								// Se comprueba que el valor del filtro no sea nulo
								if (valor != null) {
									// Se comprueba que se emplea el formato correcto
									if (valor.length() > 0 && valor.length() <= 20) {
										// Se modifica la incidencia
										inc.setTipo(valor);
										return;
									}
									// Si el valor del filtro tiene un valor o formato incorrecto, se informa al
									// usuario y se devuelve un null
									System.out.println("El valor del filtro tiene un valor o formato incorrecto");
								}
								// Si el valor del filtro es nulo, se informa al usuario y se devuelve un null
								System.out.println(
										"El valor del filtro no puede ser nulo si se ha indicado un campo de filtro");
								break;
							case "id":
							case "nombreCiudadano":
							case "dni":
							case "telefono":
								System.out.println("El campo especificado no se puede modificar");
								break;

							default:
								//si el campo no existe, se avisa al usuario
								System.out.println("El campo indicado no existe");
							}
						}

					}
				}
				// si termina el bucle for sin encuontrar la incidencia e avisa al usuario
				System.out.println("No existe ninguna incidencia con ese ID");
				return;

			}
			//Si el id tiene un formato incorrecto se avisa al usuario
			System.out.println("El valor del ID tiene un valor o formato incorrecto");
		} else {
			// si el id es nulll se avisa al usuario
			System.out.println("El ID es null");
		}

	}

	@Override
	public void eliminarIncidencia(String ID) {
		// se comprueba que el valor del filtro no sea nulo
		if (ID != null) {
			// Se comprueba que se emplea el formato correcto
			if (ID.length() > 0 && ID.length() <= 20) {
				// recorremos array de incidencias
				for (Incidencia i : incidencias) {
					// si el ID coincide con el buscado
					if (ID.equals(i.getID())) {
						// se borra del array
						incidencias.remove(i);
						// finalizamos
						return;
					}
				}
				// Avisamos al usuario de que la incidencia no existe
				System.out.println("No existe la incidencia");
			} else {
				// Avisamos al usuario de que el ID no tiene un formato valido
				System.out.println("El ID tiene un formato no valido");
			}

		} else {
			// Avisamos al usuario de que el ID no tiene un formato valido
			System.out.println("El ID tiene un formato no valido");
		}

	}
}
