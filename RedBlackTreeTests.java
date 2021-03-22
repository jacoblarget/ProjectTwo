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
 * Red-Black Tree Tests class using JUnit tests
 * will test four input cases of red-property violations (and build on them to introduce recursion)
 * (1) Left Child, Left Parent
 * (2) Right Child, Left Parent
 * (3) Left Child, Right Parent
 * (4) Right Child, Right Parent
 * this should test all rotations and recolors for all orientations
 */
public class RedBlackTreeTests {
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
}
