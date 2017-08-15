import java.util.ArrayList;


public class Degr {
	// static boolean comp = false;
	static int c = 0;
	static ArrayList<Coord> a = new ArrayList<Coord>();

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

	// ---------------------------------------------------------------
	public static int[][] solver(int imp[][]) {
		int[][] impcpy = imp;
		boolean full = true;

		System.out.println("Old");
		for (int i1 = 0; i1 < 4; i1++) {
			for (int j1 = 0; j1 < 4; j1++) {
				System.out.print("[" + imp[i1][j1] + "]");
			}
			System.out.println();
		}
		// ====================

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				if (imp[i][j] == -1) {
					full = false;
				}
			}
		}
		// =====================
		boolean completed = validate(impcpy);
		System.out.println(completed);
		int n = 4;
		if (!completed) {
			int loopNo = 0;
			do {

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {

						if (impcpy[i][j] == -1) {

							if (loopNo % 2 == 0) {
								System.out.println("entered");
								impcpy[i][j] = 1;
							} else {
								impcpy[i][j] = 0;
							}
							if (isValid(impcpy, i, j) == false) {
								if (impcpy[i][j] == 0) {
									impcpy[i][j] = 1;
								} else {
									impcpy[i][j] = 0;
								}
							}
							for (int i1 = 0; i1 < n; i1++) {
								for (int j1 = 0; j1 < n; j1++) {
									System.out.print("[" + impcpy[i1][j1] + "]");
								}
								System.out.println();
							}
							System.out.println(isValid(impcpy, i, j));
							if (isValid(impcpy, i, j) == false) {
								impcpy[i][j] = -1;
							}
						}

					}
				}
				completed = validate(impcpy);
				if (completed == false) {
					impcpy = imp;
				}
				loopNo++;

			} while (loopNo < n);
		}

		if (completed) {
			System.out.println("******SOLVED******");
			for (int i1 = 0; i1 < 4; i1++) {
				System.out.print("** ");
				for (int j1 = 0; j1 < 4; j1++) {
					
					System.out.print("[" + imp[i1][j1] + "]");
					
				}
				System.out.print(" **");
				System.out.println();
			}
			System.out.println("******************");
		}

		return impcpy;

	}

	public static boolean isValid(int grid[][], int i, int j) {
		int n = grid.length;

		if ((i < n-2)) {
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
		if (j < n-2) {
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

	public static void main(String[] args) {
		int[][] imp = new int[][] {

				{ 1, -1, 1, 0}, 
				{ 0, 1, -1, -1}, 
				{ 0, -1, 1, 1 }, 
				{ -1, 1, -1, -1} };

		solver(imp);
		System.out.println();

	}

}
