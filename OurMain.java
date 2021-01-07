import java.util.Scanner;

//This is the main class that runs the game.
public class OurMain extends ComputerPlayer {
	public static void main(String[] args) {
		
		//Get user input for depth and starting player and start the game.
		Scanner in = new Scanner(System.in);
		ComputerPlayer cp = new ComputerPlayer();
		HumanPlayer board = new HumanPlayer();
		board.createArray();
		board.printArray();
		
		
		System.out.println("Who do you want to play first? \nComputer (O) or You (X)?\n");
		String currentPlayer = in.nextLine();
		while(!(currentPlayer.equals("X") || currentPlayer.equals("O"))) {
			System.out.println("WRONG PLAYER! \nPlease choose 'O' for the computer or 'X' for you ");
			currentPlayer=in.nextLine();
		}
		if(currentPlayer.equals("O")) currentPlayer=" O";
		if(currentPlayer.equals("X")) currentPlayer=" X";

		
		System.out.println("Please choose difficulty for the game.\n");
		int depth=in.nextInt();
		cp.setDepth(depth);
		
		//This is a while loop that calls the User or the PC to make a move and then applies their move on the board.
		//If there are no moves it ends the game.
		while(!board.isTerminal()) {
			if(currentPlayer==" X") {
				System.out.println("Now it is your (X) turn!");
				board.selectSquare();
				System.out.println("You: "+count(" X")+" VS Computer: "+count(" O"));
				currentPlayer = " O";
			} else { 
				System.out.println("Computer is thinking, please wait..."+"\n");
				Node bestChoice = cp.MiniMax(" O");
				if(bestChoice.getData()!= null) { 
					board.setTable(bestChoice.getData());
					System.out.println("You: "+count(" X")+" VS Computer: "+count(" O"));
				} else {
					System.out.println("Computer can make no moves, and so it passes.");
					board.setPCPass(true);
				}
				currentPlayer = " X";
			}
			if(!board.isTerminal())	board.printArray();
		}
		System.out.println("The game is over.");
		board.finalCount();
	}	
}