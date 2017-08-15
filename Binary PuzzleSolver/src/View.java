import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Bradley Gauntlett
 *
 */

@SuppressWarnings("serial")

public class View extends JFrame {

	JPanel view = new JPanel();
	public static GameButton gameElements[][] = new GameButton[Model.n][Model.n];

	JPanel controlPanel = new JPanel();
	JPanel sizePanel = new JPanel();

	JComboBox<String> levels = new JComboBox<String>();
	// JComboBox<Integer> statusVal = new JComboBox<Integer>();

	JButton clear = new JButton("CLEAR");
	JButton check = new JButton("CHECK");
	JButton prop = new JButton("FILL");
	JButton genPuzzle = new JButton("NEW");
	JButton hint = new JButton("SEE MSGs");
	JButton solve = new JButton("SOLVE");
	JButton actHint = new JButton("HINT");

	public static GameButton g = new GameButton();

	public View(int n) {
		super("Binary Puzzle GUI & Solver");
		// setSize(1800, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setUndecorated(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		view.setLayout(new GridLayout(n, n));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				gameElements[i][j] = new GameButton();
				view.add(gameElements[i][j]);
			}
		}

		sizePanel.add(levels);
		// sizePanel.add(statusVal);
		sizePanel.setBackground(Color.BLACK);

		controlPanel.add(check);
		controlPanel.add(prop);
		controlPanel.add(genPuzzle);
		controlPanel.add(clear);
		controlPanel.add(hint);
		controlPanel.add(actHint);
		controlPanel.add(solve);
		controlPanel.setBackground(Color.BLACK);
		// ------Control bar setup------
		Color cl = new Color(216, 255, 228);
		levels.setBackground(cl);
		check.setBackground(cl);

		check.setPreferredSize(new Dimension(100, 40));
		prop.setBackground(cl);
		prop.setPreferredSize(new Dimension(100, 40));
		solve.setBackground(cl);
		solve.setPreferredSize(new Dimension(100, 40));
		genPuzzle.setBackground(cl);
		genPuzzle.setPreferredSize(new Dimension(130, 40));
		clear.setBackground(cl);
		clear.setPreferredSize(new Dimension(100, 40));
		clear.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));
		check.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));
		genPuzzle.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));
		prop.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));
		solve.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));
		hint.setBackground(cl);
		hint.setPreferredSize(new Dimension(120, 40));
		hint.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));

		actHint.setBackground(Color.CYAN);
		actHint.setPreferredSize(new Dimension(120, 40));
		actHint.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 18));

		// -------------------------------
		levels.setPreferredSize(new Dimension(710, 40));
		levels.addItem("EASY (4X4)");
		levels.addItem("AVERAGE (8X8)");
		levels.addItem("DIFFICULT (12X12)");
		levels.addItem("EXTREME (16X16)");
		levels.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 24));

		add(controlPanel, BorderLayout.SOUTH);
		add(sizePanel, BorderLayout.NORTH);
		setVisible(true);
		add(view);

	}

	/**
	 * 
	 * @param ActionListener
	 *            a
	 */
	public void addClearButtonListener(ActionListener a) {
		clear.addActionListener(a);
	}

	/**
	 * 
	 * @param ActionListener
	 *            a
	 */
	public void addCheckListener(ActionListener a) {
		check.addActionListener(a);
	}

	/**
	 * 
	 * @param ActionListener
	 *            a
	 */
	public void addHintListener(ActionListener a) {
		hint.addActionListener(a);

	}

	/**
	 * 
	 * @param ActionListener
	 *            a
	 */
	public void addPropListener(ActionListener a) {
		prop.addActionListener(a);

	}

	public GameButton[][] getElements() {
		return gameElements;
	}

	/**
	 * 
	 * @param ActionListener
	 *            a
	 */
	public void addSolveListener(ActionListener a) {
		solve.addActionListener(a);
	}

	/**
	 * 
	 * @param ActionListener
	 *            sizeListener
	 */
	public void addSizeListener(ActionListener sizeListener) {
		levels.addActionListener(sizeListener);

	}

	/**
	 * 
	 * @param ActionListener
	 *            genListener
	 */
	public void addGenListener(ActionListener genListener) {
		genPuzzle.addActionListener(genListener);
	}

	public void addActHint(ActionListener actHintListener) {
		actHint.addActionListener(actHintListener);

	}

}
