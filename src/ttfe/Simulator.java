package ttfe;
// created from me

import java.util.Random;


public class Simulator implements SimulatorInterface {
	
	//main method -- for testing purposes
//	public static void main(String[] args) {
//		Simulator newGame = (Simulator) TTFEFactory.createSimulator(4, 4, new Random(0));
//		HumanPlayer humanPlayer = (HumanPlayer) TTFEFactory.createPlayer(true);
//		UserInterface mainUI = TTFEFactory.createUserInterface(newGame);
//		
//		//this method runs the game
//		newGame.run(humanPlayer, mainUI);
//	}
  
	int height,  width;
	Random r;
	int[][] Spielfeld;
	int points;                                                      //points counter
	int move;                                                        //move counter
	
	/**
	 * this constructor creates a new Simulator
	 * @param height
	 * @param width
	 * @param seed
	 */
	public Simulator(int width , int height, Random seed){
		this.Spielfeld = new int[width][height];
		this.height = height;
		this.width = width;
		this.r = seed;
		addPiece();
		addPiece();
	}
	
	
	@Override
	public void addPiece() {
		int e = 0; //this is the new piece's value
		
		if (r.nextInt(100) > 10) {
			e = 2;
		} else {
			e = 4;
		}
		
		
		int widthCoordinate, heightCoordinate;
		boolean added = false;
		
		if (isSpaceLeft()) {
			while (added == false) {
				//try to add new piece
				widthCoordinate = r.nextInt(width);
				heightCoordinate = r.nextInt(height);
				
				//check if coordinate is available
				if (getPieceAt(widthCoordinate, heightCoordinate) == 0) {
					
					//set the piece
					setPieceAt(widthCoordinate, heightCoordinate, e);
					added = true;
				}
			}
		}
		
	}
	
	@Override
	public int getBoardHeight() {
		
		return height;
	}
	@Override
	public int getBoardWidth() {
		
		return width;
	}
	@Override
	public int getNumMoves() {
		
//		int akku =0;
//		if (performMove(MoveDirection.EAST)) {
//			akku++;
//		}
//		else {
//			if (performMove(MoveDirection.NORTH)) {
//				akku++;
//			}
//			else {
//				if (performMove(MoveDirection.SOUTH)) {
//					akku++;
//				}
//				else {
//					if (performMove(MoveDirection.WEST)) {
//						akku++;
//					}
//					else {
//						return akku;
//					}
//				}
//			}
//		}
//		return akku;
		return move;
	}
	
	@Override
	public int getNumPieces() {
		
		int Pieces = 0;                                 //z�hlt die steine
		
		for(int i=0; i< height;i++) {
			for(int j=0;j<width;j++) {
				if (getPieceAt(j, i)>0) {
					Pieces++;
				}
				else {
					continue;
				}
			}
		}
		return Pieces;
	}
	
	@Override
	public int getPieceAt(int x, int y) {
		
		if (x<width && y<height && x>=0 && y>=0) {
			return this.Spielfeld[x][y];
		}
		
		else System.out.println(String.format("Keine gueltige Eingabe an x = %d und y = %d", x,y));
		
		return 0;
	}
	
	@Override
	public int getPoints() {
		
		return points;
	}
	@Override
	public boolean isMovePossible() {
		
		boolean tr = true;
		for(int x = 0 ; x<this.width;x++){
			
			for(int y=0;y<this.height;y++) {
				
				if(Spielfeld[x][y]!=0) {
					tr=false;
					
				}
				
			}
			
		}
		
		if(tr)
			return false;
		
for(int x = 0 ; x<this.width;x++){
			
			for(int y=0;y<this.height-1;y++) {
				
				if (isSpaceLeft()||Spielfeld[x][y]==Spielfeld[x][y+1]) {
					
					return true;
				}
					
				}
				
			}

for(int x = 0 ; x<this.width-1;x++){
	
	for(int y=0;y<this.height;y++) {
		
		if (Spielfeld[x][y]==Spielfeld[x+1][y]) {
			
			return true;
		}
			
		}
		
	}
			
		
		
		
		
		return false;
	}
	
	@Override
	public boolean isMovePossible(MoveDirection direction) {
		
		switch (direction) {
		
			case NORTH:
				for (int x = 0; x < this.width; x++) {
					for (int y = 0; y < this.height-1; y++) {
						//check if values equal or empty space
						if (getPieceAt(x,y) == getPieceAt(x,y+1) || (getPieceAt(x,y) == 0)) {
							return true;
						}
					}
				}
				//if he did not find an empty field
				return false;

			case SOUTH:
				for (int x = 0; x < this.width; x++) {
					for (int y = this.height-1; y > 0; y--) {
						//check if values equal or empty space
						if (getPieceAt(x,y) == getPieceAt(x,y-1) || (getPieceAt(x,y) == 0)) {
							return true;
						}
					}
				}
				//if he did not find an empty field
				return false;
		
			case WEST:
//				for (int x = this.width-1; x > 0; x--) {
//					for (int y = 0; y < this.height; y++) {
				for (int x = 0; x < this.width - 1; x++) {
					for (int y = 0; y < this.height; y++) {
						//check if values equal or empty space
						if (getPieceAt(x,y) == getPieceAt(x+1,y) || (getPieceAt(x,y) == 0)) {    //changed - with +1
							return true;
						}
					}
				}
				return false;
			case EAST:
//				for (int x = 1; x < this.width - 1; x++) {
//					for (int y = 0; y < this.height; y++) {
				for (int x = this.width-1; x > 0; x--) {
					for (int y = 0; y < this.height; y++) {
						//check if values equal or empty space
						if (getPieceAt(x,y) == getPieceAt(x-1,y) || (getPieceAt(x,y) == 0)) {       //changed + with -1
							return true;
						}
					}
				}
				return false;
			
			}
		
		return false;
	}
	
