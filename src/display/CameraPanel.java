package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class CameraPanel extends JPanel {

	private static final long	serialVersionUID	= - 7100396253879710237L;
	private JLabel				lblImage;

	/**
	 * Create the panel.
	 */
	public CameraPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, 0.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblImage = new JLabel();
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.insets = new Insets(0, 0, 5, 0);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(lblImage, gbc_lblImage);
	}

	/**
	 * @return Label Image
	 */
	public JLabel getLblImage()
	{
		return lblImage;
	}
}