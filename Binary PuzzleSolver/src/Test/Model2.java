package Test;

public class Model2 {

	static int[][] grid = new int[][] {

			{ 9, 9, 0, 9 }, { 1, 9, 9, 1 }, { 9, 1, 9, 9 }, { 1, 9, 9, 0 } };

	public static void main(String[] args) {
		System.out.println("Original Grid");
		displayGrid(grid);

		if (solve(0, 0, grid) == true) {
			System.out.print("Solved Grid");
			displayGrid(grid);
		} else {
			System.out.println("NONE");
		}

		System.out.println(checkEquality(grid));
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
				if (grid[i][j] == 9) {
					System.out.println("incomplete");
					return false;
				}
			}
		}

		if (equalcol == false) {
			System.out.println("Col with " + (onecount2 - n) + " zeros found");
		}
		if (triplematch == false && equalrow && equalcol && checkEquality(grid) == true) {

			System.out.println("complete");
			return true;
		} else {
			System.out.println("in Complete");
			return false;
		}
	}

	static boolean solve(int i, int j, int[][] cells) {

		if (i == 4) {
			i = 0;
			if (j++ == 3)
				return true;
		}

		if (cells[i][j] != 9) { // skip filled cells
			return solve(i + 1, j, cells);
		}

		for (int val = 0; val < 2; ++val) {
			cells[i][j] = val;
			if (isValid(cells, i, j)) {
				cells[i][j] = val;
				if (solve(i + 1, j, cells) && checkEquality(grid) == true)
					return true;
			}
		}
		displayGrid(grid);
		cells[i][j] = 9;
		System.out.println("backtrack");
		displayGrid(grid);
		return false;
	}

	public static boolean isValid(int grid[][], int i, int j) {
		int n = grid.length;

		if ((i < n - 2)) {
			if ((grid[i][j] == grid[i + 1][j] && grid[i][j] == grid[i + 2][j])) {
				return false;
			}
		}
		if (i >= 2) {
			if ((grid[i][j] == grid[i - 1][j] && grid[i][j] == grid[i - 2][j])) {
				return false;
			}
		}

		if (j < n - 2) {
			if ((grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2])) {
				return false;
			}
		}
		if (j >= 2) {
			if ((grid[i][j] == grid[i][j - 1] && grid[i][j] == grid[i][j - 2])) {
				return false;
			}
		}

		if (i > 0 && i < n - 1) {
			if (((grid[i][j] == grid[i - 1][j]) && (grid[i][j] == grid[i + 1][j]))) {
				return false;
			}
		}
		if (j > 0 && j < n - 1) {
			if (((grid[i][j] == grid[i][j - 1]) && (grid[i][j] == grid[i][j + 1]))) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkEquality(int[][] grid) {
		int onecount = 0;
		boolean equalcol = true;
		boolean equalrow = true;
		int n = 4;
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
		if (equalrow == false && equalcol == false) {
			return false;
		} else {
			return true;
		}

	}

	public static void displayGrid(int[][] grid) {
		System.out.println("");
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				System.out.print("[" + grid[i][j] + "]");
			}
			System.out.println("");

		}
	}

}

// &&&&&***KEEP****&&&&&&
// int totalOnes = 0;
// int colTotalOnes = 0;
// boolean rowIsEq = false;
// boolean colIsEq = false;
// for (int i = 0; i < 4; i++) {
// for (int j = 0; j < 4; j++) {
// if (grid[i][j] == 1) {
// totalOnes++;
// }
// if (j == 3) {
// if (totalOnes == 2) {
// rowIsEq = true;
// } else {
// rowIsEq = false;
// }
// System.out.println("row " + i + " has " + totalOnes + " 1s");
// }
// }
// totalOnes = 0;
// }
//
// System.out.println("");
//
// for (int i = 0; i < 4; i++) {
// for (int j = 0; j < 4; j++) {
// if (grid[j][i] == 1) {
// colTotalOnes++;
// }
// if (j == 3) {
// if (colTotalOnes == 2) {
// colIsEq = true;
// } else {
// colIsEq = false;
// }
// System.out.println("col " + i + " has " + colTotalOnes + " 1s");
// }
// }
// colTotalOnes = 0;
// }
//
// if (rowIsEq == false && colIsEq == false) {
// return false;
// } else {
// return true;
// }