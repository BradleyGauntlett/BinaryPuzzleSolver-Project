import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * 
 * @author Bradley Gauntlett
 *
 */
public class Controller {
	private static View view;
	Color lg = new Color(216, 255, 228);
	private Model model;
	Grids grids = new Grids();
	private static Controller instance = new Controller();
	int hintctr = 0;

	private Controller() {

		model = Model.getInstance();
		view = new View(model.n);
		view.addClearButtonListener(new ClearListener());
		view.addCheckListener(new CheckListener());
		view.addHintListener(new HintListener());
		view.addActHint(new actHintListener());
		view.addPropListener(new PropListener());
		view.addSolveListener(new SolveListener());
		view.addSizeListener(new SizeListener());
		view.addGenListener(new GenListener());
		hintctr = 0;

	}

	public View getView() {
		return view;
	}

	public static GameButton[][] getElements() {
		return view.getElements();
	}

	/**
	 * 
	 * @return instance of Controller
	 */
	public static Controller getInstance() {
		return instance;
	}

	/**
	 * 
	 * @author Bradley Gauntlett
	 *
	 */
	class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			hintctr = 0;
			// for (int i = 0; i < model.n; i++) {
			// for (int j = 0; j < model.n; j++) {
			// view.gameElements[i][j].elementState = 0;
			// view.gameElements[i][j].valState = 1;
			// view.gameElements[i][j].setText("");
			// view.gameElements[i][j].setBackground(Color.BLACK);
			// view.controlPanel.setBackground(Color.BLACK);
			//
			// view.check.setBackground(lg);
			// view.solve.setBackground(lg);
			// view.genPuzzle.setBackground(lg);
			// view.clear.setBackground(lg);
			// view.prop.setBackground(lg);
			// view.hint.setBackground(lg);
			//
			// view.check.setForeground(Color.BLACK);
			// view.solve.setForeground(Color.BLACK);
			// view.genPuzzle.setForeground(Color.BLACK);
			// view.clear.setForeground(Color.BLACK);
			// view.prop.setForeground(Color.BLACK);
			// view.hint.setForeground(Color.BLACK);
			//
			// }
			// }
			view.dispose();
			view.gameElements = new GameButton[model.n][model.n];
			view = new View(model.n);
			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addGenListener(new GenListener());
			view.addSizeListener(new SizeListener());
			view.addActHint(new actHintListener());
		}

	}

	// -------------------------------

	/**
	 * 
	 * @author Bradley Gauntlett
	 *
	 */
	class CheckListener implements ActionListener {

		@Override
		/**
		 * @param ActionEvent
		 *            e
		 */
		public void actionPerformed(ActionEvent e) {
			model.checkGrid(view.gameElements);

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					System.out.print("[" + view.gameElements[i][j].valState + "]");
				}
				System.out.println();
			}

			for (int i = 0; i < Model.n; i++) {
				for (int j = 0; j < Model.n; j++) {
					if (model.triplematch == true || model.equalcol == false || model.equalrow == false) {

						if (view.gameElements[i][j].getBackground() == Color.DARK_GRAY) {
							view.gameElements[i][j].setBackground(Color.DARK_GRAY);
						} else {
							view.gameElements[i][j].setBackground(Color.red);
							view.gameElements[i][j].setForeground(Color.BLACK);
							// view.controlPanel.setBackground(Color.RED);
							view.levels.setBackground(Color.RED);
							// set button clr
							view.check.setBackground(Color.BLACK);
							view.solve.setBackground(Color.BLACK);
							view.genPuzzle.setBackground(Color.BLACK);
							view.clear.setBackground(Color.BLACK);
							view.prop.setBackground(Color.BLACK);
							view.hint.setBackground(Color.BLACK);

							view.check.setForeground(Color.RED);
							view.solve.setForeground(Color.RED);
							view.genPuzzle.setForeground(Color.RED);
							view.clear.setForeground(Color.RED);
							view.prop.setForeground(Color.RED);
							view.hint.setForeground(Color.RED);
						}

					} else {

						view.gameElements[i][j].setBackground(lg);
						view.gameElements[i][j].setForeground(Color.BLACK);
						// view.controlPanel.setBackground(lg);
						view.levels.setBackground(lg);
						view.check.setBackground(lg);
						view.solve.setBackground(lg);
						view.genPuzzle.setBackground(lg);
						view.clear.setBackground(lg);
						view.prop.setBackground(lg);
						view.hint.setBackground(lg);

						view.check.setForeground(Color.BLACK);
						view.solve.setForeground(Color.BLACK);
						view.genPuzzle.setForeground(Color.BLACK);
						view.clear.setForeground(Color.BLACK);
						view.prop.setForeground(Color.BLACK);
						view.hint.setForeground(Color.BLACK);

					}

				}

			}

			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addGenListener(new GenListener());
			view.addActHint(new actHintListener());
			view.addSizeListener(new SizeListener());

			if ((model.triplematch == false && model.equalcol == true && model.equalrow == true)) {
				// JOptionPane.showMessageDialog(view, "Complete");
			}

			else if (model.triplematch == true || model.equalcol == false || model.equalrow == false) {
				// JOptionPane.showMessageDialog(view, "Incomplete");
			}

		}
	}

	/**
	 * 
	 * @author Bradley Gauntlett
	 *
	 */
	class HintListener implements ActionListener {
		@Override
		/**
		 * @param ActionEvent
		 *            e
		 */
		public void actionPerformed(ActionEvent e) {
			String m1;
			m1 = model.hint1;
			String m2;
			m2 = model.hint2;
			int ch = (int) (Math.random() * 2);
			if ((model.triplematch == false || model.equalcol == true || model.equalrow == true) && (m1 == "")
					&& (m2 == "")) {
				JOptionPane.showMessageDialog(view, "No hint available");
			} else if ((m1 == "") && (m2 == "")) {
				JOptionPane.showMessageDialog(view, "No hint available");
			} else if ((ch == 0)) {
				JOptionPane.showMessageDialog(view, m1);
			} else if ((ch == 1)) {
				JOptionPane.showMessageDialog(view, m2);
			}
			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addActHint(new actHintListener());
			view.addGenListener(new GenListener());
			view.addSizeListener(new SizeListener());
		}
	}

	/**
	 * 
	 * @author Bradley Gauntlett
	 *
	 */
	class GenListener implements ActionListener {
		// int hintctr = 0;
		@Override
		/**
		 * @param ActionEvent
		 *            arg0
		 */
		public void actionPerformed(ActionEvent arg0) {
			hintctr = 0;

			int[][] grid = new int[model.n][model.n];

			view.dispose();
			view.gameElements = new GameButton[model.n][model.n];
			view = new View(model.n);
			if (model.n == 4) {
				Model2.n = 4;
				int[][] temp;
				temp = Grids.getRandomGrid4();
				try {
					for (int i = 0; i < model.n; i++) {
						for (int j = 0; j < model.n; j++) {
							grid[i][j] = temp[i][j];
						}
					}
				} catch (Throwable e) {
				}

				System.out.println("n = " + model.n);
			} else if (model.n == 8) {
				model.n = 8;
				Model2.n = 8;
				int[][] temp;
				temp = Grids.getRandomGrid8();
				for (int i = 0; i < model.n; i++) {
					for (int j = 0; j < model.n; j++) {
						grid[i][j] = temp[i][j];
					}
				}
				System.out.println("n = " + model.n);
			} else if (model.n == 12) {
				model.n = 12;
				Model2.n = 12;
				int[][] temp;
				temp = Grids.getRandomGrid12();
				for (int i = 0; i < model.n; i++) {
					for (int j = 0; j < model.n; j++) {
						grid[i][j] = temp[i][j];
					}
				}
				System.out.println("n = " + model.n);
			} else if (model.n == 16) {
				model.n = 16;
				Model2.n = 16;
				int[][] temp;
				temp = Grids.getRandomGrid16();
				for (int i = 0; i < model.n; i++) {
					for (int j = 0; j < model.n; j++) {
						grid[i][j] = temp[i][j];
					}
				}
				System.out.println("n = " + model.n);
			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					view.gameElements[i][j].valState = grid[i][j];
					if (grid[i][j] != 9) {
						view.gameElements[i][j].setBackground(Color.DARK_GRAY);
					}
				}
			}
			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					System.out.print("[" + view.gameElements[i][j].valState + "]");
				}
				System.out.println();
			}
			// grid = Grids.grid4;

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {

					if (view.gameElements[i][j].valState == 1 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("1");
						view.gameElements[i][j].setForeground(lg);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));
					}
					if (view.gameElements[i][j].valState == 0 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("0");
						view.gameElements[i][j].setForeground(lg);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}
				}
			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					System.out.print("[" + view.gameElements[i][j].valState + "]");
				}
				System.out.println();
			}
			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addActHint(new actHintListener());
			view.addGenListener(new GenListener());
			view.addSizeListener(new SizeListener());

		}
	}

	/**
	 * 
	 * @author Bradley Gauntlett
	 *
	 */
	class PropListener implements ActionListener {
		@Override
		/**
		 * @param ActionEvent
		 *            e
		 */
		public void actionPerformed(ActionEvent e) {

			view.gameElements = model.partSolve(view.gameElements);

			// if ((model.triplematch == true || model.equalcol == false ||
			// model.equalrow == false)) {
			// JOptionPane.showMessageDialog(view, "Complete");
			// }

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					/*
					 * More conditions needed here
					 */

					if (view.gameElements[i][j].valState == 1 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("1");
						view.gameElements[i][j].setForeground(lg);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}

					if (view.gameElements[i][j].valState == 0 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("0");
						view.gameElements[i][j].setForeground(lg);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}
				}
			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					System.out.print("[" + view.gameElements[i][j].valState + "]");
				}
				System.out.println();
			}
			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addActHint(new actHintListener());
			view.addSolveListener(new SolveListener());
			view.addSizeListener(new SizeListener());
		}
	}

	/**
	 * 
	 * @author Bradley Gauntlett
	 *
	 */
	class SolveListener implements ActionListener {
		@Override
		/**
		 * @param ActionEvent
		 *            e
		 */
		public void actionPerformed(ActionEvent e) {

			int[][] grid = new int[model.n][model.n];

			if (model.n == 4) {
				Model2.n = 4;
				System.out.println("n = " + model.n);
			} else if (model.n == 8) {
				model.n = 8;
				Model2.n = 8;
				System.out.println("n = " + model.n);
			} else if (model.n == 12) {
				model.n = 12;
				Model2.n = 12;
				System.out.println("n = " + model.n);
			} else if (model.n == 16) {
				model.n = 16;
				Model2.n = 16;
				System.out.println("n = " + model.n);

			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					grid[i][j] = view.gameElements[i][j].valState;
				}
			}

			long startTime = System.nanoTime();
			Model2.solve(0, 0, grid);
			long endTime = System.nanoTime();

			long duration = (endTime - startTime); // divide by 1000000 to get
			System.out.println(duration); // milliseconds.

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					view.gameElements[i][j].valState = grid[i][j];
				}
			}
			// if ((model.triplematch == true || model.equalcol == false ||
			// model.equalrow == false)) {
			// JOptionPane.showMessageDialog(view, "Complete");
			// }

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					/*
					 * More conditions needed here
					 */

					if (view.gameElements[i][j].valState == 1 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("1");
						view.gameElements[i][j].setForeground(lg);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}

					if (view.gameElements[i][j].valState == 0 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("0");
						view.gameElements[i][j].setForeground(lg);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}
				}
			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					System.out.print("[" + view.gameElements[i][j].valState + "]");
				}
				System.out.println();
			}
			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addActHint(new actHintListener());
			view.addSizeListener(new SizeListener());
		}
	}

	class actHintListener implements ActionListener {
		@Override
		/**
		 * @param ActionEvent
		 *            e
		 */
		public void actionPerformed(ActionEvent e) {
			hintctr++;
			int[][] grid = new int[model.n][model.n];

			if (model.n == 4) {
				Model2.n = 4;
				System.out.println("n = " + model.n);
			} else if (model.n == 8) {
				model.n = 8;
				Model2.n = 8;
				System.out.println("n = " + model.n);
			} else if (model.n == 12) {
				model.n = 12;
				Model2.n = 12;
				System.out.println("n = " + model.n);
			} else if (model.n == 16) {
				model.n = 16;
				Model2.n = 16;
				System.out.println("n = " + model.n);

			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					grid[i][j] = view.gameElements[i][j].valState;
				}
			}
			if (hintctr == 1) {
				Model2.hintSolve(grid);

			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					view.gameElements[i][j].valState = grid[i][j];
				}
			}

			// if ((model.triplematch == true || model.equalcol == false ||
			// model.equalrow == false)) {
			// JOptionPane.showMessageDialog(view, "Complete");
			// }

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					/*
					 * More conditions needed here
					 */

					if (view.gameElements[i][j].valState == 1 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("1");
						view.gameElements[i][j].setBackground(Color.CYAN);
						view.gameElements[i][j].setForeground(Color.BLACK);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}

					if (view.gameElements[i][j].valState == 0 && view.gameElements[i][j].getText() == "") {
						view.gameElements[i][j].setText("0");
						view.gameElements[i][j].setBackground(Color.CYAN);
						view.gameElements[i][j].setForeground(Color.BLACK);
						view.gameElements[i][j].setFont(new Font("Courier New", Font.BOLD, 60));

					}
				}
			}

			for (int i = 0; i < model.n; i++) {
				for (int j = 0; j < model.n; j++) {
					System.out.print("[" + view.gameElements[i][j].valState + "]");
				}
				System.out.println();
			}
			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addActHint(new actHintListener());
			view.addSizeListener(new SizeListener());
		}

	}

	class SizeListener implements ActionListener {
		int hintctr = 0;

		@Override
		public void actionPerformed(ActionEvent event) {
			System.gc();
			JComboBox<String> combo = (JComboBox<String>) event.getSource();
			String chosenSize = (String) combo.getSelectedItem();

			if (chosenSize.equals("EASY (4X4)")) {
				model.n = 4;
				Model2.n = 4;
				System.out.println("n = " + model.n);
			} else if (chosenSize.equals("AVERAGE (8X8)")) {
				model.n = 8;
				Model2.n = 8;
				System.out.println("n = " + model.n);
			} else if (chosenSize.equals("DIFFICULT (12X12)")) {
				model.n = 12;
				Model2.n = 12;
				System.out.println("n = " + model.n);
			} else if (chosenSize.equals("EXTREME (16X16)")) {
				model.n = 16;
				Model2.n = 16;
				System.out.println("n = " + model.n);

			}
			view.dispose();
			view.gameElements = new GameButton[model.n][model.n];
			view = new View(model.n);

			if (chosenSize.equals("EASY (4X4)")) {
				view.levels.setSelectedItem("EASY (4X4)");
			} else if (chosenSize.equals("AVERAGE (8X8)")) {
				view.levels.setSelectedItem("AVERAGE (8X8)");
			} else if (chosenSize.equals("DIFFICULT (12X12)")) {
				view.levels.setSelectedItem("DIFFICULT (12X12)");
			} else if (chosenSize.equals("EXTREME (16X16)")) {
				view.levels.setSelectedItem("EXTREME (16X16)");
			}

			view.addClearButtonListener(new ClearListener());
			view.addCheckListener(new CheckListener());
			view.addHintListener(new HintListener());
			view.addPropListener(new PropListener());
			view.addSolveListener(new SolveListener());
			view.addSizeListener(new SizeListener());
			view.addActHint(new actHintListener());
			view.addGenListener(new GenListener());
		}

	}

}
// ---------------------------------
