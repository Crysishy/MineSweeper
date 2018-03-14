import java.util.Random;
import java.util.Stack;

/**
 * This class represents the model for a game of MineSweeper. It has a
 * constructor that takes a preset boolean 2D array where true means there is a
 * mine. This first constructor (you'll need 2) is for testing the methods of
 * this class.
 * 
 * The second constructor that takes the number of rows, the number of columns,
 * and the number of mines to be set randomly in that sized mine field. Do this
 * last.
 * 
 * @author YANG HONG
 */
public class MineSweeper implements MineSweeperModel {

	private class GameSquare {

		private boolean isMine;
		private int row;
		private int col;
		private boolean isVisible;
		private boolean isFlagged;
		private int mineNeighbors;

		// Construct a GameSquare object with all values initialized except
		// mineNeighbors, which is an instance variables that can only be set
		// after
		// all
		// GameSquare objects have been constructed in the 2D array.
		public GameSquare(boolean isMine, int row, int col) {
			this.isMine = isMine;
			this.row = row;
			this.col = col;
			isVisible = false; // Default until someone starts clicking
			isFlagged = false; // Default until someone starts clicking
			// call setAdjacentMines() from both constructors
			// to set this for each new GameSquare.
			mineNeighbors = 0;
		}
	}

	// The instance variable represents all GameSquare objects where each knows
	// its row,
	// column, number of mines around it, if it is a mine, flagged, or visible
	private GameSquare[][] board;

	/**
	 * Construct a MineSweeper object using a given mine field represented by an
	 * array of boolean values: true means there is mine, false means there is
	 * not a mine at that location.
	 * 
	 * @param mines
	 *            A 2D array to represent a mine field so all methods can be
	 *            tested with no random placements.
	 */
	public MineSweeper(boolean[][] mines) {
		// TODO: Complete this constructor first so you can test preset mine
		// fields
		// (later on you will need to write another constructor for random
		// boards).
		// new GameSquare objects store all info about one square on the board
		// such
		// as its row, column, if it's flagged, visible, or is a mine.

		board = new GameSquare[mines.length][mines[0].length];

		// Example construction of one GameSquare stored in row 2, column 4:
		// /// board[2][4] = new GameSquare(mines[2][4], 2, 4);
		// Use a nested for loop to change all board array elements
		// from null to a new GameSquare
		for (int i = 0; i < mines.length; i++) {
			for (int n = 0; n < mines[0].length; n++) {
				board[i][n] = new GameSquare(mines[i][n], i, n);
			}
		}

		// You will need to call private void setAdjacentMines() to set
		// mineNeighbors for all GameSquare objects because each GameSquare
		// object
		// must first know if it is a mine or not. Set mineNeighbors for each.
		setAdjacentMines();
	}

