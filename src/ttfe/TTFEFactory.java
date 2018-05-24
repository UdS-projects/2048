package ttfe;

import java.util.Random;

import ttfe.PlayerInterface;
import ttfe.SimulatorInterface;
import ttfe.UserInterface;

public class TTFEFactory {

	/**
	 * Create a 2048 simulator.
	 * 
	 * @param width
	 *            The width of the simulated board, must be greater or equal than 2.
	 * @param height
	 *            The height of the simulated board, must be greater or equal than 2.
	 * @param r
	 *            The random object used to create random numbers.
	 * 
	 * @return A 2048 simulator.
	 */
	public static SimulatorInterface createSimulator(int width, int height,
			Random r) {
		// TODO: Implement me.
		return null;
	}

	/**
	 * Create a user interface for a 2048 simulator.
	 * 
	 * @param game
	 *            The 2048 simulator.
	 * 
	 * @return A graphical user interface for a 2048 simulator.
	 */
	public static UserInterface createUserInterface(SimulatorInterface game) {
		// TODO: Implement me.
		return null;
	}

	/**
	 * Create a 2048 player.
	 * 
	 * @param human
	 *            Flag to indicate a human player will play the game.
	 * 
	 * @return A 2048 player that will play automatically if human is false and
	 *         otherwise use the user interface to communicate with the human
	 *         player to select moves.
	 */
	public static PlayerInterface createPlayer(boolean human) {
		// TODO: Implement me.
		return null;
	}

}
