/**
 * The beginning of a unit test for MineSweeper.  
 */
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class MineSweeperTest {

  @Test
  public void testGetAdjacentMinesWithAGivenTwodArrayOfBooleans() {

    boolean[][] b1 =

    { { false, false, false, false, false },
      { false, false, true,  true,  false },
      { false, false, false, true,  false }, };

    // Use the non-random constructor when testing to avoid random mine placement.
    MineSweeper ms = new MineSweeper(b1);

    // Check adjacent mines around every possible GameSquare
    assertEquals(0, ms.getAdjacentMines(0, 0));
    assertEquals(1, ms.getAdjacentMines(0, 1));
    assertEquals(2, ms.getAdjacentMines(0, 2));
    assertEquals(2, ms.getAdjacentMines(0, 3));
    assertEquals(1, ms.getAdjacentMines(0, 4));

    assertEquals(0, ms.getAdjacentMines(1, 0));
    assertEquals(1, ms.getAdjacentMines(1, 1));
    assertEquals(2, ms.getAdjacentMines(1, 2)); // works even if it is a mine
    assertEquals(2, ms.getAdjacentMines(1, 3));
    assertEquals(2, ms.getAdjacentMines(1, 4));

    assertEquals(0, ms.getAdjacentMines(2, 0));
    assertEquals(1, ms.getAdjacentMines(2, 1));
    assertEquals(3, ms.getAdjacentMines(2, 2));
    assertEquals(2, ms.getAdjacentMines(2, 3));
    assertEquals(2, ms.getAdjacentMines(2, 4));
    
    System.out.print(ms.toString());
    System.out.println();
  }
  
  @Test
  public void testGetters() {
	  boolean[][] sqr = { { true, false, true, true  },
			              {false, false, false, true },
			              {false, false, false, false},
			              {false, false, false, true }, };
	  
	  MineSweeper ms = new MineSweeper(sqr);
	  
	  assertEquals(0, ms.getAdjacentMines(0, 0));
	  assertEquals(2, ms.getAdjacentMines(0, 1));
	  assertEquals(2, ms.getAdjacentMines(0, 2));
	  assertEquals(2, ms.getAdjacentMines(0, 3));
	  
	  assertEquals(1, ms.getAdjacentMines(1, 0));
	  assertEquals(2, ms.getAdjacentMines(1, 1));
	  assertEquals(3, ms.getAdjacentMines(1, 2));
	  assertEquals(2, ms.getAdjacentMines(1, 3));
	  
	  assertEquals(0, ms.getAdjacentMines(2, 0));
	  assertEquals(0, ms.getAdjacentMines(2, 1));
	  assertEquals(2, ms.getAdjacentMines(2, 2));
	  assertEquals(2, ms.getAdjacentMines(2, 3));
	  
	  assertEquals(0, ms.getAdjacentMines(3, 0));
	  assertEquals(0, ms.getAdjacentMines(3, 1));
	  assertEquals(1, ms.getAdjacentMines(3, 2));
	  assertEquals(0, ms.getAdjacentMines(3, 3));
	  
	  assertEquals(5, ms.getTotalMineCount());
	  
	  assertTrue(ms.isMine(0, 0));
	  assertFalse(ms.isMine(0, 1));
	  assertTrue(ms.isMine(0, 2));
	  assertTrue(ms.isMine(0, 3));
	  
	  assertFalse(ms.isMine(1, 0));
	  assertFalse(ms.isMine(1, 1));
	  assertFalse(ms.isMine(1, 2));
	  assertTrue(ms.isMine(1, 3));
	  
	  assertFalse(ms.isMine(2, 0));
	  assertFalse(ms.isMine(2, 1));
	  assertFalse(ms.isMine(2, 2));
	  assertFalse(ms.isMine(2, 3));
	  
	  assertFalse(ms.isMine(3, 0));
	  assertFalse(ms.isMine(3, 1));
	  assertFalse(ms.isMine(3, 2));
	  assertTrue(ms.isMine(3, 3));
	  
	  for (int i = 0; i < sqr.length; i++){
		  for (int n = 0; n < sqr[0].length; n++){
			  assertFalse(ms.isVisible(i, n));
			  assertFalse(ms.isFlagged(i, n));
		  }
	  }
	  
	  assertFalse(ms.lost());
	  assertFalse(ms.won());
	  
	  System.out.print(ms.toString());
	  System.out.println();
	  
	  Random generator = new Random();
	  for (int i = 0; i < 50; i++){
		  int r = generator.nextInt(10);
		  assertTrue(r >= 0 && r < 10);
	  }
	  
	  for (int i = 0; i < sqr.length; i++){
		  for (int n = 0; n < sqr[0].length; n++){
			  assertFalse(ms.isFlagged(i, n));
			  ms.toggleFlagged(i, n);
			  assertTrue(ms.isFlagged(i, n));
			  ms.toggleFlagged(i, n);
			  assertFalse(ms.isFlagged(i, n));
		  }
	  }
	  
	  ms.toggleFlagged(0, 1);
	  assertFalse(ms.isVisible(0, 1));
	  assertTrue(ms.isFlagged(0, 1));
	  ms.click(0, 1);
	  assertFalse(ms.isVisible(0, 1));
	  assertTrue(ms.isFlagged(0, 1));
	  
	  ms.toggleFlagged(0, 1);
	  assertFalse(ms.isVisible(0, 1));
	  assertFalse(ms.isFlagged(0, 1));
	  ms.click(0, 1);
	  assertTrue(ms.isVisible(0, 1));
	  assertFalse(ms.isFlagged(0, 1));
	  
	  assertFalse(ms.lost());
	  assertFalse(ms.won());
	  
	  ms.click(3, 0);
	  assertTrue(ms.isVisible(1, 0));
	  assertTrue(ms.isVisible(1, 1));
	  assertTrue(ms.isVisible(1, 2));
	  
	  assertTrue(ms.isVisible(2, 0));
	  assertTrue(ms.isVisible(2, 1));
	  assertTrue(ms.isVisible(2, 2));
	  
	  assertTrue(ms.isVisible(3, 0));
	  assertTrue(ms.isVisible(3, 1));
	  assertTrue(ms.isVisible(3, 2));
	  
	  assertFalse(ms.isVisible(0, 0));
	  assertTrue(ms.isVisible(0, 1));
	  assertFalse(ms.isVisible(0, 2));
	  assertFalse(ms.isVisible(0, 3));
	  assertFalse(ms.isVisible(1, 3));
	  assertFalse(ms.isVisible(2, 3));
	  assertFalse(ms.isVisible(3, 3));
	  assertFalse(ms.lost());
	  assertFalse(ms.won());
	  
	  ms.click(2, 3);
	  assertTrue(ms.isVisible(2, 3));
	  assertFalse(ms.lost());
	  assertTrue(ms.won());
	  System.out.print(ms.toString());
	  System.out.println();
  }
  
  @Test
  public void testGetters2() {
	  boolean[][] sqr = { { true, false, true, true  },
			              {false, false, false, true },
			              {false, false, false, false},
			              {false, false, false, true }, };
	  
	  MineSweeper ms = new MineSweeper(sqr);
	  
	  assertEquals(0, ms.getAdjacentMines(0, 0));
	  assertEquals(2, ms.getAdjacentMines(0, 1));
	  assertEquals(2, ms.getAdjacentMines(0, 2));
	  assertEquals(2, ms.getAdjacentMines(0, 3));
	  
	  assertEquals(1, ms.getAdjacentMines(1, 0));
	  assertEquals(2, ms.getAdjacentMines(1, 1));
	  assertEquals(3, ms.getAdjacentMines(1, 2));
	  assertEquals(2, ms.getAdjacentMines(1, 3));
	  
	  assertEquals(0, ms.getAdjacentMines(2, 0));
	  assertEquals(0, ms.getAdjacentMines(2, 1));
	  assertEquals(2, ms.getAdjacentMines(2, 2));
	  assertEquals(2, ms.getAdjacentMines(2, 3));
	  
	  assertEquals(0, ms.getAdjacentMines(3, 0));
	  assertEquals(0, ms.getAdjacentMines(3, 1));
	  assertEquals(1, ms.getAdjacentMines(3, 2));
	  assertEquals(0, ms.getAdjacentMines(3, 3));
	  
	  assertEquals(5, ms.getTotalMineCount());
	  
	  assertTrue(ms.isMine(0, 0));
	  assertFalse(ms.isMine(0, 1));
	  assertTrue(ms.isMine(0, 2));
	  assertTrue(ms.isMine(0, 3));
	  
	  assertFalse(ms.isMine(1, 0));
	  assertFalse(ms.isMine(1, 1));
	  assertFalse(ms.isMine(1, 2));
	  assertTrue(ms.isMine(1, 3));
	  
	  assertFalse(ms.isMine(2, 0));
	  assertFalse(ms.isMine(2, 1));
	  assertFalse(ms.isMine(2, 2));
	  assertFalse(ms.isMine(2, 3));
	  
	  assertFalse(ms.isMine(3, 0));
	  assertFalse(ms.isMine(3, 1));
	  assertFalse(ms.isMine(3, 2));
	  assertTrue(ms.isMine(3, 3));
	  
	  for (int i = 0; i < sqr.length; i++){
		  for (int n = 0; n < sqr[0].length; n++){
			  assertFalse(ms.isVisible(i, n));
			  assertFalse(ms.isFlagged(i, n));
		  }
	  }
	  
	  assertFalse(ms.lost());
	  assertFalse(ms.won());
	  
	  System.out.print(ms.toString());
	  System.out.println();
	  
	  Random generator = new Random();
	  for (int i = 0; i < 50; i++){
		  int r = generator.nextInt(10);
		  assertTrue(r >= 0 && r < 10);
	  }
	  
	  for (int i = 0; i < sqr.length; i++){
		  for (int n = 0; n < sqr[0].length; n++){
			  assertFalse(ms.isFlagged(i, n));
			  ms.toggleFlagged(i, n);
			  assertTrue(ms.isFlagged(i, n));
			  ms.toggleFlagged(i, n);
			  assertFalse(ms.isFlagged(i, n));
		  }
	  }
	  
	  ms.toggleFlagged(0, 1);
	  assertFalse(ms.isVisible(0, 1));
	  assertTrue(ms.isFlagged(0, 1));
	  ms.click(0, 1);
	  assertFalse(ms.isVisible(0, 1));
	  assertTrue(ms.isFlagged(0, 1));
	  
	  ms.toggleFlagged(0, 1);
	  assertFalse(ms.isVisible(0, 1));
	  assertFalse(ms.isFlagged(0, 1));
	  ms.click(0, 1);
	  assertTrue(ms.isVisible(0, 1));
	  assertFalse(ms.isFlagged(0, 1));
	  
	  assertFalse(ms.lost());
	  assertFalse(ms.won());
	  
	  ms.click(3, 0);
	  assertTrue(ms.isVisible(1, 0));
	  assertTrue(ms.isVisible(1, 1));
	  assertTrue(ms.isVisible(1, 2));
	  
	  assertTrue(ms.isVisible(2, 0));
	  assertTrue(ms.isVisible(2, 1));
	  assertTrue(ms.isVisible(2, 2));
	  
	  assertTrue(ms.isVisible(3, 0));
	  assertTrue(ms.isVisible(3, 1));
	  assertTrue(ms.isVisible(3, 2));
	  
	  assertFalse(ms.isVisible(0, 0));
	  assertTrue(ms.isVisible(0, 1));
	  assertFalse(ms.isVisible(0, 2));
	  assertFalse(ms.isVisible(0, 3));
	  assertFalse(ms.isVisible(1, 3));
	  assertFalse(ms.isVisible(2, 3));
	  assertFalse(ms.isVisible(3, 3));
	  assertFalse(ms.lost());
	  assertFalse(ms.won());
	  
	  ms.click(3, 3);
	  assertTrue(ms.isVisible(3, 3));
	  assertTrue(ms.lost());
	  assertFalse(ms.won());
	  System.out.print(ms.toString());
	  System.out.println();
  }
}