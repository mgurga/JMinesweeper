import java.util.Random;
import java.util.Scanner;

public class JMinesweeper {

	static boolean gameRunning = true;

	static Scanner scan = new Scanner(System.in);

	static Random rand = new Random();

	static int boardSizeX = 10;
	static int boardSizeY = 10;
	static int numberOfBombs = 2;
	static int[][] board = new int[boardSizeX + 2][boardSizeY + 2];
	static boolean[][] viewableBoard = new boolean[boardSizeX + 2][boardSizeY + 2];

	// what some numbers mean
	// -99 is a border
	// -2 is a bomb
	// every other number is bombs adjacent

	// board rendering
	static boolean rawData = true;

	static String representsBomb = "B";
	static String representsBlank = " ";
	static String representsUntouchedTile = "#";

	public static void main(String[] args) {

		System.out.println("generating board");
		generateBoard();
		System.out.println("board created");

		typeBoard(true);
		typeBoard(false);

		while (gameRunning) {

			int selectedX = -1;
			int selectedY = -1;

			String input = scan.nextLine();
			String[] spaceFilter = input.split(" ");
			String[] positionFilter = spaceFilter[1].split(",");

			selectedX = Integer.parseInt(positionFilter[0]);
			selectedY = Integer.parseInt(positionFilter[1]);

			if (spaceFilter[0].equals("S")) {
				step(selectedX + 1, selectedY + 1);
			} else {
				flag(selectedX + 1, selectedY + 1);
			}

		}

	}

	public static void step(int x, int y) {
		if (board[y][x] == 9) {
			gameRunning = false;
			System.out.println("GAME OVER YOU STEPPED ON A BOMB");
			System.out.println("GAME BOARD BELOW");
			typeBoard(true);
		} else {
			System.out.println(board[y][x]);
		}

	}

	public static void flag(int x, int y) {

	}

	public static void typeBoard(boolean cheat) {

		if (cheat) {
			// cheat
			String top = "|";
			for (int i = 0; i < boardSizeX + 2; i++) {
				top += "- ";
			}
			System.out.print(top);
			for (int i = 0; i < boardSizeX + 2; i++) {
				System.out.print("|");
				for (int j = 0; j < boardSizeY + 2; j++) {
					if (rawData) {
						if (board[i][j] == 0) {
							System.out.print(representsBlank);
						} else if (board[i][j] == 9) {
							System.out.print(representsBomb);
						} else {
							System.out.print(board[i][j]);
						}
					} else {
						System.out.print(board[i][j]);
					}

					System.out.print(" ");

				}
				System.out.print("|");
				System.out.println();
			}
			System.out.print(top + "|");
			System.out.println();
		} else {
			// non cheat
			String top = "|";
			for (int i = 0; i < boardSizeX + 2; i++) {
				top += "- ";
			}
			System.out.print(top);
			for (int i = 0; i < boardSizeX + 2; i++) {
				System.out.print("|");
				for (int j = 0; j < boardSizeY + 2; j++) {
					if (rawData) {
						if (viewableBoard[i][j] == true) {
							if (board[i][j] == 0) {
								System.out.print(representsBlank);
							} else if (board[i][j] == 9) {
								System.out.print(representsBomb);
							} else {
								System.out.print(board[i][j]);
							}
						} else {
							System.out.print(representsUntouchedTile);
						}
					} else {
						System.out.print(board[i][j]);
					}

					System.out.print(" ");

				}
				System.out.print("|");
				System.out.println();
			}
			System.out.print(top + "|");
			System.out.println();
		}
	}

	public static void generateBoard() {

		// generate borders
		// for(int i = 0; i < boardSizeX+2; i++) {
		// board[i][0] = -99;
		// board[i][boardSizeX+1] = -99;
		// }

		// set bombs
		for (int i = 0; i < numberOfBombs; i++) {
			int randx = rand.nextInt(boardSizeX - 1) + 1;
			int randy = rand.nextInt(boardSizeY - 1) + 1;

			if (board[randx][randy] == 9) {
				i--;
			} else {
				board[randx][randy] = 9;
				System.out.println("new bomb at X:" + randx + " Y: " + randy);
			}

		}

		// set numbers
		for (int i = 0; i < boardSizeX - 1; i++) {
			for (int j = 0; j < boardSizeY - 1; j++) {
				if (board[i + 1][j + 1] == 9) {
				} else {
					board[i + 1][j + 1] = bombsAdjacent(i, j);
				}
			}
		}
	}

	public static int bombsAdjacent(int x, int y) {
		int output = 0;
		int bombID = 9;

		x++;
		y++;

		if (board[x + 1][y + 1] == bombID) {
			output++;
		}
		if (board[x + 1][y] == bombID) {
			output++;
		}
		if (board[x + 1][y - 1] == bombID) {
			output++;
		}
		if (board[x][y + 1] == bombID) {
			output++;
		}
		if (board[x][y - 1] == bombID) {
			output++;
		}
		if (board[x - 1][y + 1] == bombID) {
			output++;
		}
		if (board[x - 1][y] == bombID) {
			output++;
		}
		if (board[x - 1][y - 1] == bombID) {
			output++;
		}

		return output;
	}

}
