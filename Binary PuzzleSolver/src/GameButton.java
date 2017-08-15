import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JButton;

/**
 * 
 * @author Bradley Gauntlett
 *
 */

@SuppressWarnings("serial")
public class GameButton extends JButton implements ActionListener {
	Color c = new Color(216, 255, 228);
	int elementState = 0;
	boolean checkOne;
	boolean checkZero;
	int valState = 9;

	public GameButton() {
		this.addActionListener(this);
		setBackground(Color.BLACK);
	}

	public void actionPerformed(ActionEvent e) {

		elementState++;
		elementState %= 3;
		switch (elementState) {
		case 0:
			if (this.getBackground() == Color.DARK_GRAY) {
				break;
			}
			valState = 9;
			setText("");
			setBackground(Color.BLACK);
			checkZero = false;
			checkOne = false;
			// if (valState == 3) {
			// valState = 5;
			// } else {
			// valState = 5;
			// }
			// final Font textFont = new Font("Lucida Console", Font.PLAIN,
			// 10).deriveFont(AffineTransform.getScaleInstance(1, 1));

			break;

		case 1:
			if (this.getBackground() == Color.DARK_GRAY) {
				break;
			}
			valState = 0;
			setText("0");
			setFont(new Font("Courier New", Font.BOLD, 60));
			setBackground(Color.BLACK);
			setForeground(c);
			checkOne = true;
			checkZero = false;
			// if (valState % 2 != 0) {
			// valState = 0;
			// }
			// if (valState % 2 == 0) {
			// valState = 0;
			// }

			break;

		case 2:
			if (this.getBackground() == Color.DARK_GRAY) {
				break;
			}
			valState = 1;
			setText("1");
			setFont(new Font("Courier New", Font.PLAIN, 60));
			// setFont(new Font("Lucida Console", Font.BOLD,
			// 60).deriveFont(AffineTransform.getScaleInstance(1, 1)));
			setBackground(Color.BLACK);
			setForeground(c);
			checkZero = true;
			checkOne = false;
			// if (valState % 2 == 0) {
			// valState = 1;
			// }
			// if (valState % 2 != 0) {
			// valState = 1;
			// }

			break;
		}
		System.out.println("valState = " + valState);
		// valState++;
	}

}
