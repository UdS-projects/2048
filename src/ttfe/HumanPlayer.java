package ttfe;

public class HumanPlayer implements PlayerInterface{       

	//implemented from me
	@Override
	public MoveDirection getPlayerMove(SimulatorInterface game, UserInterface ui) {
		// TODO Auto-generated method stub
		return ui.getUserMove();
	}

}
