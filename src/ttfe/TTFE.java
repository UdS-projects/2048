package ttfe;

import java.util.Random;

public class TTFE {

	/**
	 * If set to true a human will be playing the game, otherwise a computer player.
	 * However, command line arguments will override this choice.
	 */
	private static final boolean DEFAULT_USE_HUMAN = false;

	/**
	 * If set to true the user will be asked what player should be chosen.
	 *
	 * Note: This is a debug option only that allows you to easily show both human and
	 *       computer players without any changes in the code.
	 */
	private static final boolean DEBUG_ASK_USER = false;

	/**
	 * The default width of the board.
	 */
	private static final int DEFAULT_WIDTH = 4;

	/**
	 * The default height of the board.
	 */
	private static final int DEFAULT_HEIGHT = 4;

	/**
	 * The default random seed.
	 */
	private static final int DEFAULT_RANDOM_SEED = 4711;

	/**
	 * @param str The string that should be converted to an integer
	 * @return The integer value represented by str or def if str does not
	 *         represent an integer.
	 */
	private static int string2int(String str, int def) {
		int intValue = def;

		try {
			intValue = Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}

		return intValue;
	}

	/**
	 * @param str The string that should be converted to an boolean.
	 * @return True iff the lowercase version of str equals to "true" and false otherwise.
	 */
	private static boolean string2boolean(String str) {
		return Boolean.parseBoolean(str);
	}


	/**
	 * The main function is the entry point of our 2048 simulator/game. Once the
	 * game is started the arguments (if any) are read and a Simulator as well
	 * as the UserInterface and the Player object is created. The control is
	 * then transfered to the Simulator that should run until the game is over.
	 *
	 * @param args
	 *            The command line arguments provided when the program was
	 *            executed. The command line arguments are expected to be in the
	 *            following order and only accepted this way. However, it is
	 *            possible to omit trailing arguments, hence to provide only the
	 *            first N instead of all.
	 *
	 *            No. Meaning Possible Values
	 *
	 *            (1) random-seed (int)
	 *            (2) board-width (int)
	 *            (3) board-heigh (int)
	 *            (4) use-human   ("true" for true otherwise false)*
	 *
	 *            * See string2boolean for more information.
	 */
	public static void main(String[] args) {

		// Initialize the random seed, board size and user interface choice.
		int randomSeed = DEFAULT_RANDOM_SEED;
		int boardWidth = DEFAULT_WIDTH;
		int boardHeight = DEFAULT_HEIGHT;
		boolean useHuman = DEFAULT_USE_HUMAN;

		// The first argument (if given) is the random seed we will use.
		if (args.length > 1)
			randomSeed = string2int(args[1], randomSeed);

		// The second argument (if given) is the board width.
		if (args.length > 2)
			boardWidth = string2int(args[2], boardWidth);

		// The third argument (if given) is the board height.
		if (args.length > 3)
			boardHeight = string2int(args[3], boardHeight);

		// The fourth argument (if given) is the player choice.
		if (args.length > 4)
			useHuman = string2boolean(args[4]);

		// Create a random object that allows to get new random numbers with the
		// r.nextInt(upperBound);
		// member function.
		Random r = new Random(randomSeed);

		// Create an instance of the 2048 simulator.
		SimulatorInterface game = TTFEFactory.createSimulator(boardWidth,
				boardHeight, r);

		// Create an instance of the user interface that is used to communicate
		// with the user.
		UserInterface ui = TTFEFactory.createUserInterface(game);

		// If requested, the user is asked what player should be used.
		if (DEBUG_ASK_USER) {
			String[] PossibleAnswers = { "h", "c" };
			String Answer = ui.getUserInput(
					"Use a (h)uman or (c)omputer player?", PossibleAnswers);
			useHuman = Answer.charAt(0) == 'h';
		}

		// Create an instance of a player.
		PlayerInterface player = TTFEFactory.createPlayer(useHuman);

		// Simulate the game until it is over.
		game.run(player, ui);
	}
}