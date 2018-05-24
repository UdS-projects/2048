package ttfe;

public interface SimulatorInterface {

	/**
	 * Put a new piece to an empty (and random) position on the board.
	 *
	 * With a probability of 0.9 the new piece will be a 2, otherwise a 4.
	 * In your tests, you do not need to verify the probability distribution.
	 */
	public void addPiece();

	/**
	 * @return Return the height of the board.
	 */
	public int getBoardHeight();

	/**
	 * @return Return the width of the board.
	 */
	public int getBoardWidth();

	/**
	 * @return Return the number of moves made so far.
	 */
	public int getNumMoves();

	/**
	 * @return Return the number of pieces currently on the board.
	 */
	public int getNumPieces();

	/**
	 * Return the piece at the given position.
	 *
	 * @param x
	 *            The x coordinate of the field.
	 * @param y
	 *            The y coordinate of the field.
	 *
	 * @return The current value of the piece at the given position.
	 */
	public int getPieceAt(int x, int y);

	/**
	 * @return Return the number of points scored so far.
	 */
	public int getPoints();

	/**
	 * Test if a move in any direction is possible.
	 *
	 * @return Return true, if there is at least one possible move left that
	 *         will move at least one piece (or merge two).
	 */
	public boolean isMovePossible();

	/**
	 * Test if a move in the given direction is possible.
	 *
	 * @return Return true, if we can move at least one piece (or merge two) in
	 *         the given direction.
	 */
	public boolean isMovePossible(MoveDirection direction);

	/**
	 * @return Return true, if there is at least one tile left where we could
	 *         place a new piece.
	 */
	public boolean isSpaceLeft();

	/**
	 * Perform a move in the given direction.
	 *
	 * @param direction
	 *            The direction of the move that should be performed.
	 *
	 * @return True, if and only if a move was performed.
	 */
	public boolean performMove(MoveDirection direction);

	/**
	 * Game entry point that will execute the game logic.
	 *
	 * @param player
	 *            The player that will choose the directions of the moves.
	 * @param ui
	 *            The interface that will display the current state of the game.
	 */
	public void run(PlayerInterface player, UserInterface ui);

	/**
	 * Place (or remove) the given piece at the given position.
	 *
	 * @param x
	 *            The x coordinate of the field.
	 * @param y
	 *            The y coordinate of the field.
	 * @param piece
	 *            The piece to place or 0 if the piece should be removed.
	 */
	public void setPieceAt(int x, int y, int piece);
}
