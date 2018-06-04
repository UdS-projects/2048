package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

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
		int currentPiece = game.getNumPieces();        //derzeitiges Anzahl der Steine
		game.addPiece();              
		assertEquals("Es wird kein neuer Stein inizialisiert.",currentPiece+1,game.getNumPieces());
		
	}
	
	@Test
	public void testisSpaceLeft() {
		assertTrue("Falsch",game.isSpaceLeft());
	}
	
	@Test
	public void testPerformMove(){
		game.setPieceAt(0,0,2);
		game.performMove(MoveDirection.EAST);
		assertTrue(game.getPieceAt(3, 0) == 2);	
	}
	
	@Test
	public void testsetPieceAt() {
		game.setPieceAt(0, 0, 2);
		assertTrue(game.getPieceAt(0,0)== 2);
	}
	
	@Test
	public void testgetNumMoves() {
		game.getPieceAt(0, 0);
		game.performMove(MoveDirection.EAST);
		assertTrue(game.getNumMoves()==1);
	}
	
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
		assertTrue(game.getPoints()==4);
	}
	
	@Test
	public void testisMovePossible() {
		game.addPiece();
		assertTrue(game.isMovePossible());
	}
	
	@Test
	public void testisMovePossible2() {
		game.addPiece();
		assertTrue(game.isMovePossible(MoveDirection.EAST));
	}
	

}