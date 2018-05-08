import java.util.Random;
import java.util.Scanner;

public class JMinesweeper {
	
	static boolean gameRunning = true;
	
	static Scanner scan = new Scanner(System.in);
	
	static Random rand = new Random();
	
	static int boardSizeX = 10;
	static int boardSizeY = 10;
	static int numberOfBombs = 5;
	static int[][] board = new int[boardSizeX+2][boardSizeY+2];
	
	//what some numbers mean
		//-99 is a border
		//-2 is a bomb
		//every other number is bombs adjacent
	
	
	//board rendering
	static String representsBomb = "B";
	static String representsBlank = " ";
	
	public static void main(String[] args) {
		
		
		System.out.println("generating board");
		generateBoard();
		System.out.println("board created");
		
		while(gameRunning) {
			
			int selectedX = -1;
			int selectedY = -1;
			
			String input = scan.nextLine();
			String[] spaceFilter = input.split(" ");
			String[] positionFilter = spaceFilter[1].split(",");
			
			selectedX = Integer.parseInt(positionFilter[0]);
			selectedY = Integer.parseInt(positionFilter[1]);
			
			if(spaceFilter[0].equals("S")) {
				step(selectedX, selectedY);
			} else {
				flag(selectedX, selectedY);
			}
			
		}
		
	}
	
	public static void step(int x, int y) {
		
	}
	
	public static void flag(int x, int y) {
		
	}
	
	public static void typeBoard() {
		
	}
	
	public static void generateBoard() {
		
		//generate borders
		for(int i = 0; i < boardSizeX+2; i++) {
			board[i][0] = -99;
			board[i][boardSizeX+1] = -99;
		}
		
		//set bombs
		for(int i = 0; i < numberOfBombs; i++) {
			int randx = rand.nextInt(boardSizeX);
			int randy = rand.nextInt(boardSizeY);
			
			if(board[randx][randy] == -2) {
				i--;
			} else {
				board[randx][randy] = -2;
			}
			
		}
		
		//set numbers
		for(int i = 0; i < boardSizeX; i++) {
			for(int j = 0; j < boardSizeY; j++) {
				board[i][j] = bombsAdjacent(i,j);
			}
		}
	}
	
	public static int bombsAdjacent(int x , int y) {
		int output = 0;
		
		if(board[x+1][y+1] == -2) {
			output++;
		}
		if(board[x+1][y] == -2) {
			output++;
		}
		if(board[x+1][y-1] == -2) {
			output++;
		}
		if(board[x][y+1] == -2) {
			output++;
		}
		if(board[x][y-1] == -2) {
			output++;
		}
		if(board[x-1][y+1] == -2) {
			output++;
		}
		if(board[x-1][y] == -2) {
			output++;
		}
		if(board[x-1][y-1] == -2) {
			output++;
		}
		
		return output;
	}
	
}
