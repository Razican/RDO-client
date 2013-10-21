package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Jordan Aranda Tejada
 */
public class UserOptions extends JPanel {

	private static final long	serialVersionUID	= - 6388407664274281591L;
	private JTextField			textField_user;
	private JPasswordField		passwordField_user;
	private JTextField			textField_email;
	private JPasswordField		passwordField_email;

	/**
	 * Create the panel.
	 */
	public UserOptions()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 1.0, 1.0,
		Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
		Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.insets = new Insets(0, 0, 10, 10);
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 1;
		add(lblUser, gbc_lblUser);

		textField_user = new JTextField();
		textField_user.setForeground(Color.BLACK);
		textField_user.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_textField_user = new GridBagConstraints();
		gbc_textField_user.insets = new Insets(0, 0, 10, 5);
		gbc_textField_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_user.gridx = 2;
		gbc_textField_user.gridy = 1;
		add(textField_user, gbc_textField_user);
		textField_user.setColumns(20);

		JLabel lblNewPassword = new JLabel("Restablecer contraseña");
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblNewPassword.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewPassword.gridx = 1;
		gbc_lblNewPassword.gridy = 2;
		add(lblNewPassword, gbc_lblNewPassword);

		passwordField_user = new JPasswordField();
		passwordField_user.setColumns(20);
		passwordField_user.setForeground(Color.BLACK);
		passwordField_user.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_passwordField_user = new GridBagConstraints();
		gbc_passwordField_user.insets = new Insets(0, 0, 10, 5);
		gbc_passwordField_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_user.gridx = 2;
		gbc_passwordField_user.gridy = 2;
		add(passwordField_user, gbc_passwordField_user);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 10, 10);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		add(lblEmail, gbc_lblEmail);

		textField_email = new JTextField();
		textField_email.setForeground(Color.BLACK);
		textField_email.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField_email.setColumns(20);
		GridBagConstraints gbc_textField_email = new GridBagConstraints();
		gbc_textField_email.insets = new Insets(0, 0, 10, 5);
		gbc_textField_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_email.gridx = 2;
		gbc_textField_email.gridy = 3;
		add(textField_email, gbc_textField_email);

		JLabel lblEmailPassword = new JLabel("Contraseña email");
		lblEmailPassword.setForeground(Color.BLACK);
		lblEmailPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_lblEmailPassword = new GridBagConstraints();
		gbc_lblEmailPassword.anchor = GridBagConstraints.WEST;
		gbc_lblEmailPassword.insets = new Insets(0, 0, 10, 10);
		gbc_lblEmailPassword.gridx = 1;
		gbc_lblEmailPassword.gridy = 4;
		add(lblEmailPassword, gbc_lblEmailPassword);

		passwordField_email = new JPasswordField();
		passwordField_email.setColumns(20);
		passwordField_email.setForeground(Color.BLACK);
		passwordField_email.setFont(new Font("Calibri", Font.PLAIN, 18));
		GridBagConstraints gbc_passwordField_email = new GridBagConstraints();
		gbc_passwordField_email.insets = new Insets(0, 0, 10, 5);
		gbc_passwordField_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_email.gridx = 2;
		gbc_passwordField_email.gridy = 4;
		add(passwordField_email, gbc_passwordField_email);

	}

}
