import java.util.*;

//This is a class that checks if a move on the board is valid.
public class MoveValidation{
	//This is a method that checks if a move on the board is valid.
	public boolean isValidMove(int row, int column, String currentPlayer, String[][] currentState, boolean isToBeChanged){
		boolean success = false;
		boolean alreadySuccess = false;
		boolean canMakeMove = false;
		String otherPlayer;
		if(currentPlayer==" O"){
			otherPlayer = " X";
		}else{
			otherPlayer = " O";
		}	
		
		// Direction 2
		if(row >0 && currentState[row-1][column].equals(otherPlayer)) {
			int i;
			success = false;
			for(i = row-1;i>=0;i--) {
				if(!(currentState[i][column].equals(otherPlayer) || currentState[i][column].equals(currentPlayer))) break;
				if(i==row-1 && currentState[i][column].equals(currentPlayer)) break;
				if(i < row-1 && currentState[i][column].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				for(int j= row;j>i;j--) {
					moveAftermath(j,column,currentState,currentPlayer);
					alreadySuccess = true;
				}
			}
		}
		// Direction 7
		if(row < 7 && currentState[row+1][column].equals(otherPlayer)) {
			int i;
			success = false;
			for(i = row+1;i<8;i++) {
				if(!(currentState[i][column].equals(otherPlayer) || currentState[i][column].equals(currentPlayer))) break;
				if(i==row+1 && currentState[i][column].equals(currentPlayer)) break;
				if(i > row+1 && currentState[i][column].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				if(alreadySuccess) {
					for(int j= row+1;j<i;j++) {
						moveAftermath(j,column,currentState,currentPlayer);
					}
				} else {
					for(int j= row;j<i;j++) {
						moveAftermath(j,column,currentState,currentPlayer);
						alreadySuccess =true;
					}
				}
			}
		}
		// Direction 4
		if(column >0 && currentState[row][column-1].equals(otherPlayer)) {
			int i;
			success = false;
			for(i = column - 1;i>=0;i--) {
				if(!(currentState[row][i].equals(otherPlayer) || currentState[row][i].equals(currentPlayer))) break;
				if(i==column-1 && currentState[row][i].equals(currentPlayer)) break;
				if(i < column-1 && currentState[row][i].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				if(alreadySuccess) {
					for(int j= column-1;j>i;j--) {
						moveAftermath(row,j,currentState,currentPlayer);
					}
				} else {
					for(int j= column;j>i;j--) {
						moveAftermath(row,j,currentState,currentPlayer);
					}
					alreadySuccess =true;
				}
			}
		}
		// Direction 5
		if(column<7 && currentState[row][column+1].equals(otherPlayer)) {
			int i;
			success = false;
			for(i = column+1;i<8;i++) {
				if(!(currentState[row][i].equals(otherPlayer) || currentState[row][i].equals(currentPlayer))) break;
				if(i==column+1 && currentState[row][i].equals(currentPlayer)) break;
				if(i > column+1 && currentState[row][i].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				if(alreadySuccess) {
					for(int j=column +1;j<i;j++) {
						moveAftermath(row,j,currentState,currentPlayer);
					}
				} else {
					for(int j=column;j<i;j++) {
						moveAftermath(row,j,currentState,currentPlayer);
					}
					alreadySuccess =true;
				}
			}
		}
		// Direction 1
		if((row>0 && column>0) && currentState[row-1][column-1].equals(otherPlayer)) {
			int i;
			int tempCol = column;
			success = false;
			for(i = row-1;i>=0 && tempCol>0;i--) {
				tempCol--;
				if(!(currentState[i][tempCol].equals(otherPlayer) || currentState[i][tempCol].equals(currentPlayer))) break;
				if(i==row-1 && currentState[i][tempCol].equals(currentPlayer)) break;
				if(i < row-1 && currentState[i][tempCol].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
				
			}
			if(success && isToBeChanged) {
				tempCol = column;
				if(alreadySuccess) {
					for(int j= row-1;j>i;j--) {		
						tempCol--;
						moveAftermath(j,tempCol,currentState,currentPlayer);
					}
				} else {
					for(int j= row;j>i;j--) {					
						moveAftermath(j,tempCol,currentState,currentPlayer);
						tempCol--;
					}
					alreadySuccess =true;
				}
			}
		}		
		//Direction 3
		if((row>0 && column<7) && currentState[row-1][column+1].equals(otherPlayer)) {
			int i;
			int tempCol = column;
			success = false;
			for(i = row-1;i>=0 && tempCol<7;i--) {
				tempCol++;
				if(!(currentState[i][tempCol].equals(otherPlayer) || currentState[i][tempCol].equals(currentPlayer))) break;
				if(i==row-1 && currentState[i][tempCol].equals(currentPlayer)) break;
				if(i < row-1 && currentState[i][tempCol].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				tempCol = column;
				if(alreadySuccess) {
					for(int j= row-1;j>i;j--) {		
						tempCol++;
						moveAftermath(j,tempCol,currentState,currentPlayer);
					}
				} else {
					for(int j= row;j>i;j--) {					
						moveAftermath(j,tempCol,currentState,currentPlayer);
						tempCol++;
					}
					alreadySuccess =true;
				}
			}
		}
		
		//Direction 6
		if((row<7 && column>0) && currentState[row+1][column-1].equals(otherPlayer)) {
			int i;
			int tempCol = column;
			success = false;
			for(i = row+1;i<=7 && tempCol>0;i++) {
				tempCol--;
				if(!(currentState[i][tempCol].equals(otherPlayer) || currentState[i][tempCol].equals(currentPlayer))) break;
				if(i==row+1 && currentState[i][tempCol].equals(currentPlayer)) break;
				if(i > row+1 && currentState[i][tempCol].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				tempCol = column;
				if(alreadySuccess) {
					for(int j= row + 1;j<i;j++) {
						tempCol--;
						moveAftermath(j,tempCol,currentState,currentPlayer);
					}
				} else {
					for(int j= row;j<i;j++) {					
						moveAftermath(j,tempCol,currentState,currentPlayer);
						tempCol--;
					}
					alreadySuccess =true;
				}
			}
		}
		
		//Direction 8
		if((row<7 && column<7) && currentState[row+1][column+1].equals(otherPlayer)) {
			int i;
			int tempCol = column;
			success = false;
			for(i = row+1;i<=7 && tempCol<7;i++) {
				tempCol++;
				if(!(currentState[i][tempCol].equals(otherPlayer) || currentState[i][tempCol].equals(currentPlayer))) break;
				if(i==row+1 && currentState[i][tempCol].equals(currentPlayer)) break;
				if(i > row+1 && currentState[i][tempCol].equals(currentPlayer)) {
					success = true;
					canMakeMove = true;
					break;
				}
			}
			if(success && isToBeChanged) {
				tempCol = column;
				if(alreadySuccess) {
					for(int j= row+1;j<i;j++) {	
						tempCol++;
						moveAftermath(j,tempCol,currentState,currentPlayer);					
					}
				} else {
					for(int j= row;j<i;j++) {					
						moveAftermath(j,tempCol,currentState,currentPlayer);
						tempCol++;
					}
					alreadySuccess =true;
				}
			}
		}
	return canMakeMove;
	}
	
	//This is a method that changes the pieces on the board.
	private void moveAftermath(int row,int column,String[][] currentState,String currentPlayer) {
		currentState[row][column] = changeColour(currentState[row][column],currentPlayer);
	}
	
	//This is a method that changes the color of a piece.
	private String changeColour(String square,String currentPlayer) {
		if(square.equals(" O")) {
			return " X";
		} else if(square.equals(" X")){
			return " O";
		} else {
			return currentPlayer;
		}
	}
}