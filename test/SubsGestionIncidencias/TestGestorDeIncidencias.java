package SubsGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;

import ModeladoDeDatos.Incidencia;

class TestGestorDeIncidencias {
	// fixtures
	GestorDeIncidencias gdi;
	String DNI;
	String nombre;
	String telefono;
	String descripcion;
	String localizacion;
	String tipo;
	Incidencia i;
	Incidencia prueba1;
	Incidencia prueba2;
	Incidencia prueba3;
	Incidencia prueba4;

	@Nested
	@DisplayName("Pruebas de Caja Negra")
	class PruebasCajaNegra {

		@BeforeEach
		void setUp() throws Exception {
			// Inicializamos el gestor
			gdi = new GestorDeIncidencias();

			// Creamos a mano las incidencias que vamos a probar para comprobar que los
			// valores que dan las pruebas son correctas
			prueba1 = new Incidencia("0", "José Taboada", "93218185J", "+34999999999",
					"Uno de los bancos del parque de Vista Alegre se ha roto", "Parque de Vista Alegre",
					"mobilario público");
			prueba2 = new Incidencia("0", "José Taboada", "93218185J", "+34999999999", null, "Parque de Vista Alegre",
					"mobilario público");
		}

		@Test
		@DisplayName("PN1_CP1: Introducción de valores válidos en todos los campos con una descripción válida")
		void testPN1_CP1() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act

			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertEquals(i, prueba1, "Ambas instancias son distintas");
		}

