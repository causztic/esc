package problemSet2;

import java.util.Scanner;

// Factory Design Pattern is used to separate the concern of the Board creation away from the game.
public class TicTacToe {
	
	public static void main(String[] args){
		boolean won = false;
		int currentPlayer = 1;
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Select a board: 1: 3x3, 2: 4x4.");
			int choice = sc.nextInt();
			Board board = BoardFactory.generateBoard(choice);
			sc.nextLine();
			while (!won){
				for (int i = 1; i < 3; i++){
					try {
						currentPlayer = i;
						System.out.println("Player " + i + "'s turn. Enter x and y coordinates 0-indexed, separated by a comma. (e.g. 1,2)");
						String input = sc.nextLine();
						String[] coords = input.split(",");
						int y = Integer.parseInt(coords[0]);
						int x = Integer.parseInt(coords[1]);
						board.setCoordinates(i, x, y);
					} catch (Exception e){
						System.err.println(e.getMessage());
						i -= 1; // go back by one step
					} finally {
						board.printBoard();
						won = board.checkState();
						if (won)
							break;
					}
				}
			}
			System.out.println("Player " + currentPlayer + " has won!");
		} catch (Exception e){
			System.err.println(e.getMessage());
		}

		sc.close();
	}
}

class Board{
	private char[][] boardState;
	private int size, previousX, previousY;
	private char symbol;
	Board(int size){
		this.size = size;
		boardState = new char[size][size];
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				boardState[i][j] = ' ';
			}
		}
	}
	
	void setCoordinates(int turn, int x, int y) throws Exception{
		if (x >= size || x < 0 || y >= size || y < 0){
			throw new Exception("Coordinates cannot be >= " + size + " or < 0");
		}
		if (boardState[x][y] == ' '){
			// item not set yet
			symbol = turn % 2 == 0 ? 'X' : 'O';
			boardState[x][y] = symbol;
			previousX = x;
			previousY = y;
		} else {
			throw new Exception("Square already set.");
		}
	}
	void printBoard(){
		for (int i = 0; i < size; i++){
			String row = "| ";
			for (int j = 0; j < size; j++){
				row += boardState[i][j] + " | ";
			}
			System.out.println(row);
		}
	}
	private boolean checkVertical(){
		for (int x = 0; x < size; x++){
			if (boardState[x][previousY] != symbol)
				return false;
		}
		return true;
	}
	
	private boolean checkHorizontal(){
		for (int y = 0; y < size; y++){
			if (boardState[previousX][y] != symbol)
				return false;
		}
		return true;
	}
	
	private boolean checkDiagonal(){
		for (int i = 0; i < size; i++){
			if (boardState[i][i] != symbol)
				return false;
		}
		return true;
	}
	
	boolean checkState(){
		return checkHorizontal() || checkVertical() || checkDiagonal();
	}
}

class BoardFactory{
	static Board generateBoard(int choice) throws Exception{
		if (choice == 1){
			return new Board(3);
		} else if (choice == 2){
			return new Board(4);
		}
		throw new Exception("Invalid choice.");
	}
}
