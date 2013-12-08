package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import utils.Lang;
import display.components.IPanel;
import entities.User;

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
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblWelcome = new JLabel(Lang.getLine("user_panel_welcome") + " "
		+ User.getCurrent().getUsername());
		lblWelcome.setForeground(Color.GREEN);
		lblWelcome.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(20, 20, 5, 5);
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		add(lblWelcome, gbc_lblWelcome);

		JTextArea textAreaInfo = new JTextArea();
		textAreaInfo.setBorder(null);
		textAreaInfo.setOpaque(false);
		textAreaInfo.setBackground(new Color(0, 0, 0, 0));
		textAreaInfo.setForeground(Color.GREEN);
		GridBagConstraints gbc_textAreaInfo = new GridBagConstraints();
		gbc_textAreaInfo.gridwidth = 2;
		gbc_textAreaInfo.insets = new Insets(40, 40, 40, 40);
		gbc_textAreaInfo.fill = GridBagConstraints.BOTH;
		gbc_textAreaInfo.gridx = 0;
		gbc_textAreaInfo.gridy = 1;
		add(textAreaInfo, gbc_textAreaInfo);

	}

}
