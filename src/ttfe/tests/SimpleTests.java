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
		
		int currentPiece = 0;       //derzeitiges Anzahl der Steine
		game.setPieceAt(0, 0, 0);
		game.setPieceAt(0, 1, 0);
		game.setPieceAt(0, 2, 0);
		game.setPieceAt(0, 3, 0);
		game.setPieceAt(1, 0, 0);
		game.setPieceAt(1, 1, 0);
		game.setPieceAt(1, 2, 0);
		game.setPieceAt(1, 3, 0);
		game.setPieceAt(2, 0, 0);
		game.setPieceAt(2, 1, 0);
		game.setPieceAt(2, 2, 0);
		game.setPieceAt(2, 3, 0);
		game.setPieceAt(3, 0, 0);
		game.setPieceAt(3, 1, 0);
		game.setPieceAt(3, 2, 0);
		game.setPieceAt(3, 3, 0);

		game.addPiece();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				currentPiece += game.getPieceAt(i, j);
			}
		}
		assertTrue("Kein neuer Stein", currentPiece == 2 || currentPiece == 4);
	}
	
	@Test
	public void testisSpaceLeft() {
		assertTrue("Falsch",game.isSpaceLeft());
	}
	
	@Test
	public void testisSpaceLeft2() {
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 2);
		game.setPieceAt(1, 0, 2);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 2);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 2);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(2, 3, 2);
		game.setPieceAt(3, 0, 2);
		game.setPieceAt(3, 1, 2);
		game.setPieceAt(3, 2, 2);
		game.setPieceAt(3, 3, 2);
		assertFalse("Falsch",game.isSpaceLeft());
	
	}
	

	@Test
	public void testPerformMove(){
		boolean s1 = game.isMovePossible(MoveDirection.EAST);
		assertEquals(s1,game.performMove(MoveDirection.EAST));
			
		
		boolean s2 = game.isMovePossible(MoveDirection.NORTH);
		assertEquals(s2,game.performMove(MoveDirection.NORTH));
			
		boolean s3 = game.isMovePossible(MoveDirection.SOUTH);
		assertEquals(s3,game.performMove(MoveDirection.SOUTH));
			
		
		boolean	s4 = game.isMovePossible(MoveDirection.WEST);
			assertEquals(s4,game.performMove(MoveDirection.WEST));	
	}
	
	@Test
	public void GetNumMove(){
		
		if (game.isMovePossible(MoveDirection.EAST)) {
		game.performMove(MoveDirection.EAST);
		assertTrue(game.getNumMoves() == 1);
		}
		else {
			if(game.isMovePossible(MoveDirection.WEST)){
					game.performMove(MoveDirection.WEST);
					assertTrue(game.getNumMoves() == 1);
		}
			else {
				if(game.isMovePossible(MoveDirection.SOUTH)){
					game.performMove(MoveDirection.SOUTH);
					assertTrue(game.getNumMoves() == 1);
				}
				else {
					if(game.isMovePossible(MoveDirection.NORTH)) {
						game.performMove(MoveDirection.NORTH);
						assertTrue(game.getNumMoves() == 1);
					}
					else {
						assertTrue(game.getNumMoves() == 0);
					}
				}
			
			}
		}
		
	}
	
	@Test
	public void testsetPieceAt() {
		game.setPieceAt(0, 0, 2);
		assertTrue(game.getPieceAt(0,0)== 2);
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
		assertTrue(game.getPoints()==4 || game.getPoints()==8 || game.getPoints()==12);
	}
	
	@Test
	public void testisMovePossible() {
	if (game.isMovePossible(MoveDirection.EAST)) {
		assertTrue(game.performMove(MoveDirection.EAST));
	}
	else {
		if(game.isMovePossible(MoveDirection.NORTH)) {
			assertTrue(game.performMove(MoveDirection.NORTH));
		}
		else {
			if(game.isMovePossible(MoveDirection.SOUTH)) {
				assertTrue(game.performMove(MoveDirection.SOUTH));
			}
			else {
				if (game.isMovePossible(MoveDirection.WEST)) {
					assertTrue(game.performMove(MoveDirection.WEST));
				}
				else {
					if (game.isSpaceLeft()) {
						assertTrue(game.isMovePossible());
					}
				}
			}
		}
	}
		
	for(int i=0;i<4;i++) {
		for(int j=0;j<4;j++) {
			game.setPieceAt(i, j, 2);
		}
	}
		assertTrue("Falsch", game.isMovePossible());
		
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(1, 2, 4);
		game.setPieceAt(1, 3, 2);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(2, 3, 4);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 2);
		game.setPieceAt(3, 2, 2);
		game.setPieceAt(3, 3, 2);
	
		assertTrue("Falsch", game.isMovePossible());
		
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(1, 2, 4);
		game.setPieceAt(1, 3, 2);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(2, 3, 4);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 2);
		game.setPieceAt(3, 2, 4);
		game.setPieceAt(3, 3, 2);
		
		assertFalse("Falsch", game.isMovePossible());
		
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 2);
		game.setPieceAt(1, 0, 2);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 2);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 2);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(2, 3, 2);
		game.setPieceAt(3, 0, 2);
		game.setPieceAt(3, 1, 2);
		game.setPieceAt(3, 2, 2);
		game.setPieceAt(3, 3, 2);
		
		assertTrue("Falsch", game.isMovePossible());
	}
}