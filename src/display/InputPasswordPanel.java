package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * @author Jordan Aranda Tejada
 */
public class InputPasswordPanel extends JPanel {

	private static final long	serialVersionUID	= - 1449706879363372417L;
	private JPasswordField		passwordField;

	/**
	 * Create the dialog.
	 * 
	 * @param label The label
	 */
	public InputPasswordPanel(String label)
	{
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {0, 0};
		gbl_contentPanel.columnWeights = new double[] {0.0, 1.0,
		Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[] {1.0, Double.MIN_VALUE};
		setLayout(gbl_contentPanel);

		JLabel lblPassword = new JLabel(label);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 15));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassword.insets = new Insets(10, 10, 10, 10);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 0;
		add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(2, 5, 2, 2));
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(10, 0, 10, 10);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		add(passwordField, gbc_passwordField);
	}

	/**
	 * @return The array char password
	 */
	public char[] getPassword()
	{
		return passwordField.getPassword();
	}
}
