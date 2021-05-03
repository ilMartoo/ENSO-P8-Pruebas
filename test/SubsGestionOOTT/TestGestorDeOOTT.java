package SubsGestionOOTT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class TestGestorDeOOTT {

	@BeforeAll
	void setUpAll() throws Exception {
	}
	
	@BeforeEach
	void setUpEach() throws Exception {
		
	}

	@AfterEach
	void tearDownEach() throws Exception {
	}

	@AfterAll
	void tearDownAll() throws Exception {
	}


	@ParameterizedTest
	@CsvFileSource(resources = "/crearOT.csv", numLinesToSkip = 1)
	@DisplayName("Test para probar la creacion de ordenes de trabajo")
	void testCrearOT() {
		
	}

}
