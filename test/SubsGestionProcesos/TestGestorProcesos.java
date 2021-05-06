package SubsGestionProcesos;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ModeladoDeDatos.Incidencia;
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
		@DisplayName("PN2_CP1 - Todos los casos v�lidos")
		public void PN2_CP1() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNotNull(p, "Error en la creaci�n del proceso.");
		}

		@Test
		@DisplayName("PN2_CP2 - Descripci�n nula")
		public void PN2_CP2() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = null;
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNotNull(p, "Error en la creaci�n del proceso.");
		}

		@Test
		@DisplayName("PN2_CP3 - Nombre vac�o")
		public void PN2_CP3() {
			// Arrange
			String nombre = "";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertSame(p, null, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP4 - Nombre nulo")
		public void PN2_CP4() {
			// Arrange
			String nombre = null;
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP5 - Descripci�n vac�a")
		public void PN2_CP5() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP6 - Coste no v�lido, valor fuera de rango")
		public void PN2_CP6() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.0f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP7 - Coste nulo")
		public void PN2_CP7() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = null;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP8 - Estimado no v�lido, valor fuera de rango")
		public void PN2_CP8() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 0;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP9 - Estimado null")
		public void PN2_CP9() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = null;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP10 - Estado vac�o")
		public void PN2_CP10() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP11 - Estado nulo")
		public void PN2_CP11() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = null;
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP12 - Responsable vac�o")
		public void PN2_CP12() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP13 - Responsable nulo")
		public void PN2_CP13() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = null;
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP14 - Servicio vac�o")
		public void PN2_CP14() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP15 - Servicio nulo")
		public void PN2_CP15() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = null;
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(new Incidencia("0", "Jos� Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario p�blico"));

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP16 - Lista de incidencias vac�a")
		public void PN2_CP16() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP17 - Lista de incidencias nula")
		public void PN2_CP17() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}

		@Test
		@DisplayName("PN2_CP18 - Lista de incidencias con incidencia nula")
		public void PN2_CP18() {
			// Arrange
			String nombre = "Arreglo banco";
			String descripcion = "Uno de los bancos del parque de Vista Alegre est� roto";
			Float coste = 0.01f;
			Integer estimado = 1;
			String estado = "pendiente";
			String responsable = "Ana Garc�a";
			String servicio = "arreglo";
			List<Incidencia> listaI = new ArrayList<>();
			listaI.add(null);

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				p = gp.crearProceso(nombre, descripcion, coste, estimado, estado, responsable, servicio,
						listaI);
			});
			
			// Assert
			assertNull(p, "Hemos recibido un null.");
		}
	}
}