	/**
	 * Use the almost initialized 2D array of GameSquare objects to set the
	 * instance variable mineNeighbors for every 2D array element (even if that
	 * one GameSquare has a mine). This is similar to GameOfLife neighborCount.
	 */
	private void setAdjacentMines() {
		// Example to set the instance variable mineNeighbors of the one
		// GameSquare
		// object stored in row 2, column 4 to 8:
		// /// board[2][4].mineNeighbors = 8;
		// Use a nested for loop to set mineNeighbors for ALL GameSquare objects
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board[0].length; n++) {
				int count = 0, R = i - 1, C = n - 1;
				for (int I = 0; I < 3; I++) {
					for (int N = 0; N < 3; N++) {
						if (R == -1) {
							R = 0;
							I++;
						}
						if (R == board.length)
							break;
						if (C == -1) {
							C = 0;
							N++;
						}
						if (C == board[0].length)
							break;
						if (board[R][C].isMine == true)
							count++;
						C++;
					}
					R++;
					C = n - 1;
				}
				if (board[i][n].isMine == true)
					count--;
				board[i][n].mineNeighbors = count;
			}
		}
	}

	/**
	 * This method returns the number of mines surrounding the requested
	 * GameSquare (the mineNeighbors value of the square). A square with a mine
	 * may return the number of surrounding mines, even though it will never
	 * display that information.
	 * 
	 * @param row
	 *            - An int value representing the row in board.
	 * @param column
	 *            - An int value representing the column in board.
	 * @return The number of mines surrounding to this GameSquare
	 *         (mineNeighbors)
	 * 
	 *         Must run O(1)
	 */
	public int getAdjacentMines(int row, int column) {
		return board[row][column].mineNeighbors;
	}

	/**
	 * Construct a MineSweeper of any size that has numberOfMines randomly set
	 * so we get different games.
	 * 
	 * @param rows
	 *            Height of the board
	 * @param columns
	 *            Width of the board
	 * @param numberOfMines
	 *            How m any mines are to randomly placed
	 */
	public MineSweeper(int rows, int columns, int numberOfMines) {
		// TODO: Complete this constructor after all other methods
		//
		// Consider using class Random with its nextInt(int) method
		//
		// Random generator = new Random();
		// int r = generator(rows);
		// assertTrue(r >= 0 && r < rows);

		board = new GameSquare[rows][columns];
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board[0].length; n++) {
				board[i][n] = new GameSquare(false, i, n);
			}
		}

		Random mineCreator = new Random();
		for (int i = 0; i < numberOfMines; i++) {
			int r = mineCreator.nextInt(rows), c = mineCreator.nextInt(columns);
			if (board[r][c].isMine == false)
				board[r][c].isMine = true;
			else
				i--;
		}
		setAdjacentMines();
	}

	/**
	 * This method returns the number of mines found in the game board.
	 * 
	 * @return The number of mines.
	 */
	public int getTotalMineCount() {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board[0].length; n++) {
				if (board[i][n].isMine == true)
					count++;
			}
		}
		return count;
	}

	/**
	 * This method returned whether or not the square has been flagged by the
	 * user. Flags are a tool used by players to quickly tell which squares they
	 * think contain mines as well as prevent accidental clicking on those
	 * squares.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean value representing the flagged state of this location.
	 */
	public boolean isFlagged(int row, int column) {
		return board[row][column].isFlagged;
	}

	public void toggleFlagged(int row, int column) {
		if (board[row][column].isVisible == false) {
			if (board[row][column].isFlagged == false)
				board[row][column].isFlagged = true;
			else if (board[row][column].isFlagged == true)
				board[row][column].isFlagged = false;
		}
	}

	/**
	 * This method determines if the square in question is a mine.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean representing the mine status of the square.
	 */
	public boolean isMine(int row, int column) {
		return board[row][column].isMine;
	}

	/**
	 * This method gets the visibility of the square in question. Visibilty is
	 * initially defined for all squares to be false and uncovered when the
	 * click method checks the square.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean representing whether or not the square is set to be
	 *         visible.
	 */
	public boolean isVisible(int row, int column) {
		return board[row][column].isVisible;
	}

	/**
	 * This method determines if the player has lost on the current board. A
	 * player loses if and only if they have clicked on a mine.
	 * 
	 * @return A boolean representing player failure.
	 */
	public boolean lost() {
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board[0].length; n++) {
				if (board[i][n].isMine == true && board[i][n].isVisible == true)
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns a textual representation of the GameBoard. Squares will be
	 * represented by one character followed by a space, except the last
	 * character which will have no following space. Visible squares will either
	 * be the number of mines next to the square, a blank space if no mines are
	 * adjacent, or a '*' character for a mine. Newlines will separate each row
	 * of the game board.
	 * 
	 * @return A String representation of the game board data.
	 */
	public String toString() {
		String visualBoard = "";
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board[0].length - 1; n++) {
				if (board[i][n].isMine == false
						&& board[i][n].mineNeighbors == 0)
					visualBoard += "  ";
				if (board[i][n].isMine == false
						&& board[i][n].mineNeighbors != 0)
					visualBoard += board[i][n].mineNeighbors + " ";
				if (board[i][n].isMine == true)
					visualBoard += "* ";
			}
			if (board[i][board[0].length - 1].isMine == false
					&& board[i][board[0].length - 1].mineNeighbors == 0)
				visualBoard += " ";
			if (board[i][board[0].length - 1].isMine == false
					&& board[i][board[0].length - 1].mineNeighbors != 0)
				visualBoard += board[i][board[0].length - 1].mineNeighbors;
			if (board[i][board[0].length - 1].isMine == true)
				visualBoard += "*";
			visualBoard += "\n";
		}
		return visualBoard;
	}

	/**
	 * This method determines if a player has won the game. Winning means all
	 * non-mine squares are visible and no mines have been detonated.
	 * 
	 * @return A boolean representing whether or not the player has won.
	 */
	public boolean won() {
		int noneMineTotal = 0, count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int n = 0; n < board[0].length; n++) {
				if (board[i][n].isMine == false) {
					noneMineTotal++;
					if (board[i][n].isVisible == true)
						count++;
				}
			}
		}
		return lost() == false && noneMineTotal == count;
	}

	/**
	 * This method alerts the Game Board the user has clicked on the square at
	 * the given row/column. There are five possibilities for updating the board
	 * during the click messages to your MineSweeper. The GameSquare object
	 * stored at the just clicked row and column
	 * 
	 * 1. is a mine (player looses)
	 * 
	 * 2. is visible already (do nothing)
	 * 
	 * 3. is flagged (do nothing)
	 * 
	 * 4. has mineNeighbors >- 1 (simply mark that visible)
	 * 
	 * 5. is not adjacent to any mines with mineNeighbors == 0 (mark many
	 * visible)
	 * 
	 * Because MineSweeper automatically clears all squares adjacent to any
	 * blank square connected to the square clicked, a special algorithm is
	 * needed to set the proper part of the board visible. This pseudo-code
	 * shows the suggested algorithm.
	 */
	// Check special cases first, there may be nothing to do or the user clicked
	// a mine
	// if the clicked GameSquare is a mine
	// change the state of this object to lost (when this click method finishes,
	// the GUI will refresh with a loss)
	// else if the clicked GameSquare is flagged or the clicked GameSquare
	// isVisible
	// return, there is nothing to do
	// else if the clicked GameSquare has 1 or more neighboring mines
	// set the square to be visible, which applies only when mineNeighbors is
	// 1..8
	// else clear all possible GameSquares up to the border or GameSquares with
	// numbers 1..8
	// mark the clicked GameSquare as visible
	// push the GameSquare onto the stack
	// while the stack is not empty:
	// pop the stack and mark GameSquare as the current GameSquare
	// if the current square must has no neighboring mines (not 1..8)
	// for each adjacent square
	// if it's not visible and not flagged and mines == 0
	// push adjacent GameSquare on stack and set isVisible to true
	/**
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 */
	public void click(int row, int column) {
		// if (board[row][column].isMine == true)
		// board[row][column].isVisible = true;
		// else if (board[row][column].isFlagged == true
		// || board[row][column].isVisible == true)
		// return;
		// else if (board[row][column].mineNeighbors != 0)
		// board[row][column].isVisible = true;
		// else {
		// board[row][column].isVisible = true;

		if (board[row][column].isFlagged == false)
			board[row][column].isVisible = true;

		if (board[row][column].mineNeighbors == 0) {
			Stack<GameSquare> stack = new Stack<GameSquare>();
			stack.push(board[row][column]);

			while (!stack.isEmpty()) {
				GameSquare current = stack.pop();
				if (current.mineNeighbors == 0) {
					int R = current.row - 1, C = current.col - 1;
					for (int I = 0; I < 3; I++) {
						for (int N = 0; N < 3; N++) {
							if (R == -1) {
								R = 0;
								I++;
							}
							if (R == board.length)
								break;
							if (C == -1) {
								C = 0;
								N++;
							}
							if (C == board[0].length)
								break;
							if (board[R][C] != current
									&& board[R][C].isVisible == false) {
								board[R][C].isVisible = true;
								stack.push(board[R][C]);
							}
							//
							// board[R][C].isMine == false &&
							// board[R][C].isVisible == false &&
							// board[R][C].isFlagged == false)
							//
							C++;
						}
						R++;
						C = current.col - 1;
					}
				}
			}
		}
	}
}
