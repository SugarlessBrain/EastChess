package com.disalvo.chess;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChessPieceFactoryTest {

	private PieceFactory pieceFactory;
	private Color anyColor;
	@Mock
	private ChessPieceTargetingFactory chessPieceTargetingFactory;
	
	@Before
	public void setUp() throws Exception {
		anyColor = Color.LIGHT;
		this.pieceFactory = new ChessPieceFactory(chessPieceTargetingFactory);
	}

	private void assertFactoryCreatesPieceType(ChessPieceType pieceType, Class<?> clazz) {
		Piece piece = pieceFactory.createPiece(pieceType, anyColor);
		assertThat(piece, instanceOf(clazz));
	}
	
	@Test
	public void shouldReturnNewPawnWhenCreatingPawnType() {
		assertFactoryCreatesPieceType(ChessPieceType.PAWN, Pawn.class);
	}
	
	@Test
	public void shouldReturnNewRookWhenCreatingRookType() {
		assertFactoryCreatesPieceType(ChessPieceType.ROOK, Rook.class);
	}
	
	@Test
	public void shouldReturnNewKingWhenCreatingKingType() {
		assertFactoryCreatesPieceType(ChessPieceType.KING, King.class);
	}
	
	@Test
	public void shouldReturnNewQueenWhenCreatingQueenType() {
		assertFactoryCreatesPieceType(ChessPieceType.QUEEN, Queen.class);
	}
	
	@Test
	public void shouldReturnNewKnightWhenCreatingKnightType() {
		assertFactoryCreatesPieceType(ChessPieceType.KNIGHT, Knight.class);
	}
	
	@Test
	public void shouldReturnNewBishopWhenCreatingBishopType() {
		assertFactoryCreatesPieceType(ChessPieceType.BISHOP, Bishop.class);
	}
	
	@Test
	public void shouldReturnNewObjectsWhenMultipleCallsForSameType() {
		Piece p = pieceFactory.createPiece(ChessPieceType.PAWN, Color.LIGHT);
		Piece p2 = pieceFactory.createPiece(ChessPieceType.PAWN, Color.LIGHT);
		assertNotSame(p, p2);
	}
	
	@Test(expected=UnknownPieceTypeException.class)
	public void shouldThrowExceptionWhenNullPieceTypeGiven() {
		pieceFactory.createPiece(null, anyColor);
	}
}