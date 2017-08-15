package Test;

public class PuzzleSolver {

	static int[][] solgrid;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	static void solvePuzzle(int row, int col) {
		System.out.println("*****NEXT-STEP****");
		for (int i1 = 0; i1 < 4; i1++) {
			for (int j1 = 0; j1 < 4; j1++) {
				System.out.print("[" + solgrid[i1][j1] + "]");
			}
			System.out.println();
		}
		// ********BASE CASE***************
		if (validate(solgrid) == true) {
				System.out.println("*****SOLVED*****");
				/******* DISPLAY GRID *********/
				for (int i1 = 0; i1 < 4; i1++) {
					for (int j1 = 0; j1 < 4; j1++) {
						System.out.print("[" + solgrid[i1][j1] + "]");
					}
					System.out.println();
				}
				System.out.println("*************");
		}
		// ************************************
		for (col = 0; col < 4; col++) {
			if (solgrid[row][col] == 9) {
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				applyValue(0, solgrid, row, col);
				if (isValid(solgrid, row, col)) {
					next(row, col);
					System.out.println("@@@@@@@@@---BackTracked---@@@@@@@@");
					removeValue(solgrid, row, col);
				}
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				applyValue(1, solgrid, row, col);
				
				// System.out.println("*****GRID****");
				if (isValid(solgrid, row, col)) {
					next(row, col);

					System.out.println("@@@@@@@@@---BackTracked---@@@@@@@@");
					removeValue(solgrid, row, col);
				}
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}

		}
		next(row, col);

	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	static boolean solve(int i, int j, int[][] cells) {
		if (i == 3) {
			i = 0;
			if (++j == 3)
				solgrid = cells;
			return true;
		}
		// if (cells[i][j] != 9) // skip filled cells
		// return solve(i + 1, j, cells);

		for (int val = 0; val <= 3; ++val) {

			int apply;
			if (val % 2 == 0) {
				apply = 0;
			} else {
				apply = 1;
			}

			applyValue(apply, cells, i, j);
			if (isValid(cells, i, j)) {
				cells[i][j] = apply;
				if (solve(i + 1, j, cells))
					solgrid = cells;
				return true;
			}
		}
		cells[i][j] = 0; // reset on backtrack
		return false;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	public static boolean isValid(int grid[][], int i, int j) {
		int n = grid.length;

		if ((i < n - 2)) {
			if ((grid[i][j] == grid[i + 1][j] && grid[i][j] == grid[i + 2][j])) {
				System.out.println("test 1");
				return false;
			}
		}
		if (i >= 2) {
			if ((grid[i][j] == grid[i - 1][j] && grid[i][j] == grid[i - 2][j])) {
				System.out.println("test 2");
				return false;
			}
		}
		if (j < n - 2) {
			if ((grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2])) {
				System.out.println("test 3");
				return false;
			}
		}
		if (j >= 2) {
			if ((grid[i][j] == grid[i][j - 1] && grid[i][j] == grid[i][j - 2])) {
				System.out.println("test 4");
				return false;
			}
		}
		try {
			if (i > 0 && i < n) {
				if (((grid[i][j] == grid[i - 1][j]) && (grid[i][j] == grid[i + 1][j]))) {
					System.out.println("test 5");
					return false;
				}
			}
			if (j > 0 && j < n) {
				if (((grid[i][j] == grid[i][j - 1]) && (grid[i][j] == grid[i][j + 1]))) {
					System.out.println("test 6");
					return false;
				}
			}

		} catch (Exception ArrayIndexOutOfBoundsException) {
			System.out.println("exception");
		}
		return true;
	}

	public static void applyValue(int val, int[][] grid, int row, int col) {
		grid[row][col] = val;

	}

	public static void removeValue(int[][] grid, int row, int col) {
		grid[row][col] = 9;
	}

	public static boolean validate(int[][] grid) {
		boolean equalcol = true;
		boolean equalrow = true;
		boolean triplematch = false;
		int n = 4;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				// -------F
				if ((i == 0 && triplematch == false)) {
					if ((grid[i][j] == grid[i + 1][j] && grid[i][j] == grid[i + 2][j])) {
						triplematch = true;
					}
				}

				if (i == n && triplematch == false) {
					if ((grid[i][j] == grid[i - 1][j] && grid[i][j] == grid[i - 2][j])) {
						triplematch = true;
					}
				}
				if (j == 0 && triplematch == false) {
					if ((grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2])) {
						triplematch = true;
					}
				}
				if (j == n && triplematch == false) {
					if ((grid[i][j] == grid[i][j - 1] && grid[i][j] == grid[i][j - 2])) {
						triplematch = true;

					}
				}

				try {
					if (((grid[i][j] == grid[i - 1][j]) && (grid[i][j] == grid[i + 1][j])) && triplematch == false) {
						triplematch = true;
					}

					if (((grid[i][j] == grid[i][j - 1]) && (grid[i][j] == grid[i][j + 1])) && triplematch == false) {
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

				if (grid[i][j] == 1) {
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
			for (int j = 0; j < n; j++) {

				if (grid[j][c] == 1) {
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

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == -1) {
					System.out.println("incomplete");
					return false;
				}
			}

		}

		if (equalcol == false) {
			System.out.println("Col with " + (onecount2 - n) + " zeros found");

		}

		if (triplematch == false && equalrow && equalcol) {

			System.out.println("complete");
			return true;

		} else {
			System.out.println("in Complete");

			// System.out.println(complete);
			return false;
		}
	}

	/**
	 * Next Method
	 * 
	 * @param row
	 * @param col
	 */
	public static void next(int row, int col) {
		if (col < 4){
			solvePuzzle(row, col + 1);}
		// solve(row, col + 1, solgrid);
		else{
			solvePuzzle(row + 1, 0);}
		// solve(row + 1, col, solgrid);
	}

	public static void main(String[] args) {

		int[][] grid = new int[][] { 
			{ 9, 9, 1, 0},
			{ 0, 1, 1, 0 }, 
			{ 1, 9, 0, 1 }, 
			{ 0, 1, 0, 9 } };
			
			
			/*
			 * 
			[9][9][1][0]
			[0][1][1][0]
			[1][9][0][1]
			[0][1][0][9]
			 */

		System.out.println("*****GRID****");
		for (int i1 = 0; i1 < 4; i1++) {
			for (int j1 = 0; j1 < 4; j1++) {
				System.out.print("[" + grid[i1][j1] + "]");
			}
			System.out.println();
		}

		solgrid = grid;

		System.out.println("");
		solvePuzzle(0, 0);

		System.out.println("-------------");

		System.out.println("hello");
	}
}
