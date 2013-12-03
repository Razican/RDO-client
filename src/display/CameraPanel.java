package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import components.IPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class CameraPanel extends IPanel {

	private static final long	serialVersionUID	= 1275344895165783345L;

	/**
	 * Create the panel.
	 */
	public CameraPanel()
	{
		setOpaque(false);
		setBackgroundImage(new ImageIcon("img/background.jpg"));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblDescription = new JLabel("Camera");
		lblDescription.setForeground(Color.GREEN);
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 50));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescription.insets = new Insets(20, 20, 20, 10);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 0;
		add(lblDescription, gbc_lblDescription);
	}

}
