import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

// --== CS400 File Header Information ==--
// Name: Guilhem Ane
// Email: gane@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: Since we didn't implement the Generation selection, my 
//      generation selection test is empty. 
//      To compensate, I added another test named `TestNameNotFound()`

public class FrontEndDeveloperTests {

    Frontend frontend;

    /**
     * Initializes the frontend instance before each test
     */
    @BeforeEach
    void InitializeFrontend(){
        frontend = new Frontend(new Backend("updatedPokemon.csv"));
    }

    /**
     * Tests that the frontend exits correctly when needed
     */
    @Test
    void TestExit(){
        frontend.processInput("x");
        assertEquals(false, frontend.isRunning());
    }

    /**
     * Tests that the name selection works as expected
     */
    @Test
    void TestNameSelection(){
        frontend.processInput("t");
        frontend.processInput("1");
        frontend.processInput("x");
        frontend.processInput("n");
        frontend.processInput("Vulpix");
        String output = frontend.getCurrentOutputText();
        boolean isVulpixFound = output.contains("Vulpix #37");
        assertEquals(isVulpixFound, true);
    }

    /**
        Since our team didn't get to adding the generation selection part, 
        I added an extra test (TestNameNotFound) to make up for the lack 
        of test here.
        I left this test method empty here however, since I didn't know if
        I was allowed to remove or change my test signatures.
     */
    @Test
    void TestGenSelection(){
    }

    /**
     * Tests that the type selection works as expected
     */
    @Test
    void TestTypeSelection(){
        frontend.processInput("t");
        frontend.processInput("0");
        String type = frontend.getBackend().getTypes().get(0);
        assertEquals(type, "Normal");
    }
    
    /**
     * Tests that the name selection returns a `not found` text when 
     * a missing pokemon is entered
     */
    @Test
    void TestNameNotFound(){
        frontend.processInput("n");
        frontend.processInput("asdf");
        String output = frontend.getCurrentOutputText();
        String targetMessage = "No pokemon with the name asdf found.";
        boolean messageFound = output.contains(targetMessage);
        assertEquals(true, messageFound);
    }
}

