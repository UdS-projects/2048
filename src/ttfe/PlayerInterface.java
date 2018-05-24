package ttfe;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.UserInterface;


public interface PlayerInterface {
	
	/**	 
	 * Allow the player (human or computer) to choose the next move direction. 
	 * 
	 * @param game The current TTFE game instance.
	 * @param ui   The user interface allows for communication.
	 * 
	 * @return The move direction chosen by the user.
	 */
	MoveDirection getPlayerMove(SimulatorInterface game, UserInterface ui);
	
}
