import java.util.Random;
import java.util.Scanner;

public class JMinesweeper {

	static Scanner scan = new Scanner(System.in);

	static Random rand = new Random();

	static int boardSizeX = 10;
	static int boardSizeY = 10;
	static int numberOfBombs = 10;
	static int[][] board = new int[boardSizeX + 2][boardSizeY + 2];

	static boolean gameRunning = true;
	static boolean[][] viewableBoard = new boolean[boardSizeX + 2][boardSizeY + 2];

	static String incorrectFormat = "INCORRECT FORMAT";
	static String outsideBoundaries = "SELECTION OUTSIDE OF BOARD BOUNDARIES";

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

			if (positionFilter.length < 2 || positionFilter[0] == null || positionFilter[1] == null) {
				System.out.println(incorrectFormat);
			} else {

				selectedX = Integer.parseInt(positionFilter[0]);
				selectedY = Integer.parseInt(positionFilter[1]);

				if (selectedX > boardSizeX || selectedY > boardSizeY) {

					System.out.println(outsideBoundaries);

				} else {

					if (spaceFilter[0].equals("S")) {
						step(selectedX, selectedY);
					} else {
						flag(selectedX, selectedY);
					}
				}
			}

		}

	}

	public static void step(int x, int y) {
		if (board[x][y] == 9) {
			gameRunning = false;
			System.out.println("GAME OVER YOU STEPPED ON A BOMB");
			System.out.println("GAME BOARD BELOW");
			typeBoard(true);
		} else {
			System.out.println(board[y][x]);
			stepClear(x, y);
			// boardSnippet(x, y);
			typeBoard(false);

		}

	}

	public static boolean validTile(int x, int y) {
		boolean output = true;

		if (x < 1) {
			output = false;
		}
		if (y < 1) {
			output = false;
		}
		if (x > boardSizeX - 1) {
			output = false;
		}
		if (y > boardSizeY - 1) {
			output = false;
		}

		return output;
	}

	public static void stepClear(int x, int y) {
		viewableBoard[x][y] = true;

		// top
		if (board[x + 1][y + 1] == 0 && viewableBoard[x + 1][y + 1] == false && validTile(x + 1, y + 1)) {
			stepClear(x + 1, y + 1);
		}
		if (board[x][y + 1] == 0 && viewableBoard[x][y + 1] == false && validTile(x, y + 1)) {
			stepClear(x, y + 1);
		}
		if (board[x - 1][y + 1] == 0 && viewableBoard[x - 1][y + 1] == false && validTile(x - 1, y + 1)) {
			stepClear(x - 1, y + 1);
		}

		// middle
		if (board[x + 1][y] == 0 && viewableBoard[x + 1][y] == false && validTile(x + 1, y)) {
			stepClear(x + 1, y);
		}
		if (board[x - 1][y] == 0 && viewableBoard[x - 1][y] == false && validTile(x - 1, y)) {
			stepClear(x - 1, y);
		}

		// bottom
		if (board[x + 1][y - 1] == 0 && viewableBoard[x + 1][y - 1] == false && validTile(x + 1, y - 1)) {
			stepClear(x + 1, y - 1);
		}
		if (board[x][y - 1] == 0 && viewableBoard[x][y - 1] == false && validTile(x, y - 1)) {
			stepClear(x, y - 1);
		}
		if (board[x - 1][y - 1] == 0 && viewableBoard[x - 1][y - 1] == false && validTile(x - 1, y - 1)) {
			stepClear(x - 1, y - 1);
		}

	}

	public static void boardSnippet(int x, int y) {
		System.out.println(board[x + 1][y + 1] + " " + board[x][y + 1] + " " + board[x - 1][y + 1]);
		System.out.println(board[x + 1][y] + " " + board[x][y] + " " + board[x - 1][y]);
		System.out.println(board[x + 1][y - 1] + " " + board[x][y - 1] + " " + board[x - 1][y - 1]);
	}

	public static void flag(int x, int y) {
		// TODO finish flag, and check if flag

	}

	public static void typeBoard(boolean cheat) {
		System.out.println();
		String corner = "O";
		if (cheat) {
			// cheat
			String top = corner;
			for (int i = 1; i < boardSizeX; i++) {
				top += "- ";
			}
			System.out.println(top + corner);
			for (int i = 1; i < boardSizeY; i++) {
				System.out.print("|");
				for (int j = 1; j < boardSizeX; j++) {
					if (rawData) {
						if (board[j][i] == 0) {
							System.out.print(representsBlank);
						} else if (board[j][i] == 9) {
							System.out.print(representsBomb);
						} else {
							System.out.print(board[j][i]);
						}
					} else {
						System.out.print(board[j][i]);
					}

					System.out.print(" ");

				}
				System.out.print("|");
				System.out.println();
			}
			System.out.print(top + corner);
			System.out.println();
		} else {
			// non cheat
			String top = corner;
			for (int i = 1; i < boardSizeX; i++) {
				top += "- ";
			}
			System.out.println(top + corner);
			for (int i = 1; i < boardSizeY; i++) {
				System.out.print("|");
				for (int j = 1; j < boardSizeX; j++) {
					if (rawData) {
						if (viewableBoard[j][i] == true) {
							if (board[j][i] == 0) {
								System.out.print(representsBlank);
							} else if (board[j][i] == 9) {
								System.out.print(representsBomb);
							} else {
								System.out.print(board[j][i]);
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
			System.out.print(top + corner);
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
