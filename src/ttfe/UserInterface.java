package ttfe;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;


public interface UserInterface {
	
	/**
	 * Allow the user to answer a specific question.
	 * 
	 * @param question The question the user is supposed to answer.
	 * @param possibleAnswers The acceptable answers.
	 * 
	 * @return The answer by the user that was in PossibleAnswers.
	 * 
	 * Note: This function will not be tested but might allow you to easily test
	 *       and present your project later. (see also DEBUG_ASK_USER in TTFE.java).
	 */
	public String getUserInput(String question, String[] possibleAnswers);
	
	/**
	 * Allow the user to choose the next move direction.
	 * 
	 * @return The move direction chosen by the user.
	 */
	public MoveDirection getUserMove();
	
	/**
	 * Show a "Game Over" screen to the user including the final score and
	 * the number of moves performed.
	 * 
	 * @param game The current TTFE game instance.
	 */
	public void showGameOverScreen(SimulatorInterface game);
	
	/**
	 * Show a message to the user.
	 * 
	 * @param msg The message the user should see.
	 */
	public void showMessage(String msg);

	/**
	 * Update the current state of the board visible to the user.
	 *
	 * @param game The current TTFE game instance.
	 */
	public void updateScreen(SimulatorInterface game);

}