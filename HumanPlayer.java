import java.util.*;

//This is a class that gets the user input and makes a move, creates and interacts with the game board, keeps count of the pieces on the board
//and checks if there are no moves to be made.
public class HumanPlayer extends MoveValidation	 {
	private static String[][] othelloTable;
	private int row,column;
	private static boolean PCPass = false;
	private boolean pass= false;
	
	//Creates the game board
	public void createArray() {
		othelloTable = new String[8][8];
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				othelloTable[i][j] = " -";
			}
		}
		othelloTable[3][3] = " O";
		othelloTable[3][4] = " X";
		othelloTable[4][3] = " X";
		othelloTable[4][4] = " O";				
	}
	
	//Gets the user input and makes a move.
	public void selectSquare() {
		MoveValidation checkMove = new MoveValidation();
		Scanner in = new Scanner(System.in);
		boolean success = false;
		boolean isToBeChanged = false;
		boolean canMakeMove = true;
		//This checks if the player's move is valid
		pass = true;
		for(int i = 0;i <8;i++) {
			for(int j = 0;j<8;j++) {
				if(othelloTable[i][j]==" -") {
					canMakeMove = checkMove.isValidMove(i,j," X",getTable(),isToBeChanged);
					if(canMakeMove) pass = false;		
				}				
			}
		}
		if(pass) System.out.println("You cannot play; the computer plays again.");
		while(!success && !pass) {
		// Configuration
			while(true) {
				System.out.println("Please select the square you want to place your piece:");
				System.out.println("Select the row:");
				while(true) {
					row = in.nextInt() - 1;
					if(row>=0 && row <=7) {
						break;
					} else {
						System.out.println("ERROR: NOT_A_VALID_ROW \nPlease enter a value between 1 and 8:");
					}
				}	
				System.out.println("Select the column:");
				while(true) {
					column = in.nextInt() - 1;
					if(column>=0 && column <=7) {
						break;
					} else {
						System.out.println("ERROR: NOT_A_VALID_COLUMN \nPlease enter a value between 1 and 8:");
					}
				}
				if(othelloTable[row][column].equals(" -")) {
					break;
				} else {
					System.out.println("The cell has already a piece in it. Please retype row & column.");
				}
			}
			isToBeChanged = true;
			success=checkMove.isValidMove(row,column," X",getTable(),isToBeChanged);
			if(!success) System.out.println("ERROR! This is not a valid move!\nPlease do a new move");
		}
	}
	
	//Gets the game board
	public String[][] getTable(){
		return othelloTable;
	}
	
	//Copies the board using deepCopy
	public String[][] deepCopy(String[][] arrayToCopy) {
		String[][] arrayToReturn = new String[arrayToCopy.length][arrayToCopy[0].length];
		for (int i = 0; i < arrayToCopy.length; i++) {
			for (int j = 0; j < arrayToCopy[0].length; j++) {
				arrayToReturn[i][j] = arrayToCopy[i][j];
			}
		}
		return arrayToReturn;
	}
	
	//Sets the game board
	public void setTable(String[][] newTable){
		othelloTable = newTable;	
	}	
	
	//Sets the PCPass(when the computer has no moves)
	public void setPCPass(boolean pass){
		PCPass = pass;
	}
	
	//Prints the game board
	public void printArray() {
		System.out.println("  1 2 3 4 5 6 7 8");
		for(int i = 0;i<8;i++) {
			System.out.print(i+1);
			for(int j = 0;j<8;j++) {
				System.out.print(othelloTable[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	//Counts the pieces on the final board
	public void finalCount() {
		int countO = count(" O");
		int countX = count(" X");
		if(countO>countX) System.out.println("Computer won, with "+countO+" vs "+countX+" pieces.");
		if(countO<countX) System.out.println("Congratulations! You won, with "+countX+" vs "+countO+" pieces.");
		if(countO==countX) System.out.println("Draw...");
	}
	
	//Counts the pieces at any point during the game
	public static int count(String player) {
		int sum =0;
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				if(othelloTable[i][j]==player) sum++;
			}
		}
		return sum;
	}
	
	//This method returns true if nobody can make any moves on the board
	public boolean isTerminal(){
		 if(PCPass == true && pass == true) return true;
		 return false;
	}	 
}