		@Test
		@DisplayName("PN1_CP2: Introducción de valores válidos en todos los campos con una descripción nula")
		void testPN1_CP2() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = null;
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertEquals(i, prueba2, "Ambas instancias son distintas");

		}

		@Test
		@DisplayName("PN1_CP3: Introducción de valores válidos en todos los campos excepto el DNI. Introducimos un DNI de 9 caracteres no válido")
		void testPN1_CP3() {
			// Arrange
			DNI = "93218185A";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP4: Introducción de valores válidos en todos los campos excepto el DNI. Introducimos un DNI de menos de 9 caracteres.")
		void testPN1_CP4() {
			// Arrange
			DNI = "3218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP5: Introducción de valores válidos en todos los campos excepto el DNI. Introducimos un DNI de más de 9 caracteres")
		void testPN1_CP5() {
			// Arrange
			DNI = "193218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP6: Introducción de valores válidos en todos los campos excepto el DNI. Introducimos un DNI nulo")
		void testPN1_CP6() {
			// Arrange
			DNI = null;
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP7: Introducción de valores válidos en todos los campos excepto el nombre. Introducimos un nombre vacío")
		void testPN1_CP7() {
			// Arrange
			DNI = "93218185J";
			nombre = "";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP8: Introducción de valores válidos en todos los campos excepto el nombre. Introducimos un nombre nulo")
		void testPN1_CP8() {
			// Arrange
			DNI = "93218185J";
			nombre = null;
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP9: Introducción de valores válidos en todos los campos excepto el teléfono. Introducimos un teléfono de 12 caracteres con caracteres no numéricos.")
		void testPN1_CP9() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+346P356MK7Y";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP10: Introducción de valores válidos en todos los campos excepto el teléfono. Introducimos un teléfono de menos de 12 caracteres")
		void testPN1_CP10() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+3499999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP11: Introducción de valores válidos en todos los campos excepto el teléfono. Introducimos un teléfono de más de 12 caracteres")
		void testPN1_CP11() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+349999999991";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP12: Introducción de valores válidos en todos los campos excepto el teléfono. Introducimos un teléfono nulo")
		void testPN1_CP12() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = null;
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP13: Introducción de valores válidos en todos los campos excepto la descripción. Introducimos un valor vacío")
		void testPN1_CP13() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "";
			localizacion = "Parque de Vista Alegre";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP14: Introducción de valores válidos en todos los campos excepto la localización. Introducimos un valor vacío.")
		void testPN1_CP14() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "";
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP15: Introducción de valores válidos en todos los campos excepto la localización. Introducimos un valor nulo.")
		void testPN1_CP15() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = null;
			tipo = "mobilario público";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP16: Introducción de valores válidos en todos los campos excepto el tipo. Introducimos un valor vacío")
		void testPN1_CP16() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = "";

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");

		}

		@Test
		@DisplayName("PN1_CP17: Introducción de valores válidos en todos los campos excepto el tipo. Introducimos un valor nulo.")
		void testPN1_CP17() {
			// Arrange
			DNI = "93218185J";
			nombre = "José Taboada";
			telefono = "+34999999999";
			descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
			localizacion = "Parque de Vista Alegre";
			tipo = null;

			// Act
			assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}
	}

	@Nested
	@DisplayName("Pruebas de Caja Blanca")
	class PruebasCajaBlanca {

		@BeforeEach
		void setUp() throws Exception {
			// Inicializamos el gestor
			gdi = new GestorDeIncidencias();

			// Creamos a mano las incidencias que vamos a probar para comprobar que los
			// valores que dan las pruebas son correctas
			prueba3 = new Incidencia("0", "Maria", "12345678Z", "+12345678901", "Banco roto", "Campus Sur",
					"Mobiliario");
			prueba4 = new Incidencia("0", "Maria", "12345678Z", "+12345678901", null, "Campus Sur", "Mobiliario");
		}

		@Test
		@DisplayName("PB1-CL1. {null, null, null, null, null, null}")
		void testPB1_CL1() {
			// Arrange
			DNI = null;
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB1-CL2. {“12345678”, null, null, null, null, null}")
		void testPB1_CL2() {
			// Arrange
			DNI = "12345678";
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB1-CL3. {“1234567AB”, null, null, null, null, null}")
		void testPB1_CL3() {
			// Arrange
			DNI = "1234567AB";
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB1-CL4. {“1234567A8”, null, null, null, null, null}")
		void testPB1_CL4() {
			// Arrange
			DNI = "1234567A8";
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@DisplayName("PB1-CL5. {“12345678A”, null, null, null, null, null}")
		void testPB1_CL5() {
			// Arrange
			DNI = "12345678A";
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL6. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL6. {“12345678z”, null, null, null, null, null}")
		void testPB1_CL6() {
			// Arrange
			DNI = "12345678z";
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL7. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL7. {“12345678Z”, null, null, null, null, null}")
		void testPB1_CL7() {
			// Arrange
			DNI = "12345678Z";
			nombre = null;
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL8. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL8. {“12345678Z”, “”, null, null, null, null}")
		void testPB1_CL8() {
			// Arrange
			DNI = "12345678Z";
			nombre = "";
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL9. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL9. {“12345678Z”, “Jose Santiago Rodriguez Fernandez”, null, null, null, null}")
		void testPB1_CL9() {
			// Arrange
			DNI = "93218185J";
			nombre = "Jose Santiago Rodriguez Fernandez";
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL10. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL10. {“12345678Z”, “Maria”, null, null, null, null}")
		void testPB1_CL10() {
			// Arrange
			DNI = "93218185J";
			nombre = "Maria";
			telefono = null;
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL11. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL11. {“12345678Z”, “Maria”, “1”, null, null, null}")
		void testPB1_CL11() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "1";
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL12. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL12. {“12345678Z”, “Maria”, “123456789123”, null, null, null}")
		void testPB1_CL12() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "123456789123";
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL13. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL13. {“12345678Z”, “Maria”, “+A1234567890”, null, null, null} ")
		void testPB1_CL13() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+A1234567890";
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL14. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL14. {“12345678Z”, “Maria”, “+1A234567890”, null, null, null}")
		void testPB1_CL14() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+1A234567890";
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL15. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL15. {“12345678Z”, “Maria”, “+1234567A890”, null, null, null}")
		void testPB1_CL15() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+1234567A890";
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL16. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL16. {“12345678Z”, “Maria”, “+12345678901”, null, null, null}")
		void testPB1_CL16() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = null;
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL17. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL17. {“12345678Z”, “Maria”, “+12345678901”, “”, null, null}")
		void testPB1_CL17() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "";
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL18. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL18. {“12345678Z”, “Maria”, “+12345678901”, “Lorem ipsum dolor sit amet, consectetur\r\n"
				+ "adipiscing elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad\r\n"
				+ "minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi\r\n"
				+ "consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat\r\n"
				+ "nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia\r\n"
				+ "deserunt mollit anim id est laborum”, null, null}")
		void testPB1_CL18() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Lorem ipsum dolor sit amet, consectetur\\r\\n\"\r\n"
					+ "				+ \"adipiscing elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad\\r\\n\"\r\n"
					+ "				+ \"minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi\\r\\n\"\r\n"
					+ "				+ \"consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat\\r\\n\"\r\n"
					+ "				+ \"nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia\\r\\n\"\r\n"
					+ "				+ \"deserunt mollit anim id est laborum";
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL19. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL19. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, null, null}")
		void testPB1_CL19() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = null;
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL20. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL20. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, “”, null}")
		void testPB1_CL20() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = "";
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL21. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL21. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, “Lorem ipsum dolor sit amet,\r\n"
				+ "consectetur adipiscing elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua”,\r\n"
				+ "null}")
		void testPB1_CL21() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = "Lorem ipsum dolor sit amet,\\r\\n\"\r\n"
					+ "				+ \"consectetur adipiscing elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua";
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL22. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL22. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, “Campus Sur”, null}")
		void testPB1_CL22() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = "Campus Sur";
			tipo = null;

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL23. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL23. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, “Campus Sur”, “”}")
		void testPB1_CL23() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = "Campus Sur";
			tipo = "";

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL24. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL24. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, “Campus Sur”, “Lorem ipsum\r\n"
				+ "dolor sit amet”}")
		void testPB1_CL24() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = "Campus Sur";
			tipo = "Lorem ipsum\r\n" + "dolor sit amet";

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assertNull(i, "Hemos recibido un null");
		}

		@Test
		@Disabled("PB1-CL25. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL25. {“12345678Z”, “Maria”, “+12345678901”, “Banco roto”, “Campus Sur”, “Mobiliario”}")
		void testPB1_CL25() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = "Banco roto";
			localizacion = "Campus Sur";
			tipo = "Mobiliario";

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assumeTrue(i!= null, "La incidencia creada es nula, de forma que no comprobamos si se ha generado bien");
			assertEquals(i, prueba3, "Ambas instancias son distintas");
			
		}

		@Test
		@Disabled("PB1-CL26. Prueba no disponible por error a la hora de realizar el camino")
		@DisplayName("PB1-CL26. {“12345678Z”, “Maria”, “+12345678901”, null, “Campus Sur”, “Mobiliario”}")
		void testPB1_CL26() {
			// Arrange
			DNI = "12345678Z";
			nombre = "Maria";
			telefono = "+12345678901";
			descripcion = null;
			localizacion = "Campus Sur";
			tipo = "Mobiliario";

			// Act
			assertTimeout(Duration.ofSeconds(1), () -> {
				i = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);
			});

			// Assert
			assumeTrue(i!= null, "La incidencia creada es nula, de forma que no comprobamos si se ha generado bien");
				assertEquals(i, prueba4, "Ambas instancias son distintas");
		}
	}
}
