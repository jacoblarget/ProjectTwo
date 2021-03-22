import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        frontend.processInput("");
        assertEquals(frontend.getCurrentOutputText(), "No pokemon with the name  found.");
    }

    @Test
    void TestGenSelection(){
    }

    @Test
    void TestTypeSelection(){
        frontend.processInput("t");
        frontend.processInput("0");
        String type = frontend.getBackend().getTypes().get(0);
        assertEquals(type, "NORMAL");
    }
    
    @Test
    void TestPowerFilter(){
	fail("Filtering by power not implemented");
    }
}