	@Override
	public boolean isSpaceLeft() {
		
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				if (getPieceAt(i,j)==0) {
					return true;
					
				}
				
			}
		}
		
		return false;
	}
	
	@Override
	public boolean performMove(MoveDirection direction) {
		
		int value;      //value of the pieces
		
		if (isMovePossible(direction)) {
			switch (direction) {
			
			case NORTH: 	
			//fill the fields
				for(int x=0;x<width;x++){
					for(int y=0;y<height-1;y++) {
					
						if (getPieceAt(x,y)==0) {
							int z = y+1;
							while(z < height ) {
							if (	(value= getPieceAt(x,z) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(x,z,0);
								break;
							}
							else z+=1;
							}
						}
					}
				}
				

				for(int x=0;x<width;x++) {
					for(int y=0;y<height-1;y++) {
						if ((value=getPieceAt(x,y))== getPieceAt(x,y+1)) {
							setPieceAt(x,y,2*value);
							points += getPieceAt(x,y);                 //new score
							setPieceAt(x,y+1,0);
						}
					}
				}

				//fill the fields
				for(int x=0;x<width;x++){
					for(int y=0;y<height-1;y++) {
						if (getPieceAt(x,y)==0) {
							int z = y+1;
							while(z < height ) {
							if (	(value= getPieceAt(x,z) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(x,z,0);
								break;
							}
							else z+=1;
							}
						}
					}
				}
				move += 1;
				return true;
			case SOUTH:
				

				//fill the fields
				for(int x=0;x<width;x++){
					for(int y=height-1;y>0;y--) {
						if (getPieceAt(x,y)==0) {
							int z = y-1;
							while(z > 0 ) {
							if (	(value= getPieceAt(x,z) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(x,z,0);
								break;
							}
							else z -=1;
							}
						}
					}
				}
				for(int x=0;x<width;x++) {
					for(int y=height-1;y>0;y--) {
						if ((value=getPieceAt(x,y))== getPieceAt(x,y-1)) {
							setPieceAt(x,y,2*value);
							points += getPieceAt(x,y);
							setPieceAt(x,y-1,0);
						}
					}
				}
				//fill the fields
				for(int x=0;x<width;x++){
					for(int y=height-1;y>0;y--) {
						if (getPieceAt(x,y)==0) {
							int z = y-1;
							while(z > 0 ) {
							if (	(value= getPieceAt(x,z) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(x,z,0);
								break;
							}
							else z -=1;
							}
						}
					}
				}

				move += 1;
				return true;
			case EAST:

				//fill the fields
				for(int y=0;y<height;y++){
					for(int x=width-1;x>0;x--) {
						if (getPieceAt(x,y)==0) {
							int z = x-1;
							while(z > 0 ) {
							if (	(value= getPieceAt(z,y) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(z,y,0);
								break;
							}
							else z -= 1;
							}
						}
					}
				}

				for(int y=0;y<height;y++) {
					for(int x=width-1;x>0;x--) {
						if ((value=getPieceAt(x,y))== getPieceAt(x-1,y)) {
							setPieceAt(x,y,2*value);
							points += getPieceAt(x,y);
							setPieceAt(x-1,y,0);
						}
					}
				}

				for(int y=0;y<height;y++){
					for(int x=width-1;x>0;x--) {
						if (getPieceAt(x,y)==0) {
							int z = x-1;
							while(z > 0 ) {
							if (	(value= getPieceAt(z,y) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(z,y,0);
								break;
							}
							else z -= 1;
							}
						}
					}
				}
				move += 1;
				return true;
			case WEST:

				for(int y=0;y<height;y++){
					for(int x=0;x<width-1;x++) {
						if (getPieceAt(x,y)==0) {
							int z = x+1;
							while(z < width ) {
							if (	(value= getPieceAt(z,y) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(z,y,0);
								break;
							}
							else z +=1;
							}
						}
					}
				}
				for(int y=0;y<height;y++) {
					for(int x=0;x<width-1;x++) {
						if ((value=getPieceAt(x,y))== getPieceAt(x+1,y)) {
							setPieceAt(x,y,2*value);
							points += getPieceAt(x,y);
							setPieceAt(x+1,y,0);
						}
					}
				}

				for(int y=0;y<height;y++){
					for(int x=0;x<width-1;x++) {
						if (getPieceAt(x,y)==0) {
							int z = x+1;
							while(z < width ) {
							if (	(value= getPieceAt(z,y) )> 0) {
								setPieceAt(x,y,value);
								setPieceAt(z,y,0);
								break;
							}
							else z += 1;
							}
						}
					}
				}
				move += 1;
				return true;
			}
		}
		 return false;
	}
	
	@Override
	public void run(PlayerInterface player, UserInterface ui) {
		//this is the direction
		MoveDirection direction;
		
		
		//get the moves until moves are not possible
		while (this.isMovePossible()) {
			//get the direction
			direction = player.getPlayerMove(this, ui);
			
			if (this.isMovePossible(direction)) {
				//missing: add the points in performMove()
				this.performMove(direction);
				ui.updateScreen(this);
			} else {
				//show error message that the direction is not possible
				
			}
			ui.updateScreen(this);
			
		}
		
		//there are no moves possible so show the game over screen
		ui.showGameOverScreen(this);
	}
	
	@Override
	public void setPieceAt(int x, int y, int piece) {
		Spielfeld[x][y]=piece;
	}
	
}
