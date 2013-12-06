package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import display.components.IPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class CameraPanel extends IPanel {

	private static final long	serialVersionUID	= 1275344895165783345L;

	private JLabel				lblImage;

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
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 40));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescription.insets = new Insets(20, 20, 20, 10);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 0;
		add(lblDescription, gbc_lblDescription);

		JButton btnPhoto = new JButton("Hacer foto");
		btnPhoto.setForeground(Color.GREEN);
		btnPhoto.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnPhoto.setBorderPainted(false);
		btnPhoto.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnPhoto = new GridBagConstraints();
		gbc_btnPhoto.anchor = GridBagConstraints.SOUTH;
		gbc_btnPhoto.insets = new Insets(0, 0, 20, 20);
		gbc_btnPhoto.gridx = 1;
		gbc_btnPhoto.gridy = 0;
		add(btnPhoto, gbc_btnPhoto);

		JPanel panel_content = new JPanel();
		GridBagConstraints gbc_panel_content = new GridBagConstraints();
		gbc_panel_content.gridwidth = 2;
		gbc_panel_content.insets = new Insets(0, 20, 20, 20);
		gbc_panel_content.fill = GridBagConstraints.BOTH;
		gbc_panel_content.gridx = 0;
		gbc_panel_content.gridy = 1;
		add(panel_content, gbc_panel_content);
		GridBagLayout gbl_panel_content = new GridBagLayout();
		gbl_panel_content.columnWidths = new int[] {0, 0};
		gbl_panel_content.rowHeights = new int[] {0, 0, 0};
		gbl_panel_content.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		gbl_panel_content.rowWeights = new double[] {1.0, 0.0, Double.MIN_VALUE};
		panel_content.setLayout(gbl_panel_content);

		lblImage = new JLabel("New label");
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.insets = new Insets(0, 0, 5, 0);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		panel_content.add(lblImage, gbc_lblImage);
	}
}