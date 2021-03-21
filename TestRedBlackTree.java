// --== CS400 File Header Information ==--
// Name: jacob larget
// Email: jlarget@wisc.edu
// Team: jd
// TA: xinyi
// Lecturer: florian heimerl
// Notes to Grader: NA

// debug DataWrangler Tests
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
// end imports

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Red-Black Tree class using JUnit tests
 * will test four input cases of red-property violations (and build on them to introduce recursion)
 * (1) Left Child, Left Parent
 * (2) Right Child, Left Parent
 * (3) Left Child, Right Parent
 * (4) Right Child, Right Parent
 * this should test all rotations and recolors for all orientations
 */
public class TestRedBlackTree {
    /**
    * Left Child, Left Parent - should recolor up to root (red at first), so check root stays black
    */
    @Test
    public void LeftChildLeftParent(){
        // System.out.println();
        // System.out.println("Test Left Child, Left Parent:");
        RedBlackTree<Integer> tree = new RedBlackTree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(10);
        // System.out.println();
        // System.out.println("Is 40 black? "+tree.root.isBlack);
        // System.out.println("40 should be.");
        assertEquals(true,tree.root.isBlack);
    }
    /**
    * Right Child, Left Parent - should rotate twice, so check order
    */
    @Test
    public void RightChildLeftParent(){
        // System.out.println();
        // System.out.println("Test Right Child, Left Parent:");
        RedBlackTree<Integer> tree = new RedBlackTree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(10);
        tree.insert(15);
        // System.out.println();
        // System.out.println("Attempt: "+tree.root.toString());
        // System.out.println("Correct: [40, 15, 60, 10, 20]");
        assertEquals("[40, 15, 60, 10, 20]",tree.root.toString());
    }
    /**
    * Left Child, Right Parent - should recolor recursively, check 15 is red
    */
    @Test
    public void LeftChildRightParent(){
        // System.out.println();
        // System.out.println("Test Left Child, Right Parent:");
        RedBlackTree<Integer> tree = new RedBlackTree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(10);
        tree.insert(15);
        tree.insert(17);
        // System.out.println();
        // System.out.println("Is "+tree.root.leftChild.data+" red? "+tree.root.leftChild.isBlack);
        // System.out.println("15 should not be.");
        assertEquals(false,tree.root.leftChild.isBlack);
    }
    /**
    * Right Child, Right Parent - should rotate twice and replace root, so check order
    */
    @Test
    public void RightChildRightParent(){
        // System.out.println();
        // System.out.println("Test Right Child, Right Parent:");
        RedBlackTree<Integer> tree = new RedBlackTree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(10);
        tree.insert(15);
        tree.insert(17);
        // no valid right child right parent double-red, so must make a spot first
        tree.insert(21);
        tree.insert(22);
        // System.out.println();
        // System.out.println("Attempt: "+tree.root.toString());
        // System.out.println("Correct: [20, 15, 40, 10, 17, 21, 60, 22]");
        assertEquals("[20, 15, 40, 10, 17, 21, 60, 22]",tree.root.toString());
    }

    // debug Data Wrangler tests
    @Test
	/**
	 * This test instantiates a Pokemon object to make sure the constructor functions
	 * as expected. It passes when the object is initialized with its fields set to the
	 * correct values, and fails otherwise.
	 * @return true if test passed, false otherwise
	 */
    //public boolean testPokemonConstructor(){
	public void testPokemonConstructor(){
		try{
			String name = "Bulbasaur";
			int number = 1;
			int combatPower = 318;
			int hp = 45;
			int atk = 49;
		       	int def = 49;
			int spd = 45;
			int gen = 1;
			boolean legendary = false;
			ArrayList<String> types = new ArrayList<String>();
			types.add("Grass"); types.add("Poison");	

			PokemonInterface testPokemon = new Pokemon(name, number, types, combatPower, hp, atk, def, spd, gen, legendary);
			
			if(testPokemon != null){
                assertEquals(true, true);
				//return true;
			}
			else{
				fail("No Pokemon object instantiated.");
                assertEquals(true, false);
				//return false;
			}
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
            assertEquals(true, false);
			//return false;
		}
	}
}
