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
	private SimulatorInterface game2;
	private SimulatorInterface game3;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
//		game2 = TTFEFactory.createSimulator(2, 2, new Random(0));
//		game3 = TTFEFactory.createSimulator(8, 8, new Random(0));
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
//		boolean s1 = game.isMovePossible(MoveDirection.EAST);
//		assertEquals(s1,game.performMove(MoveDirection.EAST));
			
		
		boolean s2 = game.isMovePossible(MoveDirection.NORTH);
		assertEquals(s2,game.performMove(MoveDirection.NORTH));
			
		boolean s3 = game.isMovePossible(MoveDirection.SOUTH);
		assertEquals(s3,game.performMove(MoveDirection.SOUTH));
			
		
		boolean	s4 = game.isMovePossible(MoveDirection.WEST);
			assertEquals(s4,game.performMove(MoveDirection.WEST));	
			
			boolean s1 = game.isMovePossible(MoveDirection.EAST);
			assertEquals(s1,game.performMove(MoveDirection.EAST));
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
		assertFalse("Falsch", game.isMovePossible());
		
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
		
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
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
	
		assertTrue("Falsch", game.isMovePossible());
	}
	
	//-----------------------------------------------------game2-----------------------------------------------------
//	
//	@Test
//	public void testInitialGamePoints2() {
//		assertEquals("The initial game did not have zero points", 0,
//				game2.getPoints());
//	}
//	
//	@Test
//	public void testInitialBoardHeight2() {
//		assertTrue("The initial game board did not have correct height",
//				2 == game2.getBoardHeight());
//	}
//	
//	@Test
//	public void testInitialBoardWidth2() {
//		assertTrue("The initial game board did not have correct width",
//				2 == game2.getBoardWidth());
//	}
//		
//	@Test
//	public void testAddPiece2() {
//		
//		int currentPiece = 0;       //derzeitiges Anzahl der Steine
//		game2.setPieceAt(0, 0, 0);
//		game2.setPieceAt(0, 1, 0);
////		game2.setPieceAt(0, 2, 0);
////		game2.setPieceAt(0, 3, 0);
//		game2.setPieceAt(1, 0, 0);
//		game2.setPieceAt(1, 1, 0);
////		game2.setPieceAt(1, 2, 0);
////		game2.setPieceAt(1, 3, 0);
////		game2.setPieceAt(2, 0, 0);
////		game2.setPieceAt(2, 1, 0);
////		game2.setPieceAt(2, 2, 0);
////		game2.setPieceAt(2, 3, 0);
////		game2.setPieceAt(3, 0, 0);
////		game2.setPieceAt(3, 1, 0);
////		game2.setPieceAt(3, 2, 0);
////		game2.setPieceAt(3, 3, 0);
//
//		game2.addPiece();
//		for(int i=0;i<4;i++) {
//			for(int j=0;j<4;j++) {
//				currentPiece += game2.getPieceAt(i, j);
//			}
//		}
//		assertTrue("Kein neuer Stein", currentPiece == 2 || currentPiece == 4);
//	}
//	
//	@Test
//	public void testisSpaceLeft3() {
//		assertTrue("Falsch",game2.isSpaceLeft());
//	}
//	
//	@Test
//	public void testisSpaceLeft4() {
//		game2.setPieceAt(0, 0, 2);
//		game2.setPieceAt(0, 1, 2);
//		game2.setPieceAt(1, 0, 2);
//		game2.setPieceAt(1, 1, 2);	
////		game2.setPieceAt(2, 0, 2);
////		game2.setPieceAt(2, 1, 2);
//		assertFalse("Falsch",game.isSpaceLeft());
//	
//	}
//	
//
//	@Test
//	public void testPerformMove2(){
//		boolean s1 = game2.isMovePossible(MoveDirection.EAST);
//		assertEquals(s1,game2.performMove(MoveDirection.EAST));
//			
//		
//		boolean s2 = game2.isMovePossible(MoveDirection.NORTH);
//		assertEquals(s2,game2.performMove(MoveDirection.NORTH));
//			
//		boolean s3 = game2.isMovePossible(MoveDirection.SOUTH);
//		assertEquals(s3,game2.performMove(MoveDirection.SOUTH));
//			
//		
//		boolean	s4 = game2.isMovePossible(MoveDirection.WEST);
//			assertEquals(s4,game2.performMove(MoveDirection.WEST));	
//	}
//	
//	@Test
//	public void GetNumMove2(){
//		
//		if (game2.isMovePossible(MoveDirection.EAST)) {
//		game2.performMove(MoveDirection.EAST);
//		assertTrue(game2.getNumMoves() == 1);
//		}
//		else {
//			if(game2.isMovePossible(MoveDirection.WEST)){
//					game2.performMove(MoveDirection.WEST);
//					assertTrue(game2.getNumMoves() == 1);
//		}
//			else {
//				if(game2.isMovePossible(MoveDirection.SOUTH)){
//					game2.performMove(MoveDirection.SOUTH);
//					assertTrue(game2.getNumMoves() == 1);
//				}
//				else {
//					if(game2.isMovePossible(MoveDirection.NORTH)) {
//						game2.performMove(MoveDirection.NORTH);
//						assertTrue(game.getNumMoves() == 1);
//					}
//					else {
//						assertTrue(game2.getNumMoves() == 0);
//					}
//				}
//			
//			}
//		}
//		
//	}
//	
//	@Test
//	public void testsetPieceAt2() {
//		game2.setPieceAt(0, 0, 2);
//		assertTrue(game2.getPieceAt(0,0)== 2);
//	}
//	
//	@Test
//	public void testNumPieces2() {
//		game2.addPiece();
//		game2.addPiece();
//		assertTrue(game2.getNumPieces()==4);
//	}
//	
//	@Test
//	public void testgetPieceAt2() {
//		game2.setPieceAt(1, 1, 2);
//		assertTrue(game2.getPieceAt(1, 1)==2);
//	}
//	
//	@Test
//	public void testgetPoints2() {
//		game2.setPieceAt(0, 0, 2);
//		game2.setPieceAt(1, 0, 2);
//		game2.performMove(MoveDirection.EAST);
//		assertTrue(game2.getPoints()==4 || game2.getPoints()==8);
//	}
//	
//	@Test
//	public void testisMovePossible2() {
//	if (game2.isMovePossible(MoveDirection.EAST)) {
//		assertTrue(game2.performMove(MoveDirection.EAST));
//	}
//	else {
//		if(game2.isMovePossible(MoveDirection.NORTH)) {
//			assertTrue(game2.performMove(MoveDirection.NORTH));
//		}
//		else {
//			if(game2.isMovePossible(MoveDirection.SOUTH)) {
//				assertTrue(game2.performMove(MoveDirection.SOUTH));
//			}
//			else {
//				if (game2.isMovePossible(MoveDirection.WEST)) {
//					assertTrue(game2.performMove(MoveDirection.WEST));
//				}
//				else {
//					if (game2.isSpaceLeft()) {
//						assertTrue(game2.isMovePossible());
//					}
//				}
//			}
//		}
//	}
//		
//	for(int i=0;i<2;i++) {
//		for(int j=0;j<2;j++) {
//			game2.setPieceAt(i, j, 2);
//		}
//	}
//		assertTrue("Falsch", game2.isMovePossible());
//		
//		game2.setPieceAt(0, 0, 2);
//		game2.setPieceAt(0, 1, 4);
//		
//		game2.setPieceAt(1, 0, 4);
//		game2.setPieceAt(1, 1, 2);
//		game2.setPieceAt(2, 0, 2);
//		game2.setPieceAt(2, 1, 4);
//		
//		game2.setPieceAt(3, 0, 4);
//		game2.setPieceAt(3, 1, 2);
//		
//	
//		assertTrue("Falsch", game2.isMovePossible());
//		
//		game2.setPieceAt(0, 0, 2);
//		game2.setPieceAt(0, 1, 4);
//		
//		game2.setPieceAt(1, 0, 4);
//		game2.setPieceAt(1, 1, 2);
//	
//		game2.setPieceAt(2, 0, 2);
//		game2.setPieceAt(2, 1, 4);
//		
//		game2.setPieceAt(3, 0, 4);
//		game2.setPieceAt(3, 1, 2);
//		
//		
//		assertFalse("Falsch", game2.isMovePossible());
//		
//		game2.setPieceAt(0, 0, 2);
//		game2.setPieceAt(0, 1, 2);
//	
//		game2.setPieceAt(1, 0, 2);
//		game2.setPieceAt(1, 1, 2);
//		
//		game2.setPieceAt(2, 0, 2);
//		game2.setPieceAt(2, 1, 2);
//		
//		game2.setPieceAt(3, 0, 2);
//		game2.setPieceAt(3, 1, 2);
//
//		
//		assertTrue("Falsch", game2.isMovePossible());
//		
//		game2.setPieceAt(0, 0, 2);
//		game2.setPieceAt(0, 1, 2);
//	    game2.setPieceAt(1, 0, 4);
//		game2.setPieceAt(1, 1, 2);
//		
//		game2.setPieceAt(2, 0, 2);
//		game2.setPieceAt(2, 1, 4);
//		
//		game2.setPieceAt(3, 0, 4);
//		game2.setPieceAt(3, 1, 2);
//			
//		assertTrue("Falsch", game2.isMovePossible());
//	}
//	
//	//----------------------------------game3-------------------------------------------------------------------------
//	
//	@Test
//	public void testInitialGamePoints3() {
//		assertEquals("The initial game did not have zero points", 0,
//				game3.getPoints());
//	}
//	
//	@Test
//	public void testInitialBoardHeight3() {
//		assertTrue("The initial game board did not have correct height",
//				8 == game3.getBoardHeight());
//	}
//	
//	@Test
//	public void testInitialBoardWidth3() {
//		assertTrue("The initial game board did not have correct width",
//				8 == game3.getBoardWidth());
//	}
//	
//	@Test
//	public void testAddPiece3() {
//		
//		int currentPiece = 0;       //derzeitiges Anzahl der Steine
//		game3.setPieceAt(0, 0, 0);
//		game3.setPieceAt(0, 1, 0);
//		game3.setPieceAt(0, 2, 0);
//		game3.setPieceAt(0, 3, 0);
//		game3.setPieceAt(0, 4, 0);
//		game3.setPieceAt(0, 5, 0);
//		game3.setPieceAt(0, 6, 0);
//		game3.setPieceAt(0, 7, 0);
//		game3.setPieceAt(1, 0, 0);
//		game3.setPieceAt(1, 1, 0);
//		game3.setPieceAt(1, 2, 0);
//		game3.setPieceAt(1, 3, 0);
//		game3.setPieceAt(1, 4, 0);
//		game3.setPieceAt(1, 5, 0);
//		game3.setPieceAt(1, 6, 0);
//		game3.setPieceAt(1, 7, 0);
//		game3.setPieceAt(2, 0, 0);
//		game3.setPieceAt(2, 1, 0);
//		game3.setPieceAt(2, 2, 0);
//		game3.setPieceAt(2, 3, 0);
//		game3.setPieceAt(2, 4, 0);
//		game3.setPieceAt(2, 5, 0);
//		game3.setPieceAt(2, 6, 0);
//		game3.setPieceAt(2, 7, 0);
//		game3.setPieceAt(3, 0, 0);
//		game3.setPieceAt(3, 1, 0);
//		game3.setPieceAt(3, 2, 0);
//		game3.setPieceAt(3, 3, 0);
//		game3.setPieceAt(3, 4, 0);
//		game3.setPieceAt(3, 5, 0);
//		game3.setPieceAt(3, 6, 0);
//		game3.setPieceAt(3, 7, 0);
//
//		game3.addPiece();
//		for(int i=0;i<7;i++) {
//			for(int j=0;j<7;j++) {
//				currentPiece += game3.getPieceAt(i, j);
//			}
//		}
//		assertTrue("Kein neuer Stein", currentPiece == 2 || currentPiece == 4);
//	}
//	
//	@Test
//	public void testisSpaceLeft5() {
//		assertTrue("Falsch",game3.isSpaceLeft());
//	}
//	
//	@Test
//	public void testisSpaceLeft6() {
//		game3.setPieceAt(0, 0, 2);
//		game3.setPieceAt(0, 1, 2);
//		game3.setPieceAt(0, 2, 2);
//		game3.setPieceAt(0, 3, 2);
//		game3.setPieceAt(1, 0, 2);
//		game3.setPieceAt(1, 1, 2);
//		game3.setPieceAt(1, 2, 2);
//		game3.setPieceAt(1, 3, 2);
//		game3.setPieceAt(2, 0, 2);
//		game3.setPieceAt(2, 1, 2);
//		game3.setPieceAt(2, 2, 2);
//		game3.setPieceAt(2, 3, 2);
//		game3.setPieceAt(3, 0, 2);
//		game3.setPieceAt(3, 1, 2);
//		game3.setPieceAt(3, 2, 2);
//		game3.setPieceAt(3, 3, 2);
//		assertFalse("Falsch",game3.isSpaceLeft());
//	
//	}
//	
//
//	@Test
//	public void testPerformMove3(){
//		boolean s1 = game3.isMovePossible(MoveDirection.EAST);
//		assertEquals(s1,game3.performMove(MoveDirection.EAST));
//			
//		
//		boolean s2 = game3.isMovePossible(MoveDirection.NORTH);
//		assertEquals(s2,game3.performMove(MoveDirection.NORTH));
//			
//		boolean s3 = game3.isMovePossible(MoveDirection.SOUTH);
//		assertEquals(s3,game3.performMove(MoveDirection.SOUTH));
//			
//		
//		boolean	s4 = game3.isMovePossible(MoveDirection.WEST);
//			assertEquals(s4,game3.performMove(MoveDirection.WEST));	
//	}
//	
//	@Test
//	public void GetNumMove3(){
//		
//		if (game3.isMovePossible(MoveDirection.EAST)) {
//		game3.performMove(MoveDirection.EAST);
//		assertTrue(game3.getNumMoves() == 1);
//		}
//		else {
//			if(game3.isMovePossible(MoveDirection.WEST)){
//					game3.performMove(MoveDirection.WEST);
//					assertTrue(game3.getNumMoves() == 1);
//		}
//			else {
//				if(game3.isMovePossible(MoveDirection.SOUTH)){
//					game3.performMove(MoveDirection.SOUTH);
//					assertTrue(game3.getNumMoves() == 1);
//				}
//				else {
//					if(game3.isMovePossible(MoveDirection.NORTH)) {
//						game3.performMove(MoveDirection.NORTH);
//						assertTrue(game3.getNumMoves() == 1);
//					}
//					else {
//						assertTrue(game3.getNumMoves() == 0);
//					}
//				}
//			
//			}
//		}
//		
//	}
//	
//	@Test
//	public void testsetPieceAt3() {
//		game3.setPieceAt(0, 0, 2);
//		assertTrue(game3.getPieceAt(0,0)== 2);
//	}
//	
//
//	
//	@Test
//	public void testNumPieces3() {
//		game3.addPiece();
//		game3.addPiece();
//		assertTrue(game3.getNumPieces()==4);
//	}
//	
//	@Test
//	public void testgetPieceAt3() {
//		game.setPieceAt(2, 2, 2);
//		assertTrue(game.getPieceAt(2, 2)==2);
//	}
//	
//	@Test
//	public void testgetPoints3() {
//		game3.setPieceAt(0, 0, 2);
//		game3.setPieceAt(1, 0, 2);
//		game3.performMove(MoveDirection.EAST);
//		assertTrue(game3.getPoints()==4 || game3.getPoints()==8 || game3.getPoints()==12);
//	}
//
//	@Test
//	public void testisMovePossible3() {
//	if (game3.isMovePossible(MoveDirection.EAST)) {
//		assertTrue(game3.performMove(MoveDirection.EAST));
//	}
//	else {
//		if(game3.isMovePossible(MoveDirection.NORTH)) {
//			assertTrue(game3.performMove(MoveDirection.NORTH));
//		}
//		else {
//			if(game3.isMovePossible(MoveDirection.SOUTH)) {
//				assertTrue(game3.performMove(MoveDirection.SOUTH));
//			}
//			else {
//				if (game3.isMovePossible(MoveDirection.WEST)) {
//					assertTrue(game3.performMove(MoveDirection.WEST));
//				}
//				else {
//					if (game3.isSpaceLeft()) {
//						assertTrue(game3.isMovePossible());
//					}
//				}
//			}
//		}
//	}
//		
//	for(int i=0;i<8;i++) {
//		for(int j=0;j<8;j++) {
//			game3.setPieceAt(i, j, 2);
//		}
//	}
//		assertTrue("Falsch", game3.isMovePossible());
//		
//		game3.setPieceAt(0, 0, 2);
//		game3.setPieceAt(0, 1, 4);
//		game3.setPieceAt(0, 2, 2);
//		game3.setPieceAt(0, 3, 4);
//		game3.setPieceAt(0, 4, 2);
//		game3.setPieceAt(0, 5, 4);
//		game3.setPieceAt(0, 6, 2);
//		game3.setPieceAt(0, 7, 4);
//		game3.setPieceAt(1, 0, 4);
//		game3.setPieceAt(1, 1, 2);
//		game3.setPieceAt(1, 2, 4);
//		game3.setPieceAt(1, 3, 2);
//		game3.setPieceAt(1, 4, 4);
//		game3.setPieceAt(1, 5, 2);
//		game3.setPieceAt(1, 6, 4);
//		game3.setPieceAt(1, 7, 2);
//		game3.setPieceAt(2, 0, 2);
//		game3.setPieceAt(2, 1, 4);
//		game3.setPieceAt(2, 2, 2);
//		game3.setPieceAt(2, 3, 4);
//		game3.setPieceAt(2, 4, 2);
//		game3.setPieceAt(2, 5, 4);
//		game3.setPieceAt(2, 6, 2);
//		game3.setPieceAt(2, 7, 4);
//		game3.setPieceAt(3, 0, 4);
//		game3.setPieceAt(3, 1, 2);
//		game3.setPieceAt(3, 2, 2);
//		game3.setPieceAt(3, 3, 2);
//		game3.setPieceAt(3, 4, 4);
//		game3.setPieceAt(3, 5, 2);
//		game3.setPieceAt(3, 6, 4);
//		game3.setPieceAt(3, 7, 2);
//		
//		assertTrue("Falsch", game3.isMovePossible());
//		
//		
//			
//		game3.setPieceAt(0, 0, 2);
//		game3.setPieceAt(0, 1, 2);
//		game3.setPieceAt(0, 2, 2);
//		game3.setPieceAt(0, 3, 4);
//		game3.setPieceAt(0, 4, 2);
//		game3.setPieceAt(0, 5, 4);
//		game3.setPieceAt(0, 6, 2);
//		game3.setPieceAt(0, 7, 4);
//		game3.setPieceAt(1, 0, 4);
//		game3.setPieceAt(1, 1, 2);
//		game3.setPieceAt(1, 2, 4);
//		game3.setPieceAt(1, 3, 2);
//		game3.setPieceAt(1, 4, 4);
//		game3.setPieceAt(1, 5, 2);
//		game3.setPieceAt(1, 6, 4);
//		game3.setPieceAt(1, 7, 2);
//		game3.setPieceAt(2, 0, 2);
//		game3.setPieceAt(2, 1, 4);
//		game3.setPieceAt(2, 2, 2);
//		game3.setPieceAt(2, 3, 4);
//		game3.setPieceAt(2, 4, 2);
//		game3.setPieceAt(2, 5, 4);
//		game3.setPieceAt(2, 6, 2);
//		game3.setPieceAt(2, 7, 4);
//		game3.setPieceAt(3, 0, 4);
//		game3.setPieceAt(3, 1, 2);
//		game3.setPieceAt(3, 2, 2);
//		game3.setPieceAt(3, 3, 2);
//		game3.setPieceAt(3, 4, 4);
//		game3.setPieceAt(3, 5, 2);
//		game3.setPieceAt(3, 6, 4);
//		game3.setPieceAt(3, 7, 2);
//	
//		assertTrue("Falsch", game3.isMovePossible());
//		
//		game3.setPieceAt(0, 0, 2);
//		game3.setPieceAt(0, 1, 4);
//		game3.setPieceAt(0, 2, 2);
//		game3.setPieceAt(0, 3, 4);
//		game3.setPieceAt(0, 4, 2);
//		game3.setPieceAt(0, 5, 4);
//		game3.setPieceAt(0, 6, 2);
//		game3.setPieceAt(0, 7, 4);
//		game3.setPieceAt(1, 0, 4);
//		game3.setPieceAt(1, 1, 2);
//		game3.setPieceAt(1, 2, 4);
//		game3.setPieceAt(1, 3, 2);
//		game3.setPieceAt(1, 4, 4);
//		game3.setPieceAt(1, 5, 2);
//		game3.setPieceAt(1, 6, 4);
//		game3.setPieceAt(1, 7, 2);
//		game3.setPieceAt(2, 0, 2);
//		game3.setPieceAt(2, 1, 4);
//		game3.setPieceAt(2, 2, 2);
//		game3.setPieceAt(2, 3, 4);
//		game3.setPieceAt(2, 4, 2);
//		game3.setPieceAt(2, 5, 4);
//		game3.setPieceAt(2, 6, 2);
//		game3.setPieceAt(2, 7, 4);
//		game3.setPieceAt(3, 0, 4);
//		game3.setPieceAt(3, 1, 2);
//		game3.setPieceAt(3, 2, 2);
//		game3.setPieceAt(3, 3, 2);
//		game3.setPieceAt(3, 4, 4);
//		game3.setPieceAt(3, 5, 2);
//		game3.setPieceAt(3, 6, 4);
//		game3.setPieceAt(3, 7, 4);
//		
//		assertTrue("Falsch", game3.isMovePossible());
//	}
}