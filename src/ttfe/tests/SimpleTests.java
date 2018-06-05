package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

import static org.junit.Assert.assertFalse;
/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	private SimulatorInterface game;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
	}
	
	@Test
	public void testInitialGamePoints() {
		assertEquals("The initial game did not have zero points", 0,
				game.getPoints());
	}
	
	@Test
	public void testInitialBoardHeight() {
		assertTrue("The initial game board did not have correct height",
				4 == game.getBoardHeight());
	}
	
	@Test
	public void testInitialBoardWidth() {
		assertTrue("The initial game board did not have correct width",
				4 == game.getBoardWidth());
	}
	
	@Test
	public void testAddPiece() {
		if (game.isSpaceLeft()) {
		int currentPiece = game.getNumPieces();        //derzeitiges Anzahl der Steine
		game.addPiece();              
		assertEquals("Es wird kein neuer Stein inizialisiert.",currentPiece+1,game.getNumPieces());
		}
		else {
			int currentPiece = game.getNumPieces();        //derzeitiges Anzahl der Steine
			game.addPiece();              
			assertEquals("Die Vorbedingung stimmt nicht.",currentPiece,game.getNumPieces());
		}
	}
	
	@Test
	public void testisSpaceLeft() {
		assertTrue("Falsch",game.isSpaceLeft());
	}
	
	@Test
	public void testisSpaceLeft2() {
		game.getPieceAt(0, 0);
		game.getPieceAt(0, 1);
		game.getPieceAt(0, 2);
		game.getPieceAt(0, 3);
		game.getPieceAt(1, 0);
		game.getPieceAt(1, 1);
		game.getPieceAt(1, 2);
		game.getPieceAt(1, 3);
		game.getPieceAt(2, 0);
		game.getPieceAt(2, 1);
		game.getPieceAt(2, 2);
		game.getPieceAt(2, 3);
		game.getPieceAt(3, 0);
		game.getPieceAt(3, 1);
		game.getPieceAt(3, 2);
		game.getPieceAt(3, 3);
		assertFalse("Falsch",game.isSpaceLeft());
	
	}
	
	@Test
	public void testPerformMoveAndGetNumMove(){
		//game.setPieceAt(0,0,2);
		if (game.isMovePossible(MoveDirection.EAST)) {
		game.performMove(MoveDirection.EAST);
		}
		else {
			game.performMove(MoveDirection.WEST);
		}
		//game.getNumMoves();
		//assertTrue(game.getPieceAt(3, 0) == 2);
		assertTrue(game.getNumMoves() == 1);
	}
	
	@Test
	public void testsetPieceAt() {
		game.setPieceAt(0, 0, 2);
		assertTrue(game.getPieceAt(0,0)== 2);
	}
	
	
//	@Test
//	public void testgetNumMoves() {
//		//game.getPieceAt(0, 0);
//		if ()
//			game.performMove(MoveDirection.EAST);
//		assertTrue(game.getNumMoves()==1);
//	}
	
	@Test
	public void testNumPieces() {
		game.addPiece();
		game.addPiece();
		assertTrue(game.getNumPieces()==4);
	}
	
	@Test
	public void testgetPieceAt() {
		game.setPieceAt(2, 2, 2);
		assertTrue(game.getPieceAt(2, 2)==2);
	}
	
	@Test
	public void testgetPoints() {
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(1, 0, 2);
		game.performMove(MoveDirection.EAST);
		assertTrue(game.getPoints()==4 || game.getPoints()==8 || game.getPoints()==12);
	}
	
	@Test
	public void testisMovePossible() {
		//game.addPiece();
		assertTrue(game.isMovePossible());
	}
	
	@Test
	public void testisMovePossible2() {
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(3, 1, 2);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(1, 2, 4);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(3, 2, 4);
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(1, 3, 2);
		game.setPieceAt(2, 3, 4);
		game.setPieceAt(3, 3, 2);
		assertFalse(game.isMovePossible(MoveDirection.EAST));
	}
	

}