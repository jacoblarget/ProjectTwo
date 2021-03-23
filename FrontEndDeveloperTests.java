import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class FrontEndDeveloperTests {

    Frontend frontend;

    @BeforeEach
    void InitializeFrontend(){
        frontend = new Frontend(new Backend("updatedPokemon.csv"));
    }

    @Test
    void TestExit(){
        frontend.processInput("x");
        assertEquals(frontend.isRunning(), false);
    }

    @Test
    void TestNameSelection(){
        frontend.processInput("n");
        assertEquals(frontend.isRunning(), false);
    }

    @Test
    void TestGenSelection(){
	fail("Gen selection not implemented");
    }

    @Test
    void TestTypeSelection(){
	fail("Type selection not implemented");
    }
    
    @Test
    void TestPowerFilter(){
	fail("Filtering by power not implemented");
    }
}

