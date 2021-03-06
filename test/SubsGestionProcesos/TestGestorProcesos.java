package SubsGestionProcesos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ModeladoDeDatos.Incidencia;
import ModeladoDeDatos.OT;
import ModeladoDeDatos.Proceso;

@DisplayName("Pruebas del Gestor de Procesos")
public class TestGestorProcesos {
	private Proceso p;
	private GestorDeProcesos gp;

	@BeforeEach
	void setUp() throws Exception {
		this.gp = new GestorDeProcesos();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.gp = null;
	}

	@Nested
	@DisplayName("Pruebas de Caja Negra")
	class PruebasCajaNegra {

		@Test
		@DisplayName("PN2_CP1 - Todos los casos v?lidos")
		public void PN2_CP1() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNotNull(p, "Error en la creaci?n del proceso.");
		}

		@Test
		@DisplayName("PN2_CP2 - Descripci?n nula")
		public void PN2_CP2() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = null;
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNotNull(p, "Error en la creaci?n del proceso.");
		}

		@Test
		@DisplayName("PN2_CP3 - Nombre vac?o")
		public void PN2_CP3() {
			// Arrange
			String nombre = "";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertSame(p, null, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP4 - Nombre nulo")
		public void PN2_CP4() {
			// Arrange
			String nombre = null;
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP5 - Descripci?n vac?a")
		public void PN2_CP5() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP6 - Coste no v?lido, valor fuera de rango")
		public void PN2_CP6() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.0f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP7 - Coste nulo")
		public void PN2_CP7() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = null;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP8 - Estimado no v?lido, valor fuera de rango")
		public void PN2_CP8() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 0;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP9 - Estimado null")
		public void PN2_CP9() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = null;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP10 - Estado vac?o")
		public void PN2_CP10() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP11 - Estado nulo")
		public void PN2_CP11() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = null;
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP12 - Responsable vac?o")
		public void PN2_CP12() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP13 - Responsable nulo")
		public void PN2_CP13() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = null;
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP14 - Servicio vac?o")
		public void PN2_CP14() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP15 - Servicio nulo")
		public void PN2_CP15() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = null;
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP16 - Lista de incidencias vac?a")
		public void PN2_CP16() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP17 - Lista de incidencias nula")
		public void PN2_CP17() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP18 - Lista de incidencias con incidencia nula")
		public void PN2_CP18() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(null);

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertNull(p, "Hemos recibido un null.");
		}
	}

	@Nested
	@DisplayName("Pruebas de Caja Blanca")
	class PruebasCajaBlanca {

		@Test
		@DisplayName("PB2_CL1 - {null, null, null, null, null, null, null, null} ")
		public void PB2_CL1() {
			// Arrange
			String nombre = null;
			String descripcion = null;
			Float coste = null;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertTrue(p == null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL2 - {\"Proceso en uso\", \"\", null, null, null, null, null, null} ")
		public void PB2_CL2() {
			// Arrange
			String nombre = "Proceso en uso";
			String descripcion = "";
			Float coste = null;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL3 - {\"Proceso en uso\", \"\", null, null, null, null, null, null} ")
		public void PB2_CL3() {
			// Arrange
			String nombre = "Proceso en uso";
			String descripcion = "";
			Float coste = null;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			ArrayList<Incidencia> listaAux = new ArrayList<>();
			listaAux.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));
			gp.procesos.add(new Proceso("Proceso en uso", "Uno de los bancos del parque de Vista Alegre est? roto",
					0.01f, 1, "pendiente", "Ana Garc?a", "arreglo", listaAux, new ArrayList<>()));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL4 - {\"Proceso en uso\", \"\", null, null, null, null, null, null} ")
		public void PB2_CL4() {
			// Arrange
			String nombre = "Proceso en uso";
			String descripcion = "";
			Float coste = null;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			ArrayList<Incidencia> listaAux = new ArrayList<>();
			listaAux.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));
			gp.procesos.add(new Proceso("Proceso no en uso", "Uno de los bancos del parque de Vista Alegre est? roto",
					0.01f, 1, "pendiente", "Ana Garc?a", "arreglo", listaAux, new ArrayList<>()));
			gp.procesos.add(new Proceso("Proceso en uso", "Uno de los bancos del parque de Vista Alegre est? roto",
					0.01f, 1, "pendiente", "Ana Garc?a", "arreglo", listaAux, new ArrayList<>()));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL5 - {\"Proceso en uso\", \"\", null, null, null, null, null, null} ")
		public void PB2_CL5() {
			// Arrange
			String nombre = "Proceso en uso";
			String descripcion = "";
			Float coste = null;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			ArrayList<Incidencia> listaAux = new ArrayList<>();
			listaAux.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));
			gp.procesos.add(new Proceso("Proceso no en uso", "Uno de los bancos del parque de Vista Alegre est? roto",
					0.01f, 1, "pendiente", "Ana Garc?a", "arreglo", listaAux, new ArrayList<>()));
			for (int i = 0; i < 9; i++) {
				gp.procesos
						.add(new Proceso("Proceso en uso" + i, "Uno de los bancos del parque de Vista Alegre est? roto",
								0.01f, 1, "pendiente", "Ana Garc?a", "arreglo", listaAux, new ArrayList<>()));
			}

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL6 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", null, null, null, null, null, null} ")
		public void PB2_CL6() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = null;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL7 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, null, null, null, null, null} ")
		public void PB2_CL7() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = null;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL8 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, 1, null, null, null, null} ")
		public void PB2_CL8() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = null;
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL9 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, 1, \"pendiente\", null, null, null} ")
		public void PB2_CL9() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = null;
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL10 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, 1, \"pendiente\", \"Ana Garc?a\", null, null} ")
		public void PB2_CL10() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = null;
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL11 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, \"pendiente\", 1, \"Ana Garc?a\", \"arreglo\", null} ")
		public void PB2_CL11() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL12 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, \"pendiente\", 1, \"Ana Garc?a\", \"arreglo\", listaI} ")
		public void PB2_CL12() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertTrue(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL13 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, \"pendiente\", 1, \"Ana Garc?a\", \"arreglo\", listaI} ")
		public void PB2_CL13() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));
			listaI.add(null);

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB2_CL14 - {\"Arreglo banco\", \"Uno de los bancos del parque de Vista Alegre est? roto\", 0.01, \"pendiente\", 1, \"Ana Garc?a\", \"arreglo\", listaI} ")
		public void PB2_CL14() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est? roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc?a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos? Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p?blico"));
			for (int i = 1; i < 10; i++) {
				listaI.add(null);
			}

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio, listaI);
			});

			// Assert
			assertFalse(p != null, "Hemos recibido un null");
		}
	}
}
