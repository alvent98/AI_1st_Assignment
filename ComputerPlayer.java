import java.util.*;

//This is a class for the artificial inteligence and the moves the computer makes.
public class ComputerPlayer extends HumanPlayer {
	
	HumanPlayer arrayInstance = new HumanPlayer();
	private int maxDepth;
	
	//Setter of depth
	public void setDepth(int depth) {
		maxDepth=depth;
	}
	
	//This is a node class that contains the board and its score.
    public class Node {
        private String[][] data = null;
		private int score = 0;
		
		//Getter
		public String[][] getData() { return data; }
		
		//Prints data
		public void printData() {
			for(int row=0; row<8; row++){
				for(int col=0; col<8; col++){
					System.out.print(data[row][col]);
				}
				System.out.println("");
			}
		}
	}
	
	//These are the heuristics of the game
	public void heuristic(Node currentNode, String currentPlayer) {
		String[][] currentState = currentNode.data;
		
		//first heuristic (corners)
		if(currentState[0][0]==currentPlayer) currentNode.score += 3;
		if(currentState[0][7]==currentPlayer) currentNode.score += 3;
		if(currentState[7][0]==currentPlayer) currentNode.score += 3;
		if(currentState[7][7]==currentPlayer) currentNode.score += 3;
		
		// second heuristic (sides)
		for(int i =2;i<6;i++) {
			if(currentState[i][0]==currentPlayer) currentNode.score += 2;
			if(currentState[i][7]==currentPlayer) currentNode.score += 2;
			if(currentState[0][i]==currentPlayer) currentNode.score += 2;
			if(currentState[7][i]==currentPlayer) currentNode.score += 2;
		}
		
		// third heuristic(Max pieces we can get)
		for(int i =2;i<6;i++) {
			for(int j =2;j<6;j++) {
				if(currentState[i][j]==currentPlayer) {
					currentNode.score++;
				}
 			}
		}
		
		// fourth heuristic(bad moves)
		int array[] = {1,6};
		for(int i = 0; i <8;i++) {
			if(currentState[i][array[0]]==currentPlayer)  currentNode.score--;
			if(currentState[i][array[1]]==currentPlayer)  currentNode.score--;
			if(currentState[array[0]][i]==currentPlayer)  currentNode.score--;
			if(currentState[array[1]][i]==currentPlayer)  currentNode.score--;
		}
	}
	
	// Creates the children of a state, current or future one.
	public ArrayList<Node> getChildren(String currentPlayer){
		ArrayList<Node> children = new ArrayList<Node>();
		boolean isToBeChanged = true,moved = false;
		for(int row=0; row<8; row++){
			for(int col=0; col<8; col++){
				String[][] rootBoard = arrayInstance.deepCopy(arrayInstance.getTable());
				if(rootBoard[row][col]==" -") {
					if(isValidMove(row, col,currentPlayer,rootBoard,isToBeChanged)) {	
						Node child = new Node();
						child.data = rootBoard;
						heuristic(child, currentPlayer);
						children.add(child);
						moved = true;
					}
				}
			}
		}
		return children;
	}
	
	//Initiates the MiniMax algorithm
	public Node MiniMax(String currentPlayer){
		Node rootBoard = new Node();
		rootBoard.data = arrayInstance.getTable();
        //If the X plays then it wants to MAXimize the heuristics value
        if (currentPlayer == " X") {
            return max(rootBoard, 0);
        } else { //If the O plays then it wants to MINimize the heuristics value
            return min(rootBoard, 0);
        }
	}
	
	// The max and min functions are called interchangingly, one after another until a max depth is reached
	public Node max(Node currentNode, int depth) {
        Random r = new Random();

        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
		if((arrayInstance.isTerminal()) || (depth == maxDepth)) {
			Node lastMove = new Node();
			lastMove.data=currentNode.data;
			lastMove.score=currentNode.score;
			return lastMove;
		}
        //The children-moves of the state are calculated
		ArrayList<Node> children = new ArrayList<Node>(getChildren(" X"));
		Node maxNode = new Node();
		maxNode.score = Integer.MIN_VALUE;
		for (Node child : children) {
            //And for each child min is called, on a lower depth
			Node move = min(child, depth + 1);
            //The child-move with the greatest value is selected and returned by max
			if(move.score >= maxNode.score) {
                if ((move.score == maxNode.score)) {
                    //If the heuristic has the save value then we randomly choose one of the two moves
                    if (r.nextInt(2) == 0) {
                        maxNode.data = child.data;
                        maxNode.score = child.score;
                    }
                } else {
                    maxNode.data = child.data;
					maxNode.score = child.score;
                }
			}
		}
		return maxNode;
	}
	
	//Min works similarly to max
	public Node min(Node currentNode, int depth){
        Random r = new Random();

		if((arrayInstance.isTerminal()) || (depth == maxDepth)) {
			Node lastMove = new Node();
			lastMove.data=currentNode.data;
			lastMove.score=currentNode.score;
			return lastMove;
		}
		ArrayList<Node> children = new ArrayList<Node>(getChildren(" O"));
		Node minNode = new Node();
		minNode.score = Integer.MAX_VALUE;
		for (Node child : children) {
            //And for each child min is called, on a lower depth
			Node move = max(child, depth + 1);
            //The child-move with the greatest value is selected and returned by max
			if(move.score <= minNode.score) {
                if ((move.score == minNode.score)) {
                    //If the heuristic has the save value then we randomly choose one of the two moves
                    if (r.nextInt(2) == 0) {
                        minNode.data = child.data;
                        minNode.score = child.score;
                    }
                } else {
                    minNode.data = child.data;
					minNode.score = child.score;
                }
			}
		}
		return minNode;
	}
}