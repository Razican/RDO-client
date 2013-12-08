package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import display.components.IPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class UserPanel extends IPanel {

	private static final long	serialVersionUID	= - 1961905928730398258L;

	/**
	 * Create the panel.
	 */
	public UserPanel()
	{
		setBackgroundImage(new ImageIcon("img/background.jpg"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0};
		gridBagLayout.rowHeights = new int[] {50, 0};
		gridBagLayout.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblImage = new JLabel(new ImageIcon("img/ambulance-icon.png"));
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(lblImage, gbc_lblImage);
	}
}