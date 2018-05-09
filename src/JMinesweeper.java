import java.util.Random;
import java.util.Scanner;

public class JMinesweeper {
	
	static boolean gameRunning = true;
	
	static Scanner scan = new Scanner(System.in);
	
	static Random rand = new Random();
	
	static int boardSizeX = 20;
	static int boardSizeY = 20;
	static int numberOfBombs = 10;
	static int[][] board = new int[boardSizeX+2][boardSizeY+2];
	static boolean[][] viewableBoard = new boolean[boardSizeX+2][boardSizeY+2];
	
	//what some numbers mean
		//-99 is a border
		//-2 is a bomb
		//every other number is bombs adjacent
	
	
	//board rendering
	static boolean rawData = true;
	
	static String representsBomb = "B";
	static String representsBlank = " ";
	static String representsUntouchedTile = "#";
	
	public static void main(String[] args) {
		
		
		System.out.println("generating board");
		generateBoard();
		System.out.println("board created");
		
		typeBoard(true);
		
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
	
	public static void typeBoard(boolean cheat) {
		
		if(cheat) {
			//cheat
			for(int i = 0; i < boardSizeX+2; i++) {
			    for(int j = 0; j < boardSizeY+2; j++) {
			        if(rawData) {
				        if(board[i][j] == 0) {
				            System.out.print(representsBlank);
				        } else if(board[i][j] == 9) {
				            System.out.print(representsBomb);
				        } else {
				            System.out.print(board[i][j]);
				        }
			        } else {
			        	System.out.print(board[i][j]);
			        }
			        
			        System.out.print(" ");
			        
			    }
			    System.out.println();
			}
		} else {
			//non cheat
			for(int i = 0; i < boardSizeX+2; i++) {
			    for(int j = 0; j < boardSizeY+2; j++) {
			    	
			    	
			    	
			    }
			}
		}
	}
	
	public static void generateBoard() {
		
		//generate borders
// 		for(int i = 0; i < boardSizeX+2; i++) {
// 			board[i][0] = -99;
// 			board[i][boardSizeX+1] = -99;
// 		}
		
		//set bombs
		for(int i = 0; i < numberOfBombs; i++) {
			int randx = rand.nextInt(boardSizeX-1)+1;
			int randy = rand.nextInt(boardSizeY-1)+1;
			
			if(board[randx][randy] == 9) {
				i--;
			} else {
				board[randx][randy] = 9;
				System.out.println("new bomb at X:" + randx + " Y: " + randy);
			}
			
		}
		
		//set numbers
		for(int i = 0; i < boardSizeX-1; i++) {
			for(int j = 0; j < boardSizeY-1; j++) {
				if(board[i+1][j+1] == 9) {} else {
				board[i+1][j+1] = bombsAdjacent(i,j);
				}
			}
		}
	}
	
	public static int bombsAdjacent(int x , int y) {
		int output = 0;
		int bombID = 9;
		
		x++;
		y++;
		
		if(board[x+1][y+1] == bombID) {
			output++;
		}
		if(board[x+1][y] == bombID) {
			output++;
		}
		if(board[x+1][y-1] == bombID) {
			output++;
		}
		if(board[x][y+1] == bombID) {
			output++;
		}
		if(board[x][y-1] == bombID) {
			output++;
		}
		if(board[x-1][y+1] == bombID) {
			output++;
		}
		if(board[x-1][y] == bombID) {
			output++;
		}
		if(board[x-1][y-1] == bombID) {
			output++;
		}
		
		return output;
	}
	
}
