import java.awt.Color;
import java.util.Stack;

/**
 * @author Bradley Gauntlett
 */
public class Model {
	// private boolean complete = false;
	boolean triplematch;
	static int n = 4;
	private static Model instance = new Model();
	static boolean equalcol = true;
	static boolean equalrow = true;
	String hint1 = null;
	String hint2 = null;

	/**
	 * private ctor for Singleton pattern
	 */
	private Model() {
	}

	/**
	 * 
	 * @param grid
	 *            GameButton[][]
	 */
	public void checkGrid(GameButton[][] grid) {
		equalcol = true;
		equalrow = true;
		triplematch = false;
		hint1 = "";
		hint2 = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				// -------F
				if ((i == 0 && triplematch == false)) {
					if ((grid[i][j].valState == grid[i + 1][j].valState
							&& grid[i][j].valState == grid[i + 2][j].valState)) {
						triplematch = true;
					}
				}

				if (i == n && triplematch == false) {
					if ((grid[i][j].valState == grid[i - 1][j].valState
							&& grid[i][j].valState == grid[i - 2][j].valState)) {
						triplematch = true;
					}
				}
				if (j == 0 && triplematch == false) {
					if ((grid[i][j].valState == grid[i][j + 1].valState
							&& grid[i][j].valState == grid[i][j + 2].valState)) {
						triplematch = true;
					}
				}
				if (j == n && triplematch == false) {
					if ((grid[i][j].valState == grid[i][j - 1].valState
							&& grid[i][j].valState == grid[i][j - 2].valState)) {
						triplematch = true;

					}
				}

				try {
					if (((grid[i][j].valState == grid[i - 1][j].valState)
							&& (grid[i][j].valState == grid[i + 1][j].valState)) && triplematch == false) {
						triplematch = true;
					}

					if (((grid[i][j].valState == grid[i][j - 1].valState)
							&& (grid[i][j].valState == grid[i][j + 1].valState)) && triplematch == false) {
						triplematch = true;
					}

				} catch (Exception ArrayIndexOutOfBoundsException) {

				}

				// =====================================================================================

			}
		}

		int onecount = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j].getText() == "1") {
					onecount++;
				}
			}
			if (onecount != n / 2) {
				equalrow = false;
				break;
			} else {
				onecount = 0;
			}

		}
		if (equalrow == false) {
			System.out.println("Row with " + (n - onecount) + " zeros found");
			hint1 = "A Row is unequal and has " + (n - onecount) + "Zeros ";
		}
		// -----------
		int onecount2 = 0;

		for (int c = 0; c < n; c++) {
			for (int j = 0; j < n; j++) {

				if (grid[j][c].getText() == "1") {
					onecount2++;
				}
			}
			if (onecount2 % 2 != 0) {
				equalcol = false;
				break;
			} else {
				equalcol = true;
			}

		}
		if (equalcol == false) {
			System.out.println("Col with " + (n - onecount2) + " zeros found");
			hint2 = "A Column has an odd number of bits";
		}

		if (triplematch == true) {
			System.out.println("Incomplete");
		} else if (triplematch == false) {
			System.out.println("Complete");
		}

	}
	// ***************END CHECK*******************

	public static boolean nextValue(int r, int c, GameButton[][] solgrid) {
		if (solgrid[r][c].valState == 9) {
			solgrid[r][c].valState = 0;
			return true;
		} else if (solgrid[r][c].valState == 0) {
			solgrid[r][c].valState = 1;
			return true;
		}

		return false;
	}

	static GameButton[][] solvePuzzle(int row, int col, GameButton[][] solgrid) {
		Stack<IntPair> stack = new Stack<IntPair>();
		// ********BASE CASE***************
		if (validate(solgrid) == true) {
			System.out.println("*****SOLVED*****");
			// ******* DISPLAY GRID *********//*
			for (int i1 = 0; i1 < 4; i1++) {
				for (int j1 = 0; j1 < 4; j1++) {
					System.out.print("[" + solgrid[i1][j1] + "]");
				}
				System.out.println();
			}
			System.out.println("*************");
		}

		for (col = 0; col < n - 1; col++) {
			if (solgrid[row][col].valState == 9) {
				stack.push(new IntPair(row, col));

				for (int i = 0; i < 2; i++) {
					if (nextValue(row, col, solgrid)) {
						if (isValid(solgrid, row, col)) {
							next(row, col, solgrid);
						}
					} else {
						// pop & recurse
						System.out.println("backtracking");
						IntPair pair = stack.pop();
						removeValue(solgrid, pair.x, pair.y);
						solvePuzzle(stack.peek().x, stack.peek().y, solgrid);
					}
				}

			}

		}

		next(row, col, solgrid);
		return solgrid;
	}

	public static void removeValue(GameButton[][] solgrid, int row, int col) {
		solgrid[row][col].valState = 9;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	/////////////////////////// PART SOLVE////////////////////////////////

	private static void next(int row, int col, GameButton[][] solgrid) {
		if (col < n) {
			solvePuzzle(row, col + 1, solgrid);
		} else {
			solvePuzzle(row + 1, 0, solgrid);
		}
	}

	public GameButton[][] partSolve(GameButton[][] grid) {

		System.out.println("entered method");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				System.out.println(i + " " + j);
				try {
					if (grid[i][j].valState == 9) {
						// System.out.println(i + " " + j);

						if (i - 1 >= 0 && i + 1 < n) {
							if ((grid[i + 1][j].valState == 1) && (grid[i - 1][j].valState == 1)) {
								grid[i][j].valState = 0;
								grid[i][j].elementState = 1;

							}

							if ((grid[i + 1][j].valState == 0) && (grid[i - 1][j].valState == 0)) {
								grid[i][j].valState = 1;
								grid[i][j].elementState = 2;
							}
						}

						// j1
						if (j - 1 >= 0 && j + 1 < n) {
							if ((grid[i][j + 1].valState == 1) && (grid[i][j - 1].valState == 1)) {
								grid[i][j].valState = 0;
								grid[i][j].elementState = 1;
							}

							if ((grid[i][j + 1].valState == 0) && (grid[i][j - 1].valState == 0)) {
								grid[i][j].valState = 1;
								grid[i][j].elementState = 2;
							}
						}

						// -----------------------------------------------------------------------------

						// i-2

						if (i - 2 >= 0) {
							if (grid[i - 2][j].valState == 0 && grid[i - 1][j].valState == 0) {
								grid[i][j].valState = 1;
								grid[i][j].elementState = 2;
							}

							if (grid[i - 2][j].valState == 1 && grid[i - 1][j].valState == 1) {
								grid[i][j].valState = 0;
								grid[i][j].elementState = 1;
							}
						}

						// i+2

						if (i + 2 < n) {
							if (grid[i + 2][j].valState == 0 && grid[i + 1][j].valState == 0) {
								grid[i][j].valState = 1;
								grid[i][j].elementState = 2;
							}
							if (grid[i + 1][j].valState == 1 && grid[i + 2][j].valState == 1) {
								grid[i][j].valState = 0;
								grid[i][j].elementState = 1;
							}
						}

						// j+2
						if (j + 2 < n) {
							if (grid[i][j + 1].valState == 0 && grid[i][j + 2].valState == 0) {
								grid[i][j].valState = 1;
								grid[i][j].elementState = 2;
							}

							if (grid[i][j + 2].valState == 1 && grid[i][j + 1].valState == 1) {
								grid[i][j].valState = 0;
								grid[i][j].elementState = 1;
							}
						}

						// j-2

						if (j - 2 >= 0) {
							if (grid[i][j - 2].valState == 1 && grid[i][j - 1].valState == 1) {
								grid[i][j].valState = 0;
								grid[i][j].elementState = 1;
							}

							if (grid[i][j - 2].valState == 0 && grid[i][j - 1].valState == 0) {
								grid[i][j].valState = 1;
								grid[i][j].elementState = 2;
							}

						}

					}
				} catch (Exception ArrayOutOfBoundsException) {
				}
			}
		}
		return grid;
	}
	//////////////////////// END PART SOLVE//////////////////////////

	/**
	 * 
	 * @return instance of Model
	 */
	public static Model getInstance() {
		return instance;
	}

	static boolean solve(int i, int j, GameButton[][] cells) {
		if (i == n) {
			i = 0;
			if (++j == n)
				return true;
		}

		if (cells[i][j].valState != 9) {
			return solve(i + 1, j, cells);
		}

		for (int val = 0; val < 2; ++val) {
			cells[i][j].valState = val;
			if (isValid(cells, i, j)) {
				cells[i][j].valState = val;
				if (solve(i + 1, j, cells) && validate(cells) == true)
					return true;
			}
		}

		System.out.println("backtrack before");
		// displayGrid(cells);
		cells[i][j].valState = 9;
		System.out.println("backtrack after");
		// displayGrid(grid//);
		return false;
	}

	public static boolean isValid(GameButton[][] gripdcpy, int i, int j) {
		int n = gripdcpy.length;

		if ((i < n - 2)) {
			if ((gripdcpy[i][j].valState == gripdcpy[i + 1][j].valState
					&& gripdcpy[i][j].valState == gripdcpy[i + 2][j].valState)) {
				System.out.println("test 1");
				return false;
			}
		}
		if (i >= 2) {
			if ((gripdcpy[i][j].valState == gripdcpy[i - 1][j].valState
					&& gripdcpy[i][j].valState == gripdcpy[i - 2][j].valState)) {
				System.out.println("test 2");
				return false;
			}
		}
		if (j < n - 2) {
			if ((gripdcpy[i][j].valState == gripdcpy[i][j + 1].valState
					&& gripdcpy[i][j].valState == gripdcpy[i][j + 2].valState)) {
				System.out.println("test 3");
				return false;
			}
		}
		if (j >= 2) {
			if ((gripdcpy[i][j].valState == gripdcpy[i][j - 1].valState
					&& gripdcpy[i][j].valState == gripdcpy[i][j - 2].valState)) {
				System.out.println("test 4");
				return false;
			}
		}
		try {
			if (i > 0 && i < n) {
				if (((gripdcpy[i][j].valState == gripdcpy[i - 1][j].valState)
						&& (gripdcpy[i][j].valState == gripdcpy[i + 1][j].valState))) {
					System.out.println("test 5");
					return false;
				}
			}
			if (j > 0 && j < n) {
				if (((gripdcpy[i][j].valState == gripdcpy[i][j - 1].valState)
						&& (gripdcpy[i][j].valState == gripdcpy[i][j + 1].valState))) {
					System.out.println("test 6");
					return false;
				}
			}

		} catch (Exception ArrayIndexOutOfBoundsException) {
			System.out.println("exception");
		}

		int onecount = 0;

		for (int i1 = 0; i1 < n; i1++) {
			for (int j1 = 0; j1 < n; j1++) {

				if (gripdcpy[i1][j1].valState == 1) {
					onecount++;
				}
			}
			if (onecount != n / 2) {
				equalrow = false;
				break;
			} else {
				onecount = 0;
			}

		}
		if (equalrow == false) {
			System.out.println("Row with " + (n - onecount) + " zerosF found");

		}
		// -----------
		int onecount2 = 0;

		for (int c = 0; c < n; c++) {
			for (int j1 = 0; j1 < n; j1++) {

				if (gripdcpy[j1][c].valState == 1) {
					onecount2++;
				}
			}
			if (onecount2 % 2 != 0) {
				equalcol = false;
				break;
			} else {
				equalcol = true;
			}

		}

		return true;
	}

	public static boolean validate(GameButton[][] grid1) {
		boolean equalcol = true;
		boolean equalrow = true;
		boolean triplematch = false;
		int n = 4;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				// -------F
				if ((i == 0 && triplematch == false)) {
					if ((grid1[i][j].valState == grid1[i + 1][j].valState
							&& grid1[i][j].valState == grid1[i + 2][j].valState)) {
						triplematch = true;
					}
				}

				if (i == n && triplematch == false) {
					if ((grid1[i][j].valState == grid1[i - 1][j].valState
							&& grid1[i][j].valState == grid1[i - 2][j].valState)) {
						triplematch = true;
					}
				}
				if (j == 0 && triplematch == false) {
					if ((grid1[i][j].valState == grid1[i][j + 1].valState
							&& grid1[i][j].valState == grid1[i][j + 2].valState)) {
						triplematch = true;
					}
				}
				if (j == n && triplematch == false) {
					if ((grid1[i][j].valState == grid1[i][j - 1].valState
							&& grid1[i][j].valState == grid1[i][j - 2].valState)) {
						triplematch = true;

					}
				}

				try {
					if (((grid1[i][j].valState == grid1[i - 1][j].valState)
							&& (grid1[i][j].valState == grid1[i + 1][j].valState)) && triplematch == false) {
						triplematch = true;
					}

					if (((grid1[i][j].valState == grid1[i][j - 1].valState)
							&& (grid1[i][j].valState == grid1[i][j + 1].valState)) && triplematch == false) {
						triplematch = true;
					}

				} catch (Exception ArrayIndexOutOfBoundsException) {

				}

				// =====================================================================================

			}
		}

		int onecount = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (grid1[i][j].valState == 1) {
					onecount++;
				}
			}
			if (onecount % 2 != 0) {
				equalrow = false;
				break;
			} else {
				onecount = 0;
			}

		}
		// if (equalrow == false) {
		// //System.out.println("Row with " + (n - onecount) + " zerosF found");
		//
		// }
		// -----------
		int onecount2 = 0;

		for (int c = 0; c < n; c++) {
			for (int j = 0; j < n; j++) {

				if (grid1[j][c].valState == 1) {
					onecount2++;
				}
			}
			if (onecount2 % 2 != 0) {
				equalcol = false;
				break;
			} else {
				equalcol = true;
			}
		}
		boolean foundBlank = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid1[i][j].valState == 9) {
					foundBlank = true;
				}
			}
		}

		// if (equalcol == false) {
		// System.out.println("Col with " + (onecount2 - n) + " zeros found");
		//
		// }

		if (triplematch == false && equalrow && equalcol && !foundBlank) {
			// System.out.println("Complete");
			return true;

		} else {
			// System.out.println("Incomplete");

			// System.out.println(complete);
			return false;
		}
	}
}
