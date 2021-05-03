package SubsGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ModeladoDeDatos.Incidencia;

class TestGestorDeIncidencias {
	// fixtures
	GestorDeIncidencias gdi;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		gdi = new GestorDeIncidencias();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Comprobacion creacion correcta de incidencias")
	void testCrearIncidencia() {
		// PN1_CP1
		// Arrange
		String DNI = "93218185J";
		String nombre = "José Taboada";
		String telefono = "+34999999999";
		String descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		String localizacion = "Parque de Vista Alegre";
		String tipo = "mobilario público";

		// Act
		Incidencia i1 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP2
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = null;
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i2 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP3
		// Arrange
		DNI = "93218185A";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i3 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP4
		// Arrange
		DNI = "3218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i4 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP5
		// Arrange
		DNI = "193218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i5 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP6
		// Arrange
		DNI = null;
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i6 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP7
		// Arrange
		DNI = "93218185J";
		nombre = "";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i7 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP8
		// Arrange
		DNI = "93218185J";
		nombre = null;
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i8 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP9
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+346P356MK7Y";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i9 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP10
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+3499999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i10 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP11
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+349999999991";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i11 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP12
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = null;
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i12 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP13
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "";
		localizacion = "Parque de Vista Alegre";
		tipo = "mobilario público";

		// Act
		Incidencia i13 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_C14
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "";
		tipo = "mobilario público";

		// Act
		Incidencia i14 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP15
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = null;
		tipo = "mobilario público";

		// Act
		Incidencia i15 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP16
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = "";

		// Act
		Incidencia i16 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		

		// PN1_CP17
		// Arrange
		DNI = "93218185J";
		nombre = "José Taboada";
		telefono = "+34999999999";
		descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
		localizacion = "Parque de Vista Alegre";
		tipo = null;

		// Act
		Incidencia i17 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

		// Assert
		
		
		assertAll(
				() -> {assertNull(i1, "Hemos recibido un null");},
				() -> {assertNotEquals(i1, i2, "Son distintos");},
				() -> {assertNull(i3, "Hemos recibido un null");},
				() -> {assertNull(i4, "Hemos recibido un null");},
				() -> {assertNull(i5, "Hemos recibido un null");},
				() -> {assertNull(i6, "Hemos recibido un null");},
				() -> {assertNull(i7, "Hemos recibido un null");},
				() -> {assertNull(i8, "Hemos recibido un null");},
				() -> {assertNull(i9, "Hemos recibido un null");},
				() -> {assertNull(i10, "Hemos recibido un null");},
				() -> {assertNull(i11, "Hemos recibido un null");},
				() -> {assertNull(i12, "Hemos recibido un null");},
				() -> {assertNull(i13, "Hemos recibido un null");},
				() -> {assertNull(i14, "Hemos recibido un null");},
				() -> {assertNull(i15, "Hemos recibido un null");},
				() -> {assertNull(i16, "Hemos recibido un null");},
				() -> {assertNull(i17, "Hemos recibido un null");}
				);
	}
	
	@Test
	@DisplayName("Comprobacion tiempos de creacion de incidencias")
	void Tiempos() {
		// PN1_CP1
				// Arrange
				String DNI = "93218185J";
				String nombre = "José Taboada";
				String telefono = "+34999999999";
				String descripcion = "Uno de los bancos del parque de Vista Alegre se ha roto";
				String localizacion = "Parque de Vista Alegre";
				String tipo = "mobilario público";

				// Act
				Incidencia i1 = gdi.crearIncidencia(DNI, nombre, telefono, descripcion, localizacion, tipo);

				// Assert
				assertTimeout(Duration.ofSeconds(1), ()->{assertNull(i1, "Hemos recibido un null");});
	}

}
